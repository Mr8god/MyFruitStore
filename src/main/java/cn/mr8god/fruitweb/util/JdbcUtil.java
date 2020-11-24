package cn.mr8god.fruitweb.util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author Mr8god
 * @date 2020年11月20日08:52:49
 * @time 2020年11月20日08:52:53
 */
public class JdbcUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(JdbcUtil.class.getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName(PROPERTIES.getProperty("jdbc.driverClassName"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getconnection() throws SQLException {
        String url = PROPERTIES.getProperty("jdbc.url");
        String user = PROPERTIES.getProperty("jdbc.user");
        String password = PROPERTIES.getProperty("jdbc.password");
        return DriverManager.getConnection( url, user, password);
    }

    public static void free(Statement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void free(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        JdbcUtil.free(statement, connection);
    }
}


















