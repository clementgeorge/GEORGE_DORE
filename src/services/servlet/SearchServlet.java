package services.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import services.ServicesTools;
import services.SessionTools;
import services.TweetTools;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 17;

	@Override
	protected void doGet(HttpServletRequest req , HttpServletResponse resp)
			throws ServletException, IOException{

		PrintWriter out= resp.getWriter();
		resp.setContentType("text/plain");

		String recherche=req.getParameter("recherche");

		if(recherche==null){
			out.print(ServicesTools.error("Manque arguments dans le search servlet", 0).toString());
			return;
		}
		
		if(!recherche.equals("")){ //filtrage par recherche uniquement
			JSONObject retour=TweetTools.getResearchTweets(recherche);
			out.print(retour);
			return;
		}
		
		JSONObject retour=TweetTools.getAllTweets();
		out.print(retour);
		return;
	}
}
