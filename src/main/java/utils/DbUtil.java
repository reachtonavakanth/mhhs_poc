package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    public Connection getConnection(String filepath) throws SQLException, IOException {

        FileUtils fileUtils = new FileUtils();
        Properties props=  fileUtils.getPropValue(filepath);
        String host = props.getProperty("db.host");
        String port = props.getProperty("db.port");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        String url = "jdbc:mysql://" + host + ":" + port;

        return DriverManager.getConnection(url, user, password);
    }

    public void tearDbConnection(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception ignored) {
            }
        }
    }
}
