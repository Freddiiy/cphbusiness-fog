package model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private String email;
    private int userId;
    private Carport carport;
    private String status;
    private double totalPrice;
    private Timestamp timestamp;

    public Order(int id, int userId, Carport carport) {
        this.id = id;
        this.userId = userId;
        this.carport = carport;
    }

    public Order(int id, String email, int userId, Carport carport, String status, double totalPrice, Timestamp timestamp) {
        this.id = id;
        this.email = email;
        this.userId = userId;
        this.carport = carport;
        this.status = status;
        this.totalPrice = totalPrice;
        this.timestamp = timestamp;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
