package web.orders;

import persistance.OrderMapper;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "RemoveOrder", urlPatterns = {"/orders/remove"})
public class RemoveOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        OrderMapper orderMapper = new OrderMapper();

        int orderId = Integer.parseInt(request.getParameter("orderId"));

        orderMapper.removeOrder(orderId, session.getId());

        response.sendRedirect("/orders?success=1");
    }
}