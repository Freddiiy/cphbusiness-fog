package controller;

import util.Carport.CarportCalc;
import mapper.MaterialMapper;
import org.junit.jupiter.api.Test;
import persistance.Database;

import java.util.HashMap;
import java.util.Map;

class CarportCalcTest {

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
    void calcPriceFromComparedMaterials() {
        MaterialMapper materialMapper = new MaterialMapper(new Database());
        CarportCalc carportCalc = new CarportCalc(780,440);
        HashMap<Integer, Double> carportQuant = carportCalc.calcMaterials(6,14);
        // Instanciate materialController and get materials
        HashMap materialDatabase = materialMapper.getMaterials();

        System.out.println(materialDatabase);
        System.out.println(carportQuant);

        double totalSum = 0;

        for (Map.Entry<Integer, Double> entry : carportQuant.entrySet()) {

            double price = (double) materialDatabase.get(entry.getKey());
            double quantity = entry.getValue();
            double sum = price*quantity;

            System.out.printf("%.0f x %.2f == %.2f dkk\n", quantity, price, sum);
            totalSum += sum;
        }
        System.out.println("Sum: " + totalSum + " dkk");
    }
}