package project.command.common;

import project.command.ActionCommand;
import project.model.services.Service;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ServiceListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Service> services = getServices();
            request.setAttribute("services", services);
        } catch (SQLException e) {
            request.setAttribute("error", e);
        }
        return JspMap.SERVICES;
    }

    private List<Service> getServices() throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getServiceService().getServices();
    }
}
