package mapper;
import java.util.HashMap;

public class CarportMapper {

        int length, width;

        public CarportMapper(int length, int width) {
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
            int maxRafterSpace = 60;
            int amountRafters = (int) Math.floor(length/maxRafterSpace) + 1;

            return amountRafters;
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
            int screw70mm = 400;
            int screw50mm = 300;

            // For cirumference buy this many holetape rolls
            holetapeRolls = (int) Math.ceil(2 * (length + width)/holetape);

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

            System.out.println("Amount of holetape rolls: " + holetapeRolls);
            System.out.println("Amount of bolts: " + bolt);
            System.out.println("Amount of square pieces: " + squarePiece);
            System.out.println("Amount of univerals R: " + universalR);
            System.out.println("Amount of univerals L: " + universalL);
            System.out.println("Amount of screws: " + screwsUniversals);
            System.out.println("Amount of pack of screws to buy: " + packofScrewsUnivsersal);

            materials.put("holetape", holetapeRolls);
            materials.put("bolt", bolt);
            materials.put("squarePiece", squarePiece);
            materials.put("universalR", universalR);
            materials.put("universalL", universalL);
            materials.put("screwsUniversal", screwsUniversals);
            materials.put("packofScrews", packofScrewsUnivsersal);

            return materials;
        }
    }
