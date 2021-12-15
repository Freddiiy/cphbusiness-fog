package util.Carport;

import com.google.protobuf.GeneratedMessage;
import mapper.MaterialMapper;
import model.Material;
import persistance.Database;
import util.Geometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarportCalc {

    int length, width;
    int maxRafterSpace = 60;
    MaterialMapper materialMapper;
    Construction construction;

    // Misc
    private final int BUNDSCREW = 1;
    private final int HOLE_TAPE = 2;
    private final int UNIVERSAL_R = 3;
    private final int UNIVERSAL_L = 4;
    private final int PACKOF_SCREWSSTERN = 5;
    private final int PACKOF_SCREWSUNIVERSAL = 6;
    private final int BOLT = 7;
    private final int PACKOF_SQUAREPIECE = 8;
    private final int PACKOF_SCREWS70MM = 9;
    private final int PACKOF_SCREWS50MM = 10;

    // Wood
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


    public CarportCalc(int length, int width) {
        this.length = length;
        this.width = width;
        construction = new Construction(length, width);
        materialMapper = new MaterialMapper();
    }

    public int returnCarportPillarLightRoofNoShed() {
        int pillarSpace = 300;
        int amountPillars = (int) (Math.ceil((double) length /(double) pillarSpace) + 1) * 2;
        if (amountPillars <= 4) amountPillars = 4;

        return amountPillars;
    }

    public int returnCarportRaftersLightRoofNoShed() {
        int maxRafterSpace = 60;
        int amountRafters = (int) Math.floor((double) length / (double) maxRafterSpace) + 1;

        return amountRafters;
    }

    public HashMap<Integer, Double> calcPricePrWood()  {
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
        return pricePrWood;
    }

    public HashMap<Integer, Integer> calcQuantMaterials() {
        // hashmap
        HashMap<Integer, Integer> materialsQuant = new HashMap<>();
        int amountPillars = returnCarportPillarLightRoofNoShed();
        int amountRafters = returnCarportRaftersLightRoofNoShed();

        // Hardcode (calculate this)
        int packOfScrewsBund = 3; // 200
        int packOfScrewsStern = 1; // 200
        int screw70mm = 2; // 400
        int screw50mm = 2; // 300



        // Actual code (good to go)
        int holetape = 1000;
        int holetapeRolls = 0;
        int universalR = 0;
        int universalL = 0;
        int screwsUniversals = 0;
        int packofScrewsUnivsersal = 250;
        int bolt = 0;
        int packofBolts = 25;
        int squarePiece = 0;
        int packofSquarePiece = 50;






        // Length of holetape used
        holetapeRolls = (int) (Geometry.distBetweenPoints(maxRafterSpace, 35, length - maxRafterSpace, width - 35) * 2);
        holetapeRolls = holetapeRolls / holetape;

        // For every pillar use these materials
        bolt = amountPillars*2;
        squarePiece = amountPillars;

        // For every rafter use these materials
        universalR = amountRafters;
        universalL = amountRafters;
        screwsUniversals = amountRafters*3 + amountRafters*3 + amountRafters*4;

        // Buy this many packs of screws
        packofScrewsUnivsersal = (int) Math.ceil((double) screwsUniversals /(double) packofScrewsUnivsersal);

        // Buy this many packs of square pieces
        packofSquarePiece = (int) Math.ceil((double) squarePiece /(double) packofSquarePiece);

        // Buy this many packs of bolts
        packofBolts = (int) Math.ceil((double) packofBolts /(double) bolt);

        // Calc horizontal wood usage in CM
        int sternwood540 = length*2;
        int sternwoodreplace210 = length*2;
        int sternwoodside540 = length*2;
        int plastmo600 = length;


        // Calc vertical wood usage in CM
        int wood540 = width*2;
        int rafterwood600 = amountRafters*width;
        int sternwoodfor360 = width*2;


        // Add the values to the hashmap: (ID from database, amount calculated)
        materialsQuant.put(BUNDSCREW, packOfScrewsBund);
        materialsQuant.put(HOLE_TAPE, holetapeRolls);
        materialsQuant.put(BOLT, packofBolts);
        materialsQuant.put(PACKOF_SQUAREPIECE, packofSquarePiece);
        materialsQuant.put(UNIVERSAL_R, universalR);
        materialsQuant.put(UNIVERSAL_L, universalL);
        materialsQuant.put(PACKOF_SCREWSUNIVERSAL, packofScrewsUnivsersal);
        materialsQuant.put(PACKOF_SCREWSSTERN, packOfScrewsStern);
        materialsQuant.put(PACKOF_SCREWS70MM, screw70mm);
        materialsQuant.put(PACKOF_SCREWS50MM, screw50mm);

        // Wood
        materialsQuant.put(PILLAR, amountPillars);
        materialsQuant.put(RAFTERWOOD_600, rafterwood600);
        materialsQuant.put(WOOD_540, wood540);
        materialsQuant.put(STERNWOODFOR_360, sternwoodfor360);

        materialsQuant.put(STERNWOOD_540, sternwood540);
        materialsQuant.put(STERNWOODREPLACE_210, sternwoodreplace210);
        materialsQuant.put(STERNWOODSIDE_540, sternwoodside540);
        materialsQuant.put(PLASTMO600, plastmo600);

        return materialsQuant;
    }

    public double calcPriceFromComparedMaterials() {
        // Instanciate MaterialMontroller and get price materials from database
        HashMap materialDatabase = materialMapper.getMaterials();
        // Call calcMaterials to get amount of materials used
        HashMap<Integer, Integer> carportQuant = calcQuantMaterials();

        double totalSum = 0;

        for (Map.Entry<Integer, Integer> entry : carportQuant.entrySet()) {

            double price = (double) materialDatabase.get(entry.getKey());
            double quantity = entry.getValue();
            double sum = price*quantity;


            totalSum += sum;
        }
        return totalSum;
    }

    public HashMap<Material, Integer> returnBillOfMaterials() {
        //      Material  Quantity
        HashMap<Material, Integer> billOfMaterials = new HashMap<>();

        //      ID from DB, Quantity
        HashMap<Integer, Integer> carportQuant = calcQuantMaterials();

        // Match key fra carportQuant med Material.get(ID)
        // billofMaterials = (Material (ID), value fra carportQuant)

        for (Map.Entry<Integer, Integer> entry : carportQuant.entrySet())   {

            Material material = materialMapper.getMaterialByid(entry.getKey());

            billOfMaterials.put(material, entry.getValue());
        }
        return billOfMaterials;
    }
}
