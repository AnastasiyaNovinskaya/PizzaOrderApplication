package Servlets;

import Models.IngredientType;
import Models.PizzaType;
import Repository.Repository;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "SelectIngredientServlet", value = "/selectIngredient")
public class SelectIngredientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Repository repository = new Repository();

        ArrayList<IngredientType> ingredientTypeArrayList = repository.getIngredient();
        HttpSession session = request.getSession(true);
        session.setAttribute("ingredients", ingredientTypeArrayList);
        request.setAttribute("ingredients", ingredientTypeArrayList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/selectIngredient.jsp");
        dispatcher.forward(request, response);
    }
}
