package util;

import org.testng.annotations.Test;
import web.TestSVG;

import static org.testng.Assert.*;

public class RoundingTest {

    @Test
    void round() {
        assertEquals(Math.round(2.4), 2);
        assertEquals(Math.round(2.5), 3);
        assertEquals(Math.round(2.6), 3);
    }

    @Test
    void pctToUnits() {
        // Should round correctly
        assertEquals(TestSVG.pctToUnits(25, 109), 27);
        assertEquals(TestSVG.pctToUnits(31, 177), 55);
    }

}