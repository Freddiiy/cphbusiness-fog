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


        for (int i = 0; i < remainingPillars; i++) {
            int index = i + 2;
            // System.out.println("r: " + index);
            pillars[index] = index;
            pillars[index+1] = index+1;
        }

        pillars[numPillars-2] = numPillars - 2;
        pillars[numPillars-1] = numPillars - 1;
        return pillars;
    }

    @Test
    public void printIt(int[] pillars) {
        for (int i : pillars) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    @Test
    public void testIt() {
        int[] pillars6 = doIt(6);
        int[] pillars8 = doIt(8);

        printIt(pillars6);
        printIt(pillars8);
    }
}
