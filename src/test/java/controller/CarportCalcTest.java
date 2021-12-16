package controller;

import model.Material;
import util.Carport.CarportCalc;
import mapper.MaterialMapper;
import org.junit.jupiter.api.Test;
import util.Carport.Construction;

import java.util.*;

class CarportCalcTest {
    int length = 780;
    int width = 600;
    CarportCalc carportCalc = new CarportCalc(780, 600);
    MaterialMapper materialMapper = new MaterialMapper();
    Construction construction;

    private final int PILLAR = 23;
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

        // Change woodprice to material
        HashMap<Integer, Integer> woodPrice = carportCalc.calcWoodPrice();
        HashMap<Material, Integer> woodBill = new HashMap<>();


        for (Map.Entry<Integer, Integer> entry : carportQuant.entrySet())   {

            Material material = materialMapper.getMaterialByid(entry.getKey());

            billOfMaterials.put(material, entry.getValue());
        }

        for (Map.Entry<Integer, Integer> entry: woodPrice.entrySet()) {

            Material material = materialMapper.getMaterialByid(entry.getKey());

            woodBill.put(material,woodPrice.get(entry.getValue()));
        }
        System.out.println(billOfMaterials);
        //return billOfMaterials;
    }

    @Test
    public void returnBillOfWood()  {

        HashMap<Integer, Integer> materialsWood = new HashMap<>();
        HashMap<Integer, Double> pricePrCMWood = carportCalc.calcPricePrCmWood();
        HashMap<Integer, Double> priceOfWood = new HashMap<>();


        int amountRafters = 14;
        int amountPillars = 6;

        // Calc horizontal wood usage in CM
        int sternwood540 = length*2;
        int sternwoodreplace210 = length*2;
        int sternwoodside540 = length*2;
        int plastmo600 = length;


        // Calc vertical wood usage in CM
        int wood540 = width*2;
        int rafterwood600 = amountRafters*width;
        int sternwoodfor360 = width*2;



        // Wood
        materialsWood.put(PILLAR, amountPillars);
        materialsWood.put(RAFTERWOOD_600, rafterwood600);
        materialsWood.put(WOOD_540, wood540);
        materialsWood.put(STERNWOODFOR_360, sternwoodfor360);

        materialsWood.put(STERNWOOD_540, sternwood540);
        materialsWood.put(STERNWOODREPLACE_210, sternwoodreplace210);
        materialsWood.put(STERNWOODSIDE_540, sternwoodside540);
        materialsWood.put(PLASTMO600, plastmo600);

        for (Map.Entry<Integer, Integer> entry:materialsWood.entrySet()) {

            double pricepercmwood = pricePrCMWood.get(entry.getValue());
            System.out.println(pricepercmwood);
            int amountofwood = entry.getValue();
            System.out.println(amountofwood);

            //double sum = pricepercmwood*amountofwood;

            //priceOfWood.put((Integer) entry.getKey(), sum);

        }
        System.out.println(priceOfWood);

    }

    @Test
    public void returnWoodBill()  {

        // Change woodprice to material
        HashMap<Integer, Integer> woodPrice = carportCalc.calcWoodPrice();
        HashMap<Material, Integer> woodBill = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry: woodPrice.entrySet()) {

            Material material = materialMapper.getMaterialByid(entry.getKey());
            int valueWoodPrice = woodPrice.get(entry.getValue());

            woodBill.put(material,valueWoodPrice);
        }
        System.out.println(woodBill);

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

    @Test
    public void calcWoodPrice()    {
        HashMap<Integer, Integer> materialsWood = new HashMap<>();
        HashMap<Integer, Double> pricePrCMWood = carportCalc.calcPricePrCmWood();
        HashMap<Integer, Double> priceOfWood = new HashMap<>();



        int amountRafters = 14;
        int amountPillars = 6;

        // Calc horizontal wood usage in CM
        int sternwood540 = length*2;
        int sternwoodreplace210 = length*2;
        int sternwoodside540 = length*2;
        int plastmo600 = length;


        // Calc vertical wood usage in CM
        int wood540 = width*2;
        int rafterwood600 = amountRafters*width;
        int sternwoodfor360 = width*2;

        // Wood
        materialsWood.put(PILLAR, amountPillars);
        materialsWood.put(RAFTERWOOD_600, rafterwood600);
        materialsWood.put(WOOD_540, wood540);
        materialsWood.put(STERNWOODFOR_360, sternwoodfor360);

        materialsWood.put(STERNWOOD_540, sternwood540);
        materialsWood.put(STERNWOODREPLACE_210, sternwoodreplace210);
        materialsWood.put(STERNWOODSIDE_540, sternwoodside540);
        materialsWood.put(PLASTMO600, plastmo600);

        for (Map.Entry entry:materialsWood.entrySet()) {

            double pricepercmwood = pricePrCMWood.get(entry.getKey());
            int amountofwood = (int) entry.getValue();

            double sum = pricepercmwood*amountofwood;

            priceOfWood.put((Integer) entry.getKey(), sum);
        }
        System.out.println(priceOfWood);

    }

}