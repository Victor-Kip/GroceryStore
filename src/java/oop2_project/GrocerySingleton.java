package oop2_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GrocerySingleton {
    private static GrocerySingleton instance;
    private Connection connection;

    private GrocerySingleton() throws ClassNotFoundException {
        try {
            // Establish the database connection 
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore?useSSL=false", "root", "MyOscVic2@");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized GrocerySingleton getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new GrocerySingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
