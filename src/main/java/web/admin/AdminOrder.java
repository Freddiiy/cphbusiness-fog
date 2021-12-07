package web.admin;


import mapper.AdminMapper;
import mapper.MeasurementMapper;
import model.Measurement;
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
        MeasurementMapper measurementMapper = new MeasurementMapper();
        HttpSession session = request.getSession();

        Measurement measurements = measurementMapper.getAllMeasurement();

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        request.setAttribute("measurements", measurements);
        Order order = adminMapper.getOrderById(orderId, session.getId());

        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/admin/adminOrder.jsp").forward(request, response);
    }
}