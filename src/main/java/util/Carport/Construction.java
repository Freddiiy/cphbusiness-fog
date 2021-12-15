package util.Carport;

import mapper.CarportMapper;
import util.shapes.Line;
import util.shapes.Rect;

/**
 * This class will be used to place the items in a carport in the correct places.
 * Pillars, rafters, etc. will be placed according to a set of rules
 * and can be used for finding prices and bills of materials in util.Carport.CarportCalc
 * and for drawing SVGs in util.drawing.SVGCarport, respectively.
 */
public class Construction {

    private final CarportMapper carportMapper;
    private final Rect carportRect;

    private final Rect[] supportBars;
    private final Rect[] rafters;
    private final Rect[] pillars;
    private final Line[] holeTape;

    public Construction(int length, int width) {
        carportMapper = new CarportMapper(length, width);
        carportRect = new Rect(140, 80, length, width);

        supportBars = supportBars();
        rafters = rafters();
        pillars = pillars(supportBars);
        holeTape = holeTape(rafters, supportBars);
    }

    /**
     * The horizontal planks on which the pillars are attached below
     * and the rafters above.
     * It's based on the size of the carport.
     * @return an array of support bars.
     */
    private Rect[] supportBars() {
        // Support bars (name?). The horizontal thingy the pillars are attached to.
        // Place 35 cm. from the edge of the carport.
        int distEdgeToPillar = 35;
        int yDeltaPillarsOuter = carportRect.getW() - 2 * distEdgeToPillar;
        int hSupportBar = 20;
        int yUpper = carportRect.getY() + (carportRect.getW() - yDeltaPillarsOuter) / 2;
        Rect upperSupportBar = new Rect(
                carportRect.getX(),
                yUpper,
                carportRect.getL(),
                hSupportBar);
        Rect lowerSupportBar = new Rect(
                carportRect.getX(),
                yUpper + yDeltaPillarsOuter - hSupportBar,
                carportRect.getL(),
                hSupportBar);
        return new Rect[] { upperSupportBar, lowerSupportBar };
    }

    /** Create an array of rafters based on the size of the carport. */
    private Rect[] rafters() {
        int xRafter;
        int lRafter = 5; // pretty arbitrary number
        int numRafters = carportMapper.numRafters();

        // Push rafters slightly to the right
        int lCarport = carportRect.getL();
        int xLastRafter = xForRafter(
                carportRect.getX(), lRafter, numRafters - 1, numRafters) - carportRect.getX();
        int emptySpaceRight = lCarport - xLastRafter;
        int xOffset = emptySpaceRight / 2;

        Rect[] rafters = new Rect[numRafters];
        for (int i = 0; i < numRafters; i++) {
            xRafter = xForRafter(carportRect.getX(), lRafter, i, numRafters) + xOffset;

            rafters[i] = new Rect(
                    xRafter - lRafter / 2,
                    carportRect.getY(),
                    lRafter,
                    carportRect.getW());
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
     * @param supportBars The two support bars in the carport
     * @return An array of pillars
     */
    public Rect[] pillars(Rect[] supportBars) {
        int numPillars = carportMapper.numPillars();
        int distBetweenRafters = carportMapper.distBetweenRafters(carportMapper.numRafters());
        // SVGRect[] pillars = new SVGRect[numPillars];
        Rect[] pillars = new Rect[numPillars];

        Rect topBar = supportBars[0];
        Rect bottomBar = supportBars[1];

        int xOffset = topBar.getX();
        int lwPillar = topBar.getW();
        int xFirstPair = xOffset + distBetweenRafters;
        int xLastPair = xOffset + topBar.getL() - distBetweenRafters - lwPillar;
        int yTopRow = topBar.getY();
        int yBottomRow = bottomBar.getY();

        // Place the first and last pairs of pillars a fixed length
        // from the end of the carport
        pillars[0] = pillar(xFirstPair, yTopRow, lwPillar);
        pillars[1] = pillar(xFirstPair, yBottomRow, lwPillar);

        pillars[numPillars-2] = pillar(xLastPair, yTopRow, lwPillar);
        pillars[numPillars-1] = pillar(xLastPair, yBottomRow, lwPillar);

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
            pillars[index] = pillar(x, topBar.getY(), lwPillar);
            pillars[index + 1] = pillar(x, bottomBar.getY(), lwPillar);
        }
        return pillars;
    }

    /**
     * Helper method for quickly building a pillar.
     * @param x Left side of the pillar
     * @param y Right side of the pillar
     * @param lw Length and width of the pillar
     * @return a pillar
     */
    private Rect pillar(int x, int y, int lw) {
        return new Rect(x, y, lw, lw);
    }

    private Line[] holeTape(Rect[] rafters, Rect[] supportBars) {
        // Assumes there is no shed. todo: don't assume this
        int last = rafters.length - 1;
        int x1 = rafters[0].getX();
        int y1 = supportBars[0].getY();
        int x2 = rafters[last].getX() + rafters[last].getL();
        int y2 = supportBars[1].getY() + supportBars[1].getW();
        int x3 = x1;
        int y3 = y2;
        int x4 = x2;
        int y4 = y1;
        return new Line[] {
                new Line(x1, y1, x2, y2),
                new Line(x3, y3, x4, y4)
        };
    }

    /**
     * Ignores the left and right most pairs of pillars (as seen from above),
     * to look at the pairs between them and find a percentage
     * representing the distance they should have between each other
     * on the x-axis.
     * In a carport, the pair of pillars at either end should be placed
     * a fixed distance from the end of the carport.
     * However, if the carport is longer and needs more pillars,
     * those should be spaced out evenly.
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

    /* Getters */

    public Rect[] getSupportBars() {
        return supportBars;
    }

    public Rect[] getRafters() {
        return rafters;
    }

    public Rect[] getPillars() {
        return pillars;
    }

    public Line[] getHoleTape() {
        return holeTape;
    }
}
