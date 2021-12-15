package util;

public class Geometry {

    /** The Euclidean distance formula */
    public static double distBetweenPoints(int x1, int y1, int x2, int y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    /** The Euclidean distance formula */
    public static double distBetweenPointsDouble(double x1, double y1, double x2, double y2) {
        return Math.sqrt(((y2 - y1) * (y2 - y1)) + ((x2 - x1) * (x2 - x1)));
    }

}
