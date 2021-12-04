package util;

import java.util.HashMap;

public abstract class SVGElement {

    private final HashMap<String, String> attributes;

    abstract static class Builder<T extends Builder<T>> {
        HashMap<String, String> attributes = new HashMap<>();
        public T attr(String key, String value) {
            this.attributes.put(key, value);
            return self();
        }

        abstract SVGElement build();

        protected abstract T self();
    }

    SVGElement(Builder<?> builder) {
        this.attributes = builder.attributes;
    }

    /* Getters */

    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}
