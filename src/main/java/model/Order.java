package model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private String email;
    private int userId;
    private Carport carport;
    private Timestamp timestamp;
    private double totalPrice;

    public Order(int id, int userId, Carport carport) {
        this.id = id;
        this.userId = userId;
        this.carport = carport;
    }

    public Order(int id, String email, int userId, Carport carport, Timestamp timestamp, double totalPrice) {
        this.id = id;
        this.email = email;
        this.userId = userId;
        this.carport = carport;
        this.timestamp = timestamp;
        this.totalPrice = totalPrice;
    }

    public Order(int id, String email, int userId) {
        this.id = id;
        this.email = email;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Carport getCarport() {
        return carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
