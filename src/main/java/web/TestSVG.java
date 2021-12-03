package web;

import util.SVG;
import util.drawing.svg.basicshapes.Rect;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "TestSVG", urlPatterns = {"/test-svg"})
public class TestSVG extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int x = 0, y = 0;
        int w = 800, h = 600;
        SVG svg = new SVG(x, y, new Rect(x, y, w, h), w, h);
        svg.addArrowLine(10, 10, 210, 10);

        svg.addArrowLine(10, 80, 210, 80);

        request.setAttribute("svg", svg.toString());

        request.getRequestDispatcher("/WEB-INF/testsvg.jsp")
                .forward(request, response);
    }
}

