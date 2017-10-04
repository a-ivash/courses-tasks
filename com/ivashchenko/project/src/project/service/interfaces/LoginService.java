package project.service.interfaces;

import project.database.exceptions.WrongEmailPasswordException;
import project.model.users.AbstractUser;

import java.sql.SQLException;

public interface LoginService {
    AbstractUser login(String email, String rawPassword) throws WrongEmailPasswordException, SQLException;
}
