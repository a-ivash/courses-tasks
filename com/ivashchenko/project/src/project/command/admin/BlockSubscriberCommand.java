package project.command.admin;

import project.command.ActionCommand;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.SubscriberService;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class BlockSubscriberCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        long subscriberId = Long.parseLong(request.getParameter("subscriberId"));
        blockSubscriber(subscriberId);
        return UrlMap.SUBSCRIBERS;
    }

    private void blockSubscriber(long subscriberId) throws SQLException{
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        SubscriberService subscriberService = serviceFactory.getSubscriberService();
        Subscriber subscriber = subscriberService.getSubscriber(subscriberId);
        subscriberService.blockSubscriber(subscriber);
    }
}
