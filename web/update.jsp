<%-- 
    Document   : update
    Created on : Feb 18, 2014, 9:36:48 PM
    Author     : Owner
--%>

<%@page import="java.util.List"%>
<%@page import="Model.MenuItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
     String displayId = (String) request.getAttribute("update");
     Integer menu_id;
     String item;
     Double item_price;
     String item_img_url;
     if(String.valueOf(displayId).equals("edit")){
     List<MenuItem> mi = (List<MenuItem>)request.getAttribute("single");
     MenuItem s = mi.get(0);
     menu_id = s.getId();
     item = s.getItemName();
     item_price = s.getItemPrice();
     item_img_url = s.getItemUrl();}
     else{
      menu_id=null;
      item=null;
      item_price=null;
      item_img_url=null;     
      }
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update DB JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
    <center>
        <h1>Add or Edit</h1>
      <!--  <label>${menu_item}</label> -->
        <form id="form1" name="form1" method="POST" action="maint?action=update">
        <table border="1" width="75" cellspacing="1" cellpadding="1">
            <thead>
                <tr>
                    <th>Column</th>
                    <th>Value</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>menu_id</td>
                    <td>
                        <label><%= menu_id%></label>
                    </td>
                </tr>
                <tr>
                    <td>menu_item</td>
                    <td>
                        <input type="text" name="txt_menu_item" placeholder="Enter menu item" 
                               value = "<%= item%>"/>
                    </td>
                </tr>
                <tr>
                    <td>item_price</td>
                    <td><input type="text" name="txt_item_price" placeholder="Enter item price" 
                               value = <%= item_price%> /></td>
                </tr>
                <tr>
                    <td>item_img_url</td>
                    <td><input type="text" name="txt_item_img_url" placeholder="Enter Image URL" 
                               value = <%= item_img_url%> /></td>
                </tr>
            </tbody>
        </table>
            <br/>
            <input type="hidden" id="add" name="add" value="<%= displayId%>" >
            <input type="hidden" id="menuId" name="menuId" value="<%= menu_id%>" >
            <p><%= displayId%></p>
            
            <input type="submit" value="Process">
        </form>
    </center>
    </body>
</html>
