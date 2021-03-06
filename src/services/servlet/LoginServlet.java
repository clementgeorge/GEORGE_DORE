package services.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import bd.DatabaseTools;
import services.AuthentificationTools;
import services.ServicesTools;
import services.SessionTools;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 12;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String login=req.getParameter("login");
		String pass=req.getParameter("password");
		
		PrintWriter out= resp.getWriter();
		resp.setContentType("text/plain");
		
		if(login == null || pass==null){
			out.print(ServicesTools.error("Mauvais argument dans le login servlet", 0).toString());
			return;
			
		}try{
			boolean is_user=AuthentificationTools.userExists(login);
			if(!is_user){
				out.print(ServicesTools.error("Utilisateur inconnu", 1));
				return;
			}
			boolean pass_ok=AuthentificationTools.checkPassword(login, pass);
			if(!pass_ok){
				out.print(ServicesTools.error("Le mot de passe est faux", 2));
				return;
			}
			int id_user=AuthentificationTools.getIDUser(login);
			SessionTools.insertSession(id_user);
			JSONObject retour=new JSONObject();
			int key=SessionTools.getKeySession(id_user);
			retour.put("key", key);
			retour.put("login", login);
			retour.put("id", id_user);
			out.print(retour.toString());
			
			
		}catch(JSONException e){
			out.print(ServicesTools.error(e.getMessage(),0).toString());
		}
	}
	
}
