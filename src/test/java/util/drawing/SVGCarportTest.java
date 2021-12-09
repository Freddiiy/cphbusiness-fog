package util.drawing;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SVGCarportTest {

    @Test
    public void testBreakpoint() {
        System.out.println(SVGCarport.breakpoint(6));
        System.out.println(SVGCarport.breakpoint(8));

        double breakpoint = SVGCarport.breakpoint(8);
        System.out.println(SVGCarport.pctToUnits(breakpoint, 120));
    }
}