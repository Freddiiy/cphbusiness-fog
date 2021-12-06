package web.auth;

import model.User;
import persistance.Database;
import controller.UserController;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserController userController = new UserController();
        HttpSession session = request.getSession();

        if(userController.validateSession(session)) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        String sessionID = session.getId();

        try {
            UserController userController = new UserController();
            User user = userController.getUserFromDb(email, password);

            if(user != null && userController.emailExists(user.getEmail())) {
                user.setSessionID(sessionID);
                session.setAttribute("user", user);
                userController.updateSessionID(user.getEmail(), user.getSessionID());

                response.sendRedirect(request.getContextPath() + "/");
            } else {
                response.sendRedirect("/login?error=1");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
    }
}