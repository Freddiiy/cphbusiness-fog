package controller;
import model.Material;
import persistance.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialController {

    private final Database database;

    public MaterialController(Database database) {
        this.database = database;
    }

    public List getMaterials() {
        List<Material> materialsList = new ArrayList<>();
        String sql = "SELECT * FROM CarportMaterials";

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                materialsList.add(new Material(
                        resultSet.getInt("material_id"),
                        resultSet.getString("material_name"),
                        resultSet.getDouble("material_price"),
                        resultSet.getInt("material_length")));
            }
            if (materialsList.isEmpty()) {
                return null;
            }


        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return materialsList;
    }

    public double calcPriceFromMaterials(List<Material> materialsList)    {
        double sumOfMaterials = 0;
        for (Material material:materialsList) {
            sumOfMaterials += material.getPrice();
        }
        System.out.println(sumOfMaterials);
        return sumOfMaterials;
    }


}