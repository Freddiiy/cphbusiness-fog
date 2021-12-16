package util.Carport;

import mapper.MaterialMapper;
import model.Material;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalcTest {

    MaterialMapper materialMapper = new MaterialMapper();

    private final int PILLAR = 23;
    private final int WOOD_540 = 15;
    private final int STERNWOOD_540 = 17;
    private final int RAFTERWOOD_600 = 21;
    private final int STERNWOODREPLACE_210 = 24;
    private final int STERNWOODSIDE_540 = 25;
    private final int STERNWOODFOR_360 = 26;
    private final int PLASTMO600 = 27;

    @Test
    void calcPricePrCmWood() {

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


        HashMap<Integer, Double> pricePrCMWood = new HashMap<>();

        double pricePrCM = 0;
        for (int material:materialsToFindPriceOfHori) {
            double priceWood = materialInfoFromDB.get(material).getPrice();
            double lengthWood = materialInfoFromDB.get(material).getLength();

            pricePrCM = priceWood/ lengthWood;

            pricePrCMWood.put(material, pricePrCM);
        }

        for (int material:materialsToFindPriceOfVerti) {
            double priceWood = materialInfoFromDB.get(material).getPrice();
            double lengthWood = materialInfoFromDB.get(material).getLength();

            pricePrCM = priceWood/lengthWood;

            pricePrCMWood.put(material, pricePrCM);
        }
        System.out.println(pricePrCMWood);
        //return pricePrCMWood;

    }
}