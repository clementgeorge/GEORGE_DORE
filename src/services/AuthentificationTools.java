package services;

import java.sql.SQLException;

import bd.DatabaseTools;
import bd.MySqlConnexionException;

public class AuthentificationTools {
	
	public static boolean userExists(String login){
		
		try {
			return DatabaseTools.userExistsDB(login);
		} catch (SQLException | MySqlConnexionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

	}

	public static void createUser(String login,String password,String prenom, String nom){

		try {
			DatabaseTools.createUserDB(login, password, prenom, nom);
		} catch (SQLException | MySqlConnexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean checkPassword(String login,String password){
		try {
			return DatabaseTools.checkPasswordDB(login, password);
		} catch (SQLException | MySqlConnexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static int getIDUser(String login){
		try {
			return DatabaseTools.getIDUserDB(login);
		} catch (SQLException | MySqlConnexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

	}
	

	
}
