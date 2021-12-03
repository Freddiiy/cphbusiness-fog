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
        int w = 1000, h = 1000;
        SVG svg = new SVG(x, y, new Rect(x, y, w, h), w, h);

        /* 1 cm == 1 SVG unit */

        /* Carport measurements */
        int xCarport = 40;
        int yCarport = 40;
        int wCarport = 780; // cm. as seen from above
        int hCarport = 600;
        int distPillars = 530; // see drawing

        int wRafter = 2; // arbitrary
        int numRafters = 15;

        /* Drawing measurements */
        int margin = 10;

        /* Draw carport scale */
        // Rafter guides
        int distBetweenRafters = 55;
        int hLineGuide = 30;
        int xVerticalLine;
        int xHorizontalLine;
        for (int i = 0; i < numRafters; i++) {
            int j = Math.min(i, 13);
            xVerticalLine = xCarport + wRafter + i  * distBetweenRafters;
            svg.addLine(
                    xVerticalLine,
                    margin,
                    xVerticalLine,
                    margin + hLineGuide
            );
            xHorizontalLine = xCarport + wRafter + j * distBetweenRafters;
            svg.addArrowLine(
                    xHorizontalLine,
                    margin + hLineGuide / 2,
                    xHorizontalLine + distBetweenRafters,
                    margin + hLineGuide / 2,
                    String.format("%d cm", distBetweenRafters)
            );
        }

        svg.addArrowLine(40, 100, 40, 200, "Hej");

        /* Draw carport */
        /*
        for (int i = 0; i < numRafters; i++) {
            svg.addRect(
                    xCarport + i * distBetweenRafters,
                    yCarport,
                    wRafter,
                    hCarport
            );
        }

         */

        // svg.addArrowLine(10, 80, 210, 80, "200cm");

        request.setAttribute("svg", svg.toString());

        request.getRequestDispatcher("/WEB-INF/testsvg.jsp")
                .forward(request, response);
    }
}

