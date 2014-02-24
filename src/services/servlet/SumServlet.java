package services.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.AuthentificationTools;
import services.ServicesTools;


public class SumServlet extends HttpServlet implements Servlet {
	@Override
	protected void doGet(HttpServletRequest req , HttpServletResponse resp)
	throws ServletException, IOException{
		int Sum;
		Map<String, String[]> pers=req.getParameterMap();
		if(pers.containsKey("a") && pers.containsKey("b") && pers.containsKey("op")){
			String valueA=req.getParameter("a");
			String valueB=pers.get("b")[0]; //Une autre facon de faire
			String op=req.getParameter("op");
			if(op.equals("somme"))
				Sum=Integer.parseInt(valueA)+Integer.parseInt(valueB);
			else if(op.equals("soustraction"))
				Sum=Integer.parseInt(valueA)-Integer.parseInt(valueB);
			else if(op.equals("division"))
				Sum=Integer.parseInt(valueA)/Integer.parseInt(valueB);
			else if(op.equals("multiplication"))
				Sum=Integer.parseInt(valueA)*Integer.parseInt(valueB);
			else 
				Sum=0;
		}
		else{
			Sum=0;
		}
		PrintWriter out= resp.getWriter();
		resp.setContentType("text/plain");
		JSONObject retour=new JSONObject();
		try {
			retour.put("output", Sum);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(retour.toString());
	}
}
