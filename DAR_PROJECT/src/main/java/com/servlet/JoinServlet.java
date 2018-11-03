package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.services.JoinService;
import com.utils.PasrseJsonUtility;

@WebServlet("/join")
public class JoinServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public JoinServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
		System.out.println(jb.toString());
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(jb.toString(), JsonObject.class);
		
		int PublicationId = Integer.parseInt(jsonObject.get("pubid").getAsString());
		int userId = Integer.parseInt(jsonObject.get("userid").getAsString());
		
		boolean r = JoinService.joinPublicationByIds(PublicationId, userId);
		
		JsonObject res = new JsonObject();
		res.addProperty("result", true);
		response.getWriter().println(res);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
