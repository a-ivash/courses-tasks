package project.database.dao.implementations.mysql;

import project.database.dao.interfaces.PhoneCallDao;
import project.database.dao.interfaces.PhoneDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.model.calls.CallTypes;
import project.model.calls.PhoneCall;
import project.model.orders.Payment;
import project.model.users.Phone;
import project.model.users.Subscriber;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhoneCallDaoImpl extends GenericDaoImpl<Long, PhoneCall> implements PhoneCallDao {
    private final String COLUMNS_TO_SELECT = "callId, phoneId, durationInSeconds, callDatetime, cost, phonecalltype.typeName";
    private final String JOIN_PHONE_CALL_TYPE = "JOIN phonecalltype ON phonecalltype.typeId = phonecall.callTypeId";
    private final String SQL_SELECT_ALL = String.format("SELECT %s FROM phonecall %s", COLUMNS_TO_SELECT, JOIN_PHONE_CALL_TYPE);
    private final String SQL_SELECT_WILDCARD_ON_ID = SQL_SELECT_ALL + " WHERE callId = ?";
    private final String SQL_SELECT_WILDCARD_ON_PHONE_ID = SQL_SELECT_ALL + " WHERE phoneId = ?";
    private final String SQL_INSERT_WITH_WILDCARDS = "INSERT INTO phonecall (phoneId, durationInSeconds, callDatetime, cost, callTypeId) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_SELECT_WILDCARD_ON_PAYMENT_ID = SQL_SELECT_ALL + " WHERE paymentId = ?";
    private final String SQL_UPDATE_WILDCARD_ON_ID = "UPDATE phonecall SET paymentId = ? WHERE callId = ?";

    private AbstractDAOFactory daoFactory;

    public PhoneCallDaoImpl(Connection connection, AbstractDAOFactory daoFactory) {
        super(connection);
        this.daoFactory = daoFactory;
    }

    @Override
    protected PhoneCall buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        PhoneDao phoneDao = daoFactory.getPhoneDao();

        CallTypes callTypes = CallTypes.valueOf(resultSet.getString("phonecalltype.typeName"));
        PhoneCall phoneCall = callTypes.getPhoneCall();

        phoneCall.setId(resultSet.getLong("callId"));
        long phoneId = resultSet.getLong("phoneId");
        Phone phone = phoneDao.findById(phoneId);
        phoneCall.setPhone(phone);

        phoneCall.setDurationInSeconds(resultSet.getLong("durationInSeconds"));
        phoneCall.setCallDate(null);

        phoneCall.setCallDate(resultSet.getDate("callDatetime"));

        phoneCall.setCost(resultSet.getDouble("cost"));
        return phoneCall;
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
    protected void fillInsertStatement(PreparedStatement statement, PhoneCall entity) throws SQLException {
        statement.setLong(1, entity.getPhone().getId());
        statement.setLong(2, entity.getDurationInSeconds());
        Timestamp callDatetime = new Timestamp(entity.getCallDate().getTime());
        statement.setTimestamp(3, callDatetime);
        statement.setDouble(4, entity.getCost());
        statement.setLong(5, entity.getCallType().ordinal() + 1);
    }

    @Override
    protected String getUpdateQuery() {
        return SQL_UPDATE_WILDCARD_ON_ID;
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement statement, PhoneCall entity) throws SQLException {
        statement.setLong(1, entity.getPayment().getId());
        statement.setLong(2, entity.getId());
    }

    @Override
    protected String getDeleteQuery() {
        throw new NotImplementedException();
    }

    @Override
    protected void fillDeleteStatement(PreparedStatement statement, PhoneCall entity) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public List<PhoneCall> getCallsForPhone(Phone phone) throws SQLException {
        return getItemsForQueryWildCardOnId(SQL_SELECT_WILDCARD_ON_PHONE_ID, phone);
    }

    @Override
    public List<PhoneCall> getUnpaidCallsForSubscriber(Subscriber subscriber, Date start, Date end) throws SQLException {
        String query = SQL_SELECT_WILDCARD_ON_PHONE_ID + " AND paymentId IS NULL AND callDatetime BETWEEN ? and ? ";
        Phone phone = subscriber.getPhone();
        List<PhoneCall> phoneCalls = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, phone.getId());
            statement.setDate(2, new java.sql.Date(start.getTime()));
            statement.setDate(3, new java.sql.Date(end.getTime()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                phoneCalls.add(buildEntityFromResultSet(resultSet));
            }
        }
        return phoneCalls;
    }

    @Override
    public List<PhoneCall> getCallsForPayment(Payment payment) throws SQLException {
        return getItemsForQueryWildCardOnId(SQL_SELECT_WILDCARD_ON_PAYMENT_ID, payment);
    }
}
