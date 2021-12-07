package controller;

import mapper.MaterialMapper;
import model.Material;
import org.testng.annotations.Test;
import persistance.Database;

import java.util.HashMap;

import static org.testng.Assert.*;

class CarportControllerTest {

    @Test
    void returnCarportPillarLightRoofNoShed() {
    }

    @Test
    void returnCarportRaftersLightRoofNoShed() {
    }

    @Test
    void calcMaterials() {
    }

    @Test
    void calcPriceFromComparedMaterials() {
        MaterialMapper materialMapper = new MaterialMapper(new Database());
        // Instanciate materialController and get materials
        HashMap materialDatabase = materialMapper.getMaterials();





        for (int i = 0; i < materialDatabase.size(); i++) {


            Material material = (Material) materialDatabase.get(i);
            System.out.println(material.getId());
        }

    }
}