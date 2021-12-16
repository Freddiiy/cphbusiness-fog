package web.admin;


import mapper.AdminMapper;
import model.Carport;
import model.Order;
import util.Carport.CarportCalc;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Admin", urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminMapper adminMapper = new AdminMapper();

        String sessionID = request.getSession().getId();
        List<Order> orderList = adminMapper.getOrders(sessionID);

        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


    }
}