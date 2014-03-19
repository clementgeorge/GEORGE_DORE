package services;

import java.sql.SQLException;

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
}
