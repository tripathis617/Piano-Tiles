package com.example.pianotiles.dbscripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Authorization {
    // Set the environment variables
    private static final String url = "jdbc:postgresql://localhost:5432/musictiles";
    private static final String user = "postgres";
    private static final String user_password = "password";

    public static boolean userLogIn(String username, String password) {
        loadDriver();

        // Connect to the database and execute the query
        try (Connection connection = DriverManager.getConnection(url, user, user_password)) {
            System.out.println("DB Connection Established.");
//            String query = "SELECT * FROM DATA.\"user\" U where U.USER_NAME = '" + username + "' and U.USER_PASSWORD = '" + password + "';";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//                 ResultSet resultSet = preparedStatement.executeQuery()) {
//                List<String> userIds = new ArrayList<>();
//                while (resultSet.next()) {
//                    if (!resultSet.getString("USER_ID").isEmpty()) {
//                        return true;
//                    }
//                }
//                connection.close();
//            } catch (SQLException e) {
//                connection.close();
//                System.err.println("Query execution failed.");
//                e.printStackTrace();
//                return false;
//            }
        } catch (SQLException e) {
            System.err.println("Connection to the database failed.");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }
}
