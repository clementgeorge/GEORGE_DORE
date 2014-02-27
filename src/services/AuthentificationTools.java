package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.Database;
import bd.DatabaseTools;
import bd.exceptions.NoSqlConnectionException;

public class AuthentificationTools {
	
	public static boolean userExists(String login){
		
		try {
			return DatabaseTools.userExistsDB(login);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

	}

	public static void createUser(String login,String password,String prenom, String nom){

		try {
			DatabaseTools.createUserDB(login, password, prenom, nom);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean checkPassword(String login,String password){
		try {
			return DatabaseTools.checkPasswordDB(login, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static int getIDUser(String login){
		try {
			return DatabaseTools.getIDUserDB(login);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

	}

	public static boolean isSession(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void removeSession(String key) {
		// TODO Auto-generated method stub

	}
}
