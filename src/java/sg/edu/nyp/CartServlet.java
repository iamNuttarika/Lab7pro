/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.ejb.EJB;
/**
 *
 * @author heman
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @EJB
    private CartBean cart;
    @Resource(name = "jdbc/myDatasource")
    private DataSource dsShoppingOnline;

    private PreparedStatement preparedStatement;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Declare a list data structure to store the search result
        List<CartBean> cartDetails = new ArrayList<>();

        String customerId = (String) request.getSession().getAttribute("customerId");

        //Declare the connection, statement and resultset objects
        Connection connection = null;
        ResultSet resultset = null;

        try {
            //Initialise the connection, statement and resultset 

            // Get the connection from the DataSource 
            connection = dsShoppingOnline.getConnection();
            // Prepare the Statement using the Connection
            String orderDetailsSql = "SELECT o.quantity as quantity, c.description` as itemDescription, catal.itemid as itemid, catal.ppu as price "
                    + " FROM catalogue catal, order o, category c \n"
                    + " WHERE o.`itemid` = catal.`itemid` and catal.categoryid = c.id"
                    + " and o.customerid = ?";

            preparedStatement = connection.prepareStatement(orderDetailsSql);
            // Set the email into the prepared statement
            preparedStatement.setString(1, customerId);

            // Make a query to the DB using ResultSet through the Statement
            resultset = preparedStatement.executeQuery();

            
            float total = 0;
            while (resultset.next()) {
                //Create a Cart object
                CartBean cart = new CartBean();

                //Retrieve all row details from the resultset and store it in Record
                String quantity = resultset.getString("quantity");
                String itemDescription = resultset.getString("itemDescription");
                String itemid = resultset.getString("itemid");
                Float price = Float.parseFloat(resultset.getString("price"));
               
                

                cart.setItemDescription(itemDescription);
                cart.setQuantity(Integer.parseInt(quantity));
                cart.setItemId(Integer.parseInt(itemid));
                cart.setCost(price * cart.getQuantity()); 
               
                
                total += cart.getCost();
             

                cartDetails.add(cart);
            }

            HttpSession session = request.getSession();
            session.setAttribute("cartResult", (Object) cartDetails);
            session.setAttribute("total", total);


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

        //Redirect to results.jsp to display the result
        RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
        rd.forward(request, response);
    }
}