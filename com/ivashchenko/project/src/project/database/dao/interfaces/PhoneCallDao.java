package project.database.dao.interfaces;

import project.model.calls.PhoneCall;
import project.model.orders.Payment;
import project.model.users.Phone;
import project.model.users.Subscriber;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface PhoneCallDao extends GenericDao<Long, PhoneCall> {
    List<PhoneCall> getCallsForPhone(Phone phone) throws SQLException;
    List<PhoneCall> getCallsForPayment(Payment payment) throws SQLException;
    List<PhoneCall> getUnpaidCallsForSubscriber(Subscriber subscriber, Date start, Date end) throws SQLException;
}
