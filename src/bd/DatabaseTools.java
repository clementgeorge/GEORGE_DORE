package bd;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bd.exceptions.MongoDBConnexionException;
import bd.exceptions.MySqlConnexionException;
import bd.mongodb.TableTweetTools;
import bd.mysql.TableLoginTools;
import bd.mysql.TableSessionTools;


public class DatabaseTools {

	/**
	 * 
	 * @param login le login de l'utilisateur
	 * @return renvoie si l'utilisateur donn� en param�tre existe
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static boolean userExistsDB(String login) throws SQLException, MySqlConnexionException {
		return TableLoginTools.userExistsDB(login);
	}

	/**
	 * cr�e l'utilisateur dans la base de donn�e appropri�e
	 * @param login
	 * @param password
	 * @param prenom
	 * @param nom
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static void createUserDB(String login, String password,
			String prenom, String nom) throws SQLException, MySqlConnexionException {
		TableLoginTools.createUserDB(login, password, prenom, nom);
		
	}

	/**
	 * Verifie si le mot de passe est celui associ� � l'utilisateur donn� en parametre
	 * @param login
	 * @param password
	 * @return
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static boolean checkPasswordDB(String login, String password) throws SQLException, MySqlConnexionException {
		return TableLoginTools.checkPasswordDB(login, password);
	}

	/**
	 * Renvoie l'identifiant de l'utilisateur donn� en param�tre
	 * @param login
	 * @return
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static int getIDUserDB(String login) throws SQLException, MySqlConnexionException {
		return TableLoginTools.getIDUserDB(login);
	}

	/**
	 * Renvoie si oui ou non la cl� donn�e en param�tre correspond � une session
	 * @param key
	 * @return
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static boolean isSessionDB(String key) throws SQLException, MySqlConnexionException {
		return TableSessionTools.isSessionDB(key);
	}

	/**
	 * Supprime la session donn�e en parametre de la base de donn�e
	 * @param key
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static void removeSessionDB(String key) throws SQLException, MySqlConnexionException {
		TableSessionTools.removeSessionDB(key);
		
	}

	/**
	 * Cr�e une nouvelle session pour l'utilisateur donn� en param�tre
	 * @param IdUser l'identifiant de l'utilisateur
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static void insertSessionDB(int idUser) throws SQLException, MySqlConnexionException {
		TableSessionTools.insertSessionDB(idUser);
		
	}

	/**
	 * Retourne l'identifiant de l'utilisateur associ� � la session donn�e en param�tre
	 * @param sessionkey
	 * @return
	 * @throws MySqlConnexionException
	 * @throws SQLException
	 */
	public static int getIdOfSessionDB(String sessionKey) throws MySqlConnexionException, SQLException {
		return TableSessionTools.getId(sessionKey);
	}

	/**
	 * Retourne le login de l'utilisateur associ� � la session donn�e en param�tre
	 * @param sessionkey
	 * @return
	 * @throws MySqlConnexionException
	 * @throws SQLException
	 */
	public static String getLoginOfSessionDB(String sessionKey) throws MySqlConnexionException, SQLException {
		return TableSessionTools.getLogin(sessionKey);
	}

	/**
	 * Retourne le login de l'utilisateur dont l'identifiant est donn� en param�tre
	 * @param IdUser l'id de l'utilisateur
	 * @return
	 * @throws MySqlConnexionException
	 * @throws SQLException
	 */
	public static String getLoginDB(int idUser) throws MySqlConnexionException, SQLException {
		return TableLoginTools.getLogin(idUser);
	}
	
	/**
	 * Permet � l'utilisateur de la session active de 'tweeter'
	 * @param sessionkey la cl� de session
	 * @param message Le message � tweeter
	 * @throws MongoDBConnexionException
	 * @throws MySqlConnexionException
	 * @throws SQLException
	 */
	public static void addTweet(String sessionkey,String message) throws MongoDBConnexionException, MySqlConnexionException, SQLException{
		TableTweetTools.addTweet(sessionkey, message);
	}

	/**
	 * Renvoit la cl� de session de l'utilisateur donn� en param�tre
	 * @param iDUser l'identifiant de l'utilisateur
	 * @return l'entier correspondant � la clef
	 * @throws MySqlConnexionException
	 * @throws SQLException
	 */
	public static int getKeySession(int iDUser) throws MySqlConnexionException, SQLException {
		return TableSessionTools.getKey(iDUser);
	}
	
	/**
	 * Renvoit tous les tweets de la base de donn�e mongo
	 * @return un JSONObject de la forme {"tweet 1":"message1";"tweet 2:" ...}
	 * @throws MongoDBConnexionException 
	 * @throws JSONException 
	 */
	public static JSONObject getAllTweets() throws MongoDBConnexionException, JSONException{
		return TableTweetTools.getAllTweets();
	}
}
