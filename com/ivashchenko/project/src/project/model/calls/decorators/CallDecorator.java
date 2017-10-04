package project.model.calls.decorators;

import project.model.calls.PhoneCall;

public abstract class CallDecorator extends PhoneCall {
    protected PhoneCall phoneCall;

    public CallDecorator(PhoneCall phoneCall) {
        this.phoneCall = phoneCall;
    }

    public abstract double calculateCost();
}
