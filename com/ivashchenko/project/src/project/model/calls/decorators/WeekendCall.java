package project.model.calls.decorators;

import project.model.calls.PhoneCall;

public class WeekendCall extends CallDecorator {
    public WeekendCall(PhoneCall phoneCall) {
        super(phoneCall);
    }

    @Override
    public double calculateCost() {
        return 0.9 * getCost();
    }
}
