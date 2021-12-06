package web.orders;


import controller.OrderController;
import controller.UserController;
import persistance.Database;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "UserOrders", urlPatterns = {"/orders"})
public class UserOrders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserController userController = new UserController();
        OrderController orderController = new OrderController();
        HttpSession session = request.getSession();

        if(!userController.validateSession(session)) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            String sessionID = request.getSession().getId();
            //List orderList = orderController.getOrders(sessionID);

            //request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("/WEB-INF/userOrders.jsp").forward(request, response);
        }
    }
}