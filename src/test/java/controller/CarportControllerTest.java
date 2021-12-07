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
        List materialPrice = materialController.getMaterials();

        for (int i = 0; i < materialPrice.size(); i++) {



        }

    }
}