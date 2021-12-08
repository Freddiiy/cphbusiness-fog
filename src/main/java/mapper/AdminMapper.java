package mapper;

import model.Carport;
import model.Order;
import model.User;
import persistance.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminMapper {

    private final Database database;

    public AdminMapper() {
        this.database = Database.getInstance();
    }

    public List<User> getUsers(String sessionId) {
        if (isAdmin(sessionId)) {
            String sql = "SELECT * FROM Users";

            List<User> userList = new ArrayList<>();


            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql);

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next() && !resultSet.wasNull()) {
                    userList.add(new User(resultSet.getInt("id_user"),
                            resultSet.getString("email"),
                            resultSet.getString("fname"),
                            resultSet.getString("lname"),
                            resultSet.getString("role"),
                            resultSet.getString("sessionID")));
                }
                if (userList.isEmpty()) {
                    return null;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return userList;

        } else {
            return null;
        }
    }

    public List<Order> getOrders(String sessionId) {
        if (isAdmin(sessionId)) {
            String sql = "SELECT Orders.id_order, Orders.id_user, Orders.id_carportRequest, Orders.status, Orders.total_price, Orders.timestamp, " +
                    "CarportRequest.id_carportRequest, CarportRequest.width, CarportRequest.length, CarportRequest.id_roof, CarportRequest.hasShed, CarportRequest.shedWidth, CarportRequest.shedLength, " +
                    "CarportMaterials.material_id, CarportMaterials.material_name, " +
                    "Users.id_user, Users.email, Users.fname, Users.lname, Users.role FROM Orders " +
                    "INNER JOIN CarportRequest ON Orders.id_carportRequest = CarportRequest.id_carportRequest " +
                    "INNER JOIN CarportMaterials ON CarportRequest.id_roof = CarportMaterials.material_id " +
                    "JOIN Users ON Orders.id_user = Users.id_user";

            List<Order> orderList = new ArrayList<>();

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql);

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

        } else {
            return null;
        }
    }

    public Order getOrderById(int orderId, String sessionId) {
        if (isAdmin(sessionId)) {
            String sql = "SELECT Orders.id_order, Orders.id_user, Orders.id_carportRequest, Orders.status, Orders.total_price, Orders.timestamp, " +
                    "CarportRequest.id_carportRequest, CarportRequest.width, CarportRequest.length, CarportRequest.id_roof, CarportRequest.hasShed, CarportRequest.shedWidth, CarportRequest.shedLength, " +
                    "CarportMaterials.material_id, CarportMaterials.material_name, " +
                    "Users.id_user, Users.email, Users.fname, Users.lname, Users.role FROM Orders " +
                    "INNER JOIN CarportRequest ON Orders.id_carportRequest = CarportRequest.id_carportRequest " +
                    "INNER JOIN CarportMaterials ON CarportRequest.id_roof = CarportMaterials.material_id " +
                    "JOIN Users ON Orders.id_user = Users.id_user " +
                    "WHERE Orders.id_order = ?";

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setInt(1, orderId);

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
        }
        return null;
    }

    public Carport getCarportByOrderId(int orderId, String sessionId) {
        if (isAdmin(sessionId)) {
            String sql = "SELECT CarportRequest.id_carportRequest, CarportRequest.width, CarportRequest.length, CarportRequest.id_roof, CarportRequest.hasShed, CarportRequest.shedWidth, CarportRequest.shedLength FROM Orders " +
                    "INNER JOIN CarportRequest ON Orders.id_carportRequest = CarportRequest.id_carportRequest " +
                    "WHERE id_order = ? ";

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setInt(1, orderId);
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next()) {

                    return new Carport(resultSet.getInt("CarportRequest.id_carportRequest"), resultSet.getInt("CarportRequest.width"), resultSet.getInt("CarportRequest.length"),resultSet.getInt("CarportRequest.id_roof"), resultSet.getBoolean("CarportRequest.hasShed"), resultSet.getInt("CarportRequest.shedWidth"), resultSet.getInt("CarportRequest.shedLength"));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public void updateOrder(int orderId, int width, int length, int shedWidth, int shedLength, String sessionId) {
        if (isAdmin(sessionId)) {

            String sql = "UPDATE Orders INNER JOIN CarportRequest ON Orders.id_carportRequest = CarportRequest.id_carportRequest SET CarportRequest.width = ?, CarportRequest.length = ?, CarportRequest.shedWidth = ?, CarportRequest.shedLength = ? " +
                    "WHERE Orders.id_carportRequest = (SELECT Orders.id_carportRequest FROM Orders WHERE id_order = ?)";

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, width);
                ps.setInt(2, length);
                ps.setInt(3, shedLength);
                ps.setInt(4, shedWidth);
                ps.setInt(5, orderId);

                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void acceptOrder(int orderId, String sessionId) {
        if (isAdmin(sessionId)) {

            String sql = "UPDATE Orders SET status = ? WHERE id_order = ?";

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, "ACCEPTED");
                ps.setInt(2, orderId);

                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void rejectOrder(int orderId, String sessionId) {
        if (isAdmin(sessionId)) {

            String sql = "UPDATE Orders SET status = ? WHERE id_order = ?";

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, "REJECTED");
                ps.setInt(2, orderId);

                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void removeUser(int userId, String sessionId) {
        if (isAdmin(sessionId)) {

            String sql = "DELETE FROM Users WHERE id_user = ?";

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, userId);

                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public boolean isAdmin(String sessionId) {

        String sql = "SELECT role FROM Users WHERE sessionID = ?";
        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, sessionId);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role").equals("Admin");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


}
