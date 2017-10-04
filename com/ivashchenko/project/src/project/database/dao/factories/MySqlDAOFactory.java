package project.database.dao.factories;

import project.database.dao.interfaces.*;
import project.database.dao.implementations.mysql.*;

import java.sql.Connection;

public class MySqlDAOFactory implements AbstractDAOFactory{
    private Connection connection;
    private AddressDao addressDao;
    private OrderDao orderDao;
    private PhoneDao phoneDao;
    private ServiceDao serviceDao;
    private SubscriberDao subscriberDao;
    private SubscriberAuthDao subscriberAuthDao;
    private PaymentDao paymentDao;
    private PhoneCallDao phoneCallDao;

    public MySqlDAOFactory(Connection connection) {
        this.connection = connection;
        addressDao = new AddressDaoImpl(connection, this);
        phoneDao = new PhoneDaoImpl(connection, this);
        phoneCallDao = new PhoneCallDaoImpl(connection, this);
        serviceDao = new ServiceDaoImpl(connection, this);
        subscriberDao = new SubscriberDaoImpl(connection, this);
        subscriberAuthDao = new SubscriberDaoImpl(connection, this);
        paymentDao = new PaymentDaoImpl(connection, this);
        orderDao = new OrderDaoImpl(connection, this);
    }

    @Override
    public AddressDao getAddressDao() {
        return addressDao;
    }

    @Override
    public OrderDao getOrderDao() {
        return orderDao;
    }

    @Override
    public PhoneDao getPhoneDao() {
        return phoneDao;
    }

    @Override
    public ServiceDao getServiceDao() {
        return serviceDao;
    }

    @Override
    public SubscriberDao getSubscriberDao() {
        return subscriberDao;
    }

    @Override
    public SubscriberAuthDao getUserAuthDao() {
        return subscriberAuthDao;
    }

    @Override
    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    @Override
    public PhoneCallDao getPhoneCallDao() {
        return phoneCallDao;
    }


}
