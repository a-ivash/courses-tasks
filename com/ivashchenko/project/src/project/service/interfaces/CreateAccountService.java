package project.service.interfaces;

import project.database.exceptions.EmailAlreadyTakenException;
import project.model.users.Subscriber;

import java.sql.SQLException;

public interface CreateAccountService {
    Subscriber saveSubscriber(Subscriber subscriber) throws SQLException, EmailAlreadyTakenException;
}
