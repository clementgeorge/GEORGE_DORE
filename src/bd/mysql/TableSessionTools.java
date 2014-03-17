package bd.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.Database;
import bd.MySqlConnexionException;

public class TableSessionTools {

	public static void insertSessionDB() throws SQLException, MySqlConnexionException {
		Connection conn = Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="INSERT INTO session VALUES(null,CURRENT_TIMESTAMP());";
		inst.executeUpdate(query);
		inst.close();
		conn.close();
		
	}

	public static boolean isSessionDB(String key) throws SQLException, MySqlConnexionException {
		Connection conn=Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="SELECT id FROM session WHERE id=\'"+key+"\';";
		inst.executeQuery(query);
		ResultSet rs=inst.getResultSet();
		boolean retour;
		
		if(rs.next()){
			retour=true;;
		}
		else{
			retour=false;
		}
	
		rs.close();
		inst.close();
		conn.close();
		return retour;
	}

	public static void removeSessionDB(String key) throws SQLException, MySqlConnexionException {
		Connection conn=Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="DELETE FROM session WHERE id=\'"+key+"\';";
		inst.executeUpdate(query);
		inst.close();
		conn.close();
	}

	public static Object getId(String sessionkey) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Object getLogin(String sessionkey) {
		// TODO Auto-generated method stub
		return null;
	}

}
