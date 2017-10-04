package project.database.dao.interfaces;

import project.model.orders.Order;
import project.model.orders.Payment;
import project.model.users.Subscriber;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface OrderDao extends GenericDao<Long, Order> {
    List<Order> getUnpaidOrdersForSubscriber(Subscriber subscriber, Date start, Date end) throws SQLException;
    Order addOrderToPayment(Order order, Payment payment) throws SQLException;
    List<Order> getOrdersForPayment(Payment payment) throws SQLException;
    List<Order> getOrdersForSubscriber(Subscriber subscriber) throws SQLException;
    List<Subscriber> getSubscriberWithOrdersForDateRange(Date start, Date end) throws SQLException;
}
