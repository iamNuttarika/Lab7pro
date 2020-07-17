/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nyp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author PNC
 */

@Stateless
public class RegisterBean {
  
    @Resource(name="")
    private DataSource dsShoppingOnline;

    public String addUser(String name, String email, String address, String postal, String phNum) {

        //Declare the connection, statement and resultset objects
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;

        try {
            //Initialise the connection, statement and resultset 

            connection = dsShoppingOnline.getConnection();

       

       
            // non-existed email,Allow user to create account and added to database tables
                // Prepare the Statement using the Connection
                preparedStatement = connection.prepareStatement("INSERT INTO customer (name, deliveryaddress, postalcode, contactnumber, emailaddress) VALUES (?,?,?,?,?)");

                // Set the userinput into the prepared statement
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, address);
                preparedStatement.setString(4, postal);
                preparedStatement.setString(5, phNum);

            

                // Make a query to the DB using ResultSet through the Statement            
                preparedStatement.executeUpdate();

            

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

        return "User added";
    }

}
