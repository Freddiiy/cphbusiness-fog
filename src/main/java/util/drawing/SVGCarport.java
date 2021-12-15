package util.drawing;

import mapper.CarportMapper;
import util.Carport.Construction;
import util.shapes.Line;
import util.shapes.Rect;

import java.util.Arrays;

public class SVGCarport {

    private final Construction construction;

    private final CarportMapper carportMapper;
    private final SVGRect carportRect;
    private int wViewport = 0;
    private int hViewport = 0;
    
    public SVGCarport(int LENGTH, int WIDTH) {
        construction = new Construction(LENGTH, WIDTH);

        carportMapper = new CarportMapper(LENGTH, WIDTH);

        // todo: Find x and y
        int xCarport = 140;
        int yCarport = 80;
        carportRect = new SVGRect.Builder(xCarport, yCarport, LENGTH, WIDTH).build();
    }

    public SVGCarport(int LENGTH, int WIDTH, int wViewport, int hViewport) {
        construction = new Construction(LENGTH, WIDTH);

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
        SVGLine[] holeTape = holeTape(rafters, supportBars);

        // Measurement guides
        SVGMeasurementGuide supportBarsGuide = SVGMeasurementGuide.forSupportBars(supportBars);
        SVGMeasurementGuide hCarportGuide = SVGMeasurementGuide.forCarportHeight(carportRect, supportBarsGuide);
        SVGMeasurementGuide[] rafterGuides = SVGMeasurementGuide.forRafters(rafters);

        SVG svg = new SVG(viewport(), viewBox());

        svg.addElement(carportRect);
        svg.addElements(supportBarsGuide, hCarportGuide);
        svg.addElements(supportBars, rafters, pillars, holeTape, rafterGuides);
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
                + carportRect.getL()
                + SVGMeasurementGuide.spaceNeeded() * 2;
        int hTotal = carportRect.getY()
                + carportRect.getW()
                + SVGMeasurementGuide.spaceNeeded();
        return SVGRect.of(0, 0, wTotal, hTotal);
    }

    private SVGRect[] supportBars() {
        Rect[] supportBarRects = construction.getSupportBars();
        SVGRect upperSupportBar = new SVGRect.Builder(supportBarRects[0]).build();
        SVGRect lowerSupportBar = new SVGRect.Builder(supportBarRects[1]).build();
        return new SVGRect[] { upperSupportBar, lowerSupportBar };
    }

    private SVGRect[] rafters() {
        Rect[] rafterRects = construction.getRafters();
        SVGRect[] rafters = new SVGRect[rafterRects.length];
        for (int i = 0; i < rafterRects.length; i++) {
            rafters[i] = new SVGRect.Builder(rafterRects[i]).build();
        }

        return rafters;
    }

    public SVGRect[] pillars(int numPillars, SVGRect[] supportBars) {
        Rect[] pillarRects = construction.getPillars();
        SVGRect[] pillars = new SVGRect[pillarRects.length];
        for (int i = 0; i < pillarRects.length; i++) {
            pillars[i] = new SVGRect.Builder(pillarRects[i])
                    .fill("#D3D3D3")
                    .build();
        }
        return pillars;
    }

    private SVGLine[] holeTape(SVGRect[] rafters, SVGRect[] supportBars) {
        Line[] holeTapeLines = construction.getHoleTape();
        return new SVGLine[] {
                new SVGLine.Builder(holeTapeLines[0])
                        .attr("stroke-width", "3")
                        .attr("stroke-dasharray", "10 4")
                        .build(),
                new SVGLine.Builder(holeTapeLines[1])
                        .attr("stroke-width", "3")
                        .attr("stroke-dasharray", "10 4")
                        .build()
        };
    }

    /**
     * Two red lines, vertical and horizontal, to make development easier
     * while trying to center something within a rectangle.
     * @param rect Within this rectangle, the lines will be centered
     *             vertically and horizontally respectively.
     * @return Two red lines.
     */
    private SVGLine[] centerGuides(SVGRect rect) {
        int xCenter = rect.getX() + rect.getL() / 2;
        int yCenter = rect.getY() + rect.getW() / 2;

        SVGLine verticalLine = new SVGLine.Builder(
                xCenter,
                rect.getY(),
                xCenter,
                rect.getY() + rect.getW())
                .stroke("#ff0000")
                .build();

        SVGLine horizontalLine = new SVGLine.Builder(
                rect.getX(),
                yCenter,
                rect.getX() + rect.getL(),
                yCenter)
                .stroke("#ff0000")
                .build();
        return new SVGLine[] { verticalLine, horizontalLine };
    }
}
