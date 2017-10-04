package project.model.users.comparators;

import project.model.users.Subscriber;

import java.util.Comparator;

public class SubscriberActiveComparator implements Comparator<Subscriber> {
    @Override
    public int compare(Subscriber o1, Subscriber o2) {
        if (o1.isActive() == o2.isActive()) {
            return (int) (o1.getId() - o2.getId());
        }
        if (!o1.isActive() && o2.isActive()) {
            return -1;
        }
        return 1;
    }
}
