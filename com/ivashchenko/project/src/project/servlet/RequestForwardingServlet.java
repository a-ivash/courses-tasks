package project.servlet;

import project.command.ActionCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RequestForwardingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();

        ActionCommand executedCommand = CommandMap.getCommand(url);
        try {
            String path = executedCommand.execute(request, response);
            getServletContext().getRequestDispatcher(path).forward(request, response);
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
}
