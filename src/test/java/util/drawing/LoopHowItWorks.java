package util.drawing;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoopHowItWorks {

    @Test
    public int[] doIt(int numPillars) {
        int[] pillars = new int[numPillars];
        pillars[0] = 0;
        pillars[1] = 1;

        int remainingPillars = numPillars - 4;
        double xPct = SVGCarport.breakpoint(numPillars);
        System.out.println(remainingPillars + ", " + xPct);

        pillars[numPillars-2] = 2;
        pillars[numPillars-1] = 3;
        return pillars;
    }

    @Test
    public void printIt(int[] pillars) {
        for (int i : pillars) {
            System.out.println(i);
        }
    }

    @Test
    public void testIt() {
        int[] pillars6 = doIt(6);

        printIt(pillars6);
    }
}
