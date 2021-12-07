package web.orders;


import controller.OrderController;
import controller.UserController;
import model.Order;
import persistance.Database;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "UserOrders", urlPatterns = {"/orders", "/orders/orderId"})
public class UserOrders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserController userController = new UserController();
        OrderController orderController = new OrderController();
        HttpSession session = request.getSession();
        if(request.getServletPath().equals("/orders/orderId")) {
            int orderId = Integer.parseInt(request.getParameter("userId"));

            Order order = orderController.getOrderById(orderId, session.getId());

            request.setAttribute("order", order);
            request.getRequestDispatcher("/WEB-INF/userSpecificOrder.jsp").forward(request, response);
        } else {

            List<Order> orderList = orderController.getOrders(session.getId());

            System.out.println(orderList.size());

            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("/WEB-INF/userOrders.jsp").forward(request, response);
        }

    }
}