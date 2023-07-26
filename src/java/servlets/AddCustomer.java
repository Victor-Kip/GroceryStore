
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oop2_project.GrocerySingleton;

 
 

 
@WebServlet(urlPatterns = {"/AddCustomer"})
public class AddCustomer extends HttpServlet {
   public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
    PrintWriter out = res.getWriter();
    String userName = req.getParameter("username");
    String email = req.getParameter("email");
    int phone = Integer.parseInt(req.getParameter("phone"));
    String address = req.getParameter("address");
    String password = req.getParameter("password");

    GrocerySingleton connection = null;
       try {
           connection = GrocerySingleton.getInstance();
       } catch (ClassNotFoundException ex) {
           out.println("Error: 222:"+ ex);
       }
    Connection conn = connection.getConnection();

    if (connection.getConnection() != null) {
         out.println("Database connection established successfully!");
        try {
            String insertQuery = "INSERT INTO users (Username, Phone, Email, Password, Address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = conn.prepareStatement(insertQuery);

                preparedStatement.setString(1, userName);
                preparedStatement.setInt(2, phone);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, address);

                // Execute the INSERT statement
                preparedStatement.executeUpdate();

                System.out.println("User data added to the database successfully!");
            } catch (SQLException sq) {
                sq.printStackTrace();
                out.println("Error: " + sq);
            } finally {
                // Close the PreparedStatement in the finally block
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Null error: " + e);
        }
     }  else {
        System.out.println("Failed to establish database connection!");
    }

   }
}
