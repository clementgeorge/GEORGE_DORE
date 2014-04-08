package bd.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bd.Database;
import bd.DatabaseTools;
import bd.exceptions.MySqlConnexionException;

public class TableFriendsTools {

	public static void addFriend(String key, String friend) throws MySqlConnexionException, SQLException {
		Connection conn = Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		int ajouteur=DatabaseTools.getIdOfSessionDB(key);
		String query="INSERT INTO friends VALUES("+ajouteur+", "+friend+",CURRENT_DATE());";
		inst.executeUpdate(query);
		inst.close();
		conn.close();
	}

	public static JSONObject getAllFriends(String key) throws SQLException, MySqlConnexionException, JSONException {
		Connection conn=Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		int ajouteur=DatabaseTools.getIdOfSessionDB(key);
		String query="SELECT vers FROM friends WHERE de="+ajouteur+";";
		inst.executeQuery(query);
		ResultSet rs=inst.getResultSet();
		JSONObject retour=new JSONObject();
		JSONArray tab=new JSONArray();
		while(rs.next()){
			tab.put(rs.getInt(1));
		}
		retour.put("friends", tab);

		rs.close();
		inst.close();
		conn.close();
		return retour;
	}

	public static void removeFriend(String key, String friend) throws MySqlConnexionException, SQLException {
		Connection conn = Database.getMySQLConnection();
		Statement inst=conn.createStatement();
		int enleveur=DatabaseTools.getIdOfSessionDB(key);
		int amiAenleve=DatabaseTools.getIdOfSessionDB(friend);
		String query="DELETE FROM friends WHERE de="+enleveur+" and vers="+amiAenleve+";";
		inst.executeUpdate(query);
		inst.close();
		conn.close();
		
	}

	
}
