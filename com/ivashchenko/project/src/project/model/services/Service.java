package project.model.services;

import project.model.AbstractEntity;

public class Service extends AbstractEntity<Long> {
    private long serviceId;
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private PaymentType paymentType;

    public Service() {

    }

    public Service(String serviceName, String serviceDescription, double servicePrice, PaymentType paymentType) {
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
        this.paymentType = paymentType;
    }

    @Override
    public void setId(Long id) {
        this.serviceId = id;
    }

    @Override
    public Long getId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

}
