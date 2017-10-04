package project.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

public class PaymentFormDateFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        if (checkRequest(httpRequest)) {
            ResourceBundle rb = ResourceBundle.getBundle("localization/labels");
            httpRequest.setAttribute("paymentsCreationDateTooLate", rb.getString("paymentsPage.paymentsCreationDateTooLate"));
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean checkRequest(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return false;
        }
        String action = request.getParameter("action");
        Date date = new Date();
        return action.equals("create_payments") && date.getDay() <= 5;
    }

    @Override
    public void destroy() {

    }
}
