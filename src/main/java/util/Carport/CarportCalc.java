package util.Carport;

import mapper.MaterialMapper;
import persistance.Database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarportCalc {

    int length, width;
    MaterialMapper materialMapper = new MaterialMapper(new Database());

    // Misc
    private final int BUNDSCREW = 1;
    private final int HOLE_TAPE = 2;
    private final int UNIVERSAL_R = 3;
    private final int UNIVERSAL_L = 4;
    private final int PACKOF_SCREWSUNIVERSAL = 6;
    private final int BOLT = 7;
    private final int PACKOF_SQUAREPIECE = 8;

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
        int amountPillars = (int) (Math.ceil(length / pillarSpace) + 1) * 2;
        if (amountPillars <= 4) amountPillars = 4;

        return amountPillars;
    }

    public int returnCarportRaftersLightRoofNoShed() {
        int maxRafterSpace = 60;
        int amountRafters = (int) Math.floor(length / maxRafterSpace) + 1;

        return amountRafters;
    }

    public HashMap<Integer, Double> calcMaterials() {
        // hashmap
        HashMap<Integer, Double> materials = new HashMap<>();
        int amountPillars = returnCarportPillarLightRoofNoShed();
        int amountRafters = returnCarportRaftersLightRoofNoShed();

        double packOfScrewsBund = 200;
        double holetape = 1000;
        double holetapeRolls = 0;
        double universalR = 0;
        double universalL = 0;
        double packOfScrewsStern = 200;
        double screwsUniversals = 0;
        double packofScrewsUnivsersal = 250;
        double bolt = 0;
        double packofBolts = 25;
        double squarePiece = 0;
        double packofSquarePiece = 50;
        double screw70mm = 400;
        double screw50mm = 300;

        // For cirumference buy this many holetape rolls
        holetapeRolls = Math.ceil(2 * (length + width) / holetape);

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
        packofScrewsUnivsersal = Math.ceil(screwsUniversals / packofScrewsUnivsersal);

        // Buy this many packs of square pieces
        packofSquarePiece = Math.ceil(squarePiece / packofSquarePiece);

        // Buy this many packs of bolts
        packofBolts = Math.ceil(packofBolts / bolt);


        materials.put(HOLE_TAPE, holetapeRolls);
        materials.put(BOLT, packofBolts);
        materials.put(PACKOF_SQUAREPIECE, packofSquarePiece);
        materials.put(UNIVERSAL_R, universalR);
        materials.put(UNIVERSAL_L, universalL);
        materials.put(PACKOF_SCREWSUNIVERSAL, packofScrewsUnivsersal);

        return materials;
    }

    public double calcPriceFromComparedMaterials() {
        MaterialMapper materialMapper = new MaterialMapper(new Database());
        HashMap<Integer, Double> carportQuant = calcMaterials();
        // Instanciate materialController and get materials
        HashMap materialDatabase = materialMapper.getMaterials();

        double totalSum = 0;


        System.out.println(carportQuant);
        System.out.println(materialDatabase);

        for (Map.Entry<Integer, Double> entry : carportQuant.entrySet()) {

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

        List<Integer> billOfMaterials;




    }



}
