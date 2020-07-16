/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.resource.cci.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author lawke
 */
@WebServlet("/confirm")
public class ConfirmationServlet extends HttpServlet {
     @Resource(name = "jdbc/lab7")
    private DataSource dsOnlineGrocery;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;

        String sqlInsert = "INSERT into order (customerid, itemid, quantity) VALUES (?,?,?)";

        try {
            connection = dsOnlineGrocery.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, 1);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            request
                    .getSession()
                    .setAttribute("message",
                            "An error has occured in the insertion process, Please check with your DB administrator for more details.");
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                System.err.println(ex1.getMessage());
            }
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        } finally {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                        System.err.println(ex1.getMessage());
                    }
                    ex.printStackTrace();
                    System.err.println(ex.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                        System.err.println(ex1.getMessage());
                    }
                    ex.printStackTrace();
                    System.err.println(ex.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                        System.err.println(ex1.getMessage());
                    }
                    ex.printStackTrace();
                    System.err.println(ex.getMessage());
                }
            }
            response.sendRedirect(this.getServletContext().getContextPath() + "/orderstatus.jsp");
        }
    }
}
