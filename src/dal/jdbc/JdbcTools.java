package dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
    private final static String URLDB= Settings.getProperty("url");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URLDB);
    }
}
