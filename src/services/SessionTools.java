package services;

import java.sql.SQLException;

import bd.DatabaseTools;
import bd.exceptions.MySqlConnexionException;

public class SessionTools {
	public static boolean isSession(String key) {
		try {
			return DatabaseTools.isSessionDB(key);
		} catch (SQLException | MySqlConnexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void removeSession(String key) {
		try {
			DatabaseTools.removeSessionDB(key);
		} catch (SQLException | MySqlConnexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void insertSession(int IdUser) {
		try {
			DatabaseTools.insertSessionDB(IdUser);
		} catch (SQLException | MySqlConnexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getKeySession(int IDUser) {
		try {
			return DatabaseTools.getKeySession(IDUser);
		} catch (MySqlConnexionException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
