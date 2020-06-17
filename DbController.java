package employeeAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbController {
	
	private static Connection con = null;
	public static void initConnection() throws SQLException,ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","me_aish");
	}
	
	public static Connection getConnection() throws SQLException,ClassNotFoundException{
		if(DbController.con == null)
			DbController.initConnection();
		return con;
	}

}
