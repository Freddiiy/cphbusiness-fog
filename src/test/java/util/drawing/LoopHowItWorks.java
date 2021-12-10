package util.drawing;

import org.testng.annotations.Test;

public class LoopHowItWorks {

    @Test
    public int[] doIt(int numPillars) {
        int[] pillars = new int[numPillars];
        pillars[0] = 0;
        pillars[1] = 1;

        int distFirstToLastPair = 1000;
        int remainingPillars = numPillars - 4;
        double xPct = SVGCarport.breakpointPct(numPillars);
        int spacing = SVGCarport.pctToUnits(xPct, distFirstToLastPair);
        int[] spacings = new int[remainingPillars / 2];
        for (int i = 1; i < spacings.length + 1; i++) {
            spacings[i-1] = spacing * i;
        }

        for (int i = 0; i < remainingPillars / 2; i++) {
            int index = i * 2 + 2;
            // System.out.println("r: " + index);
            pillars[index] = spacing + spacing * i;
            pillars[index+1] = spacing + spacing * i;
        }


        /*
        int endLoop = remainingPillars + 2;
        System.out.println(endLoop);
        for (int i = 2; i < endLoop; i++) {
            int lol = (i - 2);
            pillars[i] = spacing * lol;
            pillars[i+1] = spacing * lol;
        }
        */

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
        int[] pillars10 = doIt(10);
        int[] pillars12 = doIt(12);

        printIt(pillars6);
        printIt(pillars8);
        printIt(pillars10);
        printIt(pillars12);
    }
}
