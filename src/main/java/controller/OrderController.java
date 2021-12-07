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

    public List<Order> getOrders(String sessionId) {
        String sql = "SELECT Orders.id_order, Orders.id_user, Orders.id_carportRequest, Orders.status, Orders.total_price, Orders.timestamp, " +
                "CarportRequest.id_carportRequest, CarportRequest.width, CarportRequest.length, CarportRequest.id_roof, CarportRequest.hasShed, CarportRequest.shedWidth, CarportRequest.shedLength, " +
                "CarportMaterials.material_id, CarportMaterials.material_name, " +
                "Users.id_user, Users.email, Users.fname, Users.lname, Users.role FROM Orders " +
                "INNER JOIN CarportRequest ON Orders.id_carportRequest = CarportRequest.id_carportRequest " +
                "INNER JOIN CarportMaterials ON CarportRequest.id_roof = CarportMaterials.material_id " +
                "INNER JOIN Users ON Orders.id_user = Users.id_user " +
                "WHERE Users.id_user = (SELECT id_user FROM Users WHERE sessionID = ?)";

        List<Order> orderList = new ArrayList<>();

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, sessionId);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                orderList.add(new Order(resultSet.getInt("Orders.id_order"),
                        new User(resultSet.getInt("Users.id_user"),
                                resultSet.getString("Users.email"),
                                resultSet.getString("Users.fname"),
                                resultSet.getString("Users.lname"),
                                resultSet.getString("Users.role")),
                        new Carport(resultSet.getInt("CarportRequest.id_carportRequest"),
                                resultSet.getInt("CarportRequest.length"),
                                resultSet.getInt("CarportRequest.width"),
                                resultSet.getInt("CarportRequest.id_roof"),
                                resultSet.getBoolean("CarportRequest.hasShed"),
                                resultSet.getInt("CarportRequest.shedLength"),
                                resultSet.getInt("CarportRequest.shedWidth")),
                        resultSet.getString("Orders.status"),
                        resultSet.getDouble("Orders.total_price"),
                        resultSet.getTimestamp("Orders.timestamp")));
            }
            if (orderList.isEmpty()) {
                return null;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    public Order getOrderById(int orderId, String sessionId) {
        String sql = "SELECT Orders.id_order, Orders.id_user, Orders.id_carportRequest, Orders.status, Orders.total_price, Orders.timestamp, " +
                "CarportRequest.id_carportRequest, CarportRequest.width, CarportRequest.length, CarportRequest.id_roof, CarportRequest.hasShed, CarportRequest.shedWidth, CarportRequest.shedLength, " +
                "CarportMaterials.material_id, CarportMaterials.material_name, " +
                "Users.id_user, Users.email, Users.fname, Users.lname, Users.role FROM Orders " +
                "INNER JOIN CarportRequest ON Orders.id_carportRequest = CarportRequest.id_carportRequest " +
                "INNER JOIN CarportMaterials ON CarportRequest.id_roof = CarportMaterials.material_id " +
                "JOIN Users ON Orders.id_user = Users.id_user " +
                "WHERE Orders.id_user = (SELECT id_user FROM Users WHERE sessionID = ?) AND Orders.id_order = ?";

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, sessionId);
            ps.setInt(2, orderId);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Order(resultSet.getInt("Orders.id_order"),
                        new User(resultSet.getInt("Users.id_user"),
                                resultSet.getString("Users.email"),
                                resultSet.getString("Users.fname"),
                                resultSet.getString("Users.lname"),
                                resultSet.getString("Users.role")),
                        new Carport(resultSet.getInt("CarportRequest.id_carportRequest"),
                                resultSet.getInt("CarportRequest.length"),
                                resultSet.getInt("CarportRequest.width"),
                                resultSet.getInt("CarportRequest.id_roof"),
                                resultSet.getBoolean("CarportRequest.hasShed"),
                                resultSet.getInt("CarportRequest.shedLength"),
                                resultSet.getInt("CarportRequest.shedWidth")),
                        resultSet.getString("Orders.status"),
                        resultSet.getDouble("Orders.total_price"),
                        resultSet.getTimestamp("Orders.timestamp"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
