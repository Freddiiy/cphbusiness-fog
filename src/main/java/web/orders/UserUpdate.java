package web.orders;


import mapper.UserMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "UserUpdate", urlPatterns = {"/profile/update"})
public class UserUpdate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserMapper userMapper = new UserMapper();
        HttpSession session = request.getSession();

        if(!userMapper.validateSession(session.getId())) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.getRequestDispatcher("/WEB-INF/userProfile.jsp").forward(request, response);
        }
    }
}