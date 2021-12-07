package mapper;
import model.Material;
import persistance.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaterialMapper {

    private final Database database;

    public MaterialMapper(Database database) {
        this.database = database;
    }

    public HashMap<Integer, Double> getMaterials() {
        HashMap<Integer, Double> materialHashMap = new HashMap();
        String sql = "SELECT * FROM CarportMaterials";

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                        int id = resultSet.getInt("material_id");
                        String name = resultSet.getString("material_name");
                        double price = resultSet.getDouble("material_price");
                        int length = resultSet.getInt("material_length");
                        String type = resultSet.getString("type");
                        materialHashMap.put(id,price);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return materialHashMap;
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