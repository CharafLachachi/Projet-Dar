package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.beans.Publication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.services.DashboardService;
import com.utils.PasrseJsonUtility;

@WebServlet(name = "dashboard", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet{


	public DashboardServlet() {
		super();
	}
	private static final long serialVersionUID = -5391021665344599461L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
		//System.out.println(jb.toString());
		//Gson gson = new Gson();
		//JsonObject jsonObject = gson.fromJson(jb.toString(), JsonObject.class);
		//String userId = jsonObject.get("userid").getAsString();

		String res = DashboardService.getDashBoardPublicationsByUserId("1"/*userId*/);
//		Gson gson = new Gson();
//		String r = "[";
//		for(int i = 0 ; i < res.length ; i++ ) {
//			JsonObject jsonObject = gson.fromJson(res[i], JsonObject.class);
//			DashboardService.addOwnerName(jsonObject);
//			r = r + jsonObject;
//			if(i<res.length-1) r +=",";
//		}
//		r+="]";
		response.getWriter().println(res);
		
		
//		StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
//		System.out.println(jb.toString());
//		Gson gson = new Gson();
//		JsonObject jsonObject = gson.fromJson(jb.toString(), JsonObject.class);
//		String userId = jsonObject.get("userid").getAsString();
		
//		String res = DashboardService.getDashBoardPublicationsByUserId("1");
//		response.getWriter().println(res);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
