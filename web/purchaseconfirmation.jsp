<%-- 
    Document   : purchaseconfirmation
    Created on : 8 Jul, 2020, 5:17:36 PM
    Author     : lawke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchase Confirmation Page</title>
    </head>
    <body>
        <h1>Purchase Confirmation Page</h1>
        <form action="/" method="POST">
            Credit Card:
            <label for="creditCard"></label>
            <input type="text" name="creditCard"/><br><br>
            <input type="submit" value="Confirm Purchase"/>
        </form>
    </body>
</html>
