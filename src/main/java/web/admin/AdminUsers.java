package web.admin;


import mapper.AdminMapper;
import mapper.MeasurementMapper;
import model.Measurement;
import model.Order;
import model.User;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminUsers", urlPatterns = {"/admin/users", "/admin/users/user"})
public class AdminUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminMapper adminMapper = new AdminMapper();
        MeasurementMapper measurementMapper = new MeasurementMapper();
        HttpSession session = request.getSession();

        List<User> userList = adminMapper.getUsers(session.getId());

        request.setAttribute("userList", userList);

        if (request.getServletPath().equals("/admin/users/user")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = adminMapper.getUserById(userId, session.getId());
            List<Order> orderList = adminMapper.getOrdersByUserId(userId, session.getId());
            request.setAttribute("user", user);
            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("/WEB-INF/admin/adminSpecificUser.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/admin/adminUsers.jsp").forward(request, response);
        }
    }
}