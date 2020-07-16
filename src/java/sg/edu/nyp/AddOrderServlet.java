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
import java.sql.Statement;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.ejb.EJB;


@WebServlet("/order")
public class AddOrderServlet extends HttpServlet {
    @EJB
    private OrderBean order;
    @Resource(name = "jdbc/myDatasource")
    private DataSource dsShoppingOnline;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerFound = (String) request.getSession().getAttribute("customerId");
        Integer existingOrder = null;


        // Retrieve order 
        String itemSelected = request.getParameter("item");
//        System.out.println("itemSelected : " + itemSelected);

        String selectedQuantity = request.getParameter("quantity");
//        System.out.println("selectedQuantity : " + selectedQuantity);

        //Declare the connection, statement and resultset objects
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        String sqlGetOrderByCustomerId = "SELECT * FROM order WHERE customerid = ?";

        try {
            // Get the connection from the DataSource 
            connection = dsShoppingOnline.getConnection();

            preparedStatement = connection.prepareStatement(sqlGetOrderByCustomerId);
            preparedStatement.setString(1, customerFound);

            // Make a query to the DB using ResultSet through the Statement            
            resultset = preparedStatement.executeQuery();

            // Found one customer with that order
            if (resultset.next()) {
                // Getting orderId
                existingOrder = Integer.parseInt(resultset.getString("orderid"));
            } else {
                /**
                 * Add new order line (cart) if the customer does not have one
                 * yet.
                 */
                OrderBean order = new OrderBean(Integer.parseInt(customerFound));

                // Insert that order 
                preparedStatement = connection.prepareStatement("INSERT INTO order (customerid, itemid, quantity) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, order.getCustomerId() + "");
                preparedStatement.setString(2, "0");
                preparedStatement.setString(3, "0");

                //Insert new order into the database
                preparedStatement.executeUpdate();

                ResultSet rs = preparedStatement.getGeneratedKeys();
                int generatedOrderKey = 0;
                if (rs.next()) {
                    generatedOrderKey = rs.getInt(1);
                }
                existingOrder = generatedOrderKey;
                System.out.println("*** existingOrder: " + existingOrder);

                //Commit the transaction
//                connection.commit();
            }

            /**
             * Get existing OrderDetails from order id.
             */
            String orderDetailsSql = "";

            // Make a query to the DB using ResultSet through the Statement            
//            resultset = preparedStatement.executeQuery();
            // Getting order details quantity
            preparedStatement = connection.prepareStatement("SELECT SUM(quantity) as quantity from order where itemid=?");
            preparedStatement.setString(1, itemSelected + "");

            // Make a query to the DB using ResultSet through the Statement            
            resultset = preparedStatement.executeQuery();

            if (resultset.next()) {
                System.out.println("*** result set not null: " + resultset.getInt("quantity"));

                if (resultset.getString("quantity") != null) {
                    // Getting orderId
                    Integer quantity = resultset.getInt("quantity");
                    quantity += (selectedQuantity != null ? Integer.parseInt(selectedQuantity) : 1);

                    System.out.println("*** result set not null: " + quantity);

                    
                        orderDetailsSql = "UPDATE order SET quantity = ? WHERE itemid=?";
                        preparedStatement = connection.prepareStatement(orderDetailsSql);
                        preparedStatement.setString(1, quantity + "");
                        preparedStatement.setString(2, itemSelected + "");
                        //Insert new order into the database
                        preparedStatement.executeUpdate();
                    
                }
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            //Resultset, Statement and Connection are closed in the finally 
            // clause to ensure that they will be closed no matter what 
            // happens to the system.
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

        
        //Make a client side redirect to the search result page
        response.sendRedirect(this.getServletContext().getContextPath() + "/shop.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
