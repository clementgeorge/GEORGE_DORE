package bd.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.Database;
import bd.DatabaseTools;
import bd.exceptions.MySqlConnexionException;

public class TableSessionTools {

	/**
	 * Crée la session de l'utilisateur
	 * @param IDUser l'id de l'utilisateur
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static void insertSessionDB(int IDUser) throws SQLException, MySqlConnexionException {
		Connection conn = Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="INSERT INTO session VALUES(null,"+IDUser+",CURRENT_TIMESTAMP());";
		inst.executeUpdate(query);
		inst.close();
		conn.close();
		
	}

	public static boolean isSessionDB(String key) throws SQLException, MySqlConnexionException {
		Connection conn=Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="SELECT id FROM session WHERE clef=\'"+key+"\';";
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
		String query="DELETE FROM session WHERE clef=\'"+key+"\';";
		inst.executeUpdate(query);
		inst.close();
		conn.close();
	}

	public static int getId(String sessionkey) throws MySqlConnexionException, SQLException {
		Connection conn=Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="SELECT id FROM session WHERE clef=\'"+sessionkey+"\';";
		inst.executeQuery(query);
		ResultSet rs=inst.getResultSet();
		int retour;
		
		if(rs.next()){
			retour= rs.getInt(1);
		}
		else{
			retour=-1;
		}

		rs.close();
		inst.close();
		conn.close();
		return retour;
	}

	public static String getLogin(String sessionkey) throws MySqlConnexionException, SQLException {
		Connection conn=Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="SELECT id FROM session WHERE clef="+sessionkey+";";
		inst.executeQuery(query);
		ResultSet rs=inst.getResultSet();
		String retour;
		
		if(rs.next()){
			retour=DatabaseTools.getLoginDB(rs.getInt(1));
		}
		else{
			retour=null;
		}

		rs.close();
		inst.close();
		conn.close();
		return retour;
	}

}
