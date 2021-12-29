package web.orders;


import persistance.OrderMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "UserPay", urlPatterns = {"/orders/pay"})
public class UserPay extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrderMapper orderMapper = new OrderMapper();
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        orderMapper.payOrder(orderId);
        response.sendRedirect("/orders/orderId?orderId=" + orderId);
    }
}