package project.database.dao.implementations.mysql;

import project.database.dao.interfaces.OrderDao;
import project.database.dao.interfaces.ServiceDao;
import project.database.dao.interfaces.SubscriberDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.model.orders.Order;
import project.model.orders.Payment;
import project.model.services.Service;
import project.model.users.Subscriber;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Long, Order> implements OrderDao{
    private final String COLUMNS_TO_SELECT = "orderId, serviceId, userId, price, orderDate";
    private final String SQL_SELECT_ALL = String.format("SELECT %s FROM serviceorder", COLUMNS_TO_SELECT);
    private final String SQL_SELECT_WILDCARD_ON_ID = SQL_SELECT_ALL + " WHERE orderId = ?";
    private final String SQL_SELECT_WILDCARD_ON_USER_ID = SQL_SELECT_ALL + " WHERE userId = ?";
    private final String SQL_SELECT_WILDCARD_ON_PAYMENT_ID = SQL_SELECT_ALL + " WHERE paymentId = ?";
    private final String SQL_INSERT_WITH_WILDCARDS = "INSERT INTO serviceorder (serviceId, userId, price, orderDate) VALUES (?, ?, ?, ?)";
    /** Only paymentId can be changed later. */
    private final String SQL_UPDATE_ORDER_WITH_WILDCARDS = "UPDATE serviceorder SET paymentId = ? WHERE orderId = ?";
    private final String SQL_SELECT_SUBSCRIBER_ID_WITH_ORDERS = "SELECT userId FROM serviceorder WHERE orderDate BETWEEN ? AND ? GROUP BY userId";
    private final String SQL_SELECT_FOR_SUBSCRIBER_BETWEEN_DATES = SQL_SELECT_ALL + " WHERE userId = ? AND orderDate BETWEEN ? AND ? and paymentId IS NULL";

    private final int ORDER_SERVICE_ID_INDEX = 1;
    private final int ORDER_USER_ID_INDEX = 2;
    private final int ORDER_PRICE_INDEX = 3;
    private final int ORDER_ORDER_DATE_INDEX = 4;

    private AbstractDAOFactory daoFactory;

    public OrderDaoImpl(Connection connection, AbstractDAOFactory daoFactory) {
        super(connection);
        this.daoFactory = daoFactory;
    }

    @Override
    protected Order buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        ServiceDao serviceDao = daoFactory.getServiceDao();
        SubscriberDao subscriberDao = daoFactory.getSubscriberDao();

        Order order = new Order();
        order.setId(resultSet.getLong("orderId"));
        order.setPrice(resultSet.getDouble("price"));
        order.setOrderDate(resultSet.getDate("orderDate"));

        long serviceId = resultSet.getLong("serviceId");
        Service service = serviceDao.findById(serviceId);
        order.setService(service);

        long userId = resultSet.getLong("userId");
        Subscriber subscriber = subscriberDao.findById(userId);
        order.setSubscriber(subscriber);

        return order;
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
    protected void fillInsertStatement(PreparedStatement statement, Order entity) throws SQLException{
        statement.setLong(ORDER_SERVICE_ID_INDEX, entity.getService().getId());
        statement.setLong(ORDER_USER_ID_INDEX, entity.getSubscriber().getId());
        statement.setDouble(ORDER_PRICE_INDEX, entity.getService().getServicePrice());
        statement.setDate(ORDER_ORDER_DATE_INDEX, new java.sql.Date(entity.getOrderDate().getTime()));
    }

    @Override
    protected String getUpdateQuery() {
        return SQL_UPDATE_ORDER_WITH_WILDCARDS;
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement statement, Order entity) throws SQLException {
        statement.setLong(1, entity.getPayment().getId());
        statement.setLong(2, entity.getId());
    }

    @Override
    protected String getDeleteQuery() {
        throw new NotImplementedException();
    }
    @Override
    protected void fillDeleteStatement(PreparedStatement statement, Order entity) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public List<Order> getUnpaidOrdersForSubscriber(Subscriber subscriber, Date start, Date end) throws SQLException{
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_FOR_SUBSCRIBER_BETWEEN_DATES)) {
            statement.setLong(1, subscriber.getId());
            statement.setDate(2, new java.sql.Date(start.getTime()));
            statement.setDate(3, new java.sql.Date(end.getTime()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(buildEntityFromResultSet(resultSet));
            }
        }
        return orders;
    }

    @Override
    public Order addOrderToPayment(Order order, Payment payment) throws SQLException{
        order.setPayment(payment);
        update(order);
        return order;
    }

    @Override
    public List<Order> getOrdersForPayment(Payment payment) throws SQLException{
        return getItemsForQueryWildCardOnId(SQL_SELECT_WILDCARD_ON_PAYMENT_ID, payment);
    }

    @Override
    public List<Order> getOrdersForSubscriber(Subscriber subscriber) throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_WILDCARD_ON_USER_ID)) {
            statement.setLong(1, subscriber.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(buildEntityFromResultSet(resultSet));
            }
        }
        return orders;
    }

    @Override
    public List<Subscriber> getSubscriberWithOrdersForDateRange(Date start, Date end) throws SQLException {
        SubscriberDao subscriberDao = daoFactory.getSubscriberDao();
        List<Subscriber> subscribers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SUBSCRIBER_ID_WITH_ORDERS)) {
            statement.setDate(1, new java.sql.Date(start.getTime()));
            statement.setDate(2, new java.sql.Date(end.getTime()));
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                long subscriberId = resultSet.getLong("userId");
                subscribers.add(subscriberDao.findById(subscriberId));
            }
        }
        return subscribers;
    }

    @Override
    public List<Order> save(List<Order> entities) throws SQLException {
        for (Order order: entities) {
            save(order);
        }
        return entities;
    }
}
