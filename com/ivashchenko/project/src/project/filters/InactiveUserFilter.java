package project.filters;

import project.command.utils.HttpSessionUtils;
import project.command.utils.ResourceBundleReader;
import project.model.users.AbstractUser;
import project.model.users.Subscriber;
import project.servlet.maps.JspMap;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class InactiveUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        AbstractUser user = HttpSessionUtils.getAbstractUser(httpServletRequest);
        if (user != null && !user.isAdministrator()) {
            Subscriber subscriber = (Subscriber)user;
            boolean restrictAccess = !subscriber.isActive() || subscriber.isBlocked();

            if (!subscriber.isActive()) {
                String inactiveUserMessage = ResourceBundleReader.getInstance().getProperty("subscriberDashboard.inactiveAccountMessage");
                httpServletRequest.setAttribute("INACTIVE_USER_MESSAGE", inactiveUserMessage);
            }
            if (subscriber.isBlocked()) {
                String blockedUserMessage = ResourceBundleReader.getInstance().getProperty("subscriberDashboard.blockedAccountMessage");
                httpServletRequest.setAttribute("BLOCKED_USER_MESSAGE", blockedUserMessage);
            }

            if (restrictAccess) {
                httpServletRequest.getRequestDispatcher(JspMap.DASHBOARD_SUBSCRIBER).forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
