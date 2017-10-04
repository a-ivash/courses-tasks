package project.external_systems;

import project.database.dao.interfaces.PhoneCallDao;
import project.database.dao.interfaces.PhoneDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.database.dao.factories.MySqlDAOFactory;
import project.database.connection.ConnectionPool;
import project.model.calls.PhoneCall;
import project.model.users.Phone;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneSystem implements PhoneSystemObserver {
    List<Phone> activePhones;

    public PhoneSystem() {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = new MySqlDAOFactory(connection);
            PhoneDao phoneDao = daoFactory.getPhoneDao();
            activePhones = phoneDao.getActivePhones();
        } catch (SQLException e) {
            activePhones = new ArrayList<>();
        }
    }

    @Override
    public List<Phone> getActivePhones() {
        return activePhones;
    }

    @Override
    public void notifyForPhoneCall(PhoneCall phoneCall) {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = new MySqlDAOFactory(connection);
            PhoneCallDao phoneCallDao = daoFactory.getPhoneCallDao();
            phoneCallDao.save(phoneCall);
        } catch (SQLException e) {
        }
    }
}
