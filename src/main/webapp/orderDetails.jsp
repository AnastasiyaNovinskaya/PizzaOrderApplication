<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Order" %>
<%@ page import="Models.PizzaOrder" %>
<%@ page import="Models.IngredientOrder" %>
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
<h2>Your Order Details</h2>
<table>
    <thead>
    <tr>
        <th>Order ID</th>
        <th>Pizza name</th>
        <th>Pizza price</th>
        <th>Additional product name</th>
        <th>Additional product price</th>
        <th>Total price</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
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
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td colspan="7">No orders available.</td>
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
