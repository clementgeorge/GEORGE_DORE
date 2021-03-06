package services;

import org.json.JSONException;
import org.json.JSONObject;


public class ServicesTools {
	public static JSONObject error(String message,int code){
		JSONObject retour=new JSONObject();
		try {
			retour.put("error", message);
			retour.put("code", code);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retour;
		
	}

	public static String insertSession(int id_user, boolean b) {
		// TODO Auto-generated method stub
		return "";
	}

	public static JSONObject serviceAccepted() {
		JSONObject retour=new JSONObject();
		try {
			retour.put("state","Service Accepted");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retour;
	}
	
	public static JSONObject Logout(String key){
		if(key==null)
			return ServicesTools.error("Missing the key session parameter, to close ", 0);
		if(!SessionTools.isSession(key))
			return ServicesTools.error("The session still not exist, impossible to close", 1);
		SessionTools.removeSession(key);
		return ServicesTools.serviceAccepted();
	}
}
