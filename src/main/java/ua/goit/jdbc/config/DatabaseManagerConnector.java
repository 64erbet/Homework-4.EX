package ua.goit.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManagerConnector {

    private String url;
    private Properties properties;

    public DatabaseManagerConnector(String host, int port, String dataBaseName, String user, String password) {
        init(host, port, dataBaseName, user, password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, properties);
    }

    private void init(String host, int port, String dataBaseName, String user, String password) {
        url = String.format("jdbc:postgresql://%s:%d/%s", host, port, dataBaseName);
        properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
    }
}
