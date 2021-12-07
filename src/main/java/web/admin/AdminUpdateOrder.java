package web.admin;


import mapper.AdminMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminUpdateOrder", urlPatterns = {"/admin/order/update/*"})
public class AdminUpdateOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        AdminMapper adminMapper = new AdminMapper();

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        adminMapper.removeOrder(orderId, session.getId());
        response.sendRedirect("/admin/orders?userId=" + userId);
    }
}