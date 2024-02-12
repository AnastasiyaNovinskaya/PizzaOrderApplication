package Servlets;

import Repository.Repository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateOrderStatusServlet", value = "/updateOrderStatus")
public class UpdateOrderStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String newStatus = request.getParameter("newStatus");
        Repository repository = new Repository();

        repository.updateOrderStatus(orderId, newStatus);

        response.sendRedirect(request.getContextPath() + "/allOrders");
    }
}
