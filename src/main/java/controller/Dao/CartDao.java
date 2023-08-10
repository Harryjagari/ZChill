package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.dbconnection.DbConnection;
import model.Cart;
import model.orders;



public class CartDao {
	//	Start region Create operation
	public int addCart(String query, Cart cartModel) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
		
		        // print the productId value
		        System.out.println("The Product ID: " + cartModel.getProduct_id());
		        System.out.println("The user ID: " + cartModel.getUserId());
		        System.out.println("The quantity ID: " + cartModel.getQuantity());
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setInt(1, cartModel.getQuantity());
				statement.setInt(2, cartModel.getUserId());
				statement.setInt(3, cartModel.getProduct_id());
				int result = statement.executeUpdate();
				if(result>=0) return 1;
				else return 0;
			} catch (Exception e) { return -2; }
		}else { return -3; }
	}
	
	//	Start region Create operation
	public int addOrders(String query, orders orderModel) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
		
		        // print the productId value
		        //System.out.println("The Product ID: " + cartModel.getProduct_id());
		        //System.out.println("The user ID: " + cartModel.getUser_id());
		        //System.out.println("The quantity ID: " + cartModel.getQuantity());
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setInt(1, orderModel.getCart_id());
				statement.setString(2, orderModel.getAddress());
				int result = statement.executeUpdate();
				if(result>=0) return 1;
				else return 0;
			} catch (Exception e) { return -2; }
		}else { return -3; }
	}
	
	public Boolean isCartAdded(String query,int cart_id) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setInt(1, cart_id);
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					int cartDb = result.getInt("cart_id");
					if(cartDb!=0 && cartDb==cart_id) return true;
					else return false;
				}else return false;
			} catch (SQLException e) { return null; }
		}else { return null; }
	}

	public int getCartId(Cart cart) {
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    int cart_id = 0;
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
	        String query = "SELECT cart_id FROM cart WHERE product_id = ? AND register_id = ?";
	        stmt = dbConnection.prepareStatement(query);
	        stmt.setInt(1, cart.getProduct_id());
	        stmt.setInt(2, cart.getUserId());
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            cart_id = rs.getInt("cart_id");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeConnection( stmt, rs);
	    }

	    return cart_id;
	}
		return cart_id;
	}
		
		
	private void closeConnection(PreparedStatement stmt, ResultSet rs) {
		// TODO Auto-generated method stub
		
	}

	public Boolean deleteCartItem(String query, int carttId) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setInt(1, carttId);
				int result = statement.executeUpdate();
				if(result>=0)return true;
				else return false;
			} catch (SQLException e) { return null; }
		}else { return null; }
	}
}


