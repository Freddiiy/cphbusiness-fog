package util;

public class SVGMeasurementGuide extends SVGElement {

    // Obligatory instance variables
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final String text;

    private final int MARKER_WIDTH = 10;
    private final int LINE_GUIDE_LENGTH = 30;
    private final String LINE_TEMPLATE = "" +
            "<line\n" +
            "    x1=\"%d\"\n" +
            "    y1=\"%d\"\n" +
            "    x2=\"%d\"\n" +
            "    y2=\"%d\"\n" +
            "    %s" +                                  // other attributes
            "></line>";

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

        public Builder text(String text) {
            this.text = text;
            if (text.equals("auto")) {

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

        // Normals
        SVGLine normalStart = new SVGLine.Builder(
                x1,
                y1 - LINE_GUIDE_LENGTH / 2,
                x1,
                y2 + LINE_GUIDE_LENGTH / 2)
                .build();

        SVGLine normalEnd = new SVGLine.Builder(
                x2,
                y1 - LINE_GUIDE_LENGTH / 2,
                x2,
                y2 + LINE_GUIDE_LENGTH / 2)
                .build();

        String lines = line.toString() + normalStart + normalEnd;
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

        // Normals
        SVGLine normalStart = new SVGLine.Builder(
                x1 - LINE_GUIDE_LENGTH / 2,
                y1,
                x2 + LINE_GUIDE_LENGTH / 2,
                y1)
                .build();

        SVGLine normalEnd = new SVGLine.Builder(
                x1 - LINE_GUIDE_LENGTH / 2,
                y2,
                x2 + LINE_GUIDE_LENGTH / 2,
                y2)
                .build();

        String lines = line.toString() + normalStart + normalEnd;
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
}
