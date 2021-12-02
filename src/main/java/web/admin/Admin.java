package web.admin;


import controller.AdminController;
import persistance.Database;
import controller.UserController;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Admin", urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserController userController = new UserController(new Database());
        AdminController adminController = new AdminController(new Database());
        HttpSession session = request.getSession();

        if(userController.validateSession(session) && adminController.isAdmin(session.getId())) {
            String sessionID = request.getSession().getId();
            List userList = adminController.getUsers(sessionID);

            request.setAttribute("userList", userList);
            request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


    }
}