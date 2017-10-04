package project.filters;

import org.apache.log4j.Logger;
import project.command.utils.HttpSessionUtils;
import project.model.users.AbstractUser;
import project.servlet.maps.JspMap;
import project.servlet.maps.UrlMap;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This class is used as filter to restrict unauthorized access to some kinds of pages.
 * It also allows to restrict authorized user's access to public pages.
 * */
public class AnonymousAccessFilter implements Filter {
    private Logger logger = Logger.getLogger(AnonymousAccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logRequest(httpServletRequest);
        AbstractUser user = HttpSessionUtils.getAbstractUser(httpServletRequest);
        if (user == null && !checkRequestForAnonymousAccess(httpServletRequest)) {
            // if user is not logged in and trying to access not public pages.
            httpServletRequest.getRequestDispatcher(JspMap.LOGIN).forward(servletRequest, servletResponse);
        } else if (user != null && !checkForLoggedUserPageAccess(httpServletRequest)) {
            // if user is already logged in and trying to access public pages.
            httpServletRequest.getRequestDispatcher(JspMap.DASHBOARD_REDIRECT).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void logRequest(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String requestAddr = request.getRequestURI();
        logger.info(String.format("%s requested %s", remoteAddr, requestAddr));
    }

    @Override
    public void destroy() {

    }

    private boolean checkRequestForAnonymousAccess(HttpServletRequest request) {
        String path = request.getServletPath();
        switch(path) {
            case JspMap.INDEX:
            case JspMap.LOGIN:
            case JspMap.CREATE_ACCOUNT:
            case UrlMap.LOGIN:
            case UrlMap.REGISTER: return true;
        }
        if (path.startsWith(UrlMap.RESOURCES) || path.startsWith(UrlMap.INCLUDES)) {
            return true;
        }
        return false;
    }

    private boolean checkForLoggedUserPageAccess(HttpServletRequest request) {
        String path = request.getServletPath();
        switch(path) {
            case JspMap.INDEX:
            case JspMap.LOGIN:
            case JspMap.CREATE_ACCOUNT: return false;
        }
        return true;
    }
}
