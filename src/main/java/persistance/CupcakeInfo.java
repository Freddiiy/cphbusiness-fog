package persistance;

import model.Cupcake;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CupcakeInfo {
    private final Database database;
    public CupcakeInfo(Database database) {
        this.database = database;
    }

    public Cupcake getItemFromID(String id) {
        String sql = "SELECT PremadeCupcake.*, Bottom.name, Bottom.bottomPrice, Topping.name, Topping.toppingPrice FROM PremadeCupcake\n" +
                "JOIN Bottom ON PremadeCupcake.bottom = Bottom.id_bottom\n" +
                "JOIN Topping ON PremadeCupcake.topping = Topping.id_topping\n" +
                "WHERE id_item = ?";

        try(Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id));

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return (new Cupcake(
                    resultSet.getInt("id_item"),
                    resultSet.getString("name"),
                    resultSet.getString("desc"),
                    resultSet.getString("imageURL"),
                    resultSet.getString("Bottom.name"),
                    resultSet.getInt("bottomPrice"),
                    resultSet.getString("Topping.name"),
                    resultSet.getInt("toppingPrice")

                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List getAllItems() {
        String sql = "SELECT PremadeCupcake.*, Bottom.name, Bottom.bottomPrice, Topping.name, Topping.toppingPrice \n" +
                "FROM PremadeCupcake\n" +
                "JOIN Bottom ON PremadeCupcake.bottom = Bottom.id_bottom\n" +
                "JOIN Topping ON PremadeCupcake.topping = Topping.id_topping ORDER BY id_item;";

        List<Cupcake> list = new ArrayList<>();
        try(Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                list.add(new Cupcake(
                    resultSet.getInt("id_item"),
                    resultSet.getString("name"),
                    resultSet.getString("desc"),
                    resultSet.getString("imageURL"),
                    resultSet.getString("Bottom.name"),
                    resultSet.getInt("bottomPrice"),
                    resultSet.getString("Topping.name"),
                    resultSet.getInt("toppingPrice")
                ));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
