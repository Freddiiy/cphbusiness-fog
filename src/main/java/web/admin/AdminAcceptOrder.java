package web.admin;


import persistance.AdminMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminAcceptOrder", urlPatterns = {"/admin/order/accept"})
public class AdminAcceptOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        AdminMapper adminMapper = new AdminMapper();

        int orderId = Integer.parseInt(request.getParameter("orderId"));

        adminMapper.acceptOrder(orderId, session.getId());
        response.sendRedirect("/admin/order?orderId=" + orderId);
    }
}