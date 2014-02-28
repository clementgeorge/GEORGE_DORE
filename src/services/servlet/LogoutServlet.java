package services.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.ServicesTools;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 13;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String key=req.getParameter("key");

		PrintWriter out= resp.getWriter();
		resp.setContentType("text/plain");

		if(key == null){
			out.print(ServicesTools.error("Missing parameter in LogoutServlet", 0).toString());
			return;

		}	

		JSONObject retour=ServicesTools.Logout(key);
		out.print(retour.toString());

	}

}
