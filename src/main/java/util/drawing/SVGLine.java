package util.drawing;

import util.shapes.Line;

public class SVGLine extends SVGElement {

    private final Line line;

    public static class Builder extends SVGElement.Builder<Builder> {
        private final Line line;

        public Builder(int x1, int y1, int x2, int y2) {
            line = new Line(x1, y1, x2, y2);
        }

        public Builder(Line line) {
            this.line = line;
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
        line = builder.line;
    }

    @Override
    public String toString() {
        String template = "" +
                "<line\n" +
                "    x1=\"%d\"\n" +
                "    y1=\"%d\"\n" +
                "    x2=\"%d\"\n" +
                "    y2=\"%d\"\n" +
                "    %s" +
                "></line>";
        return String.format(
                template,
                line.getX1(), line.getY1(),
                line.getX2(), line.getY2(),
                attributePairs()
        );
    }
}
