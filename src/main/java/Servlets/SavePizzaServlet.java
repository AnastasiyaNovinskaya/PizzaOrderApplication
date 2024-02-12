package Servlets;

import Models.PizzaOrder;
import Repository.Repository;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SavePizzaServlet", value = "/savePizza")
public class SavePizzaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] selectedPizzas = request.getParameterValues("selectedPizzas");

        Repository repository = new Repository();
        ArrayList <PizzaOrder> pizzaOrders = new ArrayList<>();
        double totalPrice = 0;

        if (selectedPizzas != null) {
            for (String pizzaId : selectedPizzas) {
                String pizzaQuantities = request.getParameter("pizzaQuantity" + pizzaId);
                int quantity = Integer.parseInt(pizzaQuantities);
                String pizza = repository.getPizzaById(Integer.parseInt(pizzaId));

                double price = repository.getPizzaPriceById(Integer.parseInt(pizzaId));
                totalPrice += price * quantity;

                pizzaOrders.add(new PizzaOrder(pizza, quantity));
                }
            }
        request.getSession().setAttribute("pizzaOrders", pizzaOrders);
        request.getSession().setAttribute("pizzaTotalPrice", totalPrice);

            response.sendRedirect(request.getContextPath() + "/selectIngredient");
        }
    }