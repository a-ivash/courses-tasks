package project.database.dao.implementations.mysql;

import project.database.dao.interfaces.PhoneDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.database.exceptions.NoAvailablePhonesException;
import project.model.users.Phone;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDaoImpl extends GenericDaoImpl<Long, Phone> implements PhoneDao {
    private final String COLUMNS_TO_SELECT = "phoneId, phoneNumber, isUsed";
    private final String SQL_SELECT_ALL = String.format("SELECT %s FROM phone", COLUMNS_TO_SELECT);
    private final String SQL_SELECT_WILDCARD_ON_ID = SQL_SELECT_ALL + " WHERE phoneId = ?";
    private final String SQL_INSERT_WITH_WILDCARDS = "INSERT INTO phone (phoneNumber) VALUES (?)";
    private final String SQL_UPDATE_WILDCARD_ON_ID = "UPDATE phone SET isUsed = ? WHERE phoneId = ?";
    private final String SQL_SELECT_UNUSED_NUMBERS_WILDCARD_ON_LIMIT = String.format("SELECT %s FROM phone WHERE isUsed = false LIMIT ?", COLUMNS_TO_SELECT);
    private final String SQL_SELECT_ACTIVE_PHONES = SQL_SELECT_ALL + " WHERE isUsed = true";

    public PhoneDaoImpl(Connection connection, AbstractDAOFactory daoFactory) {
        super(connection);
    }

    @Override
    protected Phone buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Phone phone = new Phone();
        phone.setId(resultSet.getLong("phoneId"));
        phone.setPhoneNumber(resultSet.getString("phoneNumber"));
        phone.setUsed(resultSet.getBoolean("isUsed"));
        return phone;
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
        return SQL_INSERT_WITH_WILDCARDS;
    }

    @Override
    protected void fillInsertStatement(PreparedStatement statement, Phone entity) throws SQLException {
        statement.setString(1, entity.getPhoneNumber());
    }

    @Override
    protected String getUpdateQuery() {
        return SQL_UPDATE_WILDCARD_ON_ID;
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement statement, Phone entity) throws SQLException {
        statement.setBoolean(1, entity.isUsed());
        statement.setLong(2, entity.getId());
    }


    @Override
    protected String getDeleteQuery() {
        throw new NotImplementedException();
    }

    @Override
    protected void fillDeleteStatement(PreparedStatement statement, Phone entity) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public List<Phone> getAvailablePhones(int limit) throws SQLException {
        List<Phone> phones = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_UNUSED_NUMBERS_WILDCARD_ON_LIMIT)) {
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                phones.add(buildEntityFromResultSet(resultSet));
            }
            return phones;
        }
    }

    @Override
    public Phone getFirstAvailablePhone() throws SQLException{
        List<Phone> phones = getAvailablePhones(1);
        if (phones.isEmpty()) {
            throw new NoAvailablePhonesException();
        }
        return phones.get(0);
    }

    @Override
    public List<Phone> getActivePhones() throws SQLException {
        List<Phone> activePhones = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ACTIVE_PHONES);
            while (resultSet.next()) {
                activePhones.add(buildEntityFromResultSet(resultSet));
            }
        }
        return activePhones;
    }
}
