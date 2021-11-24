package model;

public class Cart {
    private int id;
    private int userId;
    private CartItems cartItems;

    public Cart(int id, int userId, CartItems cartItems) {
        this.id = id;
        this.userId = userId;
        this.cartItems= cartItems;
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

    public CartItems getCartItems() {
        return cartItems;
    }

    public void setCartItems(CartItems cartItems) {
        this.cartItems = cartItems;
    }
}
