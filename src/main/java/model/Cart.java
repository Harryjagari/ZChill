package model;


public class Cart extends Products {
    private int quantity;
    private int userId;

	public Cart() {}
	public Cart(int product_id,int quantity, int userId) {
        super(product_id);
        this.quantity = quantity;
        this.userId = userId;

	}
    public Cart(int quantity, int userId,double price) {
        super(price);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}