package bd.mongodb;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBList;
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
	 * Renvoit tous les tweets de la base de donn�e mongo
	 * @return un JSONObject de la forme {"tweets":[{message1},{message2}, ...}
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



	public static JSONObject getResearchTweets(String recherche) throws MongoDBConnexionException, JSONException {
		DBCollection coll = Database.getMongoCollection(bd);
		JSONObject js = new JSONObject();
		JSONArray ar=new JSONArray();
		String[] tabrecherche = recherche.split(" ");
		if(tabrecherche.length==0){
			return getAllTweets();
		}
		
		BasicDBList and=new BasicDBList();
		BasicDBObject query=null;
		for(int i=0;i<tabrecherche.length;i++){
			Pattern regex=Pattern.compile(tabrecherche[i],Pattern.CASE_INSENSITIVE);
			and.add(new BasicDBObject("text",regex));
			
		}
		query=new BasicDBObject("$and",and);
		
		
		
		DBCursor c = coll.find(query);
		
		while (c.hasNext()) {
			DBObject o = c.next();
			ar.put(o);
		}
		
		js.put("tweets", ar);
		return js;
	}
}
