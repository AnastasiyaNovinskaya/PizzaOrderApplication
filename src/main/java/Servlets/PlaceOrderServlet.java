package Servlets;

import Models.IngredientOrder;
import Models.PizzaOrder;
import Models.orderStatus;
import Repository.Repository;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PlaceOrderServlet", value = "/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ArrayList<PizzaOrder> pizzaOrders = (ArrayList<PizzaOrder>) session.getAttribute("pizzaOrders");
        double totalPizzaPrice = (double) session.getAttribute("pizzaTotalPrice");

        String[] selectedIngredients = request.getParameterValues("selectedIngredients");

        Repository repository = new Repository();
        ArrayList <IngredientOrder> ingredientOrders = new ArrayList<>();
        double totalPrice = 0;
        String status = String.valueOf(orderStatus.IN_PROGRESS);

        if (selectedIngredients != null) {
            for (String ingredientId : selectedIngredients) {
                String ingredientQuantities = request.getParameter("ingredientQuantity" + ingredientId);
                int quantity = Integer.parseInt(ingredientQuantities);
                String ingredient = repository.getIngredientById(Integer.parseInt(ingredientId));

                double price = repository.getIngredientPriceById(Integer.parseInt(ingredientId));
                totalPrice += price * quantity;

                ingredientOrders.add(new IngredientOrder(ingredient, quantity));

            }
        }
        repository.addOrder (pizzaOrders, totalPizzaPrice, ingredientOrders, totalPrice, status);

        response.sendRedirect(request.getContextPath() + "/OrderDetailsServlet");
    }
}

