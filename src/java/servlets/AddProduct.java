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

 
 

 
@WebServlet(urlPatterns = {"/AddProduct"})
public class AddProduct extends HttpServlet {
   public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
    PrintWriter out = res.getWriter();
    String name = req.getParameter("productname");
    String description = req.getParameter("description");
    int category = Integer.parseInt(req.getParameter("category"));
    int price = Integer.parseInt(req.getParameter("price"));
    int supplier = Integer.parseInt(req.getParameter("supplier"));

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
            String insertQuery = "INSERT INTO Products (ProductName,Description,CategoryID, Price, SupplierID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = conn.prepareStatement(insertQuery);

                preparedStatement.setString(1, name);
                preparedStatement.setInt(3,category );
                preparedStatement.setString(2, description);
                preparedStatement.setInt(4, price);
                preparedStatement.setInt(5, supplier);

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
