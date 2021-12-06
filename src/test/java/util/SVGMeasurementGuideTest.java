package util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SVGMeasurementGuideTest {

    @Test
    void distBetweenPoints() {
        double errorMargin = 0.000001;

        assertEquals(
                SVGMeasurementGuide.distBetweenPoints(-7, -4, 17, 6),
                26
        );
        assertEquals(
                SVGMeasurementGuide.distBetweenPoints(9, 5, 18, 2),
                9.486833,
                errorMargin
        );
    }

}