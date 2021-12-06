package web.admin;


import controller.AdminController;
import persistance.Database;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "AdminAddBalance", urlPatterns = {"/admin/add-balance"})
public class AdminAddBalance extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        AdminController adminController = new AdminController();

        response.sendRedirect("/admin");
    }
}