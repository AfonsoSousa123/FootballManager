/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager.DB;

import java.sql.*;

/**
 * @author afonso, milena, tânia
 */
public class DB {
    public static void connection() {
        Connection connection = null;

        // JDBC URL, username, and password of PostgreSQL server
        String url = "postgres://postgres:DAa*Bf1Gd6bC3EEc2dgdg15f35Aef6Ag@viaduct.proxy.rlwy.net:15197/railway";
        String user = "postgres";
        String password = "DAa*Bf1Gd6bC3EEc2dgdg15f35Aef6Ag";

        try {
//            // Explicitly load the PostgreSQL driver class
//            Class.forName("org.postgresql.Driver");

            // Establishing a connection to your database
            connection = DriverManager.getConnection(url, user, password);

            // Creating a SQL statement
            Statement statement = connection.createStatement();

            // Executing a simple query to fetch records
            String query = "SELECT * FROM jogados";
            ResultSet resultSet = statement.executeQuery(query);

            // Displaying the results
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");
                // ... (retrieve other columns as needed)
                System.out.println("ID: " + id + ", Name: " + nome + ", Age: " + idade);
            }

            // Closing resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
