package web.admin;


import controller.AdminController;
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



        request.getRequestDispatcher("/WEB-INF/adminOrder.jsp").forward(request, response);
    }
}