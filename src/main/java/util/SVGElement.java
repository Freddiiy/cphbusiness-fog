package util;

import java.util.HashMap;
import java.util.Map;

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
        return String.format("%s: %s\n\t",
                keyValuePair.getKey(),
                keyValuePair.getValue());
    }

    /* Getters */

    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}
