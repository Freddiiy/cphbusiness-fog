package model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private User user;
    private Carport carport;
    private String status;
    private double totalPrice;
    private Timestamp timestamp;

    public Order(int id, User user, Carport carport) {
        this.id = id;
        this.user = user;
        this.carport = carport;
    }

    public Order(int id, User user, Carport carport, String status, double totalPrice, Timestamp timestamp) {
        this.id = id;
        this.user = user;
        this.carport = carport;
        this.status = status;
        this.totalPrice = totalPrice;
        this.timestamp = timestamp;
    }

    public Order(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
