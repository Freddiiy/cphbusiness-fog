package util.drawing;

import util.shapes.Rect;

import java.util.HashMap;

public class SVGRect extends SVGElement {

    private final Rect rect;

    public static class Builder extends SVGElement.Builder<Builder> {
        private final Rect rect;

        public Builder(int x, int y, int l, int w) {
            rect = new Rect(x, y, l, w);
        }

        public Builder(Rect rect) {
            this.rect = rect;
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
        rect = builder.rect;
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
                rect.getX(), rect.getY(), rect.getL(), rect.getW(),
                attributePairs()
        );
    }

    public static SVGRect of(int x, int y, int l, int w) {
        return new SVGRect.Builder(x, y, l, w).build();
    }

    /* Getters */

    public HashMap<String, String> getAttributes() {
        return getAttributes();
    }

    public int getX() {
        return rect.getX();
    }

    public int getY() {
        return rect.getY();
    }

    public int getL() {
        return rect.getL();
    }

    public int getW() {
        return rect.getW();
    }
}
