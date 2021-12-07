package web.view;

import mapper.UserMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Shop", urlPatterns = {"/shop"})
public class Shop extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserMapper userMapper = new UserMapper();
        HttpSession session = request.getSession();
        if (userMapper.validateSession(session)) {
            request.getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }

    }
}