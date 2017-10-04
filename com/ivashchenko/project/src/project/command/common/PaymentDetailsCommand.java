package project.command.common;

import project.command.ActionCommand;
import project.model.orders.Payment;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PaymentDetailsCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return processGet(request, response);
    }

    private String processGet(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        long paymentId = Long.valueOf(request.getParameter("paymentId"));
        Payment payment = getPayment(paymentId);
        request.setAttribute("payment", payment);
        return JspMap.PAYMENT_DETAILS;
    }

    private Payment getPayment(long paymentId) throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getPaymentService().getPayment(paymentId);
    }
}
