package project.database.dao.factories;

import project.database.dao.interfaces.*;

import java.sql.Connection;

public interface AbstractDAOFactory {
    AddressDao getAddressDao();
    OrderDao getOrderDao();
    PhoneDao getPhoneDao();
    ServiceDao getServiceDao();
    SubscriberDao getSubscriberDao();
    SubscriberAuthDao getUserAuthDao();
    PaymentDao getPaymentDao();
    PhoneCallDao getPhoneCallDao();

    static AbstractDAOFactory getDefaultFactory(Connection connection) {
        return new MySqlDAOFactory(connection);
    }
}
