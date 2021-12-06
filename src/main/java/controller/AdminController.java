package controller;

import model.Carport;
import model.Order;
import model.User;
import persistance.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminController {

    private final Database database;

    public AdminController(Database database) {
        this.database = database;
    }

    public List getUsers(String sessionId) {
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
                            resultSet.getDouble("balance"),
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

    public void updateUserBalance(double addBalance, int userId, String sessionId) {
        if (isAdmin(sessionId)) {
            String sql = "UPDATE Users SET balance = balance + ? WHERE id_user = ?";

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setDouble(1, addBalance);
                ps.setInt(2, userId);

                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
/*
    public List getOrders(String sessionId) {
        if (isAdmin(sessionId)) {
            String sql = "SELECT Bottom.name, Bottom.bottomPrice, Topping.name, Topping.toppingPrice, ((Bottom.bottomPrice + Topping.toppingPrice) * Orderitems.amount) AS total_price, Orderitems.id_orderitems, Orderitems.amount, Orders.id_order, Users.id_user, Users.email FROM Orders " +
                    "INNER JOIN Orderitems ON Orders.id_orderitems = Orderitems.id_orderitems" +
                    " JOIN Bottom ON Orderitems.id_bottom = Bottom.id_bottom " +
                    "INNER JOIN Topping ON Orderitems.id_topping = Topping.id_topping " +
                    "INNER JOIN Users ON Orders.id_user = Users.id_user";

            List<Order> orderList = new ArrayList<>();

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql);

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next() && !resultSet.wasNull()) {
                    orderList.add(new Order(resultSet.getInt("Orders.id_order"),
                            resultSet.getString("Users.email"),
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

        } else {
            return null;
        }
    }
 */
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
    /*
    public List getOrdersByUserId(String sessionId, int userId) {
        if (isAdmin(sessionId)) {
            String sql = "SELECT Bottom.name, Bottom.bottomPrice, Topping.name, Topping.toppingPrice, ((Bottom.bottomPrice + Topping.toppingPrice) * Orderitems.amount) AS total_price, Orderitems.id_orderitems, Orderitems.amount, Orders.id_order, Users.id_user, Users.email FROM Orders " +
                    "INNER JOIN Orderitems ON Orders.id_orderitems = Orderitems.id_orderitems" +
                    " JOIN Bottom ON Orderitems.id_bottom = Bottom.id_bottom " +
                    "INNER JOIN Topping ON Orderitems.id_topping = Topping.id_topping " +
                    "INNER JOIN Users ON Orders.id_user = Users.id_user " +
                    "WHERE Orders.id_user = ?";

            List<Order> orderList = new ArrayList<>();

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setInt(1, userId);

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next() && !resultSet.wasNull()) {
                    orderList.add(new Order(resultSet.getInt("Orders.id_order"),
                            resultSet.getString("Users.email"),
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

        } else {
            return null;
        }
    }
*/

    public void removeOrder(int orderId, String sessionId) {
        if (isAdmin(sessionId)) {

            String sql = "DELETE FROM Orders WHERE id_order = ?";

            try (Connection connection = database.connect()) {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, orderId);

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
