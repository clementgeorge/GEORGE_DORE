package services.servlet;

import java.net.SocketException;

import org.json.JSONObject;

import services.AuthentificationTools;
import services.ServicesTools;

public class LogoutServlet {

	public static JSONObject Logout(String key){
		if(key==null)
			return ServicesTools.error("-", 0);
		if(!AuthentificationTools.isSession(key))
			return ServicesTools.error("pb session", 1);
		AuthentificationTools.removeSession(key);
		return ServicesTools.serviceAccepted();
	}

}
