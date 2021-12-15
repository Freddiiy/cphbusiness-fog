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
    MaterialMapper materialMapper = new MaterialMapper();

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
        for (int i = 0; i < amountPillars; i++) {
            //
            bolt += 2;
            squarePiece++;
        }

        // For every rafter use these materials
        for (int i = 0; i < amountRafters; i++) {
            // Universals + their screws
            universalR++;
            universalL++;
            screwsUniversals = universalL * 3 + universalR * 3;

            // Holetape screws
            screwsUniversals += amountRafters * 4;
        }

        // Buy this many packs of screws
        packofScrewsUnivsersal = (int) Math.ceil((double) screwsUniversals /(double) packofScrewsUnivsersal);

        // Buy this many packs of square pieces
        packofSquarePiece = (int) Math.ceil((double) squarePiece /(double) packofSquarePiece);

        // Buy this many packs of bolts
        packofBolts = (int) Math.ceil((double) packofBolts /(double) bolt);

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

        return materialsQuant;
    }

    public double calcPriceFromComparedMaterials() {
        // Instanciate MaterialMontroller and get price materials from database
        MaterialMapper materialMapper = new MaterialMapper();
        HashMap materialDatabase = materialMapper.getMaterials();
        // Call calcMaterials to get amount of materials used
        HashMap<Integer, Integer> carportQuant = calcQuantMaterials();

        double totalSum = 0;

        for (Map.Entry<Integer, Integer> entry : carportQuant.entrySet()) {

            double price = (double) materialDatabase.get(entry.getKey());
            double quantity = entry.getValue();
            double sum = price*quantity;


            System.out.printf("%.0f x %.2f == %.2f dkk\n", quantity, price, sum);
            totalSum += sum;
        }
        System.out.println("Sum: " + totalSum + " dkk");
        return totalSum;
    }

    public void returnBillOfMaterials() {
        //      Material  Quantity
        HashMap<Material, Integer> billOfMaterials = new HashMap<>();

        //      ID from DB, Quantity
        HashMap<Integer, Integer> carportQuant = calcQuantMaterials();


        // Match key fra carportQuant med Material.get(ID)
        // billofMaterials = (Material (ID), value fra carportQuant)

        for (Map.Entry<Integer, Integer> entry : carportQuant.entrySet())   {

            


        }






    }



}
