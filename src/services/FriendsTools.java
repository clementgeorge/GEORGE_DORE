package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bd.DatabaseTools;
import bd.exceptions.MySqlConnexionException;

public class FriendsTools {

	public static JSONObject addFriend(String key, String friend) {
		try {
			DatabaseTools.addFriend(key,friend);
			return ServicesTools.serviceAccepted();
		} catch (MySqlConnexionException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ServicesTools.error("erreur addFriend", 1);
		}
	}

	public static JSONObject getAllFriends(String key) {
		try {
			return DatabaseTools.getAllFriends(key);
		} catch (SQLException | MySqlConnexionException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ServicesTools.error("erreur getAllFriends", 1);
		}
	}

	public static JSONObject removeFriend(String key, String friend) {
		try {
			DatabaseTools.removeFriend(key,friend);
			return ServicesTools.serviceAccepted();
		} catch (MySqlConnexionException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ServicesTools.error("erreur removeFriend", 1);
		}
		
	}

}
