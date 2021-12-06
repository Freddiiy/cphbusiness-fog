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

        /* Carport measurements in cm*/
        int xCarport = 140;
        int yCarport = 60;
        int wCarport = 780;
        int hCarport = 600;

        int wRafter = 3; // arbitrary
        int numRafters = 15;

        int margin = 10;

        // Draw rect around carport
        svg.addRect(xCarport, yCarport, wCarport, hCarport);

        /* Draw carport scale guides */

        // Height guide
        int xHeightLineGuide = 40;
        svg.addArrowLine(
                xHeightLineGuide,
                yCarport,
                xHeightLineGuide,
                yCarport + hCarport,
                String.format("%.2f", (double) hCarport / 100)
        );

        // Distance between pillars (edges furthest away)
        int distPillars = 530; // see drawing
        int xDistPillarsGuide = 100;
        int yDistPillarsGuide = yCarport + (hCarport - distPillars) / 2;
        svg.addArrowLine(
                xDistPillarsGuide,
                yDistPillarsGuide,
                xDistPillarsGuide,
                yDistPillarsGuide + distPillars,
                String.format("%.2f", (double) distPillars / 100)
        );

        // Rafter guides
        int distBetweenRafters = 55;
        int hLineGuide = 30;
        int xHorizontalLine;
        for (int i = 0; i < numRafters - 1; i++) {
            xHorizontalLine = xCarport + wRafter + i * distBetweenRafters;
            svg.addArrowLine(
                    xHorizontalLine,
                    margin + hLineGuide / 2,
                    xHorizontalLine + distBetweenRafters,
                    margin + hLineGuide / 2,
                    String.format("%.2f", (double) distBetweenRafters / 100)
            );
        }

        /* Draw carport */

        // Support bars (name?). The horizontal thingy the pillars are attached to.
        // A lot of these values are arbitrary, until we get the carport calculator working.
        int hSupportBar = 20;
        svg.addRect(
                xCarport,
                yDistPillarsGuide,
                wCarport,
                hSupportBar
        );
        svg.addRect(
                xCarport,
                yDistPillarsGuide + distPillars - hSupportBar,
                wCarport,
                hSupportBar
        );

        // Rafters
        int xRafter;
        for (int i = 0; i < numRafters; i++) {
            xRafter = xCarport
                    + wRafter / 2
                    + i * distBetweenRafters;
            svg.addRect(
                    xRafter - wRafter / 2,
                    yCarport,
                    wRafter,
                    hCarport
            );
        }

        request.setAttribute("svg", svg.toString());
        request.getRequestDispatcher("/WEB-INF/testsvg.jsp")
                .forward(request, response);
    }
}

