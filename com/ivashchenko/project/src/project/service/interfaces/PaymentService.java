package project.service.interfaces;

import project.model.orders.Payment;
import project.model.users.Subscriber;

import java.sql.SQLException;
import java.util.List;

public interface PaymentService {
    List<Payment> getSubscribersPayments(Subscriber subscriber) throws SQLException;
    List<Payment> getPayments() throws SQLException;
    Payment getPayment(long paymentId) throws SQLException;
    Payment confirmPayment(Payment payment) throws SQLException;
}
