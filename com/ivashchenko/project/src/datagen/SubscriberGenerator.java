package datagen;

import project.database.dao.interfaces.SubscriberDao;
import project.database.dao.factories.AbstractDAOFactory;
import project.model.users.Address;
import project.model.users.Subscriber;

import java.sql.SQLException;
import java.util.Random;

public class SubscriberGenerator extends AbstractGenerator{
    private static String[] firstNames = {"Jack", "John", "Peter", "Michael", "David", "Sarah", "Steven", "Bill", "Kate"};
    private static String[] lastNames = {"Doe", "Smith", "Johnson", "Lee", "Burton"};
    private static String[] streetNames = {"Central street", "99th Ave", "Lombard street", "Bay street", "Union street"};

    public static int numberOfSubscribers = firstNames.length * lastNames.length;

    SubscriberDao subscriberDao;
    Random random = new Random();

    public SubscriberGenerator(AbstractDAOFactory daoFactory) {
        super(daoFactory);
        subscriberDao = daoFactory.getSubscriberDao();
    }

    public void generateSubscribers() throws SQLException {
        for(String firstName: firstNames) {
            for(String lastName: lastNames) {
                Subscriber subscriber = buildSubscriber(firstName, lastName);
                subscriberDao.save(subscriber);
            }
        }
    }

    private Subscriber buildSubscriber(String firstName, String lastName) {
        String email = firstName + "@" + lastName + ".com";
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName(firstName);
        subscriber.setLastName(lastName);
        subscriber.setEmail(email);
        subscriber.setAddress(getRandomAddress());
        return subscriber;
    }

    private Address getRandomAddress() {
        int streetNumber = streetNames.length;
        Address address = new Address();
        String streetName = streetNames[random.nextInt(streetNumber)];
        address.setStreetName(streetName);
        address.setBuildingNumber(String.valueOf(random.nextInt(100)));
        address.setApartmentsNumber(String.valueOf(random.nextInt(100)));
        return address;
    }


}
