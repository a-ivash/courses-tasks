package project.command.subscriber;

import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;
import project.command.utils.ResourceBundleReader;
import project.database.exceptions.EmailAlreadyTakenException;
import project.model.users.Address;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CreateAccountCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return processPost(request);
    }

    private String processPost(HttpServletRequest request) {
        Subscriber subscriber = parseSubscriber(request);
        try {
            subscriber = saveSubscriber(subscriber);
            HttpSessionUtils.addUserToSession(subscriber, request);
            String generatedPassword = subscriber.getRawPassword();
            request.setAttribute("generatedPassword", generatedPassword);
        } catch (EmailAlreadyTakenException e) {
            String emailAlreadyInUse = ResourceBundleReader.getInstance().getProperty("createAccountPage.emailAlreadyInUse");
            request.setAttribute("emailAlreadyInUse", emailAlreadyInUse);
            return JspMap.CREATE_ACCOUNT;
        } catch (SQLException e) {
            request.setAttribute("error", "Error with database " + e);
            return JspMap.CREATE_ACCOUNT;
        }
        return JspMap.REGISTRATION_CONFIRM;
    }

    private Subscriber saveSubscriber(Subscriber subscriber) throws SQLException{
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        return serviceFactory.getCreateAccountService().saveSubscriber(subscriber);
    }

    private Subscriber parseSubscriber(HttpServletRequest request) {
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName(request.getParameter("firstName"));
        subscriber.setLastName(request.getParameter("lastName"));
        subscriber.setEmail(request.getParameter("email"));
        subscriber.setJoinDate(null);
        subscriber.setAddress(parseAddress(request));
        return subscriber;
    }

    private Address parseAddress(HttpServletRequest request) {
        Address address = new Address();
        address.setStreetName(request.getParameter("street"));
        address.setBuildingNumber(request.getParameter("building"));
        address.setApartmentsNumber(request.getParameter("apartments"));
        return address;
    }
}
