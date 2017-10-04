package datagen;

import project.database.dao.interfaces.PhoneDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.model.users.Phone;

import java.sql.SQLException;

public class PhoneGenerator extends AbstractGenerator{
    PhoneDao phoneDao;

    public PhoneGenerator(AbstractDAOFactory daoFactory) {
        super(daoFactory);
        phoneDao = daoFactory.getPhoneDao();
    }

    public void generatePhones() throws SQLException{
        for (int i = 1000000; i < 1000100; i++) {
            String phoneNumber = String.valueOf(i);
            Phone phone = new Phone();
            phone.setPhoneNumber(phoneNumber);
            phone.setUsed(false);
            phoneDao.save(phone);
        }
    }
}
