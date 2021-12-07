package util;

import org.testng.annotations.Test;
import util.drawing.SVGRect;

class SVGElementTest {

    @Test
    void build() {
        SVGRect rect = new SVGRect.Builder(10, 10, 10, 10)
                .attr("fill", "#ddd")
                .attr("stroke-weight", "4")
                .build();
        System.out.println(rect.getAttributes());
        System.out.println(rect);
    }
}