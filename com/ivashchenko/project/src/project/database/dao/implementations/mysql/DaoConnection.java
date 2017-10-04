package project.database.dao.implementations.mysql;

import java.sql.Connection;

public abstract class DaoConnection {
    protected Connection connection;
    public DaoConnection(Connection connection){
        this.connection = connection;
    }
}
