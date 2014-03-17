package services;

import java.sql.SQLException;

import bd.DatabaseTools;
import bd.MySqlConnexionException;

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

	public static void insertSession() {
		try {
			DatabaseTools.insertSessionDB();
		} catch (SQLException | MySqlConnexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
