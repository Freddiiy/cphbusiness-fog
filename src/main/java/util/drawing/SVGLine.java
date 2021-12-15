package util.drawing;

import util.Geometry;

public class SVGLine extends SVGElement {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    private final String TEMPLATE = "" +
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

        public Builder(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public SVGLine build() {
            if (!attributes.containsKey("stroke")) {
                stroke("#000");
            }
            return new SVGLine(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder stroke(String hex) {
            attr("stroke", hex);
            return this;
        }
    }

    private SVGLine(Builder builder) {
        super(builder);
        x1 = builder.x1;
        y1 = builder.y1;
        x2 = builder.x2;
        y2 = builder.y2;
    }

    @Override
    public String toString() {
        return String.format(
                TEMPLATE,
                x1, y1, x2, y2,
                attributePairs()
        );
    }

    public double length() {
        return Geometry.distBetweenPoints(x1, y1, x2, y2);
    }
}
