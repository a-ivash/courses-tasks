package project.command.admin;

import project.command.ActionCommand;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.CreatePaymentsService;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CreatePaymentsCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return processPost(request, response);
    }

    private String processPost(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            formPaymentsForLastMonth();
        } catch (SQLException e) {

        }
        return UrlMap.PAYMNENTS;
    }

    private void formPaymentsForLastMonth() throws SQLException {
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        CreatePaymentsService paymentsService = serviceFactory.getCreatePaymentsService();
        paymentsService.formServicesForLastMonth();
    }
}
