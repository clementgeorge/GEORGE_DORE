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

public class AddFriendServlet extends HttpServlet{

	private static final long serialVersionUID = 14;
	
	@Override
	protected void doGet(HttpServletRequest req , HttpServletResponse resp)
	throws ServletException, IOException{
		
		PrintWriter out= resp.getWriter();
		resp.setContentType("text/plain");
		
		// On recupere les parametres de l'url
		String key=req.getParameter("key");
		String friend=req.getParameter("friend");

		if(key==null || friend ==null){
			out.print(ServicesTools.error("Manque arguments dans le Addfriends servlet", 0).toString());
			return;
		}
		if(!SessionTools.isSession(key)){
			out.print(ServicesTools.error("Session inactive dans le Addfriends", 0).toString());
			return;
		}
		JSONObject retour=FriendsTools.addFriend(key,friend);
		out.print(retour.toString());
	}

}
