package services;

import java.sql.SQLException;

import bd.DatabaseTools;

public class SessionTools {
	public static boolean isSession(String key) {
		try {
			return DatabaseTools.isSessionDB(key);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void removeSession(String key) {
		try {
			DatabaseTools.removeSessionDB(key);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
