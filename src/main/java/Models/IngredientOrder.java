package Models;

public class IngredientOrder {

        private String ingredientType;
        private int quantity;

    public IngredientOrder(String ingredientType, int quantity) {
        this.ingredientType = ingredientType;
        this.quantity = quantity;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
