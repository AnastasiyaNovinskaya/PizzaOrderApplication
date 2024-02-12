<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.PizzaType" %>
<%@ page import="Models.IngredientType" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Place Order</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            text-align: center;
            margin-top: 50px;
            background-color: #f4f4f4;
        }
        input {
            font-family: 'Arial', sans-serif;
            font-size: 16px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        img {
            width: 100px;
            height: 100px;
        }
        input[type="submit"] {
            display: inline-block;
            margin: 0 10px;
            padding: 10px 0;
            width: 170px;
            text-decoration: none;
            color: #fff;
            background-color: #000000;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #3868b1;
        }
    </style>
</head>
<body>
<h2>Select additional products</h2>
<form action="placeOrder" method="post">
    <table style="width: 80%; margin: 0 auto; border-collapse: collapse; margin-bottom: 20px;">
        <tr>
            <th style="width: 35%;">Products</th>
            <th style="width: 10%;">Image</th>
            <th style="width: 10%;">Price</th>
            <th style="width: 45%;">Quantity</th>
        </tr>
        <%
            ArrayList<IngredientType> ingredients = (ArrayList<IngredientType>) session.getAttribute("ingredients");
            if (ingredients != null && !ingredients.isEmpty()) {
                for (IngredientType ingredient : ingredients) {
        %>
        <tr>
            <td>
                <label>
                    <input type="checkbox" name="selectedIngredients" value="<%= ingredient.getId() %>">
                    <b><%= ingredient.getName() %></b>
                </label>
            <td><img src="<%= ingredient.getImageUrl() %>" alt="<%= ingredient.getName() %>"></td>
            <td><%= ingredient.getPrice() + " €" %></td>
            <td>Quantity:
                <label>
                    <input type="number" name="ingredientQuantity<%= ingredient.getId() %>" value="1" min="1">
                </label>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            No ingredients available.
        </tr>
        <%
            }
        %>
    </table>
    <p>To place your order, please select "Submit"</p>
    <input type="submit" value="Submit">
    </form>
    </body>
    </html>
