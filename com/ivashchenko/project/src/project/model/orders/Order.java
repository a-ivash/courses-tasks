package project.model.orders;

import project.model.AbstractEntity;
import project.model.services.Service;
import project.model.users.Subscriber;

import java.util.Date;

public class Order extends AbstractEntity<Long> {
    private long orderId;
    private Subscriber subscriber;
    private Service service;
    private double price;
    private Date orderDate;
    private Payment payment;

    public Order() {

    }

    @Override
    public void setId(Long id) {
        this.orderId = id;
    }

    @Override
    public Long getId() {
        return orderId;
    }


    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
