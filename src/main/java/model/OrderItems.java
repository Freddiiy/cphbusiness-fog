package model;

public class OrderItems {
    private int id;
    private String bottom;
    private double bottomPrice;
    private String topping;
    private double toppingPrice;
    private int amount;
    private double totalPrice;

    public OrderItems(int id, String bottom, String topping, int amount) {
        this.id = id;
        this.bottom = bottom;
        this.topping = topping;
        this.amount = amount;
    }

    public OrderItems(int id, String bottom, double bottomPrice, String topping, double toppingPrice, int amount, double totalPrice) {
        this.id = id;
        this.bottom = bottom;
        this.bottomPrice = bottomPrice;
        this.topping = topping;
        this.toppingPrice = toppingPrice;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public OrderItems(String bottom, String topping, int amount) {
        this.bottom = bottom;
        this.topping = topping;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public double getBottomPrice() {
        return bottomPrice;
    }

    public void setBottomPrice(double bottomPrice) {
        this.bottomPrice = bottomPrice;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public double getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(double toppingPrice) {
        this.toppingPrice = toppingPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
