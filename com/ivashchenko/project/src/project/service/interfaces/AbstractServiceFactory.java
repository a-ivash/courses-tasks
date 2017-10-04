package project.service.interfaces;

import project.service.implementation.DefaultServiceFactory;

public interface AbstractServiceFactory {
    LoginService getLoginService();
    CreateAccountService getCreateAccountService();
    CreatePaymentsService getCreatePaymentsService();
    ServiceService getServiceService();
    SubscriberService getSubscriberService();
    OrdersService getOrdersService();
    PaymentService getPaymentService();

    static AbstractServiceFactory getDefaultFactory() {
        return new DefaultServiceFactory();
    }
}
