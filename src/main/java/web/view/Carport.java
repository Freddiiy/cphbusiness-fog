package web.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "CustomCarpot", urlPatterns = {"/carport", "/carport/flat-roof", "/carport/raised-roof"})
public class Carport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getServletPath().equals("/carport")) {
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

