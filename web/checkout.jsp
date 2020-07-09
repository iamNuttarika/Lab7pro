<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int requestCode = (int) session.getAttribute("requestCode");
    if (requestCode != 9023)
    {
        response.sendRedirect(this.getServletContext().getContextPath() + "/checkout");
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
    </body>
</html>
<%
    session.setAttribute("requestCode", null);
%>