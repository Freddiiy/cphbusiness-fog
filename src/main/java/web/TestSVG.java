package web;

import util.drawing.SVG;
import util.drawing.SVGCarport;
import util.drawing.SVGMeasurementGuide;
import util.drawing.SVGRect;
import util.drawing.svg.basicshapes.Rect;

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



    /**
     * @param carportRect - x, y, w and h of the carport
     * @param yDeltaPillarsOuter - The distance from the uppermost part of the upper support bar
     *                           to the lowest part of the lower support bar.
     * @return Two SVGRects
     */
    private SVGRect[] supportBars(SVGRect carportRect, int yDeltaPillarsOuter) {
        // Support bars (name?). The horizontal thingy the pillars are attached to.
        int hSupportBar = 20;
        int yUpper = carportRect.getY() + (carportRect.getH() - yDeltaPillarsOuter) / 2;
        SVGRect upperSupportBar = new SVGRect.Builder(
                carportRect.getX(),
                yUpper,
                carportRect.getW(),
                hSupportBar)
                .build();
        SVGRect lowerSupportBar = new SVGRect.Builder(
                carportRect.getX(),
                yUpper + yDeltaPillarsOuter - hSupportBar,
                carportRect.getW(),
                hSupportBar)
                .build();
        return new SVGRect[] { upperSupportBar, lowerSupportBar };
    }

    private SVGRect[] pillars(SVGRect upperSupportBar, SVGRect lowerSupportBar, int numPillars) {
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

