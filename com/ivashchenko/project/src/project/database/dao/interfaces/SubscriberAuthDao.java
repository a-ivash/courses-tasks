package project.database.dao.interfaces;

import project.database.exceptions.WrongEmailPasswordException;
import project.model.users.Subscriber;

import java.sql.SQLException;

public interface SubscriberAuthDao {
    Subscriber login(String email, String password) throws WrongEmailPasswordException, SQLException;
}
