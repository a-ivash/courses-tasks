package project.database.dao.interfaces;

import project.model.users.Phone;

import java.sql.SQLException;
import java.util.List;

public interface PhoneDao extends GenericDao<Long, Phone> {
    List<Phone> getAvailablePhones(int limit) throws SQLException;
    Phone getFirstAvailablePhone() throws SQLException;
    List<Phone> getActivePhones() throws SQLException;
}
