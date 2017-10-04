package project.service.implementation;

import project.database.dao.interfaces.SubscriberDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.database.exceptions.EmailAlreadyTakenException;
import project.database.connection.ConnectionPool;
import project.model.users.Subscriber;
import project.service.interfaces.CreateAccountService;
import project.model.utils.PasswordUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DefaultCreateAccountService implements CreateAccountService {
    @Override
    public Subscriber saveSubscriber(Subscriber subscriber) throws SQLException, EmailAlreadyTakenException {
        if (checkIfEmailMatchesAdminEmail(subscriber.getEmail())) {
            throw new EmailAlreadyTakenException();
        }

        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            SubscriberDao subscriberDao = daoFactory.getSubscriberDao();
            generatePasswordForSubscriber(subscriber);
            subscriber = subscriberDao.save(subscriber);
            return subscriber;
        }
    }

    private void generatePasswordForSubscriber(Subscriber subscriber) {
        subscriber.setRawPassword(PasswordUtils.generatePassword());
    }

    private boolean checkIfEmailMatchesAdminEmail(String email) {
        ResourceBundle rb = ResourceBundle.getBundle("admin");
        return rb.getString("admin.email").equals(email);
    }
}
