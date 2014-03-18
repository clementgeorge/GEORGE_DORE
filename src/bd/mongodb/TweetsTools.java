package bd.mongodb;

import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import bd.Database;
import bd.DatabaseTools;
import bd.MongoDBConnexionException;
import bd.MySqlConnexionException;

/**
 * 
 * Contient les méthodes pour ajouter des tweets dans la base de donnée
 *
 */
public class TweetsTools {
	private static String bd = "tweet";
	
	/**
	 * Post un message dans la BD
	 * 
	 * @param sessionkey : la clé de session
	 * @param message : le tweet
	 * @throws MongoDBConnexionException 
	 * @throws SQLException 
	 * @throws MySqlConnexionException 
	 * @throws BDException
	 */
	public static void posterMessage(String sessionkey, String message) throws MongoDBConnexionException, MySqlConnexionException, SQLException{
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
}
