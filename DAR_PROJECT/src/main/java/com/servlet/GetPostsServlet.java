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
 * Servlet implementation class GetPostsServlet
 */
@WebServlet("/GetPosts")
public class GetPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPostsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(jb.toString(), JsonObject.class);
		String userId = jsonObject.get("userid").getAsString();
		String Posts_type = jsonObject.get("Posts_type").getAsString();
		
		String res = Posts_type.equals("Own") ? ShowProfileService.GetPersonnalPosts(userId) : ShowProfileService.GetPostsOfInterst(userId);
		response.getWriter().println(res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}