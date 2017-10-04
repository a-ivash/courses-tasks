package project.command.subscriber;

import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;
import project.model.orders.Order;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowOrdersCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return processGet(request, response);
    }

    private String processGet(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        Subscriber subscriber = HttpSessionUtils.getSubscriber(request);
        List<Order> orders = getOrdersForSubscriber(subscriber);
        request.setAttribute("orders", orders);
        return JspMap.ORDERS_SUBSCRIBER;
    }

    private List<Order> getOrdersForSubscriber(Subscriber subscriber) throws SQLException{
        return AbstractServiceFactory.getDefaultFactory().getOrdersService().getOrdersForSubscriber(subscriber);
    }
}
