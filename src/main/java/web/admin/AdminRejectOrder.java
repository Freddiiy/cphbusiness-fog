package web.admin;


import mapper.AdminMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminRejectOrder", urlPatterns = {"/admin/order/reject"})
public class AdminRejectOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        AdminMapper adminMapper = new AdminMapper();

        int orderId = Integer.parseInt(request.getParameter("orderId"));

        adminMapper.rejectOrder(orderId, session.getId());
        response.sendRedirect("/admin/order?orderId=" + orderId);
    }
}