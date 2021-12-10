package controller;

import util.Carport.CarportCalc;
import mapper.MaterialMapper;
import org.junit.jupiter.api.Test;
import persistance.Database;

import java.util.HashMap;
import java.util.Map;

class CarportCalcTest {

    CarportCalc carportCalc = new CarportCalc(780, 440);


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
    void calcPriceFromComparedMaterials(CarportCalc carportCalc) {
        MaterialMapper materialMapper = new MaterialMapper(new Database());
        HashMap<Integer, Double> carportQuant = carportCalc.calcMaterials();
        // Instanciate materialController and get materials
        HashMap materialDatabase = materialMapper.getMaterials();

        double totalSum = 0;

        for (Map.Entry<Integer, Double> entry : carportQuant.entrySet()) {

            double price = (double) materialDatabase.get(entry.getKey());
            double quantity = entry.getValue();
            double sum = price * quantity;

            System.out.printf("%.0f x %.2f == %.2f dkk\n", quantity, price, sum);
            totalSum += sum;
        }
        System.out.println("Sum: " + totalSum + " dkk");
        //return totalSum;
    }
}