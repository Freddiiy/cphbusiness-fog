package web.admin;


import controller.AdminController;
import model.Order;
import persistance.Database;
import controller.UserController;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminOrder", urlPatterns = {"/admin/order"})
public class AdminOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminController adminController = new AdminController();
        HttpSession session = request.getSession();

        int userId = Integer.parseInt(request.getParameter("userId"));
        Order order = adminController.getOrderById(userId, session.getId());

        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/admin/adminOrder.jsp").forward(request, response);
    }
}