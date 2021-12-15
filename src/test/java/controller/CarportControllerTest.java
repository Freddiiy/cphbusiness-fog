package controller;

import mapper.CarportMapper;
import mapper.MaterialMapper;
import model.Material;
import org.testng.annotations.Test;
import persistance.Database;

import java.util.HashMap;

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
        CarportMapper cm = new CarportMapper(780, 600);
        int numRafters = cm.numRafters();
        int distBetweenRafters = cm.distBetweenRafters(numRafters);
        System.out.printf("%d, %d\n", numRafters, distBetweenRafters);
    }

    @Test
    void numPillars() {
        CarportMapper cm0 = new CarportMapper(780, 600);
        CarportMapper cm1 = new CarportMapper(599, 600);
        CarportMapper cm2 = new CarportMapper(600, 600);
        CarportMapper cm3 = new CarportMapper(901, 600);
        CarportMapper cm4 = new CarportMapper(1201, 600);

        assertEquals(cm0.numPillars(), 6);
        assertEquals(cm1.numPillars(), 4);
        assertEquals(cm2.numPillars(), 4);
        assertEquals(cm3.numPillars(), 8);
        assertEquals(cm4.numPillars(), 10);
    }

    @Test
    void distBetweenRafters() {
        CarportMapper cm0 = new CarportMapper(780, 600);
        CarportMapper cm1 = new CarportMapper(478, 600);
        CarportMapper cm2 = new CarportMapper(199, 600);
        CarportMapper cm3 = new CarportMapper(980, 600);
        CarportMapper cm4 = new CarportMapper(123, 600);
        CarportMapper cm5 = new CarportMapper(71, 600);
        CarportMapper cm6 = new CarportMapper(1999, 600);
        CarportMapper cm7 = new CarportMapper(725, 600);

        assertTrue(cm0.distBetweenRafters(cm0.numRafters()) <= 60);
        assertTrue(cm1.distBetweenRafters(cm1.numRafters()) <= 60);
        assertTrue(cm2.distBetweenRafters(cm2.numRafters()) <= 60);
        assertTrue(cm3.distBetweenRafters(cm3.numRafters()) <= 60);
        assertTrue(cm4.distBetweenRafters(cm4.numRafters()) <= 60);
        assertTrue(cm5.distBetweenRafters(cm5.numRafters()) <= 60);
        assertTrue(cm6.distBetweenRafters(cm6.numRafters()) <= 60);
        assertTrue(cm7.distBetweenRafters(cm7.numRafters()) <= 60);
    }
}