package project.service.implementation;

import project.database.dao.interfaces.SubscriberDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.database.connection.ConnectionPool;
import project.model.users.Subscriber;
import project.model.users.comparators.SubscriberActiveComparator;
import project.service.interfaces.SubscriberService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DefaultSubscriberService implements SubscriberService {
    @Override
    public Subscriber activateSubscriber(long subscriberId) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            SubscriberDao subscriberDao = daoFactory.getSubscriberDao();
            Subscriber subscriber = subscriberDao.findById(subscriberId);
            subscriberDao.activateSubscriber(subscriber);
            return subscriber;
        }
    }

    @Override
    public void blockSubscriber(Subscriber subscriber) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            SubscriberDao subscriberDao = daoFactory.getSubscriberDao();
            subscriberDao.blockSubscriber(subscriber);
        }
    }

    @Override
    public List<Subscriber> getSubscribers() throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            SubscriberDao subscriberDao = daoFactory.getSubscriberDao();
            List<Subscriber> subscribers = subscriberDao.findAll();
            Collections.sort(subscribers, new SubscriberActiveComparator());
            return subscribers;
        }
    }

    @Override
    public Subscriber getSubscriber(long subscriberId) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            SubscriberDao subscriberDao = daoFactory.getSubscriberDao();
            return subscriberDao.findById(subscriberId);
        }
    }
}
