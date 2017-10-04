package project.command.subscriber;

import project.command.ActionCommand;
import project.model.orders.Payment;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.PaymentService;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ConfirmPaymentCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        long paymentId = Long.parseLong(request.getParameter("paymentId"));
        confirmPayment(paymentId);
        return UrlMap.PAYMNENTS;
    }

    private void confirmPayment(long paymentId) throws SQLException {
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        PaymentService paymentService = serviceFactory.getPaymentService();
        Payment payment = paymentService.getPayment(paymentId);
        paymentService.confirmPayment(payment);
    }
}
