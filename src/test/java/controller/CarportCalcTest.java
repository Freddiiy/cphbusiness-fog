package controller;

import model.Material;
import util.Carport.CarportCalc;
import mapper.MaterialMapper;
import org.junit.jupiter.api.Test;
import persistance.Database;
import util.Geometry;

import java.util.HashMap;
import java.util.Map;
import static org.testng.Assert.*;

class CarportCalcTest {

    CarportCalc carportCalc = new CarportCalc(780, 600);
    MaterialMapper materialMapper = new MaterialMapper();


    @Test
    void returnCarportPillarLightRoofNoShed() {
        System.out.println(carportCalc.returnCarportPillarLightRoofNoShed());
    }

    @Test
    void returnCarportRaftersLightRoofNoShed() {
    }

    @Test
    void calcMaterials() {
    }


    private void assertEquals(double distBetweenPoints) {
    }

    @Test
    void calcPriceFromComparedMaterials() {
        MaterialMapper materialMapper = new MaterialMapper();
        HashMap<Integer, Integer> carportQuant = carportCalc.calcQuantMaterials();
        // Instanciate materialController and get materials
        HashMap materialDatabase = materialMapper.getMaterials();

        double totalSum = 0;

        for (Map.Entry<Integer, Integer> entry : carportQuant.entrySet()) {

            double price = (double) materialDatabase.get(entry.getKey());
            int quantity = entry.getValue();
            double sum = price * quantity;

            System.out.printf("%d x %.2f == %.2f dkk\n", quantity, price, sum);
            totalSum += sum;
        }
        System.out.println("Sum: " + totalSum + " dkk");
        //return totalSum;
    }

    @Test
    public void returnBillOfMaterials() {
        //      Material  Quantity
        HashMap<Material, Integer> billOfMaterials = new HashMap<>();

        //      ID from DB, Quantity
        HashMap<Integer, Integer> carportQuant = carportCalc.calcQuantMaterials();


        // Match key fra carportQuant med Material.get(ID)
        // billofMaterials = (Material (ID), value fra carportQuant)

        for (Map.Entry<Integer, Integer> entry : carportQuant.entrySet()) {


            Material material = materialMapper.getMaterialByid(entry.getKey());

            billOfMaterials.put(material, entry.getValue());

        }

        System.out.println(billOfMaterials);

    }
}