package project.command.utils;

import project.model.users.AbstractUser;
import project.model.users.Subscriber;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static Subscriber getSubscriber(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Subscriber) session.getAttribute("currentUser");
    }

    public static AbstractUser getAbstractUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (AbstractUser) session.getAttribute("currentUser");
    }

    public static void addUserToSession(AbstractUser user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", user);
    }

    public static void setLanguageToSession(HttpServletRequest request, String languageCode) {
        HttpSession session = request.getSession();
        session.setAttribute("lang", languageCode);
    }
}
