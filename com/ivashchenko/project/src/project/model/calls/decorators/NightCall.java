package project.model.calls.decorators;

import project.model.calls.PhoneCall;

public class NightCall extends CallDecorator {

    public NightCall(PhoneCall phoneCall) {
        super(phoneCall);
    }

    @Override
    public double calculateCost() {
        return 0.5 * phoneCall.getCost();
    }
}
