package project.model.orders.comparators;

import project.model.orders.Payment;

import java.util.Comparator;

public class PaymentComparator implements Comparator<Payment> {
    @Override
    public int compare(Payment o1, Payment o2) {
        if (o1.isCompleted() && !o2.isCompleted()) {
            return -1;
        }

        if (!o1.isCompleted() && o2.isCompleted()) {
            return 1;
        }

        if (!o1.getOrderDate().equals(o2.getOrderDate())) {
            return o1.getOrderDate().compareTo(o2.getOrderDate());
        }

        if (o1.getPaymentDate() == null && o2.getPaymentDate() != null) {
            return -1;
        }

        if (o2.getPaymentDate() == null && o1.getPaymentDate() != null) {
            return 1;
        }

        if (o1.getPaymentDate() == null && o2.getPaymentDate() == null) {
            return 0;
        }

        return o1.getPaymentDate().compareTo(o2.getPaymentDate());
    }
}
