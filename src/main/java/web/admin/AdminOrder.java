package web.admin;


import persistance.AdminMapper;
import persistance.MeasurementMapper;
import model.Material;
import model.Measurement;
import model.Order;
import util.Carport.CarportCalc;
import util.drawing.SVGCarport;

import java.io.*;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminOrder", urlPatterns = {"/admin/order"})
public class AdminOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminMapper adminMapper = new AdminMapper();
        MeasurementMapper measurementMapper = new MeasurementMapper();
        HttpSession session = request.getSession();

        Measurement measurements = measurementMapper.getAllMeasurement();

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        request.setAttribute("measurements", measurements);

        Order order = adminMapper.getOrderById(orderId, session.getId());
        request.setAttribute("order", order);


        String svg = new SVGCarport(order.getCarport().getLength(), order.getCarport().getWidth(), order.getCarport().getWidth(), order.getCarport().getLength()).toString();
        request.setAttribute("svg", svg);

        CarportCalc carportCalc = new CarportCalc(order.getCarport().getLength(), order.getCarport().getWidth());
        HashMap<Material, Integer> billOfMaterials = carportCalc.returnBillOfMaterials();
        request.setAttribute("billOfMaterials", billOfMaterials);

        HashMap<Material, Double> woodBillOfMaterials = carportCalc.calcWoodPrice();
        request.setAttribute("woodBillOfMaterials", woodBillOfMaterials);

        double totalPrice = carportCalc.calcPriceFromComparedMaterials();
        request.setAttribute("totalPrice", totalPrice);

        request.getRequestDispatcher("/WEB-INF/admin/adminOrder.jsp").forward(request, response);
    }
}