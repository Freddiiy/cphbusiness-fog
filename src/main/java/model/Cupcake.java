package model;

public class Cupcake {
    private int id;
    private String name;
    private String desc;
    private String imageURL;
    private String bottom;
    private double bottomPrice;
    private String topping;
    private double toppingPrice;
    private double totalPrice;

    public Cupcake(int id, String name, String desc, String imageURL, String bottom, int bottomPrice, String topping, int toppingPrice) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.imageURL = imageURL;
        this.bottom = bottom;
        this.bottomPrice = bottomPrice;
        this.topping = topping;
        this.toppingPrice = toppingPrice;
    }

    public Cupcake(String bottom, String topping) {
        this.bottom = bottom;
        this.topping = topping;
    }

    public Cupcake(String bottom, double bottomPrice, String topping, double toppingPrice, double totalPrice) {
        this.bottom = bottom;
        this.bottomPrice = bottomPrice;
        this.topping = topping;
        this.toppingPrice = toppingPrice;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageURL() {
        return imageURL;
    }


    public double getBottomPrice() {
        return bottomPrice;
    }


    public double getToppingPrice() {
        return toppingPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBottom() {
        return bottom;
    }

    public String getTopping() {
        return topping;
    }
}