package Models;

import java.util.ArrayList;

public class Order {
    public int id;
    public ArrayList<PizzaOrder> pizzasList = new ArrayList<>();
    public double pizzaPrice;
    public ArrayList<IngredientOrder> ingredientsList = new ArrayList<>();
    public double ingredientPrice;
    public double totalPrice;
    public String status;

    public Order(int id, ArrayList<PizzaOrder> pizzasList, double pizzaPrice, ArrayList<IngredientOrder> ingredientsList, double ingredientPrice, double totalPrice, String status) {
        this.id = id;
        this.pizzasList = pizzasList;
        this.pizzaPrice = pizzaPrice;
        this.ingredientsList = ingredientsList;
        this.ingredientPrice = ingredientPrice;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<PizzaOrder> getPizzasList() {
        return pizzasList;
    }

    public void setPizzasList(ArrayList<PizzaOrder> pizzasList) {
        this.pizzasList = pizzasList;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public ArrayList<IngredientOrder> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(ArrayList<IngredientOrder> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public double getIngredientPrice() {
        return ingredientPrice;
    }

    public void setIngredientPrice(double ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
