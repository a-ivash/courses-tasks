package project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface ActionCommand {
    String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
