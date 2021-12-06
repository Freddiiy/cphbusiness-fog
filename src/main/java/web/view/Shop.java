package web.view;

import persistance.Database;
import controller.UserController;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Shop", urlPatterns = {"/shop"})
public class Shop extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserController userController = new UserController();
        HttpSession session = request.getSession();
        if (userController.validateSession(session)) {
            request.getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }

    }
}