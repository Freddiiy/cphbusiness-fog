package web.admin;


import persistance.AdminMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "AdminRemoveUser", urlPatterns = {"/admin/remove-user"})
public class AdminRemoveUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        AdminMapper adminMapper = new AdminMapper();

        int userId = Integer.parseInt(request.getParameter("userId"));
        System.out.println(userId);

        adminMapper.removeUser(userId, session.getId());
        response.sendRedirect("/admin");
    }
}