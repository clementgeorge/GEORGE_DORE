package services.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.AuthentificationTools;
import services.ServicesTools;
import services.SessionTools;

public class CreateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 11;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		PrintWriter out= resp.getWriter();
		resp.setContentType("text/plain");
		
		Map<String, String[]> arguments=req.getParameterMap();
		
		if(!arguments.containsKey("login") ||
				!arguments.containsKey("password") ||
				!arguments.containsKey("nom") ||
				!arguments.containsKey("prenom")){
			out.print(ServicesTools.error("Missing parameter(s) in CreateUserServlet", 0).toString());
			return;
			
		}
		
		String login=req.getParameter("login");
		String pass=req.getParameter("password");
		String prenom=req.getParameter("nom");
		String nom=req.getParameter("prenom");
		
		
		
		try{
			boolean is_user=AuthentificationTools.userExists(login);
			if(is_user){
				out.print(ServicesTools.error("L'utilisateur existe deja", 1).toString());
			return;
			}
			AuthentificationTools.createUser(login, pass, prenom, nom);
			JSONObject retour=new JSONObject();
			int id_user=AuthentificationTools.getIDUser(login);
			SessionTools.insertSession(id_user);
			String key=ServicesTools.insertSession(id_user,false);
			retour.put("key", key);
			out.print(retour.toString());
			return;
		}catch(JSONException e){
			
		}
	}
}
