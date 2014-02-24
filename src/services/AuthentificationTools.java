package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.Database;
import bd.exceptions.NoSqlConnectionException;

public class AuthentificationTools {

	public static boolean userExists(String login){
		try {
			Connection conn=Database.getMySQLConnection();
			boolean retour;
			String query="SELECT id FROM login WHERE login=\'"+login+"\';";
			Statement inst=conn.createStatement();
			inst.executeQuery(query);
			ResultSet rs=inst.getResultSet();
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


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public static void createUser(String login,String password,String prenom, String nom) throws SQLException, NoSqlConnectionException{

		Connection conn;
		try {
			conn = Database.getMySQLConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		String query="INSERT INTO login VALUES(null,\'"+login+"\',PASSWORD(\'"+password+"\'),\'"+prenom+
				"\',\'"+nom+"\');";
		Statement inst=conn.createStatement();
		inst.executeUpdate(query);
		inst.close();
		conn.close();


	}

	public static boolean checkPassword(String login,String password){
		try{
			Connection conn=Database.getMySQLConnection();

			boolean retour=false;
			String query="SELECT id FROM login WHERE login=\'"+login+"\' AND password=PASSWORD(\'"+
					password+"\');";
			Statement inst=conn.createStatement();
			inst.executeQuery(query);
			ResultSet rs=inst.getResultSet();
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
		}catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static int getIDUser(String login){
		try{
			Connection conn=Database.getMySQLConnection();
			int retour;
			String query="SELECT id FROM login WHERE login=\'"+login+"\';";
			Statement inst=conn.createStatement();
			inst.executeQuery(query);
			ResultSet rs=inst.getResultSet();
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
		}catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

	}

	public static boolean isSession(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void removeSession(String key) {
		// TODO Auto-generated method stub

	}
}
