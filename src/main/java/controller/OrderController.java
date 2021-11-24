package controller;

import model.*;
import persistance.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private final Database database;

    public OrderController(Database database) {
        this.database = database;
    }

    public void addToOrder(CartItems cartItems, String sessionId) {
        int idKey = 0;

        String sql = "INSERT INTO Orderitems (id_bottom, id_topping, amount) VALUES(" +
                "(SELECT id_bottom FROM Bottom WHERE name = ?)," +
                "(SELECT id_topping FROM Topping WHERE name = ?)," +
                "?)";

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, cartItems.getBottom());
            ps.setString(2, cartItems.getTopping());
            ps.setInt(3, cartItems.getAmount());

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
            String insertIdToCart = "INSERT INTO Orders (id_orderitems, id_user, status) VALUES(?, (SELECT id_user FROM Users WHERE sessionID = ?), ?)";

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(insertIdToCart);
                ps.setInt(1, idKey);
                ps.setString(2, sessionId);
                ps.setString(3, "RECIEVED");

                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            CartController cartController = new CartController(new Database());
            cartController.removeCart(sessionId);
        }
    }

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
}
