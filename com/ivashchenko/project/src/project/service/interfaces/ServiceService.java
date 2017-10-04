package project.service.interfaces;

import project.model.services.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceService {
    Service saveService(Service service) throws SQLException;
    List<Service> getServices() throws SQLException;
    Service getService(long serviceId) throws SQLException;
}
