package project.command.common;

import project.command.ActionCommand;
import project.model.services.Service;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ServiceDetailsCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long serviceId = Long.valueOf(request.getParameter("serviceId"));

        try {
            Service service = getService(serviceId);
            request.setAttribute("service", service);
        } catch (SQLException e) {
            request.setAttribute("error", e);
        }
        return JspMap.SERVICE_DETAILS;
    }

    private Service getService(long serviceId) throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getServiceService().getService(serviceId);
    }
}
