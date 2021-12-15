package util.drawing;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
public class MatricesTest {

    double[][] m1 = {
            new double[] { 1, 2, 3 },
            new double[] { 4, 5, 6 },
    };
    double[][] m2 = {
            new double[] { 7, 8 },
            new double[] { 9, 10 },
            new double[] { 11, 12 }
    };
    double[][] expected = {
            new double[] { 58, 64 },
            new double[] { 139, 154 }
    };

    @Test
    public void testDotProduct() {
        assertEquals(Matrices.dotProduct(m1, m2), expected);
    }
}