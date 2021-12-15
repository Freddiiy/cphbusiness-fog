package util.drawing;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SVGCarportTest {

    @Test
    public void testBreakpoint() {
        double errorMargin = 0.000001;

        assertThrows(ArithmeticException.class, () -> {
            SVGCarport.breakpointPct(4);
            SVGCarport.breakpointPct(5);
            SVGCarport.breakpointPct(7);
        });
        assertEquals(SVGCarport.breakpointPct(6), 50);
        assertEquals(SVGCarport.breakpointPct(8), 33.333333, errorMargin);
        assertEquals(SVGCarport.breakpointPct(10), 25);
        assertEquals(SVGCarport.breakpointPct(12), 20);
    }
}