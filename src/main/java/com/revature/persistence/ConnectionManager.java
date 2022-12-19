package com.revature.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection connection;
    /*
    load the JDBC driver to fix the weird class not found bug.
    we need a properties file with the secret info in it, and we need to I/O that file
    assemble the connection string, and establish the connection with it in our connect() method.

    singleton? yes. why?
            We only ever want one connection at a time.
            It makes it very easy to get a reference to the connection object, because of static

     How might we handle the connection being terminated unexpectedly, should we try to re-open it?
     */

    private ConnectionManager() {

    }

    public static Connection getConnection() {
        if(connection == null) {
            connect();
        }
        return connection;
    }


    private static void connect() {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("jdbc.properties");
            Properties props = new Properties();
            props.load(input);

            Class.forName(props.getProperty("driver"));

            StringBuilder builder = new StringBuilder();
            builder.append("jdbc:postgresql://");
            builder.append(props.getProperty("host"));
            builder.append(":");
            builder.append(props.getProperty("port"));
            builder.append("/");
            builder.append(props.getProperty("dbname"));
            builder.append("?user=");
            builder.append(props.getProperty("username"));
            builder.append("&password=");
            builder.append(props.getProperty("password"));

            connection = DriverManager.getConnection(builder.toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
