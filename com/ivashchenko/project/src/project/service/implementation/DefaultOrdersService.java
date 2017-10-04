package project.service.implementation;

import project.database.dao.interfaces.OrderDao;
import project.database.dao.interfaces.ServiceDao;
import project.database.dao.interfaces.SubscriberDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.database.connection.ConnectionPool;
import project.model.orders.Order;
import project.model.services.Service;
import project.model.users.Subscriber;
import project.service.interfaces.OrdersService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DefaultOrdersService implements OrdersService {
    @Override
    public List<Order> getOrdersForSubscriber(Subscriber subscriber) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            OrderDao orderDao = daoFactory.getOrderDao();
            return orderDao.getOrdersForSubscriber(subscriber);
        }
    }

    @Override
    public void createOrderForService(long serviceId, long subscriberId) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            SubscriberDao subscriberDao = daoFactory.getSubscriberDao();
            ServiceDao serviceDao = daoFactory.getServiceDao();
            OrderDao orderDao = daoFactory.getOrderDao();
            Subscriber subscriber = subscriberDao.findById(subscriberId);
            Service service = serviceDao.findById(serviceId);
            Order order = createOrder(service, subscriber);
            orderDao.save(order);
        }
    }

    private Order createOrder(Service service, Subscriber subscriber) {
        Order order = new Order();
        order.setService(service);
        order.setSubscriber(subscriber);
        order.setOrderDate(new Date());
        return order;
    }
}
