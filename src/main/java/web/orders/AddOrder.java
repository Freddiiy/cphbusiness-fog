package web.orders;

import mapper.OrderMapper;
import model.Carport;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AddOrder", urlPatterns = {"/orders/add"})
public class AddOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/confirmedOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (request.getSession().getId() == null) return;

        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        int roof = Integer.parseInt(request.getParameter("roof"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));

        OrderMapper orderMapper = new OrderMapper();

        if (shedWidth == 0 || shedLength == 0) {
            orderMapper.addToOrder(new Carport(carportWidth, carportLength, roof, false), session.getId());
        } else {
            orderMapper.addToOrder(new Carport(carportWidth, carportLength, roof, true, shedWidth, shedLength), session.getId());
        }

    }
}
