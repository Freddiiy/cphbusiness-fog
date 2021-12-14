package util.drawing;

public class Matrices {

    public static double[][] dotProduct(double[][] m1, double[][] m2) {
        double[][] dotProduct = new double[m1.length][m2[0].length];
        for (int row = 0; row < dotProduct.length; row++) {
            for (int col = 0; col < dotProduct[row].length; col++) {
                dotProduct[row][col] = dotProductCell(m1, m2, row, col);
            }
        }
        return dotProduct;
    }

    public static double dotProductCell(double[][] m1, double[][] m2, int row, int col) {
        double product = 0;
        for (int i = 0; i < m2.length; i++) {
            product += m1[row][i] * m2[i][col];
        }
        return product;
    }

}
