package bd.mongodb;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONArray;
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
 * Contient les méthodes pour ajouter des tweets dans la base de donnée
 *
 */
public class TableTweetTools {
	private static String bd = "tweet";
	
	/**
	 * Post un message dans la BD
	 * 
	 * @param sessionkey : la clé de session
	 * @param message : le tweet
	 * @return 
	 * @throws MongoDBConnexionException 
	 * @throws SQLException 
	 * @throws MySqlConnexionException 
	 * @throws JSONException 
	 * @throws BDException
	 */
	public static JSONObject addTweet(String sessionkey, String message) throws MongoDBConnexionException, MySqlConnexionException, SQLException, JSONException{
		Date now=new Date();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String s=date.format(now);

		DBCollection coll = Database.getMongoCollection(bd);
		DBObject obj = new BasicDBObject();

		obj.put("auteur_id", DatabaseTools.getIdOfSessionDB(sessionkey));
		obj.put("auteur_login", DatabaseTools.getLoginOfSessionDB(sessionkey));
		obj.put("date", s);
		obj.put("text", message);

		coll.insert(obj);
		JSONObject retour= new JSONObject();
		retour.put("tweet", obj);
		
		return retour;
	}
	
	/**
	 * Renvoit tous les tweets de la base de donnée mongo
	 * @return un JSONObject de la forme {"tweet 1":"message1";"tweet 2:" ...}
	 * @throws MongoDBConnexionException 
	 * @throws JSONException 
	 */
	public static JSONObject getAllTweets() throws MongoDBConnexionException, JSONException{
		DBCollection coll = Database.getMongoCollection(bd);
		DBCursor c = coll.find();

		JSONObject js = new JSONObject();
		JSONArray ar=new JSONArray();
		while (c.hasNext()) {
			DBObject o = c.next();
			ar.put(o);
		}
		js.put("tweets", ar);
		return js;
	}
}
