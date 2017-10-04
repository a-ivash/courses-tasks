package project.database.dao.implementations.mysql;

import project.database.dao.interfaces.ServiceDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.model.services.PaymentType;
import project.model.services.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDaoImpl extends GenericDaoImpl<Long, Service> implements ServiceDao {

    private final String COLUMNS_TO_SELECT = "serviceId, serviceName, serviceDescription, servicePrice, paymenttype.paymentTypeName";
    private final String JOIN_PAYMENT_TYPE = "JOIN paymenttype ON paymenttype.paymentTypeId = service.paymentTypeId";
    private final String SQL_SELECT_ALL = String.format("SELECT %s FROM service %s", COLUMNS_TO_SELECT, JOIN_PAYMENT_TYPE);
    private final String SQL_SELECT_WILDCARD_ON_ID = SQL_SELECT_ALL + " WHERE serviceId = ?";
    private final String SQL_INSERT_WITH_WILDCARDS = "INSERT INTO service (serviceName, serviceDescription, servicePrice, paymentTypeId) VALUES (?, ?, ?, ?)";
    private final String SQL_UPDATE_WILDCARD_ON_ID = "UPDATE service SET serviceName = ?, serviceDescription = ?, servicePrice = ? WHERE serviceId = ?";

    private final int SERVICE_NAME_INDEX = 1;
    private final int SERVICE_DESCRIPTION_INDEX = 2;
    private final int SERVICE_PRICE_INDEX = 3;
    private final int SERVICE_PAYMENT_TYPE_ID_INDEX = 4;

    public ServiceDaoImpl(Connection connection, AbstractDAOFactory daoFactory) {
        super(connection);
    }

    @Override
    public Service buildEntityFromResultSet(ResultSet resultSet) throws SQLException{
        Service service = new Service();
        service.setId(resultSet.getLong("serviceId"));
        service.setServiceName(resultSet.getString("serviceName"));
        service.setServiceDescription(resultSet.getString("serviceDescription"));
        service.setServicePrice(resultSet.getDouble("servicePrice"));
        service.setPaymentType(PaymentType.valueOf(resultSet.getString("paymenttype.paymentTypeName")));
        return service;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_SELECT_ALL;
    }

    @Override
    public String getSelectEntityByIdQuery() {
        return SQL_SELECT_WILDCARD_ON_ID;
    }

    @Override
    protected void fillSelectByIdPreparedStatement(PreparedStatement statement, Long id) throws SQLException{
        statement.setLong(1, id);
    }

    @Override
    protected String getInsertQuery() {
        return SQL_INSERT_WITH_WILDCARDS;
    }

    @Override
    protected void fillInsertStatement(PreparedStatement statement, Service service) throws SQLException{
        statement.setString(SERVICE_NAME_INDEX, service.getServiceName());
        statement.setString(SERVICE_DESCRIPTION_INDEX, service.getServiceDescription());
        statement.setDouble(SERVICE_PRICE_INDEX, service.getServicePrice());
        statement.setLong(SERVICE_PAYMENT_TYPE_ID_INDEX, service.getPaymentType().ordinal() + 1);
    }

    @Override
    protected String getUpdateQuery() {
        return SQL_UPDATE_WILDCARD_ON_ID;
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement statement, Service entity) throws SQLException {
        statement.setString(1, entity.getServiceName());
        statement.setString(2, entity.getServiceDescription());
        statement.setDouble(3, entity.getServicePrice());
        statement.setLong(4, entity.getId());
    }

    @Override
    protected String getDeleteQuery() {
        throw new NotImplementedException();
    }

    @Override
    protected void fillDeleteStatement(PreparedStatement statement, Service entity) throws SQLException {
        throw new NotImplementedException();
    }
}
