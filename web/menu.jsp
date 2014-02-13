

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.List"%>
<%@page import="Model.MenuItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<MenuItem> menuList = (List<MenuItem>) request.getAttribute("menulist");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
    <center>
        <a href="http://cooltext.com"><img src="http://images.cooltext.com/3600402.png" width="713" height="93" alt="Teppy's Cupcake Cupboard" /></a>

        <form id='form1' name='form1' method='POST' action='bakery'>

            <br/>
            <table border="1" width="150" cellspacing="5">
                <thead>
                    <tr>
                        <th>Check</th>
                        <th>Item</th>

                        <th>Price</th>
                        <th>Image</th>
                    </tr>
                </thead>
                <tbody>
                    <%            NumberFormat nf = NumberFormat.getCurrencyInstance();
                        for (MenuItem menuItem : menuList) {
                            String item = menuItem.getItemName();
                            double itemPrice = menuItem.getItemPrice();
                            String itemUrl = menuItem.getItemUrl();

                    %>
                    <tr>
                        <td align="center">
                            <input name = "select" type="checkbox" >
                        </td>
                        <td>
                            <%= item%></td>

                        <td> <%= nf.format(itemPrice)%></td>
                        <td>
                            <img src =" <%= itemUrl%> "> 
                        </td>
                    </tr>

                    <%
                        }
                    %>
                </tbody>
            </table>
            <br/>
            <input id='order' name='order' type='submit' value='Place Order'>     
        </form>                   
    </center>

</body>
</html>
