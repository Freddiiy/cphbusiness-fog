package web.admin;


import controller.AdminController;
import persistance.Database;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminRemoveUser", urlPatterns = {"/adminRemoveUser"})
public class AdminRemoveUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        AdminController adminController = new AdminController(new Database());

        if (adminController.isAdmin(session.getId())) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            System.out.println(userId);

            adminController.removeUser(userId, session.getId());
            response.sendRedirect("/admin");
        } else {
            response.sendRedirect("/");
        }
    }
}