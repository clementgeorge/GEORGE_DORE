package bd;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBStatic {

	public static boolean mysql_pooling = false;
	public static String mysql_host = "132.227.201.129:33306";
	public static String mysql_db = "george_dore";
	public static String mysql_username = "george_dore";
	public static String mysql_password = "george_dore";
	
	public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
		return (Connection) DriverManager.getConnection("jdbc:mysql://132.227.201.129:33306/george_dore,george_dore,george_dore");
	}
	
}
