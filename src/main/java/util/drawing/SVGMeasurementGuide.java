package util.drawing;

public class SVGMeasurementGuide extends SVGElement {

    // Obligatory instance variables
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final String text;

    private final int MARKER_WIDTH = 10;
    private static final int NORMAL_LENGTH = 30;

    public static class Builder extends SVGElement.Builder<Builder> {
        private final int x1;
        private final int y1;
        private final int x2;
        private final int y2;
        private String text;

        public Builder(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
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
                this.text = String.format("%.2f", distBetweenPoints(x1, y1, x2, y2) / 100);
            }
            return this;
        }
    }

    private SVGMeasurementGuide(Builder builder) {
        super(builder);
        x1 = builder.x1;
        y1 = builder.y1;
        x2 = builder.x2;
        y2 = builder.y2;
        text = builder.text;
    }

    public static SVGMeasurementGuide forSupportBars(SVGRect[] supportBars) {
        return new Builder(
                supportBars[0].getX() - spaceNeeded(),
                supportBars[0].getY(),
                supportBars[1].getX() - spaceNeeded(),
                supportBars[1].getY() + supportBars[1].getH())
                .text("auto")
                .build();
    }

    public static SVGMeasurementGuide forCarportHeight(SVGRect carportRect, SVGMeasurementGuide supportBarsGuide) {
        return new SVGMeasurementGuide.Builder(
                supportBarsGuide.getX1() - SVGMeasurementGuide.spaceNeeded(),
                carportRect.getY(),
                supportBarsGuide.getX1() - SVGMeasurementGuide.spaceNeeded(),
                carportRect.getY() + carportRect.getH())
                .text("auto")
                .build();
    }

    public static SVGMeasurementGuide[] forRafters(SVGRect[] rafters) {
        int y = rafters[0].getY() - spaceNeeded();
        SVGMeasurementGuide[] guides = new SVGMeasurementGuide[rafters.length - 1];
        for (int i = 1; i < rafters.length; i++) {
            int x1 = rafters[i-1].getX() + rafters[i-1].getW();
            int x2 = rafters[i].getX();
            guides[i-1] = new SVGMeasurementGuide.Builder(x1, y, x2, y)
                    .text("auto")
                    .build();
        }
        return guides;
    }

    /** Can be used for x or y */
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
        if (y1 == y2 && x1 != x2) return true;
        if (x1 == x2 && y1 != y2) return false;

        System.out.println("WARNING: We're not equipped to draw skewed lines. See SVG.lineIsHorizontal()");
        int xDiff = x2 - x1;
        int yDiff = y2 - y1;
        return xDiff > yDiff;
    }

    private String horizontalGuide() {
        SVGLine line = new SVGLine.Builder(
                x1 + MARKER_WIDTH,
                y1,
                x2 - MARKER_WIDTH,
                y2)
                .attr("marker-start", "url(#startarrow)")
                .attr("marker-end", "url(#endarrow)")
                .build();

        String lines = line.toString() + normals();//normalStart + normalEnd;
        if (text != null) {
            return lines + textForHorizontalGuide();
        }
        return lines;
    }

    private String verticalGuide() {
        SVGLine line = new SVGLine.Builder(
                x1,
                y1 + MARKER_WIDTH,
                x2,
                y2 - MARKER_WIDTH)
                .attr("marker-start", "url(#startarrow)")
                .attr("marker-end", "url(#endarrow)")
                .build();

        String lines = line.toString() + normals(); // normalStart + normalEnd;
        if (text != null) {
            return lines + textForVerticalGuide();
        }
        return lines;
    }

    private String textForHorizontalGuide() {
        if (text == null) {
            return null;
        }
        int xText = x1 + (x2 - x1) / 2;
        int yText = y1 - 10;
        SVGText svgText = new SVGText.Builder(xText, yText, text)
                .attr("text-anchor", "middle")
                .build();
        return svgText.toString();
    }

    private String textForVerticalGuide() {
        if (text == null) {
            return null;
        }
        int xText = x1 - MARKER_WIDTH;
        int yText = y1 + (y2 - y1) / 2;
        SVGText svgText = new SVGText.Builder(xText, yText, text)
                .attr("text-anchor", "end")
                .attr("dominant-baseline", "middle")
                .build();
        return svgText.toString();
    }

    /** Draw the normals that are placed at each end of the measurement guide */
    private String normals() {
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

    public static double distBetweenPoints(int x1, int y1, int x2, int y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}
