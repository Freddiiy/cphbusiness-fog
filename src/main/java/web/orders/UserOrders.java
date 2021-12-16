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


@WebServlet(name = "UserOrders", urlPatterns = {"/orders", "/orders/orderId"})
public class UserOrders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserMapper userMapper = new UserMapper();
        MeasurementMapper measurementMapper = new MeasurementMapper();
        OrderMapper orderMapper = new OrderMapper();
        HttpSession session = request.getSession();
        if(request.getServletPath().equals("/orders/orderId")) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));

            Order order = orderMapper.getOrderById(orderId, session.getId());


            if (order.getStatus().equals("PAID")){
                String svg = new SVGCarport(order.getCarport().getLength(), order.getCarport().getWidth(), order.getCarport().getWidth(), order.getCarport().getLength()).toString();
                request.setAttribute("svg", svg);

                CarportCalc carportCalc = new CarportCalc(order.getCarport().getLength(), order.getCarport().getWidth());
                HashMap<Material, Integer> billOfMaterials = carportCalc.returnBillOfMaterials();
                request.setAttribute("billOfMaterials", billOfMaterials);

                double totalPrice = carportCalc.calcPriceFromComparedMaterials();
                request.setAttribute("totalPrice", totalPrice);
            }

            request.setAttribute("order", order);
            request.getRequestDispatcher("/WEB-INF/userSpecificOrder.jsp").forward(request, response);
        } else {

            List<Order> orderList = orderMapper.getOrders(session.getId());

            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("/WEB-INF/userOrders.jsp").forward(request, response);
        }

    }
}