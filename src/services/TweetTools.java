package services;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bd.DatabaseTools;
import bd.exceptions.MongoDBConnexionException;
import bd.exceptions.MySqlConnexionException;

public class TweetTools {

	public static void addTweet(String sessionKey,String message){
		try {
			DatabaseTools.addTweet(sessionKey, message);
		} catch (MongoDBConnexionException | MySqlConnexionException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static JSONObject getAllTweets(){
		try {
			return DatabaseTools.getAllTweets();
		} catch (MongoDBConnexionException | JSONException e) {
			e.printStackTrace();
			return ServicesTools.error("Requete impossible", 1);
		}
	}
}
