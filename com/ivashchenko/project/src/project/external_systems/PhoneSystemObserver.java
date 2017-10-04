package project.external_systems;

import project.model.calls.PhoneCall;
import project.model.users.Phone;

import java.util.List;

public interface PhoneSystemObserver {
    List<Phone> getActivePhones();
    void notifyForPhoneCall(PhoneCall phoneCall);
}
