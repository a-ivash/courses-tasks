package datagen;

import project.database.dao.interfaces.ServiceDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.model.services.PaymentType;
import project.model.services.Service;

import java.sql.SQLException;

public class ServiceGenerator extends AbstractGenerator{

    ServiceDao serviceDao;

    public ServiceGenerator(AbstractDAOFactory daoFactory) {
        super(daoFactory);
        serviceDao = daoFactory.getServiceDao();
    }

    public void generateServices() throws SQLException{
        serviceDao.save(getConnectionService());
        serviceDao.save(getPhoneDetectionService());
        serviceDao.save(getTelephoneExchageService());
    }

    private Service getConnectionService() {
        Service service = new Service();
        service.setServiceName("Connection to system");
        service.setServiceDescription("Connects your phone to our phone network.");
        service.setServicePrice(100.0);
        service.setPaymentType(PaymentType.once);
        return service;
    }

    private Service getTelephoneExchageService() {
        Service service = new Service();
        service.setServiceName("Telephone exchange");
        service.setServiceDescription("Allows you to receive multiple calls on the same number.");
        service.setServicePrice(50.0);
        service.setPaymentType(PaymentType.monthly);
        return service;
    }

    private Service getPhoneDetectionService() {
        Service service = new Service();
        service.setServiceName("Phone number detection");
        service.setServiceDescription("Allows you to detect who is calling you.");
        service.setServicePrice(20.0);
        service.setPaymentType(PaymentType.monthly);
        return service;
    }
}
