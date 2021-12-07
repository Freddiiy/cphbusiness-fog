package web;

import util.*;
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
        SVGRect carportRect = new SVGRect.Builder(
                xCarport, yCarport, wCarport, hCarport).build();
        svg.addElement(carportRect);

        /* Draw carport scale guides */

        // Height guide
        int xHeightLineGuide = 40;
        svg.addElement(
                new SVGMeasurementGuide.Builder(
                        xHeightLineGuide,
                        yCarport,
                        xHeightLineGuide,
                        yCarport + hCarport)
                        .text("auto")
                        .build()
        );

        // Distance between pillars (edges furthest away)
        int distPillars = 530; // see drawing
        int xDistPillarsGuide = 100;
        int yDistPillarsGuide = yCarport + (hCarport - distPillars) / 2;
        svg.addElement(
                new SVGMeasurementGuide.Builder(
                        xDistPillarsGuide,
                        yDistPillarsGuide,
                        xDistPillarsGuide,
                        yDistPillarsGuide + distPillars)
                        .text("auto")
                        .build()
        );

        // Rafter guides
        int distBetweenRafters = 55;
        int hLineGuide = 30;
        int xHorizontalLine;
        for (int i = 0; i < numRafters - 1; i++) {
            xHorizontalLine = xCarport + wRafter + i * distBetweenRafters;
            svg.addElement(
                    new SVGMeasurementGuide.Builder(
                            xHorizontalLine,
                            margin + hLineGuide / 2,
                            xHorizontalLine + distBetweenRafters,
                            margin + hLineGuide / 2)
                            .text("auto")
                            .build()
            );
        }

        /* Draw carport */

        // Support bars (name?). The horizontal thingy the pillars are attached to.
        // A lot of these values are arbitrary, until we get the carport calculator working.
        int hSupportBar = 20;
        SVGRect supportBar0 = new SVGRect.Builder(
                xCarport, yDistPillarsGuide,
                wCarport, hSupportBar)
                .build();
        SVGRect supportBar1 = new SVGRect.Builder(
                xCarport,
                yDistPillarsGuide + distPillars - hSupportBar,
                wCarport, hSupportBar)
                .build();

        svg.addElements(supportBar0, supportBar1);

        // Rafters
        int xRafter;
        for (int i = 0; i < numRafters; i++) {
            xRafter = xCarport
                    + wRafter / 2
                    + i * distBetweenRafters;
            svg.addElement(
                    new SVGRect.Builder(
                            xRafter - wRafter / 2,
                            yCarport,
                            wRafter,
                            hCarport)
                            .build()
            );
        }

        // Pillars (for now)
        /*  4 Pillars:
            15% --- 85%

            6 Pillars:
            12.5% -- 50% -- 87.5%
        */

        int numPillars = 6;
        SVGRect[] pillars = createPillars(supportBar0, supportBar1, numPillars);
        svg.addElements(pillars);


        request.setAttribute("svg", svg.toString());
        request.getRequestDispatcher("/WEB-INF/testsvg.jsp")
                .forward(request, response);
    }

    private SVGRect[] createPillars(SVGRect upperSupportBar, SVGRect lowerSupportBar, int numPillars) {
        // Note to self: I think the pillars might actually be placed correctly,
        // but it's the rafters that need to be pushed to the right a little.
        int[] placementAlongXAxis = new int[numPillars]; // percentage
        int carportWidth = upperSupportBar.getW();
        switch (numPillars) {
            case 4:
                placementAlongXAxis = new int[] {
                        pctToUnits(15.0, carportWidth),
                        pctToUnits(85.0, carportWidth)};
                break;
            case 6:
                placementAlongXAxis = new int[] {
                        pctToUnits(12.5, carportWidth),
                        pctToUnits(50, carportWidth),
                        pctToUnits(87.5, carportWidth)};
                break;
            default:
                System.out.println("Warning: You need to use either 4 or 6 pillars");
                break;
        }

        SVGRect[] pillars = new SVGRect[numPillars];
        int wPillar = upperSupportBar.getH();
        int hPillar = upperSupportBar.getH();

        for (int i = 0; i < numPillars / 2; i++) {
            int xOffset = upperSupportBar.getX();
            int xPillar = xOffset + placementAlongXAxis[i] - wPillar / 2;
            SVGRect upperPillar = new SVGRect.Builder(
                    xPillar, upperSupportBar.getY(), wPillar, hPillar)
                    .fill("#D3D3D3")
                    .build();
            SVGRect lowerPillar = new SVGRect.Builder(
                    xPillar, lowerSupportBar.getY(), wPillar, hPillar)
                    .fill("#D3D3D3")
                    .build();
            pillars[i*2] = upperPillar;
            pillars[i*2+1] = lowerPillar;
        }
        return pillars;
    }

    public static int pctToUnits(double pct, int totalUnits) {
        double notRounded = (double) totalUnits / 100.0 * pct;
        return (int) Math.round(notRounded);
    }
}

