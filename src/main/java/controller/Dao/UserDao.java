package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.PasswordEncryptionWithAes;
import model.Products;
import model.User;
import resources.MyConstants;
import controller.dbconnection.*;

public class UserDao {
	
//	Start region Read operation
	public List<User> getAllData(String query) {
        List<User> dataList = new ArrayList<User>();
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
	            Statement stmt = dbConnection.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            while (rs.next()) {
	            	User data = new User();
	                data.setEmail(rs.getString("email"));
	                data.setUsername(rs.getString("username"));
	                dataList.add(data);
	            }
	            rs.close();
	            stmt.close();
	            dbConnection.close();
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

	public Boolean isUserAlreadyRegistered(String username) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(MyConstants.CHECK_LOGIN_INFO);
				statement.setString(1, username);
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					return true;		
				}else return false;
			} catch (SQLException e) { return null; }
		}else { return null; }
						
	}
	
	public Boolean isUserRegistered(String query, String username, String password) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, username);
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					String userDb = result.getString("username");
					String passwordDb  = result.getString("password");
					String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, username);
					if(decryptedPwd!=null && userDb.equals(username) && decryptedPwd.equals(password)) return true;
					else return false;
				}else return false;
			} catch (SQLException e) { return null; }
		}else { return null; }
	}
		

	//	End region Read operation

	//	Start region Create operation
	public int registerUser(String query, User userModel) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
				if(isUserAlreadyRegistered(userModel.getUsername())) return -1;
				
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, userModel.getUsername());
				statement.setString(2, userModel.getEmail());
				statement.setString(3, PasswordEncryptionWithAes.encrypt(
						userModel.getUsername(), userModel.getPassword()));
				statement.setString(4, userModel.getImageUrlFromPart());

				int result = statement.executeUpdate();
				if(result>=0) return 1;
				else return 0;
			} catch (Exception e) { return -2; }
		}else { return -3; }
	}
	//	End region Create operation
	
	//	Start region Update operation

	
	public Boolean updateUser(String query, User userModel) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
				System.out.println(" name: " + userModel.getUsername());
		        System.out.println("The email ID: " + userModel.getEmail());
		        System.out.println("The image ID: " + userModel.getImageUrlFromPart());
		        System.out.println("The  ID: " + userModel.getRegister_id());
				
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, userModel.getUsername());
				statement.setString(2, userModel.getEmail());
				statement.setString(3, PasswordEncryptionWithAes.encrypt(
						userModel.getUsername(), userModel.getPassword()));
				statement.setString(4, userModel.getImageUrlFromPart());
				statement.setInt(5, userModel.getRegister_id());
				int result = statement.executeUpdate();
				if(result>=0)return true;
				else return false;
			} catch (SQLException e) { return null; }
		}else { return null; }
	}
	//	End region Update operation
	
	
	public User getUserById(int userId) throws SQLException {
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    User user = null;
	    Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
	    try {
	    	// get a database connection
	        stmt = dbConnection.prepareStatement("SELECT * FROM register WHERE register_id = ?");
	        stmt.setInt(1, userId);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            user = new User();
	            user.setUsername(rs.getString("username"));
	            // set other properties of the user object
	        }
	    } finally {
	        close(dbConnection, stmt, rs); // close database resources
	    }
	    return user;
		}
		return user;
	}
	
	private void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
	    if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException e) { /* ignored */}
	    }
	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException e) { /* ignored */}
	    }
	    if (conn != null) {
	        try {
	            conn.close();
	        } catch (SQLException e) { /* ignored */}
	    }
	}
	
	
	
}









