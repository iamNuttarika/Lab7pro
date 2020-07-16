/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;
import java.io.Serializable;
import javax.ejb.Stateless;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


@WebServlet("/RegisterServlet")
@Stateless
public class RegisterServlet extends HttpServlet
{
    //Inject your EJB here
    
    @EJB
    private RegisterBean register;
    @Resource(name = "jdbc/myDatasource")
    private DataSource dsShoppingOnline;

    /**
    
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Create a new instance of the RegistrationRecord object
        RegisterBean register = new RegisterBean();
        HttpSession session = request.getSession();

        /* TODO output your page here. You may use following sample code. */
        
        //Retrieve the form attributes
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String postal = request.getParameter("postal");
        String password = request.getParameter("password");

        
        //Store the name in the registrationRecord object
        register.setName(name);

        register.setEmail(request.getParameter("email"));

        register.setAddress(request.getParameter("address"));
        register.setPostal(request.getParameter("postal"));

        String phNum = request.getParameter("phNum");
        int NphNum = Integer.parseInt(phNum);
        register.setPhNum(NphNum);

        register.setPassword(request.getParameter("password"));

        //Declare the connection, statement and resultset objects
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        
        boolean Emailex = false;

        try {
            
             // Get the connection from the DataSource 
            connection = dsShoppingOnline.getConnection();
            // Prepare the Statement using the Connection
            preparedStatement = connection.prepareStatement("SELECT count(*) FROM customer WHERE emailaddress=?");
            // Set the userinput into the prepared statement
            preparedStatement.setString(1, request.getParameter("email"));

            // Make a query to the DB using ResultSet through the Statement            
            resultset = preparedStatement.executeQuery();
            //Rertieve all records from the resultset
            resultset.next();

            //Check if it is existed email
            if (resultset.getInt(1) > 0) {
                
                Emailex = true;
                session.setAttribute("Reject", "yes");
                response.sendRedirect(this.getServletContext().getContextPath() + "/Register.jsp");

            }
            
            else{
            // non-existed email,Allow user to create account and added to database tables
            // Prepare the Statement using the Connection
            preparedStatement = connection.prepareStatement("INSERT INTO customer (name, deliveryaddress, postalcode, contactnumber, emailaddress) VALUES (?,?,?,?,?)");

            // Set the userinput into the prepared statement
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, postal);
            preparedStatement.setString(5, phNum);
            preparedStatement.setString(6, password);

            // Make a query to the DB using ResultSet through the Statement            
            preparedStatement.executeUpdate();
                
            }

           

        } catch (SQLException ex) {
            //Usually, the error should be logged somewhere in the system log.
            //Sometimes, users may also need to be notified regarding such error
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        } finally {
            //Resultset, Statement and Connection are closed in the finally 
            // clause to ensure that they will be closed no matter what 
            // happens to the system.
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.err.println(ex.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.err.println(ex.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.err.println(ex.getMessage());
                }
            }
        }


        if(!Emailex){
              session.setAttribute("User", register);
        //Make a client-side redirect into results.jsp
        response.sendRedirect(this.getServletContext().getContextPath() + "/Orders.html");
        }
     
    }

}

   
    

