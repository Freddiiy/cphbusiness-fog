package controller;

import model.Material;
import org.junit.jupiter.api.Test;
import persistance.Database;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        MaterialController materialController = new MaterialController(new Database());
        // Instanciate materialController and get materials
        HashMap materialDatabase = materialController.getMaterials();





        for (int i = 0; i < materialDatabase.size(); i++) {


            Material material = (Material) materialDatabase.get(i);
            System.out.println(material.getId());
        }

    }
}