package util.drawing;

import util.Geometry;
import util.shapes.Line;

public class SVGMeasurementGuide extends SVGElement {

    // Obligatory instance variables
    private final Line line;
    private final String text;

    private final int MARKER_WIDTH = 10;
    private static final int NORMAL_LENGTH = 30;

    public static class Builder extends SVGElement.Builder<Builder> {
        private final Line line;
        private String text;

        public Builder(int x1, int y1, int x2, int y2) {
            line = new Line(x1, y1, x2, y2);
        }

        @Override
        public SVGMeasurementGuide build() {
            return new SVGMeasurementGuide(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        /** Adds text to the SVGElement. Pass "auto" for the will to live for five minutes longer. */
        public Builder text(String text) {
            this.text = text;
            if (text.equals("auto")) {
                this.text = String.format(
                        "%.2f",
                        Geometry.distBetweenPoints(
                                line.getX1(), line.getY1(),
                                line.getX2(), line.getY2()) / 100);
            }
            return this;
        }
    }

    private SVGMeasurementGuide(Builder builder) {
        super(builder);
        line = builder.line;
        text = builder.text;
    }

    public static SVGMeasurementGuide forSupportBars(SVGRect[] supportBars) {
        int x = supportBars[0].getX() - spaceNeeded();
        int y1 = supportBars[0].getY();
        int y2 = supportBars[1].getY() + supportBars[1].getW();
        return new Builder(x, y1, x, y2)
                .text("auto")
                .build();
    }

    public static SVGMeasurementGuide forCarportHeight(SVGRect carportRect, SVGMeasurementGuide supportBarsGuide) {
        int x = supportBarsGuide.getX1() - SVGMeasurementGuide.spaceNeeded();
        int y1 = carportRect.getY();
        int y2 = carportRect.getY() + carportRect.getW();
        return new SVGMeasurementGuide.Builder(x, y1, x, y2)
                .text("auto")
                .build();
    }

    public static SVGMeasurementGuide[] forRafters(SVGRect[] rafters) {
        SVGMeasurementGuide[] guides = new SVGMeasurementGuide[rafters.length - 1];

        int y = rafters[0].getY() - spaceNeeded();
        for (int i = 1; i < rafters.length; i++) {
            int x1 = rafters[i-1].getX() + rafters[i-1].getL();
            int x2 = rafters[i].getX();
            guides[i-1] = new SVGMeasurementGuide.Builder(x1, y, x2, y)
                    .text("auto")
                    .build();
        }
        return guides;
    }

    /**
     * Finds the space needed for a measurement guide on the short axis.
     * @return The space needed on the y-axis if the guide is horizontal,
     *          on the x-axis if the guide is vertical.
     */
    public static int spaceNeeded() {
        int margin = 10;
        return margin + NORMAL_LENGTH;
    }

    @Override
    public String toString() {
        if (lineIsHorizontal()) {
            return horizontalGuide();
        }
        return verticalGuide();
    }

    private boolean lineIsHorizontal() {
        int x1 = line.getX1();
        int y1 = line.getY1();
        int x2 = line.getX2();
        int y2 = line.getY2();
        if (y1 == y2 && x1 != x2) return true;
        if (x1 == x2 && y1 != y2) return false;

        System.out.println("WARNING: We're not equipped to draw skewed lines. See SVG.lineIsHorizontal()");
        int xDiff = x2 - x1;
        int yDiff = y2 - y1;
        return xDiff > yDiff;
    }

    private String horizontalGuide() {
        SVGLine guide = new SVGLine.Builder(
                line.getX1() + MARKER_WIDTH,
                line.getY1(),
                line.getX2() - MARKER_WIDTH,
                line.getY2())
                .attr("marker-start", "url(#startarrow)")
                .attr("marker-end", "url(#endarrow)")
                .build();

        String lines = guide.toString() + normals();//normalStart + normalEnd;
        if (text != null) {
            return lines + textForHorizontalGuide();
        }
        return lines;
    }

    private String verticalGuide() {
        SVGLine guide = new SVGLine.Builder(
                line.getX1(),
                line.getY1() + MARKER_WIDTH,
                line.getX2(),
                line.getY2() - MARKER_WIDTH)
                .attr("marker-start", "url(#startarrow)")
                .attr("marker-end", "url(#endarrow)")
                .build();

        String lines = guide.toString() + normals(); // normalStart + normalEnd;
        if (text != null) {
            return lines + textForVerticalGuide();
        }
        return lines;
    }

    private String textForHorizontalGuide() {
        if (text == null) {
            return null;
        }
        int xText = line.getX1() + (line.getX2() - line.getX1()) / 2;
        int yText = line.getY1() - 10;
        SVGText svgText = new SVGText.Builder(xText, yText, text)
                .attr("text-anchor", "middle")
                .build();
        return svgText.toString();
    }

    private String textForVerticalGuide() {
        if (text == null) {
            return null;
        }
        int xText = line.getX1() - MARKER_WIDTH;
        int yText = line.getY1() + (line.getY2() - line.getY1()) / 2;
        SVGText svgText = new SVGText.Builder(xText, yText, text)
                .attr("text-anchor", "end")
                .attr("dominant-baseline", "middle")
                .build();
        return svgText.toString();
    }

    /** Draw the normals that are placed at each end of the measurement guide */
    private String normals() {
        int x1 = line.getX1();
        int y1 = line.getY1();
        int x2 = line.getX2();
        int y2 = line.getY2();

        SVGLine normalStart;
        SVGLine normalEnd;
        int x1start, y1start;
        int x2start, y2start;
        int x1end, y1end;
        int x2end, y2end;

        if (lineIsHorizontal()) {
            x1start = x1;
            y1start = y1 - NORMAL_LENGTH / 2;
            x2start = x1;
            y2start = y2 + NORMAL_LENGTH / 2;
            x1end = x2;
            y1end = y1 - NORMAL_LENGTH / 2;
            x2end = x2;
            y2end = y2 + NORMAL_LENGTH / 2;
        } else {
            x1start = x1 - NORMAL_LENGTH / 2;
            y1start = y1;
            x2start = x2 + NORMAL_LENGTH / 2;
            y2start = y1;
            x1end = x1 - NORMAL_LENGTH / 2;
            y1end = y2;
            x2end = x2 + NORMAL_LENGTH / 2;
            y2end = y2;
        }

        // Create both normals
        normalStart = new SVGLine.Builder(x1start, y1start, x2start, y2start).build();
        normalEnd = new SVGLine.Builder(x1end, y1end, x2end, y2end).build();

        return normalStart.toString() + normalEnd.toString();
    }

    public int getX1() {
        return line.getX1();
    }

    public int getY1() {
        return line.getY1();
    }

    public int getX2() {
        return line.getX2();
    }

    public int getY2() {
        return line.getY2();
    }
}
