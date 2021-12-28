package web.orders;

import mapper.OrderMapper;
import mapper.UserMapper;
import model.Carport;
import model.User;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AddOrder", urlPatterns = {"/orders/add"})
public class AddOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (request.getSession().getId() == null) return;

        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        int roof = Integer.parseInt(request.getParameter("roof"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));

        if(request.getParameter("address") != null || request.getParameter("city") != null) {
            UserMapper userMapper = new UserMapper();

            String address = request.getParameter("address");
            int zipcode = Integer.parseInt(request.getParameter("zipcode"));
            String city = request.getParameter("city");
            String phone = request.getParameter("phone");

            User sessionUser = (User) session.getAttribute("user");
            sessionUser.setAddress(address);
            sessionUser.setZipcode(zipcode);
            sessionUser.setCity(city);
            sessionUser.setPhone(phone);
            session.setAttribute("user", sessionUser);

            userMapper.updateUserAddress(address, zipcode, city, phone, session.getId());
        }

        OrderMapper orderMapper = new OrderMapper();

        if (shedWidth == 0 || shedLength == 0) {
            orderMapper.addToOrder(new Carport(carportWidth, carportLength, roof, false), session.getId());
        } else {
            orderMapper.addToOrder(new Carport(carportWidth, carportLength, roof, true, shedWidth, shedLength), session.getId());
        }

        response.sendRedirect("/orders?success=1");
    }
}
