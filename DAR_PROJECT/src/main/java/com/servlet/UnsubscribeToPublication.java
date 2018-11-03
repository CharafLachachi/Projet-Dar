package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.services.ShowProfileService;
import com.utils.PasrseJsonUtility;

/**
 * Servlet implementation class UnsubscribeToPublication
 */
@WebServlet("/UnsubscribeToPublication")
public class UnsubscribeToPublication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnsubscribeToPublication() {
   
    	super();
   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
		System.out.println(jb.toString());
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(jb.toString(), JsonObject.class);
		String publicationId = jsonObject.get("publicationId").getAsString();
		String UserId = jsonObject.get("UserId").getAsString();
		String res = ShowProfileService.DeletePublicationOfInterest(UserId,publicationId);
		
		JsonObject result = new JsonObject();
		result.addProperty("result", res);
		response.getWriter().println(result);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
