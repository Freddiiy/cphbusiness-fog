package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SVGElementTest {

    @Test
    void build() {
        SVGRect rect = new SVGRect.Builder(10, 10, 10, 10)
                .attr("fill", "#ddd")
                .attr("stroke-weight", "4")
                .build();
        System.out.println(rect.getAttributes());
    }

}