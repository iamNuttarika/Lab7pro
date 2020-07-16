<%-- 
    Document   : cart
    Created on : 9 Jul, 2020, 8:04:35 AM
    Author     : heman
--%>

<%@page import="sg.edu.nyp.CartBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
       
        <form action="checkout" method="get" class="login">
            <h1>Your Cart</h1>
            
            <table class="searchtable1">
                <tr><th>Item Description</th><th>Quantity</th><th>Cost(S$)</th></tr>
                        <%
                            List<CartBean> cartDetails = (List<CartBean>) request.getSession().getAttribute("cartResult");
                            if (cartDetails == null || cartDetails.size() <= 0) {
                        %>
                <tr><td colspan="5">(No order is found)</td></tr>
                <%
                } else {
                    for (CartBean cartDetail : cartDetails) {
                %>
                <tr>
                    <td><%=cartDetail.getItemDescription()%></td>
                    <td><%=cartDetail.getQuantity()%></td>
                    <td><%=cartDetail.getCost()%></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
            <%
                
                Float total = (Float) request.getSession().getAttribute("total");
            %>
            
            <input type="submit" value="Checkout" class="login-submit">
        </form>
            <br/>
            
            <div style="text-align: center">
                <h3 style="font-size: 18px; font-weight: bold">Total Cost (S$): <%=total%></h3>
            </div>
                <%
                 if(total < 50){
                %>
                <h3 style="font-size: 18px; font-weight: bold">Delivery Charge (S$): 5</h3>
                 }
                 
                 <%
                 if(total >= 50){
                %>
                <h3 style="font-size: 18px; font-weight: bold">Free Delivery</h3>
                 }
        <br/><br/><br/>
        <a href="shop.jsp" class="searchagain">Go To Shopping</a> 
        
     
    </body>
</html>
