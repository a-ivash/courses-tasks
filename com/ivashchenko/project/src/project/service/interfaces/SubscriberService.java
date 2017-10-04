package project.service.interfaces;

import project.model.users.Subscriber;

import java.sql.SQLException;
import java.util.List;

public interface SubscriberService {
    Subscriber activateSubscriber(long subscriberId) throws SQLException;
    void blockSubscriber(Subscriber subscriber) throws SQLException;
    List<Subscriber> getSubscribers() throws SQLException;
    Subscriber getSubscriber(long subscriberId) throws SQLException;
}
