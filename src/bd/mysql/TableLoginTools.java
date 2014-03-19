package bd.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.Database;
import bd.exceptions.MySqlConnexionException;

public class TableLoginTools {

	public static boolean checkPasswordDB(String login,String password) throws MySqlConnexionException, SQLException{
			Connection conn=Database.getMySQLConnection();
			Statement inst=conn.createStatement();
			String query="SELECT id FROM login WHERE login=\'"+login+"\' AND password=PASSWORD(\'"+
					password+"\');";
			inst.executeQuery(query);
			ResultSet rs=inst.getResultSet();
			boolean retour=false;
			
			if(rs.next()){
				retour=true;
			}
			else{
				retour=false;
			}
			
			rs.close();
			inst.close();
			conn.close();
			return retour;
	}

	public static void createUserDB(String login,String password,String prenom, String nom) throws SQLException,MySqlConnexionException{
		Connection conn = Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="INSERT INTO login VALUES(null,\'"+login+"\',PASSWORD(\'"+password+"\'),\'"+prenom+
				"\',\'"+nom+"\',CURRENT_DATE());";
		inst.executeUpdate(query);
		inst.close();
		conn.close();
	}

	public static int getIDUserDB(String login) throws SQLException, MySqlConnexionException{
			Connection conn=Database.getMySQLConnection();
			Statement inst=conn.createStatement();
			String query="SELECT id FROM login WHERE login=\'"+login+"\';";
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

	public static boolean userExistsDB(String login) throws SQLException, MySqlConnexionException{
		Connection conn=Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="SELECT id FROM login WHERE login=\'"+login+"\';";	
		inst.executeQuery(query);
		ResultSet rs=inst.getResultSet();
		boolean retour;
		
		if(rs.next()){
			retour=true;
		}
		else{
			retour=false;
		}
	
		rs.close();
		inst.close();
		conn.close();
		return retour;
	}

	public static String getLogin(int idUser) throws MySqlConnexionException, SQLException {
		Connection conn=Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="SELECT login FROM login WHERE id="+idUser+";";
		inst.executeQuery(query);
		ResultSet rs=inst.getResultSet();
		String retour;
		
		if(rs.next()){
			retour= rs.getString(1);
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
