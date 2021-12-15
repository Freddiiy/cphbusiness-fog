package controller;

import model.Material;
import util.Carport.CarportCalc;
import mapper.MaterialMapper;
import org.junit.jupiter.api.Test;
import persistance.Database;
import util.Geometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.testng.Assert.*;

class CarportCalcTest {

    CarportCalc carportCalc = new CarportCalc(780, 600);
    MaterialMapper materialMapper = new MaterialMapper();

    private final int WOOD_360 = 14;
    private final int WOOD_540 = 15;
    private final int STERNWOOD_420 = 16;
    private final int STERNWOOD_540 = 17;
    private final int RAFTERWOOD_600 = 21;
    private final int PILLAR = 23;
    private final int STERNWOODREPLACE_210 = 24;
    private final int STERNWOODSIDE_540 = 25;
    private final int STERNWOODFOR_360 = 26;
    private final int PLASTMO600 = 27;
    private final int PLASTMO420 = 28;


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

    @Test
    public void calcPricePerCM()  {
        List<Integer> materialsToFindPriceOf = new ArrayList();
        List<Material> priceOfMaterials = materialMapper.getMaterialList();

        materialsToFindPriceOf.add(WOOD_540);
        materialsToFindPriceOf.add(STERNWOOD_540);
        materialsToFindPriceOf.add(RAFTERWOOD_600);
        materialsToFindPriceOf.add(STERNWOODREPLACE_210);
        materialsToFindPriceOf.add(STERNWOODSIDE_540);
        materialsToFindPriceOf.add(STERNWOODFOR_360);
        materialsToFindPriceOf.add(PLASTMO600);

        double pricePrCM = 0;
        for (int material:materialsToFindPriceOf) {
            Material materialToCalc = priceOfMaterials.get(material);

            double price = materialToCalc.getPrice();
            double length = materialToCalc.getLength();

            pricePrCM = price/length;

            System.out.println(materialToCalc.getName() + ": " + pricePrCM);
        }

        System.out.println(pricePrCM);
    }
}