package web.admin;


import controller.AdminController;
import persistance.Database;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminRemoveOrder", urlPatterns = {"/adminRemoveOrder"})
public class AdminRemoveOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        AdminController adminController = new AdminController(new Database());

        if (adminController.isAdmin(session.getId())) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int userId = Integer.parseInt(request.getParameter("userId"));

            adminController.removeOrder(orderId, session.getId());
            response.sendRedirect("/admin/orders?userId=" + userId);
        } else {
            response.sendRedirect("/");
        }
    }
}