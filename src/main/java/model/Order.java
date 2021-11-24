package model;

public class Order {
    private int id;
    private String email;
    private int userId;
    private OrderItems orderItems;

    public Order(int id, int userId, OrderItems orderItems) {
        this.id = id;
        this.userId = userId;
        this.orderItems= orderItems;
    }

    public Order(int id, String email, int userId, OrderItems orderItems) {
        this.id = id;
        this.email = email;
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItems orderItems) {
        this.orderItems = orderItems;
    }
}
