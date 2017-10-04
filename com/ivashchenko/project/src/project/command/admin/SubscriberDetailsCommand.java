package project.command.admin;

import project.command.ActionCommand;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class SubscriberDetailsCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long subscriberId = Long.valueOf(request.getParameter("subscriberId"));
        try {
            Subscriber subscriber = getSubscriber(subscriberId);
            request.setAttribute("subscriber", subscriber);
        } catch (SQLException e) {
            request.setAttribute("error", e);
        }
        return JspMap.SUBSCRIBER_DETAILS;
    }

    private Subscriber getSubscriber(long subscriberId) throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getSubscriberService().getSubscriber(subscriberId);
    }
}
