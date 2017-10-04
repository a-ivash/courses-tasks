package project.model.calls;

public class InternationalCall extends PhoneCall {
    public InternationalCall() {
        setCallType(CallTypes.international);
    }

    @Override
    public double calculateCost() {
        return getDurationInMinutes() * 5; // cost of 1 minute
    }
}
