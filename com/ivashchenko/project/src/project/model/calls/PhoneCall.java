package project.model.calls;

import project.model.AbstractEntity;
import project.model.orders.Payment;
import project.model.users.Phone;

import java.util.Date;

public abstract class PhoneCall extends AbstractEntity<Long> {
    private Long id;
    private Phone phone;
    private long durationInSeconds;
    private Date callDate;
    private double cost;
    private Payment payment;
    private CallTypes callType;


    public PhoneCall() {

    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public long getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(long durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public long getDurationInMinutes() {
        return Math.round(getDurationInSeconds() / 60.0);
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public double getCost() {
        return cost;
    };

    public void setCost(double cost) {
        this.cost = cost;
    }

    public CallTypes getCallType() {
        return callType;
    }

    public void setCallType(CallTypes callType) {
        this.callType = callType;
    }

    public abstract double calculateCost();
}
