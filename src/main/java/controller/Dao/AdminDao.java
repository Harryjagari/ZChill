package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.dbconnection.DbConnection;
import model.Admin;


public class AdminDao {
	//Start region Read operation
	public List<Admin> getAllData(String query) {
        List<Admin> dataList = new ArrayList<Admin>();
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
	            Statement stmt = dbConnection.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            while (rs.next()) {
	            	Admin data = new Admin();
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
	
	public Boolean isAdminRegistered(String query, String username, String password) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, username);
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					String userDb = result.getString("username");
					String passwordDb  = result.getString("password");					
					if(passwordDb!=null && userDb.equals(username) && passwordDb.equals(password)) return true;
					else return false;
				}else return false;
			} catch (SQLException e) { return null; }
		}else { return null; }
	}
		

	//	End region Read operation


	
	//	Start region Update operation
	public Boolean updateAdmin(String query, String username) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, username);
				int result = statement.executeUpdate();
				if(result>=0)return true;
				else return false;
			} catch (SQLException e) { return null; }
		}else { return null; }
	}
	//	End region Update operation
	
	//	Start region Delete operation
	public Boolean deleteAdmin(String query, String username) {
		Connection dbConnection = DbConnection.getConnection();
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, username);
				int result = statement.executeUpdate();
				if(result>=0)return true;
				else return false;
			} catch (SQLException e) { return null; }
		}else { return null; }
	}

}
