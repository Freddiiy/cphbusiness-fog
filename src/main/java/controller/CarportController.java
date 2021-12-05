package controller;

public class CarportController {

    int length, width;

    public CarportController(int length, int width) {

        this.length = length;
        this.width = width;
    }

    public int returnCarportPillarLightRoofNoShed() {
        int pillarSpace = 300;
        int amountPillars = (int) (Math.ceil(length/pillarSpace) + 1) * 2;
        if (amountPillars <= 4) amountPillars = 4;

        return amountPillars;
    }

    public int returnCarportRaftersLightRoofNoShed() {
        int remSpace = 55;
        int amountRafters = length/remSpace;

        return amountRafters;
    }

    public void calcMaterials(int amountPillars, int amountRafters)    {

        int packOfScrewsBund = 200;
        int holetape = 1000;
        int holetapeRolls = 0;
        int universalR = 0;
        int universalL = 0;
        int packOfScrewsStern = 200;
        int screwsUniversals = 0;
        int packofScrewsUnivsersal = 250;
        int bolt = 0;
        int squarePiece = 0;
        int screw70mm = 400;
        int screw50mm = 300;


        // For cirumference buy this many holetape rolls
        holetapeRolls = (length*2 + width+2)/holetape;

        // For every pillar use these materials
        for (int i = 0; i < amountPillars; i++) {
            //
            bolt++;
            bolt++;
            squarePiece++;
        }

        // For every rafter use these materials
        for (int i = 0; i < amountRafters; i++) {
            // Universals + their screws
            universalR++;
            universalL++;
            screwsUniversals = universalL*3 + universalR*3;

            // Holetape screws
            screwsUniversals += amountRafters*4;
        }

        // Buy this many packs of screws
        packofScrewsUnivsersal = packofScrewsUnivsersal/screwsUniversals;


        System.out.println("Amount of holetape rolls: " + holetapeRolls);
        System.out.println("Amount of bolts: " + bolt);
        System.out.println("Amount of square pieces: " + squarePiece);
        System.out.println("Amount of univerals R: " + universalR);
        System.out.println("Amount of univerals L: " + universalL);
        System.out.println("Amount of screws: " + screwsUniversals);
        System.out.println("Amount of pack of screws to buy: " + packofScrewsUnivsersal);

    }
}
