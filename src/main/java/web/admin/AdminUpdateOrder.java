package web.admin;


import persistance.AdminMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminUpdateOrder", urlPatterns = {"/admin/order/update"})
public class AdminUpdateOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        AdminMapper adminMapper = new AdminMapper();
        int shedLength = 0;
        int shedWidth = 0;

        int length = Integer.parseInt(request.getParameter("carportLength"));
        int width = Integer.parseInt(request.getParameter("carportWidth"));
        if (request.getParameter("carportShedLength") != null || request.getParameter("carportShedWidth") != null) {
            shedLength = Integer.parseInt(request.getParameter("carportShedLength"));
            shedWidth = Integer.parseInt(request.getParameter("carportShedWidth"));
        }


        int orderId = Integer.parseInt(request.getParameter("orderId"));

        adminMapper.updateOrder(orderId, width, length, shedWidth, shedLength, session.getId());
        response.sendRedirect("/admin/order?orderId=" + orderId);
    }
}