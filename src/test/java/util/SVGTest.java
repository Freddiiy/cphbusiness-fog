package util;

import org.junit.jupiter.api.Test;
import util.drawing.svg.basicshapes.Rect;

import static org.junit.jupiter.api.Assertions.*;

class SVGTest {

    @Test
    void lineIsHorizontal() {
        int x = 0, y = 0;
        int w = 1000, h = 1000;
        SVG svg = new SVG(x, y, new Rect(x, y, w, h), w, h);

        assertTrue(svg.lineIsHorizontal(40, 10, 50, 10));
        assertFalse(svg.lineIsHorizontal(40, 10, 40, 90));

        assertTrue(svg.lineIsHorizontal(10, 10, 50, 20));
        assertFalse(svg.lineIsHorizontal(10, 20, 20, 31));
    }
}