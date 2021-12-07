package web.auth;

import model.User;
import mapper.UserMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserMapper userMapper = new UserMapper();
        HttpSession session = request.getSession();

        if(userMapper.validateSession(session)) {
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
            UserMapper userMapper = new UserMapper();
            User user = userMapper.getUserFromDb(email, password);

            if(user != null && userMapper.emailExists(user.getEmail())) {
                user.setSessionID(sessionID);
                session.setAttribute("user", user);
                userMapper.updateSessionID(user.getEmail(), user.getSessionID());

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