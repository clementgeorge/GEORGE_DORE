package bd;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.Connection;

public class Database {

	public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Connection)DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + "/" +DBStatic.mysql_db, DBStatic.mysql_username,DBStatic.mysql_password);  

	}

}
