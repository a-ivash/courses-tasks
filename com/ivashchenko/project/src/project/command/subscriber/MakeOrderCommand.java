package project.command.subscriber;

import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class MakeOrderCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return processPost(request, response);
    }

    private String processPost(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        long serviceId = Long.parseLong(request.getParameter("serviceId"));
        long subscriberId = HttpSessionUtils.getSubscriber(request).getId();
        createOrderForService(serviceId, subscriberId);
        return UrlMap.ORDERS;
    }

    private void createOrderForService(long serviceId, long subscriberId) throws SQLException {
        AbstractServiceFactory.getDefaultFactory().getOrdersService().createOrderForService(serviceId, subscriberId);
    }
}
