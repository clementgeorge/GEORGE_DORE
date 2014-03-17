package bd;

import java.sql.SQLException;

import bd.mysql.TableLoginTools;
import bd.mysql.TableSessionTools;


public class DatabaseTools {

	public static boolean userExistsDB(String login) throws SQLException, MySqlConnexionException {
		return TableLoginTools.userExistsDB(login);
	}

	public static void createUserDB(String login, String password,
			String prenom, String nom) throws SQLException, MySqlConnexionException {
		TableLoginTools.createUserDB(login, password, prenom, nom);
		
	}

	public static boolean checkPasswordDB(String login, String password) throws SQLException, MySqlConnexionException {
		return TableLoginTools.checkPasswordDB(login, password);
	}

	public static int getIDUserDB(String login) throws SQLException, MySqlConnexionException {
		return TableLoginTools.getIDUserDB(login);
	}

	public static boolean isSessionDB(String key) throws SQLException, MySqlConnexionException {
		return TableSessionTools.isSessionDB(key);
	}

	public static void removeSessionDB(String key) throws SQLException, MySqlConnexionException {
		TableSessionTools.removeSessionDB(key);
		
	}

	public static void insertSessionDB() throws SQLException, MySqlConnexionException {
		TableSessionTools.insertSessionDB();
		
	}

	public static Object getIdOfSession(String sessionkey) {
		return TableSessionTools.getId(sessionkey);
	}

	public static Object getLoginOfSession(String sessionkey) {
		return TableSessionTools.getLogin(sessionkey);
	}
}
