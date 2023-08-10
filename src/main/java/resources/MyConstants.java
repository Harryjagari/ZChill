package resources;

import java.io.File;

public class MyConstants {
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/ZChill";
	public static final String DB_USER_NAME = "root"; 		
	public static final String DB_USER_PASSWORD= ""; 
	
	public static final String ADMIN = "admin";
	public static final String USER = "user";
	
	public static final String IMAGE_DIR = "xampp\\tomcat\\webapps\\images\\";
	public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR;
	// End Region: Database Configuration
	
	// Start Region: Select Query
	public static final String CHECK_LOGIN_INFO = "SELECT username, password FROM register WHERE username = ?";
	public static final String GET_ALL_INFO = "SELECT * FROM register";
	public static final String GET_ALL_INFO_BY_ID = "SELECT * FROM register WHERE id = ?";
	public static final String CHECK_ADMIN_LOGIN_INFO = "SELECT username, password FROM Admin WHERE username = ?";
	public static final String CHECK_PRODUCT_ADDED_INFO = "SELECT product_name, price FROM product WHERE product_name = ?";
	// Start Region: Insert Query
	public static final String USER_REGISTER = "INSERT INTO register"
			+ "(username, email, password, image)"
			+ " VALUES(?,?,?,?)";
	// Start Region: Insert Query
	public static final String ADD_PRODUCT = "INSERT INTO product"
			+ "(product_name, price, description,product_type, product_image1,product_stock,product_brand,product_rating)"
			+ " VALUES(?,?,?,?,?,?,?,?)";
	public static final String ADD_CART = "INSERT INTO cart" + "(quantity, register_id,product_id)"+ " VALUES(?,?,?)";
	// End Region: Insert Query
	public static final String ADD_ORDER = "INSERT INTO orders"+ "(cart_id, address)"+ " VALUES(?,?)";
	// Start Region: Update Query
	public static final String UPDATE_USER_INFO = "UPDATE register SET username=?, "
				+ "email=?, password=?, image=? WHERE register_id=?";
	// End Region: Update Query
	public static final String UPDATE_PRODUCT = "UPDATE product SET product_name=?, price=?, description=? WHERE product_id=?";
	// Start Region: Delete Query
	public static final String DELETE_USER = "DELETE FROM register WHERE username=?";
	public static final String DELETE_PRODUCT = "DELETE FROM product WHERE product_id=?";
	public static final String DELETE_CART = "DELETE FROM cart WHERE cart_id=?";
	// End Region: Delete Query
	
}
