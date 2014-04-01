package services.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.ServicesTools;
import services.SessionTools;
import services.TweetTools;

public class AddTweetServlet extends HttpServlet{

	private static final long serialVersionUID = 16;

	@Override
	protected void doGet(HttpServletRequest req , HttpServletResponse resp)
			throws ServletException, IOException{

		PrintWriter out= resp.getWriter();
		resp.setContentType("text/plain");

		Map<String, String[]> arguments=req.getParameterMap();

		if(!arguments.containsKey("session")){
			out.print(ServicesTools.error("Missing sessionKey in parameters of AddTweetServlet request", 0).toString());
			return;
		}
		
		if(!arguments.containsKey("message")){
			out.print(ServicesTools.error("Missing message in parameters of AddTweetServlet request", 1).toString());
			return;
		}
		
		String sessionKey=req.getParameter("session");
		String message=req.getParameter("message");
		
		if(!SessionTools.isSession(sessionKey)){
			out.print(ServicesTools.error("Expired Session",2).toString());
			return;
		}
		
		if(message.compareTo("")==0){
			out.print(ServicesTools.error("Empty Message",3).toString());
			return;
		}
		
		JSONObject retour=TweetTools.addTweet(sessionKey, message);
		out.print(retour.toString());
	}

}
