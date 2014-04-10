package services;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.DBObject;

import bd.DatabaseTools;
import bd.exceptions.MongoDBConnexionException;
import bd.exceptions.MySqlConnexionException;

public class TweetTools {

	public static JSONObject addTweet(String sessionKey,String message){
		try {
			return DatabaseTools.addTweet(sessionKey, message);
		} catch (MongoDBConnexionException | MySqlConnexionException
				| SQLException | JSONException e) {
			return ServicesTools.error("Erreur lors de l'ajout du commentaire", 1);
		}
	}
	
	public static JSONObject getAllTweets(){
		try {
			return DatabaseTools.getAllTweets();
		} catch (MongoDBConnexionException | JSONException e) {
			e.printStackTrace();
			return ServicesTools.error("Requete impossible", 2);
		}
	}

	

	public static JSONObject getResearchTweets(String recherche) {
		try {
			return DatabaseTools.getResearchTweets(recherche);
		} catch (MongoDBConnexionException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ServicesTools.error("Requete impossible", 2);
		}
	}
}
