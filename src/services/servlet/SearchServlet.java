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

		Map<String, String[]> arguments=req.getParameterMap();
		
		JSONObject retour=TweetTools.getAllTweets();
		out.print(retour);
	}
}
