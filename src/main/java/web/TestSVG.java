package web;


import util.drawing.SVGCarport;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "TestSVG", urlPatterns = {"/test-svg"})
public class TestSVG extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String svg1 = new SVGCarport(780, 600).toString();
        String svg2 = new SVGCarport(598, 600).toString();
        String svg3 = new SVGCarport(900, 300).toString();
        String svg4 = new SVGCarport(1400, 600, 1600, 1000).toString();
        String svg5 = new SVGCarport(550, 600).toString();

        request.setAttribute("svg1", svg1);
        request.setAttribute("svg2", svg2);
        request.setAttribute("svg3", svg3);
        request.setAttribute("svg4", svg4);
        request.setAttribute("svg5", svg5);
        request.getRequestDispatcher("/WEB-INF/testsvg.jsp")
                .forward(request, response);
    }
}
