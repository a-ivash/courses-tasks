package project.command.admin;

import project.command.ActionCommand;
import project.model.services.PaymentType;
import project.model.services.Service;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.ServiceService;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CreateServiceCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return processPost(request);
    }

    private String processPost(HttpServletRequest request) {
        Service service = parseService(request);
        try {
            saveService(service);
            request.setAttribute("message", "Service has been added");
        } catch (SQLException e) {
            request.setAttribute("message", "Error while creating service. " + e);
        }

        return UrlMap.SERVICES;
    }

    private Service saveService(Service service) throws SQLException {
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        ServiceService serviceService = serviceFactory.getServiceService();
        return serviceService.saveService(service);
    }

    private Service parseService(HttpServletRequest request) {
        Service service = new Service();
        service.setServiceName(request.getParameter("serviceName"));
        service.setServiceDescription(request.getParameter("serviceDescription"));
        service.setServicePrice(Double.valueOf(request.getParameter("servicePrice")));
        PaymentType paymentType = PaymentType.valueOf(request.getParameter("paymentType"));
        service.setPaymentType(paymentType);
        return service;
    }
}
