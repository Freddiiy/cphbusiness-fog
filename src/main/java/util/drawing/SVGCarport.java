package util.drawing;

import mapper.CarportMapper;
import util.drawing.svg.basicshapes.Rect;

public class SVGCarport {
    
    private final CarportMapper carportMapper;
    private final SVGRect carportRect;
    
    public SVGCarport(int LENGTH, int WIDTH) {
        carportMapper = new CarportMapper(LENGTH, WIDTH);

        // todo: Find x and y
        int xCarport = 140;
        int yCarport = 60;
        carportRect = new SVGRect.Builder(xCarport, yCarport, LENGTH, WIDTH).build();
    }

    public String toString() {
        SVGRect[] supportBars = supportBars();
        SVGRect[] rafters = rafters();
        SVGRect[] pillars = pillars(supportBars[0], supportBars[1]);

        int x = 0, y = 0;
        int w = 1000, h = 1000;
        SVG svg = new SVG(x, y, new Rect(x, y, w, h), w, h);

        // Measurement guides
        SVGMeasurementGuide supportBarsGuide = SVGMeasurementGuide.forSupportBars(supportBars);
        SVGMeasurementGuide hCarportGuide = SVGMeasurementGuide.forCarportHeight(carportRect, supportBarsGuide);
        SVGMeasurementGuide[] rafterGuides = SVGMeasurementGuide.forRafters(rafters);

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

    private SVGRect[] pillars(SVGRect upperSupportBar, SVGRect lowerSupportBar) {
        // Note to self: I think the pillars might actually be placed correctly,
        // but it's the rafters that need to be pushed to the right a little.
        int numPillars = carportMapper.numPillars();

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
