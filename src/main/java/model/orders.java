package model;

public class orders extends Cart{

	String address;
	int cart_id;
	public orders(String address, int cart_id) {
		this.address = address;
		this.cart_id = cart_id;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
