package Servlets;

import Models.Order;
import Repository.Repository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetAllOrdersServlet", value = "/allOrders")
public class GetAllOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Repository repository = new Repository();

        ArrayList<Order> allOrders = repository.getAllOrders();
        HttpSession session = request.getSession(true);
        session.setAttribute("allOrders", allOrders);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allOrders.jsp");
        dispatcher.forward(request, response);
    }
}
