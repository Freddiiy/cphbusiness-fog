package web.admin;


import mapper.AdminMapper;
import model.Order;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminOrder", urlPatterns = {"/admin/order"})
public class AdminOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminMapper adminMapper = new AdminMapper();
        HttpSession session = request.getSession();

        int userId = Integer.parseInt(request.getParameter("userId"));
        Order order = adminMapper.getOrderById(userId, session.getId());

        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/admin/adminOrder.jsp").forward(request, response);
    }
}