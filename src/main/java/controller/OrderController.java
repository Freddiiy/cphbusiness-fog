package controller;

import model.*;
import persistance.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private final Database database;

    public OrderController() {
        this.database = Database.getInstance();
    }

    public void addToOrder(Carport carport, String sessionId) {
        int idKey = 0;

        String sql = "INSERT INTO CarportRequest (width, length, id_roof, hasShed, shedWidth, shedLength) VALUES(?, ?, (SELECT material_id FROM CarportMaterials WHERE material_id = ?), ?, ?, ?)";

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int hasShed = (carport.hasShed()) ? 1 : 0;

            ps.setInt(1, carport.getWidth());
            ps.setInt(2, carport.getLength());
            ps.setInt(3, carport.getIdRoof());
            ps.setInt(4, hasShed);
            ps.setInt(5, carport.getShedWidth());
            ps.setInt(6, carport.getShedLength());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No affected rows");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idKey = generatedKeys.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (idKey == 0) {
            System.out.println("No key retrieved");
        } else {
            String insertIdToCart = "INSERT INTO Orders (id_user, id_carportRequest, status) VALUES((SELECT id_user FROM Users WHERE sessionID = ?), ?, ?)";

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(insertIdToCart);
                ps.setString(1, sessionId);
                ps.setInt(2, idKey);
                ps.setString(3, "RECIEVED");

                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /*
    public List getOrders(String sessionId) {
        String sql = "SELECT Bottom.name, Bottom.bottomPrice, Topping.name, Topping.toppingPrice, ((Bottom.bottomPrice + Topping.toppingPrice) * Orderitems.amount) AS total_price, Orderitems.id_orderitems, Orderitems.amount, Orders.id_order, Users.id_user FROM Orders " +
                "INNER JOIN Orderitems ON Orders.id_orderitems = Orderitems.id_orderitems" +
                " JOIN Bottom ON Orderitems.id_bottom = Bottom.id_bottom " +
                "INNER JOIN Topping ON Orderitems.id_topping = Topping.id_topping " +
                "INNER JOIN Users ON Orders.id_user = Users.id_user " +
                "WHERE Users.id_user = (SELECT id_user FROM Users WHERE sessionID = ?)";

        List<Order> orderList = new ArrayList<>();

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, sessionId);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next() && !resultSet.wasNull()) {
                orderList.add(new Order(resultSet.getInt("Orders.id_order"),
                        resultSet.getInt("Users.id_user"),
                        new OrderItems(resultSet.getInt("Orderitems.id_orderitems"),
                                resultSet.getString("Bottom.name"),
                                resultSet.getDouble("Bottom.bottomPrice"),
                                resultSet.getString("Topping.name"),
                                resultSet.getDouble("Topping.toppingPrice"),
                                resultSet.getInt("Orderitems.amount"),
                                resultSet.getDouble("total_price"))));
            }
            if (orderList.isEmpty()) {
                return null;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

     */
}
