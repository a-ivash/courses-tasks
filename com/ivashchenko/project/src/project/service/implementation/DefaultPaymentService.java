package project.service.implementation;

import project.database.dao.interfaces.PaymentDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.database.connection.ConnectionPool;
import project.model.orders.Payment;
import project.model.orders.comparators.PaymentComparator;
import project.model.users.Subscriber;
import project.service.interfaces.PaymentService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DefaultPaymentService implements PaymentService {
    @Override
    public List<Payment> getSubscribersPayments(Subscriber subscriber) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            PaymentDao paymentDao = daoFactory.getPaymentDao();
            return paymentDao.getPaymentsForSubscriber(subscriber);
        }
    }

    @Override
    public List<Payment> getPayments() throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            PaymentDao paymentDao = daoFactory.getPaymentDao();
            List<Payment> payments = paymentDao.findAll();
            /* Sort payments: first unpaid, then sorting by orderDate and paymentDate*/
            Collections.sort(payments, new PaymentComparator());
            return payments;
        }
    }

    @Override
    public Payment getPayment(long paymentId) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            PaymentDao paymentDao = daoFactory.getPaymentDao();
            return paymentDao.findById(paymentId);
        }
    }

    @Override
    public Payment confirmPayment(Payment payment) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            PaymentDao paymentDao = daoFactory.getPaymentDao();
            payment.setCompleted(true);
            payment.setPaymentDate(new java.util.Date());
            paymentDao.update(payment);
        }
        return null;
    }
}
