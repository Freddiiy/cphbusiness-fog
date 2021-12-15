package util.drawing;

import java.util.HashMap;

public class SVGRect extends SVGElement {

    private final int x;
    private final int y;
    private final int w;
    private final int h;

    public static class Builder extends SVGElement.Builder<Builder> {
        private final int x;
        private final int y;
        private final int w;
        private final int h;

        public Builder(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        @Override
        public SVGRect build() {
            // Set defaults
            if (!attributes.containsKey("stroke")) {
                attr("stroke", "#000");
            }
            if (!attributes.containsKey("fill")) {
                attr("fill", "#fff");
            }
            return new SVGRect(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder stroke(String hex) {
            attr("stroke", hex);
            return this;
        }

        public Builder fill(String hex) {
            attr("fill", hex);
            return this;
        }
    }

    private SVGRect(Builder builder) {
        super(builder);
        x = builder.x;
        y = builder.y;
        w = builder.w;
        h = builder.h;
    }

    @Override
    public String toString() {
        String rectTemplate = "" +
                "<rect\n" +
                "    x=\"%d\"\n" +
                "    y=\"%d\"\n" +
                "    width=\"%d\"\n" +
                "    height=\"%d\"\n" +
                "    %s" +
                "/>";
        return String.format(
                rectTemplate,
                x, y, w, h,
                attributePairs()
        );
    }

    public static SVGRect of(int x, int y, int w, int h) {
        return new SVGRect.Builder(x, y, w, h).build();
    }

    /* Getters */

    public HashMap<String, String> getAttributes() {
        return getAttributes();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
