package controller;

public class CarportController {

    int length, width;

    public CarportController(int length, int width) {

        this.length = length;
        this.width = width;
    }

    public int returnCarportPillarLightRoofNoShed() {
        // Stolpe hvert 3m
        int pillarSpace = 300;
        int amountPillars = (int) (Math.ceil(length/pillarSpace) + 1) * 2;
        if (amountPillars <= 4) amountPillars = 4;

        return amountPillars;
    }

    public int returnCarportRemLightRoofNoShed() {
        // 55cm afstand mellem rembrædder
        int remSpace = 55;
        int amountRem = length/remSpace;

        return amountRem;
    }

    


}
