package Repository;

import Models.*;
import Util.DBConfiguration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;


public class Repository {
    Connection connection;

    public Repository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBConfiguration.url, DBConfiguration.user, DBConfiguration.password);

        } catch (SQLException | ClassNotFoundException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
    }

    public void sendRegistration(String username, String password) {
        String sendRegistration = "INSERT INTO pizza.users (username, password) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sendRegistration);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
    }

    public boolean validateUser(String username, String password) {

        String getUsers = "SELECT * FROM pizza.Users WHERE username=? AND password=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getUsers);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (Exception exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return false;
    }

    public ArrayList<PizzaType> getPizza() {

        String getPizza = "SELECT * FROM pizza.pizzatype";
        ArrayList<PizzaType> pizzaArray = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getPizza);
            while (resultSet.next()) {

                PizzaType pizzaType = new PizzaType();
                pizzaType.setId(resultSet.getInt("ID"));
                pizzaType.setName(resultSet.getString("PizzaType"));
                pizzaType.setDescription(resultSet.getString("PizzaDescription"));
                pizzaType.setPrice(resultSet.getDouble("Price"));
                pizzaType.setImageUrl(resultSet.getString("ImageUrl"));
                pizzaArray.add(pizzaType);
            }
        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return pizzaArray;
    }

    public String getPizzaById(int pizzaId) {
        String getPizzaById = "SELECT PizzaType FROM pizza.pizzatype WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getPizzaById);
            preparedStatement.setInt(1, pizzaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("PizzaType");
                }
            }
        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return null;
    }

    public double getPizzaPriceById(int pizzaId) {
        String getPizzaPriceById = "SELECT Price FROM pizza.pizzatype WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getPizzaPriceById);
            preparedStatement.setInt(1, pizzaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("Price");
                }
            }
        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return 0;
    }

    public ArrayList<IngredientType> getIngredient() {

        String getIngredient = "SELECT * FROM pizza.ingredients";
        ArrayList<IngredientType> ingredientArray = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getIngredient);
            while (resultSet.next()) {

                IngredientType ingredientType = new IngredientType();
                ingredientType.setId(resultSet.getInt("ID"));
                ingredientType.setName(resultSet.getString("Ingredient"));
                ingredientType.setPrice(resultSet.getDouble("Price"));
                ingredientType.setImageUrl(resultSet.getString("ImageUrl"));
                ingredientArray.add(ingredientType);
            }
        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return ingredientArray;
    }

    public String getIngredientById(int ingredientId) {
        String getIngredientById = "SELECT Ingredient FROM pizza.ingredients WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getIngredientById);
            preparedStatement.setInt(1, ingredientId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("Ingredient");
                }
            }
        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return null;
    }

    public double getIngredientPriceById(int ingredientId) {
        String getIngredientById = "SELECT Price FROM pizza.ingredients WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getIngredientById);
            preparedStatement.setInt(1, ingredientId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("Price");
                }
            }
        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return 0;
    }

    public void addOrder(ArrayList<PizzaOrder> pizzaOrder, double pizzaPrice, ArrayList<IngredientOrder> ingredientOrder, double ingredientPrice, String status) {

        Gson gson = new Gson();
        String pizzaOrdersJson = gson.toJson(pizzaOrder);
        String ingredientOrdersJson = gson.toJson(ingredientOrder);

        String sendOrder = "INSERT INTO pizza.orders (Pizza_Name, Pizza_Price, Ingredient_Name, Ingredient_Price, Total_Price, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sendOrder);
            preparedStatement.setString(1, pizzaOrdersJson);
            preparedStatement.setDouble(2, pizzaPrice);
            preparedStatement.setString(3, ingredientOrdersJson);
            preparedStatement.setDouble(4, ingredientPrice);
            preparedStatement.setDouble(5, pizzaPrice + ingredientPrice);
            preparedStatement.setString(6, status);

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
    }

    public ArrayList<Order> getOrderDetails() {
        String getOrder = "SELECT * FROM pizza.orders ORDER BY orderID DESC LIMIT 1";

        ArrayList<PizzaOrder> pizzaOrders = new ArrayList<>();
        ArrayList<IngredientOrder> ingredientsOrders = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getOrder);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String pizzaJson = resultSet.getString("Pizza_Name");
                String ingredientJson = resultSet.getString("Ingredient_Name");
                double pizzaPrice = resultSet.getDouble("Pizza_Price");
                double ingredientPrice = resultSet.getDouble("Ingredient_Price");
                double totalPrice = resultSet.getDouble("Total_Price");
                int orderID = resultSet.getInt("orderID");
                String status = resultSet.getString("Status");

                // Преобразование JSON обратно в список объектов PizzaOrder
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<PizzaOrder>>() {
                }.getType();
                Type listType2 = new TypeToken<ArrayList<IngredientOrder>>() {
                }.getType();
                ArrayList<PizzaOrder> pizzaOrderList = gson.fromJson(pizzaJson, listType);
                ArrayList<IngredientOrder> ingredientOrderList = gson.fromJson(ingredientJson, listType2);

                pizzaOrders.addAll(pizzaOrderList);
                ingredientsOrders.addAll(ingredientOrderList);

                Order order = new Order(orderID, pizzaOrders, pizzaPrice, ingredientsOrders, ingredientPrice, totalPrice, status);
                orders.add(order);

            }
        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return orders;
    }

    public ArrayList<Order> getAllOrders() {
        String getOrder = "SELECT * FROM pizza.orders";

        ArrayList<Order> allOrders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getOrder);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String pizzaJson = resultSet.getString("Pizza_Name");
                String ingredientJson = resultSet.getString("Ingredient_Name");
                double pizzaPrice = resultSet.getDouble("Pizza_Price");
                double ingredientPrice = resultSet.getDouble("Ingredient_Price");
                double totalPrice = resultSet.getDouble("Total_Price");
                int orderID = resultSet.getInt("orderID");
                String status = resultSet.getString("Status");

                ArrayList<PizzaOrder> pizzaOrders = new ArrayList<>();
                ArrayList<IngredientOrder> ingredientsOrders = new ArrayList<>();

                // Преобразование JSON обратно в список объектов PizzaOrder
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<PizzaOrder>>() {
                }.getType();
                Type listType2 = new TypeToken<ArrayList<IngredientOrder>>() {
                }.getType();
                ArrayList<PizzaOrder> pizzaOrderList = gson.fromJson(pizzaJson, listType);
                ArrayList<IngredientOrder> ingredientOrderList = gson.fromJson(ingredientJson, listType2);

                pizzaOrders.addAll(pizzaOrderList);
                ingredientsOrders.addAll(ingredientOrderList);

                Order order = new Order(orderID, pizzaOrders, pizzaPrice, ingredientsOrders, ingredientPrice, totalPrice, status);
                allOrders.add(order);

            }
        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return allOrders;
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        String updateStatus = "UPDATE pizza.orders SET Status = ? WHERE orderID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateStatus);
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            System.err.println("An error occurred while updating order status: " + exception.getMessage());
        }
    }
}
