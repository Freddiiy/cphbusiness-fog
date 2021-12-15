package util.drawing;

import org.testng.annotations.Test;
import util.Carport.Construction;

import static org.testng.Assert.*;

public class SVGCarportTest {

    @Test
    public void testBreakpoint() {
        double errorMargin = 0.000001;

        assertThrows(ArithmeticException.class, () -> {
            Construction.breakpointPct(4);
            Construction.breakpointPct(5);
            Construction.breakpointPct(7);
        });
        assertEquals(Construction.breakpointPct(6), 50);
        assertEquals(Construction.breakpointPct(8), 33.333333, errorMargin);
        assertEquals(Construction.breakpointPct(10), 25);
        assertEquals(Construction.breakpointPct(12), 20);
    }
}