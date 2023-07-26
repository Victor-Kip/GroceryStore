/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oop2_project.GrocerySingleton;

/**
 *
 * @author Vic Lan
 */
@WebServlet(name = "AdminLoginServlet", urlPatterns = {"/AdminLoginServlet"})
public class AdminLoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
           PrintWriter out = res.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
          GrocerySingleton connection = null;
       try {
           connection = GrocerySingleton.getInstance();
       } catch (ClassNotFoundException ex) {
           out.println("Error: 222:"+ ex);
       }
    Connection conn = connection.getConnection();

    if (connection.getConnection() != null) {
        System.out.println("Database connection established successfully!");
        String sqlQuery = "SELECT * FROM administrator WHERE Name = ? AND password = ?";
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        boolean authenticated = false;
        try{
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            resultSet = pstmt.executeQuery();
            authenticated = resultSet.next();
        }catch(SQLException sq){
            out.println("Error: " + sq);
        }finally {
            // Close resources properly
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            
            } catch (SQLException e) {
                out.println("Error: " + e);
            }
        }
        if (authenticated) {
            
             
            res.sendRedirect("addProducts.html");
        } else {
            
            out.println("<p>Wrong username or password entered</p>");
            out.println("<p>Go to the previous page and try again</p>");
        }
         
     }  else {
        System.out.println("Failed to establish database connection!");
    }
      }

}