package web.orders;


import persistance.UserMapper;
import model.User;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "UserUpdate", urlPatterns = {"/profile/update"})
public class UserUpdate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserMapper userMapper = new UserMapper();
        HttpSession session = request.getSession();

        String fname = request.getParameter("fname");
        String lname =  request.getParameter("lname");
        String address = request.getParameter("address");
        int zipcode = Integer.parseInt(request.getParameter("zipcode"));
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");

        User sessionUser = (User) session.getAttribute("user");
        sessionUser.setFname(fname);
        sessionUser.setLname(lname);
        sessionUser.setAddress(address);
        sessionUser.setZipcode(zipcode);
        sessionUser.setCity(city);
        sessionUser.setPhone(phone);
        session.setAttribute("user", sessionUser);

        userMapper.updateUser(fname, lname, address, zipcode, city, phone, session.getId());
        response.sendRedirect("/profile");
    }
}