package web.view;

import mapper.MeasurementMapper;
import mapper.OrderMapper;
import mapper.UserMapper;
import model.Measurement;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "CustomCarpot", urlPatterns = {"/carport", "/carport/flat-roof", "/carport/raised-roof"})
public class Carport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserMapper userMapper = new UserMapper();
        MeasurementMapper measurementMapper = new MeasurementMapper();
        OrderMapper orderMapper = new OrderMapper();
        HttpSession session = request.getSession();

        if (request.getServletPath().equals("/carport")) {
            Measurement measurements = measurementMapper.getAllMeasurement();

            for (Integer integer : measurements.getLengthList()) {
                System.out.println(integer);
            }

            request.setAttribute("measurements", measurements);
            request.getRequestDispatcher("/WEB-INF/carports/customCarportFlatRoof.jsp").forward(request, response);
        }
        if (request.getServletPath().equals("/carport/flat-roof")) {
            request.getRequestDispatcher("/WEB-INF/carports/customCarportFlatRoof.jsp").forward(request, response);
        }
        if (request.getServletPath().equals("/carport/raised-roof")) {
            request.getRequestDispatcher("/WEB-INF/carports/customCarportRaisedRoof.jsp").forward(request, response);
        }
    }
}

