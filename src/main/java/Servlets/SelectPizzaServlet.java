package Servlets;

import Models.PizzaType;
import Repository.Repository;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "SelectPizzaServlet", value = "/selectPizza")
public class SelectPizzaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Repository repository = new Repository();

        ArrayList<PizzaType> pizzaTypeArrayList = repository.getPizza();
        HttpSession session = request.getSession(true);
        session.setAttribute("pizzas", pizzaTypeArrayList);
        request.setAttribute("pizzas", pizzaTypeArrayList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/selectPizza.jsp");
        dispatcher.forward(request, response);
    }
}
