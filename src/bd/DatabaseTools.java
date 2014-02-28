package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseTools {

	public static boolean userExistsDB(String login) throws ClassNotFoundException, SQLException{
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
	
	public static void createUserDB(String login,String password,String prenom, String nom) throws SQLException, ClassNotFoundException{
		Connection conn = Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="INSERT INTO login VALUES(null,\'"+login+"\',PASSWORD(\'"+password+"\'),\'"+prenom+
				"\',\'"+nom+"\');";
		inst.executeUpdate(query);
		inst.close();
		conn.close();
	}

	public static boolean checkPasswordDB(String login,String password) throws ClassNotFoundException, SQLException{
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

	public static int getIDUserDB(String login) throws ClassNotFoundException, SQLException{
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

	public static boolean isSessionDB(String key) throws ClassNotFoundException, SQLException {
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

	public static void removeSessionDB(String key) throws ClassNotFoundException, SQLException {
		Connection conn=Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="DELETE FROM session WHERE id=\'"+key+"\';";
		inst.executeUpdate(query);
		inst.close();
		conn.close();
	}


	public static void insertSessionDB() throws SQLException, ClassNotFoundException {
		Connection conn = Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		String query="INSERT INTO session VALUES(null,CURRENT_TIMESTAMP());";
		inst.executeUpdate(query);
		inst.close();
		conn.close();
		
	}
}
