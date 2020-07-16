<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int requestCode = (int) session.getAttribute("requestCode");
    double deliveryFee = 0;
    if (requestCode != 9023)
    {
        response.sendRedirect(this.getServletContext().getContextPath() + "/checkout");
        return;
    }
    else
    {
        deliveryFee = (double) session.getAttribute("deliveryFee");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>delivery fee: <%= deliveryFee %></p>
    </body>
</html>
<%
    session.setAttribute("requestCode", null);
    session.setAttribute("deliveryFee", null);
%>