<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Propertties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Register Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
      
    </head>
    <body>
        
        <b>Please Register!</b><br/><br>
        <form name="Register" action="validate" method="post" onsubmit="return checkPass()" >
        Name: <input type="text" name="name" required/><br/>
        Email Address: <input type="email" name="email" required/><br/>
        
        Address: <input type="text" name="address1" required/><br/>
                  
        Postal Code : <input type="text" pattern="^[0-9]{6}" title="Please enter a 6-digit postal code "  name="postal" required/><br/>
        
        Mobile Number :<input type="tel" pattern="^[6,8,9]{1}[0-9]{7}" name="phNum" required/><br/>
     

        
        <input type="submit" value="Register"/>
    </form>
    </body>
</html>