package web.auth;

import mapper.UserMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Logout", urlPatterns = {"/logout"})
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserMapper userMapper = new UserMapper();
        HttpSession session = request.getSession();

        if(userMapper.validateSession(session.getId())) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        response.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public void destroy() {
    }
}