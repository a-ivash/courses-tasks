package project.model.orders;

import project.model.AbstractEntity;
import project.model.calls.PhoneCall;
import project.model.users.Subscriber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Payment extends AbstractEntity<Long> {
    private long paymentId;
    private Subscriber subscriber;
    private boolean isCompleted;
    private Date orderDate;
    private Date paymentDate;
    private Date finalPaymentDate;
    private List<Order> orders;
    private List<PhoneCall> phoneCalls;

    private double totalCost;

    public Payment() {
        this.orders = new ArrayList<>();
        this.phoneCalls = new ArrayList<>();
    }

    @Override
    public void setId(Long id) {
        this.paymentId = id;
    }

    @Override
    public Long getId() {
        return paymentId;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getFinalPaymentDate() {
        return finalPaymentDate;
    }

    public void setFinalPaymentDate(Date finalPaymentDate) {
        this.finalPaymentDate = finalPaymentDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<PhoneCall> getPhoneCalls() {
        return phoneCalls;
    }

    public void setPhoneCalls(List<PhoneCall> phoneCalls) {
        this.phoneCalls = phoneCalls;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
