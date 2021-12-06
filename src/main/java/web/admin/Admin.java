package web.admin;


import controller.AdminController;
import model.Carport;
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
        AdminController adminController = new AdminController(new Database());

        String sessionID = request.getSession().getId();
        List userList = adminController.getUsers(sessionID);

        Carport carportFromDB = adminController.getCarportByOrderId(2, sessionID);
        System.out.println(carportFromDB.getWidth() + " " + carportFromDB.getLength() + " " + carportFromDB.hasShed());
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


    }
}