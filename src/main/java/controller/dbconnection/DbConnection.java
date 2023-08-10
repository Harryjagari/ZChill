package controller.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import resources.MyConstants;

public class DbConnection {
	public static Connection getConnection(){
		try {
			Class.forName(MyConstants.DRIVER_NAME);
			Connection connection = DriverManager.getConnection(
					MyConstants.DB_URL,
					MyConstants.DB_USER_NAME,
					MyConstants.DB_USER_PASSWORD);
			return connection;
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}