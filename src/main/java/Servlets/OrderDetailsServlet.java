package Servlets;

import Models.Order;
import Repository.Repository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "OrderDetailsServlet", value = "/OrderDetailsServlet")
public class OrderDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Repository repository = new Repository();

        ArrayList<Order> orders = repository.getOrderDetails();
        HttpSession session = request.getSession(true);
        session.setAttribute("orders", orders);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/orderDetails.jsp");
        dispatcher.forward(request, response);
    }
}
