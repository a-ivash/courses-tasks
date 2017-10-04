package datagen;

import project.database.dao.factories.AbstractDAOFactory;
import project.database.dao.factories.MySqlDAOFactory;
import project.database.connection.ConnectionPool;

import java.sql.SQLException;

public class DataGenerator {
    public static void main(String[] args) throws SQLException{
        AbstractDAOFactory daoFactory = new MySqlDAOFactory(ConnectionPool.getSingleConnection());
        PhoneGenerator phoneGenerator = new PhoneGenerator(daoFactory);
        phoneGenerator.generatePhones();
        SubscriberGenerator subscriberGenerator = new SubscriberGenerator(daoFactory);
        subscriberGenerator.generateSubscribers();
        ServiceGenerator serviceGenerator = new ServiceGenerator(daoFactory);
        serviceGenerator.generateServices();
    }
}
