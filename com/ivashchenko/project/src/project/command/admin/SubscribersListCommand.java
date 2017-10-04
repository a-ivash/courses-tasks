package project.command.admin;

import project.command.ActionCommand;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class SubscribersListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        try {
            List<Subscriber> subscribers = getSubscribersList();
            request.setAttribute("subscribers", subscribers);
        } catch (SQLException e) {
            request.setAttribute("error", e);
            throw e;
        }
        return JspMap.SUBSCRIBERS;
    }

    private List<Subscriber> getSubscribersList() throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getSubscriberService().getSubscribers();
    }
}
