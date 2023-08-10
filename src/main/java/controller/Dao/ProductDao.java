package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import controller.dbconnection.*;
import model.Products;
import resources.MyConstants;

public class ProductDao {
	
	private String query;
    private PreparedStatement stmt;
	private ResultSet rs;
   
	//	Start region Create operation
	public int addProduct(String query, Products productsModel) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
				if(isProductAlreadyAdded(productsModel.getProduct_name())) return -1;
				
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, productsModel.getProduct_name());
				statement.setDouble(2, productsModel.getPrice());
				statement.setString(3, productsModel.getDescription());
				statement.setString(4, productsModel.getProduct_type());
				statement.setString(5, productsModel.getImageUrlFromPart());
				statement.setString(6, productsModel.getProduct_stock());
				statement.setString(7, productsModel.getProduct_brand());
				statement.setString(8, productsModel.getProduct_rating());

				int result = statement.executeUpdate();
				if(result>=0) return 1;
				else return 0;
			} catch (Exception e) { return -2; }
		}else { return -3; }
	}
	
	public Boolean isProductAlreadyAdded(String product_name) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(MyConstants.CHECK_PRODUCT_ADDED_INFO);
				statement.setString(1, product_name);
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					return true;		
				}else return false;
			} catch (SQLException e) { return null; }
		}else { return null; }
						
	}
	
	
	//public class AdminDao {
		//Start region Read operation
		public List<Products> getAllData() {
	        List<Products> dataList = new ArrayList<Products>();
			Connection con = DbConnection.getConnection();
			if(con != null) {
				try {
		            query = "select * from product";
		            stmt = con.prepareStatement(query);
		            rs = stmt.executeQuery();
		            Statement stmt = con.createStatement();
		            ResultSet rs = stmt.executeQuery(query);
		            while (rs.next()) {
		            	Products data = new Products();
		                data.setProduct_name(rs.getString("product_name"));
		                data.setPrice(rs.getDouble("price"));
		                data.setDescription(rs.getString("description"));
		                data.setProduct_type(rs.getString("product_type"));
		                data.setProduct_name(rs.getString("product_image1"));		              
		                dataList.add(data);
		            }
		            rs.close();
		            stmt.close();
		            con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
	        return dataList;
		}
		
		public ResultSet selectWhereQuery(String query, String id) {
			Connection dbConnection = DbConnection.getConnection();
			if(dbConnection != null) {
				try {
					PreparedStatement statement = dbConnection.prepareStatement(query);
					statement.setString(1, id);
					ResultSet result = statement.executeQuery();
					return result;
				} catch (SQLException e) {
					return null;
				}
			}else {
				return null;
			}
		}
		
		public Boolean isProductAdded(String query, String product_name, Double price) {
			Connection dbConnection = DbConnection.getConnection();
			if(dbConnection != null) {
				try {
					PreparedStatement statement = dbConnection.prepareStatement(query);
					statement.setString(1, product_name);
					ResultSet result = statement.executeQuery();
					if(result.next()) {
						String productNameDb = result.getString("product_name");
						Double priceDb  = result.getDouble("price");					
						if(priceDb!=null && productNameDb.equals(product_name) && priceDb.equals(price)) return true;
						else return false;
					}else return false;
				} catch (SQLException e) { return null; }
			}else { return null; }
		}
			
		//	End region Read operation


		
		//	Start region Update operation
		
		public Boolean updateProduct(String query, Products productModel) {
			Connection dbConnection = DbConnection.getConnection();
			if(dbConnection != null) {
				try {
					System.out.println("The Product ID: " + productModel.getProduct_id());
			        System.out.println("The user ID: " + productModel.getProduct_name());
			        System.out.println("The Product ID: " + productModel.getPrice());
			        System.out.println("The user ID: " + productModel.getDescription());
					
					PreparedStatement statement = dbConnection.prepareStatement(query);
					statement.setString(1, productModel.getProduct_name());
					statement.setDouble(2, productModel.getPrice());
					statement.setString(3, productModel.getDescription());
					statement.setInt(4, productModel.getProduct_id());
					int result = statement.executeUpdate();
					if(result>=0)return true;
					else return false;
				} catch (SQLException e) { return null; }
			}else { return null; }
		}

		//	End region Update operation
		
		//	Start region Delete operation
		public Boolean deleteProduct(String query,int product_id) {
			Connection dbConnection = DbConnection.getConnection();
			if(dbConnection != null) {
				try {
					PreparedStatement statement = dbConnection.prepareStatement(query);
					statement.setInt(1, product_id);
					int result = statement.executeUpdate();
					if(result>=0)return true;
					else return false;
				} catch (SQLException e) { return null; }
			}else { return null; }
		}
		
		

		
		 public Products getSingleProduct(int product_id) {
			 Products row = null;
			 Connection con = DbConnection.getConnection();
				if(con != null) {
		        try {
		            query = "select * from product where product_Id=? ";

		            stmt = con.prepareStatement(query);
		            stmt.setInt(1, product_id);
		            ResultSet rs = stmt.executeQuery();

		            while (rs.next()) {
		            	row = new Products();
		                row.setProduct_id(rs.getInt("product_Id"));
		                row.setProduct_name(rs.getString("Product_name"));
		                row.setPrice(rs.getDouble("Price"));
		                row.setDescription(rs.getString("description"));
		                row.setProduct_type(rs.getString("Product_type"));
		                row.setProduct_brand(rs.getString("Product_brand"));
		                row.setProduct_stock(rs.getString("Product_stock"));
		                row.setProduct_rating(rs.getString("Product_rating"));

		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println(e.getMessage());
		        }

		        return row;
		    }
				return row;
		}
			   
}
