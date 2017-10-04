package project.database.dao.interfaces;

import project.model.users.Subscriber;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface SubscriberDao extends GenericDao<Long, Subscriber> {
    void blockSubscriber(Subscriber subscriber) throws SQLException;
    void activateSubscriber(Subscriber subscriber) throws SQLException;
    List<Subscriber> getSubscribersForLastPaymentDate(Date date);
}
