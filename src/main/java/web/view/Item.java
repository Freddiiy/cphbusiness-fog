package web.view;

import model.Cupcake;
import persistance.CupcakeInfo;
import persistance.Database;
import controller.UserController;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "Item", urlPatterns = {"/shop/item"})
public class Item extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserController userController = new UserController(new Database());
        HttpSession session = request.getSession();
        String sessionID = session.getId();

        //No ID query specified (temp)
        if (request.getParameter("id") == null) {
            PrintWriter writer=response.getWriter();
            writer.append("No cupcake specified");
        } else {
            Cupcake cupcakeData = new CupcakeInfo(new Database()).getItemFromID(request.getParameter("id"));



            request.setAttribute("cupcakeData", cupcakeData);
            request.setAttribute("sessionID", sessionID);
            request.getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
        }
    }
}