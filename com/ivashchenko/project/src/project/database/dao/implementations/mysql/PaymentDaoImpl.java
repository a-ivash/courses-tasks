package project.database.dao.implementations.mysql;

import project.database.dao.interfaces.OrderDao;
import project.database.dao.interfaces.PaymentDao;
import project.database.dao.interfaces.PhoneCallDao;
import project.database.dao.interfaces.SubscriberDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.model.calls.PhoneCall;
import project.model.orders.Order;
import project.model.orders.Payment;
import project.model.users.Subscriber;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PaymentDaoImpl extends GenericDaoImpl<Long, Payment> implements PaymentDao {
    private final String COLUMNS_TO_SELECT = "paymentId, userId, isCompleted, orderDate, finalPaymentDate, paymentDate";
    private final String SQL_SELECT_ALL = String.format("SELECT %s FROM payment", COLUMNS_TO_SELECT);
    private final String SQL_SELECT_WILDCARD_ON_ID = SQL_SELECT_ALL + " WHERE paymentId = ?";
    private final String SQL_SELECT_WILDCARD_ON_USER_ID = SQL_SELECT_ALL + " WHERE userId = ?";
    private final String SQL_DELETE_WILDCARD_ON_ID = "DELETE FROM payment WHERE paymentId = ?";
    private final String SQL_INSERT_WITH_WILDCARDS = "INSERT INTO payment (userId, orderDate, finalPaymentDate) VALUES (?, ?, ?)";
    private final String SQL_UPDATE_WITH_WILDCARDS = "UPDATE payment SET isCompleted = ?, paymentDate = ? WHERE paymentId = ?";

    private final int PAYMENT_USER_ID_INDEX = 1;
    private final int PAYMENT_ORDER_DATE_INDEX = 2;
    private final int PAYMENT_FINAL_DATE_INDEX = 3;

    /** Accessing other entities dao. */
    private AbstractDAOFactory daoFactory;

    public PaymentDaoImpl(Connection connection, AbstractDAOFactory daoFactory) {
        super(connection);
        this.daoFactory = daoFactory;
    }

    @Override
    public Payment save(Payment entity) throws SQLException {
        OrderDao orderDao = daoFactory.getOrderDao();
        PhoneCallDao phoneCallDao = daoFactory.getPhoneCallDao();

        connection.setAutoCommit(false);
        try {
            super.save(entity);
            Subscriber subscriber = entity.getSubscriber();
            // Finding all orders and phone calls for subscriber with orderDate in range [startDate, endDate] and set their paymentId to entity.getId()
            List<Order> ordersForPayment = orderDao.getUnpaidOrdersForSubscriber(subscriber, PaymentDao.getStartDate(), PaymentDao.getEndDate());
            List<PhoneCall> phoneCallsForPayment = phoneCallDao.getUnpaidCallsForSubscriber(subscriber, PaymentDao.getStartDate(), PaymentDao.getEndDate());
            updateOrdersForPayment(ordersForPayment, entity);
            updatePhoneCallsForPayment(phoneCallsForPayment, entity);

            // there is already payment for this items
            if (ordersForPayment.isEmpty() && phoneCallsForPayment.isEmpty()) {
                throw new SQLException(); // to rollback
            }

            // updating orders and phone calls
            orderDao.update(ordersForPayment);
            phoneCallDao.update(phoneCallsForPayment);
            return entity;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    private void updatePhoneCallsForPayment(List<PhoneCall> phoneCalls, Payment payment) {
        for (PhoneCall phoneCall: phoneCalls) {
            phoneCall.setPayment(payment);
        }
    }

    private void updateOrdersForPayment(List<Order> orders, Payment payment) {
        for (Order order: orders) {
            order.setPayment(payment);
        }
    }

    @Override
    public List<Payment> getPaymentsForSubscriber(Subscriber subscriber) throws SQLException{
        return getItemsForQueryWildCardOnId(SQL_SELECT_WILDCARD_ON_USER_ID, subscriber);
    }

    @Override
    protected Payment buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        SubscriberDao subscriberDao = daoFactory.getSubscriberDao();
        OrderDao orderDao = daoFactory.getOrderDao();
        PhoneCallDao phoneCallDao = daoFactory.getPhoneCallDao();

        Payment payment = new Payment();
        payment.setId(resultSet.getLong("paymentId"));
        payment.setOrderDate(resultSet.getDate("orderDate"));
        payment.setPaymentDate(resultSet.getDate("paymentDate"));
        payment.setFinalPaymentDate(resultSet.getDate("finalPaymentDate"));
        payment.setCompleted(resultSet.getBoolean("isCompleted"));

        long userId = resultSet.getLong("userId");
        Subscriber subscriber = subscriberDao.findById(userId);
        payment.setSubscriber(subscriber);

        List<Order> orders = orderDao.getOrdersForPayment(payment);
        payment.setOrders(orders);

        List<PhoneCall> phoneCalls = phoneCallDao.getCallsForPayment(payment);
        payment.setPhoneCalls(phoneCalls);
        return payment;
    }

    @Override
    protected String getSelectAllQuery() {
        return SQL_SELECT_ALL;
    }

    @Override
    protected String getSelectEntityByIdQuery() {
        return SQL_SELECT_WILDCARD_ON_ID;
    }

    @Override
    protected void fillSelectByIdPreparedStatement(PreparedStatement statement, Long id) throws SQLException {
        statement.setLong(1, id);
    }

    @Override
    protected String getInsertQuery() {
        return SQL_INSERT_WITH_WILDCARDS;
    }

    @Override
    protected void fillInsertStatement(PreparedStatement statement, Payment entity) throws SQLException {
        statement.setLong(PAYMENT_USER_ID_INDEX, entity.getSubscriber().getId());
        Calendar calendar = new GregorianCalendar();
        statement.setDate(PAYMENT_ORDER_DATE_INDEX, new java.sql.Date(calendar.getTime().getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, 5);
        statement.setDate(PAYMENT_FINAL_DATE_INDEX, new java.sql.Date(calendar.getTime().getTime()));
    }

    @Override
    protected String getUpdateQuery() {
        return SQL_UPDATE_WITH_WILDCARDS;
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement statement, Payment entity) throws SQLException {
        statement.setBoolean(1, entity.isCompleted());
        statement.setDate(2, new java.sql.Date(entity.getPaymentDate().getTime())); // entity.getPaymentDate()
        statement.setLong(3, entity.getId());
    }


    @Override
    protected String getDeleteQuery() {
        throw new NotImplementedException();
    }

    @Override
    protected void fillDeleteStatement(PreparedStatement statement, Payment entity) throws SQLException {
        throw new NotImplementedException();
    }
}
