package gui.model.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    /**
     * Retrieves Azure SQL Database Connection object by using properties (in application.properties file)
     *
     * @return the Connection object to access database
     * @throws IOException
     * @throws SQLException
     */
    public static Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(DatabaseConnection.class.getClassLoader().getResourceAsStream("application.properties"));
        return DriverManager.getConnection(properties.getProperty("url"), properties);
    }
}
