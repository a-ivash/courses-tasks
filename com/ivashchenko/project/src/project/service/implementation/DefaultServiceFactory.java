package project.service.implementation;

import project.service.interfaces.*;

public class DefaultServiceFactory implements AbstractServiceFactory {
    @Override
    public LoginService getLoginService() {
        return new DefaultLoginService();
    }

    @Override
    public CreateAccountService getCreateAccountService() {
        return new DefaultCreateAccountService();
    }

    @Override
    public CreatePaymentsService getCreatePaymentsService() {
        return new DefaultCreatePaymentsService();
    }

    @Override
    public ServiceService getServiceService() {
        return new DefaultServiceService();
    }

    @Override
    public SubscriberService getSubscriberService() {
        return new DefaultSubscriberService();
    }

    @Override
    public OrdersService getOrdersService() {
        return new DefaultOrdersService();
    }

    @Override
    public PaymentService getPaymentService() {
        return new DefaultPaymentService();
    }
}
