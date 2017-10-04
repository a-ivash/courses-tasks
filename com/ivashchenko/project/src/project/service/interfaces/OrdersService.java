package project.service.interfaces;

import project.model.orders.Order;
import project.model.users.Subscriber;

import java.sql.SQLException;
import java.util.List;

public interface OrdersService {
    List<Order> getOrdersForSubscriber(Subscriber subscriber) throws SQLException;
    void createOrderForService(long serviceId, long subscriberId) throws SQLException;
}
