package util;
import mapper.MaterialMapper;
import persistance.Database;

import java.util.HashMap;

public class CarportMapper {

        int length, width;
        MaterialMapper materialMapper = new MaterialMapper(new Database());

        private final int HOLE_TAPE = 2;
        private final int BOLT = 7;
        private final int PACKOF_SQUAREPIECE = 8;
        private final int UNIVERSAL_R = 3;
        private final int UNIVERSAL_L = 4;
        private final int PACKOF_SCREWSUNIVERSAL = 6;


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

        public HashMap<Integer, Integer> calcMaterials(int amountPillars, int amountRafters)    {
            // hashmap
            HashMap<Integer, Integer> materials = new HashMap<>();

            int packOfScrewsBund = 200;
            int holetape = 1000;
            int holetapeRolls = 0;
            int universalR = 0;
            int universalL = 0;
            int packOfScrewsStern = 200;
            int screwsUniversals = 0;
            int packofScrewsUnivsersal = 250;
            int bolt = 0;
            int packofBolts = 25;
            int squarePiece = 0;
            int packofSquarePiece = 50;
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

            // Buy this many packs of square pieces
            packofSquarePiece = (int) Math.ceil(packofSquarePiece/squarePiece);

            // Buy this many packs of bolts
            packofBolts = (int) Math.ceil(packofBolts/bolt);



            materials.put(HOLE_TAPE, holetapeRolls);
            materials.put(BOLT, packofBolts);
            materials.put(PACKOF_SQUAREPIECE, packofSquarePiece);
            materials.put(UNIVERSAL_R, universalR);
            materials.put(UNIVERSAL_L, universalL);
            materials.put(PACKOF_SCREWSUNIVERSAL, packofScrewsUnivsersal);

            return materials;
        }

        public void calcPriceFromComparedMaterials(MaterialMapper materialMapper, HashMap<String, Integer> materials)  {

            // Instanciate materialController and get materials
            this.materialMapper = materialMapper;
            //List materialPrice = materialController.getMaterials();

            //System.out.println(materialPrice);


        }

    }
