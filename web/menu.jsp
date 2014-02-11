

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
        <h1 id='logo'>Teppy's Cupcake Cupboard</h1>
    <center>
        <form id='form1' name='form1' method='POST' action='bakery'>
        
        <br/>
         <table border="1" width="150" cellspacing="5">
            <thead>
                <tr>
                    <th>Check</th>
                    <th>Item</th>
                    <th></th>
                    <th>Price</th>
                    <th>Image</th>
                </tr>
            </thead>
            <tbody>
        <%
        NumberFormat nf = NumberFormat.getCurrencyInstance();
            for(MenuItem menuItem : menuList) {
                            String item = menuItem.getItemName();
                            double itemPrice = menuItem.getItemPrice();
                            %>
                            <tr>
                    <td></td>
                    <td>
                        <%= item %></td>
                    <td></td>
                    <td> <%= nf.format(itemPrice) %></td>
                    <td></td>
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
