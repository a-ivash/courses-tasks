package project.service.implementation;

import project.database.dao.interfaces.SubscriberAuthDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.database.dao.factories.MySqlDAOFactory;
import project.database.exceptions.WrongEmailPasswordException;
import project.database.connection.ConnectionPool;
import project.model.users.AbstractUser;
import project.model.users.Administrator;
import project.model.users.Subscriber;
import project.service.interfaces.LoginService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DefaultLoginService implements LoginService {

    @Override
    public AbstractUser login(String email, String rawPassword) throws WrongEmailPasswordException, SQLException {
        if (checkIfAdmin(email, rawPassword)) {
            return new Administrator();
        }
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = new MySqlDAOFactory(connection);
            SubscriberAuthDao authDao = daoFactory.getUserAuthDao();
            Subscriber subscriber = authDao.login(email, rawPassword);
            return subscriber;
        }
    }

    private boolean checkIfAdmin(String email, String rawPassword) {
        ResourceBundle rb = ResourceBundle.getBundle("admin");
        String adminEmail = rb.getString("admin.email");
        String adminPassword = rb.getString("admin.password");
        return adminEmail.equals(email) && adminPassword.equals(rawPassword);
    }
}
