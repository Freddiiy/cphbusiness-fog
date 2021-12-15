package controller;

import model.Material;
import util.Carport.CarportCalc;
import mapper.MaterialMapper;
import org.junit.jupiter.api.Test;
import persistance.Database;
import util.Carport.Construction;
import util.Geometry;

import java.util.*;

import static org.testng.Assert.*;

class CarportCalcTest {
    int length = 780;
    int width = 600;
    CarportCalc carportCalc = new CarportCalc(780, 600);
    MaterialMapper materialMapper = new MaterialMapper();
    Construction construction;

    private final int WOOD_540 = 15;
    private final int STERNWOOD_540 = 17;
    private final int RAFTERWOOD_600 = 21;
    private final int STERNWOODREPLACE_210 = 24;
    private final int STERNWOODSIDE_540 = 25;
    private final int STERNWOODFOR_360 = 26;
    private final int PLASTMO600 = 27;


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
        List<Integer> materialsToFindPriceOfHori = new ArrayList();
        List<Integer> materialsToFindPriceOfVerti = new ArrayList();
        List<Material> materialInfoFromDB = materialMapper.getMaterialList();

        materialsToFindPriceOfVerti.add(WOOD_540);
        materialsToFindPriceOfHori.add(STERNWOOD_540);
        materialsToFindPriceOfVerti.add(RAFTERWOOD_600);
        materialsToFindPriceOfHori.add(STERNWOODREPLACE_210);
        materialsToFindPriceOfHori.add(STERNWOODSIDE_540);
        materialsToFindPriceOfVerti.add(STERNWOODFOR_360);
        materialsToFindPriceOfHori.add(PLASTMO600);


        HashMap<Integer, Double> pricePrWood = new HashMap<>();

        double pricePrCM = 0;
        for (int material:materialsToFindPriceOfHori) {
            double priceWood = materialInfoFromDB.get(material).getPrice();
            double lengthWood = materialInfoFromDB.get(material).getLength();

            pricePrCM = priceWood/lengthWood;

            double finalPrice = pricePrCM * length;

            pricePrWood.put(material, finalPrice);
        }

        for (int material:materialsToFindPriceOfVerti) {
            double priceWood = materialInfoFromDB.get(material).getPrice();
            double lengthWood = materialInfoFromDB.get(material).getLength();

            pricePrCM = priceWood/lengthWood;

            double finalPrice = pricePrCM * width;

            pricePrWood.put(material, finalPrice);
        }
        System.out.println(pricePrWood);
    }
}