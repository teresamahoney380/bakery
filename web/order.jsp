<%-- 
    Document   : order
    Created on : Feb 13, 2014, 2:47:37 PM
    Author     : Owner
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.List"%>
<%@page import="Model.MenuItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<MenuItem> orderList = (List<MenuItem>) request.getAttribute("orderlist");
    String total = (String) request.getAttribute("itemtotals");
    double orderTotal = Double.valueOf(total);
    NumberFormat nf = NumberFormat.getCurrencyInstance();


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">

    </head>
    <body>
    <center>
        <a href="http://cooltext.com"><img src="http://images.cooltext.com/3600402.png" width="713" height="93" alt="Teppy's Cupcake Cupboard" /></a>
        <br/>
        <h1>Order Summary</h1>

        <table border="1" width="150" cellspacing="5">
            <thead>
                <tr>
                    <!--    <th>Check</th>-->
                    <th>Item</th>

                    <th>Price</th>
                    <th>Image</th>
                </tr>
            </thead>
            <tbody>
                <%                    for (MenuItem menuItem : orderList) {
                        int id = menuItem.getId();
                        String item = menuItem.getItemName();
                        double itemPrice = menuItem.getItemPrice();
                        String itemUrl = menuItem.getItemUrl();

                %>
                <tr>
                    <!--    <td align="center">
                            <input name = "itemId" type="checkbox" value="<%= id%>" >
                        </td>-->
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
                <tr>
                    <td></td>
                    <td>Total</td>
                    <td><%= nf.format(orderTotal)%></td>
                </tr>
            </tbody>
        </table>
        <!--   ${orderlist} -->
        <br/>
        <a href="index.html"> Home</a>
    </center>
</body>
</html>
