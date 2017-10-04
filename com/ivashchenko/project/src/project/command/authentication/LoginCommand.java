package project.command.authentication;

import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;
import project.command.utils.ResourceBundleReader;
import project.database.exceptions.WrongEmailPasswordException;
import project.model.users.AbstractUser;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.LoginService;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LoginCommand implements ActionCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return processPost(request);
    }

    private String processPost(HttpServletRequest request) {
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        try {
            AbstractUser abstractUser = login(userEmail, userPassword);
            HttpSessionUtils.addUserToSession(abstractUser, request);
            return JspMap.DASHBOARD_REDIRECT;
        } catch (WrongEmailPasswordException e) {
            String wrongEmailPasswordMessage = ResourceBundleReader.getInstance().getProperty("loginPage.emailPasswordIncorrect");
            request.setAttribute("wrongEmailPasswordMessage", wrongEmailPasswordMessage);
            return JspMap.LOGIN;
        } catch (SQLException e) {
            return JspMap.INDEX;
        }
    }

    private AbstractUser login(String email, String password) throws SQLException, WrongEmailPasswordException{
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        LoginService loginService = serviceFactory.getLoginService();
        return loginService.login(email, password);
    }
}
