package bd;

import java.sql.SQLException;


public class DatabaseTools {

	public static boolean userExistsDB(String login) throws ClassNotFoundException, SQLException {
		return TableLoginTools.userExistsDB(login);
	}

	public static void createUserDB(String login, String password,
			String prenom, String nom) throws ClassNotFoundException, SQLException {
		TableLoginTools.createUserDB(login, password, prenom, nom);
		
	}

	public static boolean checkPasswordDB(String login, String password) throws ClassNotFoundException, SQLException {
		return TableLoginTools.checkPasswordDB(login, password);
	}

	public static int getIDUserDB(String login) throws ClassNotFoundException, SQLException {
		return TableLoginTools.getIDUserDB(login);
	}

	public static boolean isSessionDB(String key) throws ClassNotFoundException, SQLException {
		return TableSessionTools.isSessionDB(key);
	}

	public static void removeSessionDB(String key) throws ClassNotFoundException, SQLException {
		TableSessionTools.removeSessionDB(key);
		
	}
}
