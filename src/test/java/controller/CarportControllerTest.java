package controller;

import util.CarportMapper;
import mapper.MaterialMapper;
import org.junit.jupiter.api.Test;
import persistance.Database;

import java.util.HashMap;
import java.util.Map;

class CarportControllerTest {

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
        CarportMapper carportMapper = new CarportMapper(780,440);
        HashMap<Integer, Integer> carportQuant = carportMapper.calcMaterials(6,14);
        // Instanciate materialController and get materials
        HashMap materialDatabase = materialMapper.getMaterials();

        System.out.println(materialDatabase);
        System.out.println(carportQuant);

        double totalSum = 0;

        for (Map.Entry<Integer, Integer> entry : carportQuant.entrySet()) {

            double price = (double) materialDatabase.get(entry.getKey());
            int quantity = entry.getValue();
            double sum = price*quantity;

            System.out.printf("%d * %.2f == %.2f \n", quantity, price, sum);
            totalSum += sum;
        }
        System.out.println(totalSum);
    }
}