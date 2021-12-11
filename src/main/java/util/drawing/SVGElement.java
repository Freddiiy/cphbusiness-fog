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
 *
 * Call toString() to get the SVG element in markup.
 */
public abstract class SVGElement {

    private final HashMap<String, String> attributes;

    abstract static class Builder<T extends Builder<T>> {
        HashMap<String, String> attributes = new HashMap<>();

        /**
         * Add an attribute to the element as a key-value pair.
         */
        public T attr(String key, String value) {
            this.attributes.put(key, value);
            return self();
        }

        /**
         * The final step of object instantiation with the builder pattern.
         * Any time you want to use a builder, you finalize it by calling build() on it.
         * This should call the private constructor of the class implementing this method.
         * Validation also goes in this method.
         */
        abstract SVGElement build();

        /**
         * A way for children to return an object of their own type,
         * rather than having to return an abstract object
         * of type SVGElement
         * @return this, where this is the type of the child implementing this method
         */
        protected abstract T self();
    }

    SVGElement(Builder<?> builder) {
        this.attributes = builder.attributes;
    }

    /**
     * @return a string of all the key-value pairs added to this element.
     */
    public String attributePairs() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            sb.append(attributePairToString(entry));
        }
        return sb.toString();
    }

    /**
     * Turns a key-value pair into a string that can be used
     * within an SVG element.
     */
    private String attributePairToString(Map.Entry<String, String> keyValuePair) {
        return String.format("%s = \"%s\"\n\t",
                keyValuePair.getKey(),
                keyValuePair.getValue());
    }

    /* Getters */

    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}
