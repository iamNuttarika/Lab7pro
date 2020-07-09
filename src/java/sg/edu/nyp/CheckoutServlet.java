/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nyp;

import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @EJB
    private CheckoutBean checkoutBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart");
        int fixedCode = 9023;
        int requestCode = (int) session.getAttribute("requestCode");
        
        if(requestCode != fixedCode)
        {
            if(cartList != null)
            {
                double deliveryFee = checkoutBean.calculateDelivery(cartList);
                session.setAttribute("requestCode", 9023);
                response.sendRedirect(this.getServletContext().getContextPath() + "/checkout.jsp");
            }
            else
            {
                session.setAttribute("requestCode", 9023);
                response.sendRedirect(this.getServletContext().getContextPath() + "/checkout.jsp");
            }
        }
        else
        {
            response.sendRedirect(this.getServletContext().getContextPath() + "/checkout.jsp");
        }
    }
}
