package web.admin;


import controller.AdminController;
import persistance.Database;
import controller.UserController;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminOrders", urlPatterns = {"/admin/orders"})
public class AdminOrders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserController userController = new UserController(new Database());
        AdminController adminController = new AdminController(new Database());
        HttpSession session = request.getSession();

        String sessionID = request.getSession().getId();
        int userId = Integer.parseInt(request.getParameter("userId"));
        List orderList = adminController.getOrdersByUserId(sessionID, userId);

        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/WEB-INF/adminOrders.jsp").forward(request, response);
    }
}