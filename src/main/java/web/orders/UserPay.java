package web.orders;


import mapper.MeasurementMapper;
import mapper.OrderMapper;
import mapper.UserMapper;
import model.Material;
import model.Measurement;
import model.Order;
import util.Carport.CarportCalc;
import util.drawing.SVGCarport;

import java.io.*;
import java.util.HashMap;
import java.util.List;
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