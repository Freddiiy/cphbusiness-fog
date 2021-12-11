package util.drawing;

import mapper.CarportMapper;

/**
 * A carport SVG as seen from above.
 * Since it is seen from above, any time x, y, width or height is mentioned,
 * it is referring to the actual width on the screen.
 * For example, a pillar seen from above has a height.
 * If you were standing up, looking at the carport from its side in real life,
 * that height would its depth. Likewise, its height in real life doesn't exist in the drawing,
 * since we have mapped something in three dimensions to two dimensions.
 */
public class SVGCarport {
    
    private final CarportMapper carportMapper;
    private final SVGRect carportRect;
    private int wViewport = 0;
    private int hViewport = 0;
    
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
        int yCarport = 80;
        carportRect = new SVGRect.Builder(xCarport, yCarport, LENGTH, WIDTH).build();
    }

    public String toString() {

        SVGRect[] supportBars = supportBars();
        SVGRect[] rafters = rafters();
        SVGRect[] pillars = pillars(carportMapper.numPillars(), supportBars);

        // Measurement guides
        SVGMeasurementGuide supportBarsGuide = SVGMeasurementGuide.forSupportBars(supportBars);
        SVGMeasurementGuide hCarportGuide = SVGMeasurementGuide.forCarportHeight(carportRect, supportBarsGuide);
        SVGMeasurementGuide[] rafterGuides = SVGMeasurementGuide.forRafters(rafters);

        SVG svg = new SVG(viewport(), viewBox());

        svg.addElement(carportRect);
        svg.addElements(supportBarsGuide, hCarportGuide);
        svg.addElements(supportBars, rafters, pillars, rafterGuides);
        return svg.toString();
    }

    /**
     * Creates a viewport with x and y set to 0
     * and width and height to a fixed length
     * unless otherwise specified with a constructor.     *
     * @return The viewport rectangle.
     */
    private SVGRect viewport() {
        int x = 0, y = 0;
        int w = 1000, h = 1000;
        if (wViewport != 0) {
            w = wViewport;
        }
        if (hViewport != 0) {
            h = hViewport;
        }
        return SVGRect.of(x, y, w, h);
    }

    private SVGRect viewBox() {
        // Find the total width and height of the drawing
        int wTotal = carportRect.getX()
                + carportRect.getW()
                + SVGMeasurementGuide.spaceNeeded() * 2;
        int hTotal = carportRect.getY()
                + carportRect.getH()
                + SVGMeasurementGuide.spaceNeeded();
        return SVGRect.of(0, 0, wTotal, hTotal);
    }

    /**
     * The horizontal planks on which the pillars are attached below
     * and the rafters above.
     * It's based on the size of the carport.
     * @return an array of support bars.
     */
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

    /**
     * Create an array of rafters based on the size of the carport.
     * Rafters are the numerous vertical planks that are there to
     * hold the roof.
     * @return the rafters.
     */
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

    /**
     * Finds the x position of a rafter.
     * @param xOffset This offset should stay the same, no matter the value of i
     * @param wRafter The width of a rafter
     * @param i The index from your for loop
     * @param numRafters The total number of rafters
     * @return the x position found
     */
    private int xForRafter(int xOffset, int wRafter, int i, int numRafters) {
        return xOffset
                + wRafter / 2
                + i * carportMapper.distBetweenRafters(numRafters);
    }

    /**
     * Places the first and last pair of pillars statically, and the extra pairs
     * of pillars between them dynamically (spaced out evenly).
     * @param numPillars The number of pillars in the carport
     * @param supportBars The two support bars in the carport
     * @return An array of SVGRects (pillars)
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

    /**
     * Helper method for quickly building a pillar.
     * @param x Left side of the pillar
     * @param y Right side of the pillar
     * @param wh Width and height of the pillar
     * @return a pillar
     */
    private SVGRect pillar(int x, int y, int wh) {
        return new SVGRect.Builder(x, y, wh, wh)
                .fill("#D3D3D3")
                .build();
    }

    /**
     * Ignores the left and right most pairs of pillars,
     * to look at the pairs between them and find a percentage
     * representing the distance they should have between each other
     * on the x-axis.
     * In a carport, the pair of pillars at either end should be placed
     * a fixed distance from the end of the carport.
     * However, if the carport is longer and needs more pillars,
     * those should be placed with an equal width between them.
     *
     * @param numPillars The total number of pillars for a given carport.
     * @return A percentage describing the x distance between two (pairs of) pillars.
     * @throws ArithmeticException if the number of pillars is less than 4
     *          or is not divisible by 2
     */
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

    /**
     * Convert a percentage to a number of units.
     * @throws ArithmeticException if pct == 0 (division by 0)
     */
    public static int pctToUnits(double pct, int totalUnits) throws ArithmeticException {
        if (pct == 0) {
            throw new ArithmeticException("pct can't be zero as it is used as a divisor");
        }
        double notRounded = (double) totalUnits / 100.0 * pct;
        return (int) Math.round(notRounded);
    }

    /**
     * Two red lines, vertical and horizontal, to make development easier
     * while trying to center something within a rectangle.
     * @param rect Within this rectangle, the lines will be centered
     *             vertically and horizontally respectively.
     * @return Two red lines.
     */
    private SVGLine[] centerGuides(SVGRect rect) {
        int xCenter = rect.getX() + rect.getW() / 2;
        int yCenter = rect.getY() + rect.getH() / 2;

        SVGLine verticalLine = new SVGLine.Builder(
                xCenter,
                rect.getY(),
                xCenter,
                rect.getY() + rect.getH())
                .stroke("#ff0000")
                .build();

        SVGLine horizontalLine = new SVGLine.Builder(
                rect.getX(),
                yCenter,
                rect.getX() + rect.getW(),
                yCenter)
                .stroke("#ff0000")
                .build();
        return new SVGLine[] { verticalLine, horizontalLine };
    }
}
