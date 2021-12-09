package mapper;
import persistance.Database;

import java.util.HashMap;

public class CarportMapper {

        private final int LENGTH, WIDTH;
        MaterialMapper materialMapper = new MaterialMapper(new Database());

        public CarportMapper(int LENGTH, int WIDTH) {
            this.LENGTH = LENGTH;
            this.WIDTH = WIDTH;
        }

        /** A carport should have n pairs of pillars, where n == (carport LENGTH / 300) cm. rounded up. */
        public int numPillars() {
            double breakpoint = 300;
            int numSupportBars = 2;
            return (int) Math.ceil((double) LENGTH / breakpoint) * numSupportBars;
        }

        /*
        public int returnCarportPillarLightRoofNoShed() {
            int pillarSpace = 300;
            int amountPillars = (int) (Math.ceil(LENGTH/pillarSpace) + 1) * 2;
            if (amountPillars <= 4) amountPillars = 4;

            return amountPillars;
        }
         */

        public int numRafters() {
            double maxDistBetweenRafters = 60.0;
            return (int) Math.floor((double) LENGTH / maxDistBetweenRafters) + 1;
        }

        public int distBetweenRafters(int numRafters) {
            return (int) Math.floor(((double) LENGTH / (double) numRafters));
        }

        public HashMap<String, Integer> calcMaterials(int amountPillars, int amountRafters)    {
            // hashmap
            HashMap<String, Integer> materials = new HashMap<>();

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
            int packofSquarePiece = 50;
            int screw70mm = 400;
            int screw50mm = 300;

            // For cirumference buy this many holetape rolls
            holetapeRolls = (int) Math.ceil(2 * (LENGTH + WIDTH)/holetape);

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
                screwsUniversals = universalL*3 + universalR*3;

                // Holetape screws
                screwsUniversals += amountRafters*4;
            }

            // Buy this many packs of screws
            packofScrewsUnivsersal = packofScrewsUnivsersal/screwsUniversals;

            // Buy this many packs of square pieces
            packofSquarePiece = packofSquarePiece/squarePiece;

            System.out.println("Amount of holetape rolls: " + holetapeRolls);
            System.out.println("Amount of bolts: " + bolt);
            System.out.println("Amount of square pieces: " + squarePiece);
            System.out.println("Amount of univerals R: " + universalR);
            System.out.println("Amount of univerals L: " + universalL);
            System.out.println("Amount of screws: " + screwsUniversals);
            System.out.println("Amount of pack of screws to buy: " + packofScrewsUnivsersal);

            materials.put("holetape", holetapeRolls);
            materials.put("bolt", bolt);
            materials.put("squarePiece", packofSquarePiece);
            materials.put("universalR", universalR);
            materials.put("universalL", universalL);
            materials.put("packofScrews", packofScrewsUnivsersal);

            return materials;
        }

        public void calcPriceFromComparedMaterials(MaterialMapper materialMapper, HashMap<String, Integer> materials)  {

            // Instanciate materialController and get materials
            this.materialMapper = materialMapper;
            //List materialPrice = materialController.getMaterials();

            //System.out.println(materialPrice);


        }

    public int getLENGTH() {
        return LENGTH;
    }

    public int getWIDTH() {
        return WIDTH;
    }
}
