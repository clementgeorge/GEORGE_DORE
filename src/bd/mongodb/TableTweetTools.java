package bd.mongodb;

import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import bd.Database;
import bd.DatabaseTools;
import bd.exceptions.MongoDBConnexionException;
import bd.exceptions.MySqlConnexionException;

/**
 * 
 * Contient les m�thodes pour ajouter des tweets dans la base de donn�e
 *
 */
public class TableTweetTools {
	private static String bd = "tweet";
	
	/**
	 * Post un message dans la BD
	 * 
	 * @param sessionkey : la cl� de session
	 * @param message : le tweet
	 * @throws MongoDBConnexionException 
	 * @throws SQLException 
	 * @throws MySqlConnexionException 
	 * @throws BDException
	 */
	public static void addTweet(String sessionkey, String message) throws MongoDBConnexionException, MySqlConnexionException, SQLException{
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		DBCollection coll = Database.getMongoCollection(bd);
		DBObject obj = new BasicDBObject();

		obj.put("auteur_id", DatabaseTools.getIdOfSessionDB(sessionkey));
		obj.put("auteur_login", DatabaseTools.getLoginOfSessionDB(sessionkey));
		obj.put("date", date);
		obj.put("text", message);

		coll.insert(obj);
	}
	
	/**
	 * Renvoit tous les tweets de la base de donn�e mongo
	 * @return un JSONObject de la forme {"tweet 1":"message1";"tweet 2:" ...}
	 * @throws MongoDBConnexionException 
	 * @throws JSONException 
	 */
	public static JSONObject getAllTweets() throws MongoDBConnexionException, JSONException{
		DBCollection coll = Database.getMongoCollection(bd);
		DBCursor c = coll.find();

		JSONObject js = new JSONObject();
		int i = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			js.put("tweet " + i, new JSONObject(o.toString()));
			i++;
		}
		return js;
	}
}
