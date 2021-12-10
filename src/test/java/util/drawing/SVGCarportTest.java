package util.drawing;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SVGCarportTest {

    @Test
    public void testBreakpoint() {
        double errorMargin = 0.000001;

        assertThrows(ArithmeticException.class, () -> {
            SVGCarport.breakpoint(4);
            SVGCarport.breakpoint(5);
            SVGCarport.breakpoint(7);
        });
        assertEquals(SVGCarport.breakpoint(6), 50);
        assertEquals(SVGCarport.breakpoint(8), 25);
        assertEquals(SVGCarport.breakpoint(10), 16.666666, errorMargin);
    }
}