package web.view;

import mapper.UserMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Item", urlPatterns = {"/shop/item"})
public class Item extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserMapper userMapper = new UserMapper();
        HttpSession session = request.getSession();
        String sessionID = session.getId();

        //No ID query specified (temp)
        if (request.getParameter("id") == null) {
            PrintWriter writer=response.getWriter();
            writer.append("No cupcake specified");
        } else {
            request.setAttribute("sessionID", sessionID);
            request.getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
        }
    }
}