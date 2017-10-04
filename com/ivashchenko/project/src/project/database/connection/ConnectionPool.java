package project.database.connection;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private static final String DATASOURCE_NAME = "jdbc/epamproject";
    private static DataSource ds = null;

    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup(DATASOURCE_NAME);
        } catch (NamingException e) {

        }
    }

    public static Connection getConnection() throws SQLException {
        if (ds == null) {
            throw new SQLException("Error creating connection pool");
        }
        return ds.getConnection();
    }

    public static Connection getSingleConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e) {

        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/epamproject", "root", "password");
    }
}
