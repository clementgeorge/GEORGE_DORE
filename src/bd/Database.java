package bd;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.Connection;

public class Database {
	
	private DataSource dataSource;
	
	public Database(String jndiname) throws SQLException {
	   try {
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   dataSource = (DataSource)new InitialContext().lookup("java:comp/env/"+jndiname);
	   } catch (NamingException e) {
	      // Handle error that is not configured in JNDI.
	      throw new SQLException(jndiname + " is missing in JNDI! : "+e.getMessage());
	   } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   } 
	}
	
	public Connection getConnection() throws SQLException {
	   return (Connection)dataSource.getConnection();
	}

	public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
		Database database = null;
	      if (DBStatic.mysql_pooling==false)
	    	  return (Connection)DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + "/" +DBStatic.mysql_db, DBStatic.mysql_username,DBStatic.mysql_password);  
	      else {
	            if (database == null)
	                  database = new Database("jdbc/db");
	            return(database.getConnection());
	      }
	}
	
}
