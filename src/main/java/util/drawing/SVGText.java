package util.drawing;

public class SVGText extends SVGElement {

    private final int x;
    private final int y;
    private final String text;

    public static class Builder extends SVGElement.Builder<Builder> {
        private final int x;
        private final int y;
        private final String text;

        public Builder(int x, int y, String text) {
            this.x = x;
            this.y = y;
            this.text = text;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public SVGText build() {
            if (!attributes.containsKey("font-size")) {
                attr("font-size", "smaller");
            }
            return new SVGText(this);
        }

        public Builder stroke(String hex) {
            attr("stroke", hex);
            return this;
        }
    }

    private SVGText(Builder builder) {
        super(builder);
        x = builder.x;
        y = builder.y;
        text = builder.text;
    }

    @Override
    public String toString() {
        String template = "" +
                "<text " +
                "    x=\"%d\" " +
                "    y=\"%d\" " +
                "    %s" +
                ">%s</text>";

        return String.format(
                template,
                x, y,
                attributePairs(),
                text
        );
    }
}
