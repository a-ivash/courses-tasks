package project.database.dao.interfaces;

import project.model.orders.Payment;
import project.model.users.Subscriber;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public interface PaymentDao extends GenericDao<Long, Payment> {
    List<Payment> getPaymentsForSubscriber(Subscriber subscriber) throws SQLException;

    static Date getStartDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    static Date getEndDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }
}
