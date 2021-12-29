package controller;

import org.testng.annotations.Test;
import util.Carport.Construction;

import static org.testng.Assert.*;

public class CarportControllerTest {

    @Test
    void returnCarportPillarLightRoofNoShed() {
    }

    @Test
    void returnCarportRaftersLightRoofNoShed() {
    }

    @Test
    void calcMaterials() {
    }

    @Test
    void equations() {
        Construction construction = new Construction(780, 600);
        int numRafters = construction.numRafters();
        int distBetweenRafters = construction.distBetweenRafters(numRafters);
        System.out.printf("%d, %d\n", numRafters, distBetweenRafters);
    }

    @Test
    void numPillars() {
        Construction constr0 = new Construction(780, 600);
        Construction constr1 = new Construction(599, 600);
        Construction constr2 = new Construction(600, 600);
        Construction constr3 = new Construction(901, 600);
        Construction constr4 = new Construction(1201, 600);

        assertEquals(constr0.numPillars(), 6);
        assertEquals(constr1.numPillars(), 4);
        assertEquals(constr2.numPillars(), 4);
        assertEquals(constr3.numPillars(), 8);
        assertEquals(constr4.numPillars(), 10);
    }

    @Test
    void distBetweenRafters() {
        Construction constr0 = new Construction(780, 600);
        Construction constr1 = new Construction(478, 600);
        Construction constr2 = new Construction(199, 600);
        Construction constr3 = new Construction(980, 600);
        Construction constr4 = new Construction(123, 600);
        Construction constr5 = new Construction(71, 600);
        Construction constr6 = new Construction(1999, 600);
        Construction constr7 = new Construction(725, 600);

        assertTrue(constr0.distBetweenRafters(constr0.numRafters()) <= 60);
        assertTrue(constr1.distBetweenRafters(constr1.numRafters()) <= 60);
        assertTrue(constr2.distBetweenRafters(constr2.numRafters()) <= 60);
        assertTrue(constr3.distBetweenRafters(constr3.numRafters()) <= 60);
        assertTrue(constr4.distBetweenRafters(constr4.numRafters()) <= 60);
        assertTrue(constr5.distBetweenRafters(constr5.numRafters()) <= 60);
        assertTrue(constr6.distBetweenRafters(constr6.numRafters()) <= 60);
        assertTrue(constr7.distBetweenRafters(constr7.numRafters()) <= 60);
    }
}