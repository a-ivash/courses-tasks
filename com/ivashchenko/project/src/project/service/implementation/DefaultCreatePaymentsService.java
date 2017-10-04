package project.service.implementation;

import project.database.dao.interfaces.OrderDao;
import project.database.dao.interfaces.PaymentDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.database.connection.ConnectionPool;
import project.model.orders.Payment;
import project.model.users.Subscriber;
import project.service.interfaces.CreatePaymentsService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class DefaultCreatePaymentsService implements CreatePaymentsService {
    @Override
    public void formServicesForLastMonth() throws SQLException {
        List<Subscriber> subscribersToProceed = getSubscribersToCreatePayments();
        List<Payment> payments = formPaymentsForSubscribers(subscribersToProceed);
        savePayments(payments);
    }

    private List<Subscriber> getSubscribersToCreatePayments() throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            OrderDao orderDao = daoFactory.getOrderDao();
            Date startDate = PaymentDao.getStartDate();
            Date endDate = PaymentDao.getEndDate();
            return orderDao.getSubscriberWithOrdersForDateRange(startDate, endDate);
        }
    }

    private List<Payment> formPaymentsForSubscribers(List<Subscriber> subscribers) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        for (Subscriber subscriber: subscribers) {
            Payment payment = createEmptyPayment(subscriber);
            payments.add(payment);
        }
        return payments;
    }

    private Payment createEmptyPayment(Subscriber subscriber) {
        Payment payment = new Payment();
        Calendar today = GregorianCalendar.getInstance();
        today.setTime(new Date());
        payment.setOrderDate(today.getTime());
        today.add(Calendar.DAY_OF_YEAR, 5); // pay in 5 days
        payment.setFinalPaymentDate(today.getTime());
        payment.setSubscriber(subscriber);
        return payment;
    }

    private void savePayments(List<Payment> payments) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            PaymentDao paymentDao = daoFactory.getPaymentDao();
            paymentDao.save(payments);
        }
    }


}
