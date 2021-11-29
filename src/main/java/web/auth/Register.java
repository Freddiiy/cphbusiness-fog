package web.auth;

import model.User;
import persistance.Database;
import controller.UserController;
import util.Validation;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserController userController = new UserController(new Database());
        HttpSession session = request.getSession();

        if(userController.validateSession(session)) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        HttpSession session = request.getSession();
        String sessionID = session.getId();

        Validation validation = new Validation();
        if (validation.validateEmail(email) && validation.matchPasswords(password1, password2)) {
            User user = new User(email, password1, "Customer", sessionID);

            try {
                UserController userController = new UserController(new Database());

                if (!userController.emailExists(email)) {
                    userController.insertUserToDb(user);

                    session.setAttribute("user", user);

                    response.sendRedirect(request.getContextPath() + "/");
                }
                else {
                    response.sendRedirect("/register?error=1");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}