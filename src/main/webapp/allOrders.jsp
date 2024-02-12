<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Order" %>
<%@ page import="Models.PizzaOrder" %>
<%@ page import="Models.IngredientOrder" %>
<%@ page import="Models.orderStatus" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Last Order Details</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            text-align: center;
            margin-top: 50px;
            background-color: #f4f4f4;
        }
        table {
            border-collapse: collapse;
            width: 90%;
            margin: auto;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
        }
        a {
            display: inline-block;
            margin: 0 10px;
            padding: 10px 0;
            width: 170px;
            text-decoration: none;
            color: #fff;
            background-color: #000000;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #3868b1;
        }
    </style>
</head>
<body>
<h2> Orders Details</h2>
<table>
    <thead>
    <tr>
        <th style="width: 7%;">Order ID</th>
        <th style="width: 20%;">Pizza name</th>
        <th style="width: 10%;">Pizza price</th>
        <th style="width: 23%;">Additional product name</th>
        <th style="width: 10%;">Additional product price</th>
        <th style="width: 10%;">Total price</th>
        <th style="width: 10%;">Status</th>
        <th style="width: 10%;">Action</th>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("allOrders");
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {
    %>
    <tr>
        <td><%= order.getId() %></td>
        <td><% for (PizzaOrder pizza : order.getPizzasList()) { %>
            <%= pizza.getPizzaType() %> (Quantity: <%= pizza.getQuantity() %>)<br>
            <% } %></td>
        <td><%= order.getPizzaPrice() %> €</td>
        <td><% for (IngredientOrder ingredient : order.getIngredientsList()) { %>
            <%= ingredient.getIngredientType() %> (Quantity: <%= ingredient.getQuantity() %>)<br>
            <% } %></td>
        <td><%= order.getIngredientPrice() %> €</td>
        <td><%= order.getTotalPrice() %> €</td>
        <td><%= order.getStatus() %></td>
        <td>
            <form action="updateOrderStatus" method="post">
                <input type="hidden" name="orderId" value="<%= order.getId() %>">
                <select name="newStatus">
                    <option value=<%=String.valueOf(orderStatus.IN_PROGRESS)%>><%=String.valueOf(orderStatus.IN_PROGRESS)%></option>
                    <option value=<%=String.valueOf(orderStatus.READY)%>><%=String.valueOf(orderStatus.READY)%></option>
                    <option value=<%=String.valueOf(orderStatus.DONE)%>><%=String.valueOf(orderStatus.DONE)%></option>
                </select>
                <input type="submit" value="Update Status">
            </form>
        </td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td colspan="8">No orders available.</td>
    </tr>
    <% } %>
    </tbody>
</table>
<br>
<br>
<a href="selectPizza">Place an order again</a>
<a href="allOrders">Check orders status</a>
<a href="index.jsp">Logout</a>

</body>
</html>
