package services.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.FriendsTools;
import services.ServicesTools;
import services.SessionTools;

public class GetAllFriendsServlet extends HttpServlet{
	
	private static final long serialVersionUID = 16;

	@Override
	protected void doGet(HttpServletRequest req , HttpServletResponse resp)
	throws ServletException, IOException{
		
		PrintWriter out= resp.getWriter();
		resp.setContentType("text/plain");
		
		// On recupere les parametres de l'url
		String key=req.getParameter("key");

		if(key==null){
			out.print(ServicesTools.error("Manque arguments dans le GetAllfriends servlet", 0).toString());
			return;
		}
		if(!SessionTools.isSession(key)){
			out.print(ServicesTools.error("Session inactive dans le GetAllfriends", 0).toString());
			return;
		}
		JSONObject retour=FriendsTools.getAllFriends(key);
		out.print(retour.toString());
	}
}
