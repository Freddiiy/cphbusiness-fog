package util.drawing;

import mapper.CarportMapper;
import util.drawing.svg.basicshapes.Rect;

import java.util.ArrayList;

public class SVGCarport {
    
    private final CarportMapper carportMapper;
    private final SVGRect carportRect;
    private int wViewport = 0;
    private int hViewport = 0;

    private final int MARGIN = 20;
    
    public SVGCarport(int LENGTH, int WIDTH) {
        carportMapper = new CarportMapper(LENGTH, WIDTH);

        // todo: Find x and y
        int xCarport = 140;
        int yCarport = 80;
        carportRect = new SVGRect.Builder(xCarport, yCarport, LENGTH, WIDTH).build();
    }

    public SVGCarport(int LENGTH, int WIDTH, int wViewport, int hViewport) {
        carportMapper = new CarportMapper(LENGTH, WIDTH);
        this.wViewport = wViewport;
        this.hViewport = hViewport;

        // todo: Find x and y
        int xCarport = 140;
        int yCarport = 60;
        carportRect = new SVGRect.Builder(xCarport, yCarport, LENGTH, WIDTH).build();
    }

    public String toString() {
        SVGRect[] supportBars = supportBars();
        SVGRect[] rafters = rafters();
        SVGRect[] pillars = pillars(carportMapper.numPillars(), supportBars); //pillars(supportBars[0], supportBars[1]);

        int x = 0, y = 0;
        int w = 1000, h = 1000;
        if (wViewport != 0) {
            w = wViewport;
        }
        if (hViewport != 0) {
            h = hViewport;
        }

        // Measurement guides
        SVGMeasurementGuide supportBarsGuide = SVGMeasurementGuide.forSupportBars(supportBars);
        SVGMeasurementGuide hCarportGuide = SVGMeasurementGuide.forCarportHeight(carportRect, supportBarsGuide);
        SVGMeasurementGuide[] rafterGuides = SVGMeasurementGuide.forRafters(rafters);

        // Find the total width and height of the drawing
        int wTotal = carportRect.getX()
                + carportRect.getW()
                + SVGMeasurementGuide.spaceNeeded() * 2;
        int hTotal = carportRect.getY()
                + carportRect.getH()
                + SVGMeasurementGuide.spaceNeeded();

        Rect viewBox = new Rect(0, 0, wTotal, hTotal);
        SVG svg = new SVG(x, y, viewBox, w, h);

        svg.addElement(carportRect);
        svg.addElements(supportBarsGuide, hCarportGuide);
        svg.addElements(supportBars, rafters, pillars, rafterGuides);
        return svg.toString();
    }

    private SVGRect[] supportBars() {
        // Support bars (name?). The horizontal thingy the pillars are attached to.
        // Place 35 cm. from the edge of the carport.
        int distEdgeToPillar = 35;
        int yDeltaPillarsOuter = carportRect.getH() - 2 * distEdgeToPillar;
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

    private SVGRect[] rafters() {
        int xRafter;
        int wRafter = 5; // pretty arbitrary number
        int numRafters = carportMapper.numRafters();

        // Push rafters slightly to the right
        int wCarport = carportRect.getW();
        int xLastRafter = xForRafter(
                carportRect.getX(), wRafter, numRafters - 1, numRafters) - carportRect.getX();
        int emptySpaceRight = wCarport - xLastRafter;
        int xOffset = emptySpaceRight / 2;

        SVGRect[] rafters = new SVGRect[numRafters];
        for (int i = 0; i < numRafters; i++) {
            xRafter = xForRafter(carportRect.getX(), wRafter, i, numRafters) + xOffset;

            rafters[i] = new SVGRect.Builder(
                    xRafter - wRafter / 2,
                    carportRect.getY(),
                    wRafter,
                    carportRect.getH())
                    .build();
        }
        return rafters;
    }

    private int xForRafter(int xOffset, int wRafter, int i, int numRafters) {
        return xOffset
                + wRafter / 2
                + i * carportMapper.distBetweenRafters(numRafters);
    }

    /**
     * Places the first and last pair of pillars statically, and the extra pairs
     * of pillars between them dynamically (spaced out evenly).
     * @param numPillars - The number of pillars in the carport
     * @param supportBars - The two support bars in the carport
     * @return - An array of SVGRects (pillars)
     */
    public SVGRect[] pillars(int numPillars, SVGRect[] supportBars) {
        int distBetweenRafters = carportMapper.distBetweenRafters(carportMapper.numRafters());
        SVGRect[] pillars = new SVGRect[numPillars];

        SVGRect topBar = supportBars[0];
        SVGRect bottomBar = supportBars[1];

        int xOffset = topBar.getX();
        int whPillar = topBar.getH();
        int xFirstPair = xOffset + distBetweenRafters;
        int xLastPair = xOffset + topBar.getW() - distBetweenRafters - whPillar;
        int yTopRow = topBar.getY();
        int yBottomRow = bottomBar.getY();

        // Place the first and last pairs of pillars a fixed length
        // from the end of the carport
        pillars[0] = pillar(xFirstPair, yTopRow, whPillar);
        pillars[1] = pillar(xFirstPair, yBottomRow, whPillar);

        pillars[numPillars-2] = pillar(xLastPair, yTopRow, whPillar);
        pillars[numPillars-1] = pillar(xLastPair, yBottomRow, whPillar);

        if (numPillars <= 4) {
            return pillars;
        }

        // Place the central pairs of pillars a fixed length from each other
        int distFirstToLastPair = xLastPair - xFirstPair;

        int remainingPillars = numPillars - 4;
        double deltaXPct = breakpointPct(numPillars);
        int spacing = pctToUnits(deltaXPct, distFirstToLastPair);

        // Iterate over the pairs of pillars that need to be placed dynamically
        for (int i = 0; i < remainingPillars / 2; i++) {
            int index = i * 2 + 2;

            int x = xFirstPair + spacing + spacing * i;
            pillars[index] = pillar(x, topBar.getY(), whPillar);
            pillars[index + 1] = pillar(x, bottomBar.getY(), whPillar);
        }
        return pillars;
    }

    /** A pillar has the same width and height seen from above */
    private SVGRect pillar(int x, int y, int wh) {
        return new SVGRect.Builder(x, y, wh, wh)
                .fill("#D3D3D3")
                .build();
    }

    public static double breakpointPct(int numPillars) throws ArithmeticException {
        if (numPillars <= 4 ||
            numPillars % 2 != 0) {
            // Consider not throwing this. It's a runtime exception
            throw new ArithmeticException("Invalid number of pillars: " + numPillars);
        }
        int pairsOfPillars = numPillars / 2;
        double percentage = 100;
        return percentage / (pairsOfPillars - 1);
    }

    public static int pctToUnits(double pct, int totalUnits) throws ArithmeticException {
        if (pct == 0) {
            throw new ArithmeticException("pct can't be zero as it is used as a divisor");
        }
        double notRounded = (double) totalUnits / 100.0 * pct;
        return (int) Math.round(notRounded);
    }
}
