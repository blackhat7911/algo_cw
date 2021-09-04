package cw2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	public Connection connection() {
		Connection conn = null;
		
		try {
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "bishal", "Bishal@123");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
