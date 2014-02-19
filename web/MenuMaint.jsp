<%-- 
    Document   : MenuMaint
    Created on : Feb 18, 2014, 2:34:40 PM
    Author     : Owner
--%>
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
        <title>Menu DB Maint JSP</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
    <center>
        <h1>Menu Database Maintenance</h1>
        ${returnMsg}
         <form id='form1' name='form1' method='POST' action='maint?action=delete'>

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
                            int id = menuItem.getId();
                            String item = menuItem.getItemName();
                            double itemPrice = menuItem.getItemPrice();
                            String itemUrl = menuItem.getItemUrl();

                    %>
                    <tr>
                        <td align="center">
                            <input name = "itemId" type="checkbox" value="<%= id%>" >
                        </td>
                        <td>
                            <%= item%></td>

                        <td> <%= nf.format(itemPrice)%></td>
                        <td>
                           <%= itemUrl%>  
                        </td>
                    </tr>

                    <%
                        }
                    %>
                </tbody>
            </table>
            <br/>
            <input id="addEdit" name="addEdit" type="submit" value="addEdit">&nbsp
            <input id='delete' name='delete' type='submit' value='Delete'>     
        </form> 
    </center>
    </body>
</html>
