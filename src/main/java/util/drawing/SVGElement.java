package util.drawing;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for SVG Elements.
 * It uses the builder pattern
 * ("Effective Java" - Chapter 2, item 2 - "Consider a builder
 * when faced with many constructor parameters).
 * The builder pattern also promotes immutability.
 *
 * Usage:
 *
 * Put obligatory attributes in the concrete class
 * and optional attributes in this.
 * Any variables that are not svg attributes go in the
 * concrete class as well.
 * Any validation goes in the implementation of build()
 */
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

    public String attributePairs() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            sb.append(attributePair(entry));
        }
        return sb.toString();
    }

    private String attributePair(Map.Entry<String, String> keyValuePair) {
        return String.format("%s = \"%s\"\n\t",
                keyValuePair.getKey(),
                keyValuePair.getValue());
    }

    /* Getters */

    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}
