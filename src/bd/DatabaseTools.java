package bd;

import java.sql.SQLException;

import bd.mysql.TableLoginTools;
import bd.mysql.TableSessionTools;


public class DatabaseTools {

	/**
	 * 
	 * @param login le login de l'utilisateur
	 * @return renvoie si l'utilisateur donné en paramètre existe
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static boolean userExistsDB(String login) throws SQLException, MySqlConnexionException {
		return TableLoginTools.userExistsDB(login);
	}

	/**
	 * crée l'utilisateur dans la base de donnée appropriée
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
	 * Verifie si le mot de passe est celui associé à l'utilisateur donné en parametre
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
	 * Renvoie l'identifiant de l'utilisateur donné en paramètre
	 * @param login
	 * @return
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static int getIDUserDB(String login) throws SQLException, MySqlConnexionException {
		return TableLoginTools.getIDUserDB(login);
	}

	/**
	 * Renvoie si oui ou non la clé donnée en paramètre correspond à une session
	 * @param key
	 * @return
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static boolean isSessionDB(String key) throws SQLException, MySqlConnexionException {
		return TableSessionTools.isSessionDB(key);
	}

	/**
	 * Supprime la session donnée en parametre de la base de donnée
	 * @param key
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static void removeSessionDB(String key) throws SQLException, MySqlConnexionException {
		TableSessionTools.removeSessionDB(key);
		
	}

	/**
	 * Crée une nouvelle session pour l'utilisateur donné en paramètre
	 * @param IdUser l'identifiant de l'utilisateur
	 * @throws SQLException
	 * @throws MySqlConnexionException
	 */
	public static void insertSessionDB(int IdUser) throws SQLException, MySqlConnexionException {
		TableSessionTools.insertSessionDB(IdUser);
		
	}

	/**
	 * Retourne l'identifiant de l'utilisateur associé à la session donnée en paramètre
	 * @param sessionkey
	 * @return
	 * @throws MySqlConnexionException
	 * @throws SQLException
	 */
	public static int getIdOfSessionDB(String sessionkey) throws MySqlConnexionException, SQLException {
		return TableSessionTools.getId(sessionkey);
	}

	/**
	 * Retourne le login de l'utilisateur associé à la session donnée en paramètre
	 * @param sessionkey
	 * @return
	 * @throws MySqlConnexionException
	 * @throws SQLException
	 */
	public static String getLoginOfSessionDB(String sessionkey) throws MySqlConnexionException, SQLException {
		return TableSessionTools.getLogin(sessionkey);
	}

	/**
	 * Retourne le login de l'utilisateur dont l'identifiant est donné en paramètre
	 * @param IdUser l'id de l'utilisateur
	 * @return
	 * @throws MySqlConnexionException
	 * @throws SQLException
	 */
	public static String getLoginDB(int IdUser) throws MySqlConnexionException, SQLException {
		return TableLoginTools.getLogin(IdUser);
	}
}
