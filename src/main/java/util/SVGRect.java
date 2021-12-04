package util;

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
            return new SVGRect(this);
        }

        @Override
        protected Builder self() {
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

    /* Getters */

    public HashMap<String, String> getAttributes() {
        return super.getAttributes();
    }
}
