package project.command.admin;

import project.command.ActionCommand;
import project.database.exceptions.NoAvailablePhonesException;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.SubscriberService;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ActivateSubscriberCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return processPost(request, response);
    }

    private String processPost(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        long subscriberId = Long.valueOf(request.getParameter("subscriberId"));
        try {
            Subscriber subscriber = activateSubscriber(subscriberId);
        } catch(NoAvailablePhonesException e) {
            request.setAttribute("error_message", "No available phone numbers left");
        }
        return String.format(UrlMap.SUBSCRIBER, subscriberId);
    }

    private Subscriber activateSubscriber(long subscriberId) throws SQLException {
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        SubscriberService activateSubscriberService = serviceFactory.getSubscriberService();
        return activateSubscriberService.activateSubscriber(subscriberId);
    }
}
