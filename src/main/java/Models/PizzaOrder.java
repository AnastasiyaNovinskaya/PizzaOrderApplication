package Models;

public class PizzaOrder {
    private String pizzaType;
    private int quantity;

    public PizzaOrder(String pizzaType, int quantity) {
        this.pizzaType = pizzaType;
        this.quantity = quantity;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}