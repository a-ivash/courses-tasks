package project.command.common;

import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;
import project.model.orders.Payment;
import project.model.users.AbstractUser;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class PaymentListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
            return processGet(request, response);
    }

    private String processGet(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        AbstractUser abstractUser = HttpSessionUtils.getAbstractUser(request);
        if (abstractUser.isAdministrator()) {
            List<Payment> payments = getPayments();
            request.setAttribute("payments", payments);
        } else {
            Subscriber subscriber = (Subscriber)abstractUser;
            List<Payment> payments = getSubscribersPayments(subscriber);
            request.setAttribute("payments", payments);
        }
        return JspMap.PAYMENTS;
    }

    private List<Payment> getSubscribersPayments(Subscriber subscriber) throws SQLException{
        return AbstractServiceFactory.getDefaultFactory().getPaymentService().getSubscribersPayments(subscriber);
    }

    private List<Payment> getPayments() throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getPaymentService().getPayments();
    }
}
