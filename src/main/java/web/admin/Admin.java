package web.admin;


import controller.AdminController;
import model.Carport;
import model.Order;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Admin", urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminController adminController = new AdminController();

        String sessionID = request.getSession().getId();
        List<Order> orderList = adminController.getOrders(sessionID);

        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


    }
}