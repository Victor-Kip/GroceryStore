/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oop2_project.GrocerySingleton;
import oop2_project.Product;

/**
 *
 * @author Vic Lan
 */
@WebServlet(name = "ViewProducts", urlPatterns = {"/ViewProducts"})
public class ViewProducts extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
        PrintWriter out = res.getWriter();
        List<Product> products = new ArrayList<>();
        GrocerySingleton connection = null;
       try {
           connection = GrocerySingleton.getInstance();
       Connection conn = connection.getConnection();

    if (connection.getConnection() != null) {
        out.println("Connected successfully");
        String sql = "SELECT ProductName,Description,Price from Products ";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            String productName = resultSet.getString("ProductName");
            String description = resultSet.getString("Description");
            int price = resultSet.getInt("Price");
            Product product = new Product(productName, description, price);
            products.add(product);
        }   
        
        
         
       
        

        for (Product product : products) {
            out.print("<p>" + product.getName() + " - " + product.getDescription() + " - $" + product.getprice() + "</p>");
            out.println();
            
        
        resultSet.close();
        statement.close();
        
    } 
    
    }else {
        System.out.println("Failed to establish database connection!");
    }
       
       }catch (ClassNotFoundException | SQLException e){
        out.println("Error: 222:"+ e);
            }

    }
   

}
