package project.model.calls;

public class RegionalCall extends PhoneCall {

    public RegionalCall() {
        setCallType(CallTypes.regional);
    }

    @Override
    public double calculateCost() {
        return getDurationInMinutes() * 2.5; // cost of 1 minute
    }
}
