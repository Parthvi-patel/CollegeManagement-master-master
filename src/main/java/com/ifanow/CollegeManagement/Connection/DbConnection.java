package com.ifanow.CollegeManagement.Connection;

import com.ifanow.CollegeManagement.Credentials.DbCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class DbConnection {
    static Connection connection ;
    static DbCredentials credentials = new DbCredentials();
    static String DB_URL = credentials.url;
    static String USER = credentials.user;
    static String PASS = credentials.pass;

    public static Connection getconnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return connection;
        }
        if (connection != null) {
            System.out.println("Connection working");
        } else {
            System.out.println("Connection not working");
        }
        return connection;
    }
}
