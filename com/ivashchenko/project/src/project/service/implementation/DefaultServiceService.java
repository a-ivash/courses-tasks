package project.service.implementation;

import project.database.dao.interfaces.ServiceDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.database.connection.ConnectionPool;
import project.model.services.Service;
import project.service.interfaces.ServiceService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DefaultServiceService implements ServiceService {
    @Override
    public Service saveService(Service service) throws SQLException{
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            ServiceDao serviceDao = daoFactory.getServiceDao();
            service = serviceDao.save(service);
            return service;
        }
    }

    @Override
    public List<Service> getServices() throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            ServiceDao serviceDao = daoFactory.getServiceDao();
            return serviceDao.findAll();
        }
    }

    @Override
    public Service getService(long serviceId) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            AbstractDAOFactory daoFactory = AbstractDAOFactory.getDefaultFactory(connection);
            ServiceDao serviceDao = daoFactory.getServiceDao();
            return serviceDao.findById(serviceId);
        }
    }
}
