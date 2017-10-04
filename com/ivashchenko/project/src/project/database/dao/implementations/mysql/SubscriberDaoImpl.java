package project.database.dao.implementations.mysql;

import project.database.dao.interfaces.AddressDao;
import project.database.dao.interfaces.PhoneDao;
import project.database.dao.interfaces.SubscriberAuthDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.database.exceptions.EmailAlreadyTakenException;
import project.database.exceptions.WrongEmailPasswordException;
import project.model.users.Address;
import project.database.dao.interfaces.SubscriberDao;
import project.model.users.Phone;
import project.model.users.Subscriber;
import project.model.utils.PasswordUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriberDaoImpl extends GenericDaoImpl<Long, Subscriber> implements SubscriberDao, SubscriberAuthDao {
    private static final String COLUMN_TO_SELECT = "userId, firstName, lastName, email," +
                                                   "joinDate, isActive, addressId, phoneId";

    private static final String SQL_SELECT_ALL = String.format("SELECT %s FROM user", COLUMN_TO_SELECT);

    private final String SQL_SELECT_WILDCARD_ON_ID = SQL_SELECT_ALL + " WHERE userId = ?";
    private final String SQL_SELECT_WILDCARD_ON_EMAIL = SQL_SELECT_ALL + " WHERE email = ?";
    private final String SQL_INSERT_WITH_WILDCARD = "INSERT INTO user (firstName, lastName, email, addressId, passwordHash) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_DELETE_WILDCARD_ON_ID = "DELETE FROM user WHERE userId = ?";
    private final String SQL_UPDATE_WILDCARD_ON_ID = "UPDATE user SET isActive = ?, phoneId = ?, joinDate = ? WHERE userId = ?";
    private final String SQL_SELECT_BY_EMAIL_PASSWORD = String.format("SELECT %s FROM user WHERE email = ? and passwordHash = ?", COLUMN_TO_SELECT);

    private final int SUBSCRIBER_FIRST_NAME_INDEX = 1;
    private final int SUBSCRIBER_LAST_NAME_INDEX = 2;
    private final int SUBSCRIBER_EMAIL_INDEX = 3;
    private final int SUBSCRIBER_ADDRESS_ID_INDEX = 4;

    private AbstractDAOFactory daoFactory;

    public SubscriberDaoImpl(Connection connection, AbstractDAOFactory daoFactory) {
        super(connection);
        this.daoFactory = daoFactory;
    }

    @Override
    protected Subscriber buildEntityFromResultSet(ResultSet resultSet) throws SQLException{
        AddressDao addressDao = daoFactory.getAddressDao();
        PhoneDao phoneDao = daoFactory.getPhoneDao();

        Subscriber subscriber = new Subscriber();

        subscriber.setId(resultSet.getLong("userId"));
        subscriber.setFirstName(resultSet.getString("firstName"));
        subscriber.setLastName(resultSet.getString("lastName"));
        subscriber.setEmail(resultSet.getString("email"));
        subscriber.setJoinDate(resultSet.getDate("joinDate"));
        subscriber.setActive(resultSet.getBoolean("isActive"));

        long addressId = resultSet.getLong("addressId");
        long phoneId = resultSet.getLong("phoneId");
        Address address = addressDao.findById(addressId);
        subscriber.setAddress(address);
        if (phoneId != 0) {
            Phone phone = phoneDao.findById(phoneId);
            subscriber.setPhone(phone);
        }

        return subscriber;
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
    protected void fillSelectByIdPreparedStatement(PreparedStatement statement, Long id) throws SQLException {
        statement.setLong(1, id);
    }

    @Override
    protected String getInsertQuery() {
        return SQL_INSERT_WITH_WILDCARD;
    }

    @Override
    protected void fillInsertStatement(PreparedStatement statement, Subscriber entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getEmail());
        statement.setLong(4, entity.getAddress().getId());
        String hashedPassword = PasswordUtils.getHashedPassword(entity.getRawPassword());
        statement.setString(5, hashedPassword);
    }

    @Override
    public Subscriber save(Subscriber entity) throws SQLException, EmailAlreadyTakenException {
        AddressDao addressDao = daoFactory.getAddressDao();

        connection.setAutoCommit(false);
        addressDao.save(entity.getAddress());
        try(PreparedStatement statement = connection.prepareStatement(SQL_INSERT_WITH_WILDCARD,  Statement.RETURN_GENERATED_KEYS)) {
            fillInsertStatement(statement, entity);
            statement.execute();
            entity.setId(getGeneratedKey(statement));
            return entity;
        } catch (SQLIntegrityConstraintViolationException e){
            connection.rollback();
            throw new EmailAlreadyTakenException();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    @Override
    protected String getUpdateQuery() {
        return SQL_UPDATE_WILDCARD_ON_ID;
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement statement, Subscriber entity) throws SQLException {
        statement.setBoolean(1, entity.isActive());
        if (entity.getPhone() == null) {
            statement.setNull(2, Types.INTEGER);
        } else {
            statement.setLong(2, entity.getPhone().getId());
        }

        statement.setDate(3, new java.sql.Date(entity.getJoinDate().getTime()));
        statement.setLong(4, entity.getId());
    }

    @Override
    protected String getDeleteQuery() {
        return SQL_DELETE_WILDCARD_ON_ID;
    }

    @Override
    protected void fillDeleteStatement(PreparedStatement statement, Subscriber entity) throws SQLException {
        statement.setLong(1, entity.getId());
    }

    public void blockSubscriber(Subscriber subscriber) throws SQLException{
        subscriber.setActive(false);
        update(subscriber);
    }

    @Override
    public void activateSubscriber(Subscriber subscriber) throws SQLException {
        if (subscriber.getPhone() != null) { // this subscriber was banned, but has a phone
            subscriber.setActive(true);
            update(subscriber);
            return;
        }

        // Otherwise, it is a new subscriber and he needs a new phone number;
        PhoneDao phoneDao = daoFactory.getPhoneDao();

        connection.setAutoCommit(false);
        try {
            Phone newPhone = phoneDao.getFirstAvailablePhone();
            newPhone.setUsed(true);
            phoneDao.update(newPhone);
            subscriber.setPhone(newPhone);
            subscriber.setActive(true);
            subscriber.setJoinDate(new Date());
            update(subscriber);
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.commit();
            connection.setAutoCommit(true);
        }

    }

    @Override
    public List<Subscriber> getSubscribersForLastPaymentDate(Date date) {
        List<Subscriber> subscribers = new ArrayList<>();
        return subscribers;
    }

    public Subscriber login(String email, String rawPassword) throws WrongEmailPasswordException, SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL_PASSWORD)) {
            statement.setString(1, email);
            String passwordHash = PasswordUtils.getHashedPassword(rawPassword);
            statement.setString(2, passwordHash);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Subscriber subscriber = buildEntityFromResultSet(resultSet);
                return subscriber;
            }
            throw new WrongEmailPasswordException(); // not found record
        }
    }
}
