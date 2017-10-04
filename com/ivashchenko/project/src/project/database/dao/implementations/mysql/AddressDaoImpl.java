package project.database.dao.implementations.mysql;

import project.database.dao.factories.AbstractDAOFactory;
import project.model.users.Address;
import project.database.dao.interfaces.AddressDao;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;

public class AddressDaoImpl extends GenericDaoImpl<Long, Address> implements AddressDao {
    private final String SQL_SELECT_ALL = "SELECT addressId, streetName, building, apartments FROM address";
    private final String SQL_SELECT_WILDCARD_ON_ID = SQL_SELECT_ALL + " WHERE addressId = ?";
    private final String SQL_INSERT_WITH_WILDCARD = "INSERT INTO address (streetName, building, apartments) VALUES (?, ?, ?)";
    private final String SQL_DELETE_WILDCARD_ON_ID = "DELETE FROM address WHERE addressId = ?";
    private final int ADDRESS_STREET_NAME_INDEX = 1;
    private final int ADDRESS_BUILDING_INDEX = 2;
    private final int ADDRESS_APARTMENTS_INDEX = 3;


    public AddressDaoImpl(Connection connection, AbstractDAOFactory daoFactory) {
        super(connection);
    }

    @Override
    protected Address buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setId(resultSet.getLong("addressId"));
        address.setStreetName(resultSet.getString("streetName"));
        address.setBuildingNumber(resultSet.getString("building"));
        address.setApartmentsNumber(resultSet.getString("apartments"));
        return address;
    }

    @Override
    protected String getSelectAllQuery() {
        return SQL_SELECT_ALL;
    }

    @Override
    protected String getSelectEntityByIdQuery() {
        return SQL_SELECT_WILDCARD_ON_ID;
    }
    @Override
    protected void fillSelectByIdPreparedStatement(PreparedStatement statement, Long id) throws SQLException {
        statement.setLong(1, id);
    }

    @Override
    protected String getInsertQuery() {
        return SQL_INSERT_WITH_WILDCARD;
    }
    @Override
    protected void fillInsertStatement(PreparedStatement statement, Address address) throws SQLException {
        statement.setString(ADDRESS_STREET_NAME_INDEX, address.getStreetName());
        statement.setString(ADDRESS_BUILDING_INDEX, address.getBuildingNumber());
        statement.setString(ADDRESS_APARTMENTS_INDEX, address.getApartmentsNumber());
    }


    @Override
    protected String getUpdateQuery() {
        throw new NotImplementedException();
    }
    @Override
    protected void fillUpdateStatement(PreparedStatement statement, Address entity) throws SQLException {
        throw new NotImplementedException();
    }


    @Override
    protected String getDeleteQuery() {
        return SQL_DELETE_WILDCARD_ON_ID;
    }

    @Override
    protected void fillDeleteStatement(PreparedStatement statement, Address entity) throws SQLException {
        statement.setLong(1, entity.getId());
    }
}
