package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.services.CommentService;
import com.utils.PasrseJsonUtility;

/**
 * Servlet implementation class GetCommentsServlet
 */
@WebServlet("/GetComments")
public class GetCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCommentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*  This servlet is used in the android app version */
		
		StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
		Gson gson = new Gson();
		String j = jb.toString().substring(1, jb.toString().length()-1);
		JsonObject jsonObject = gson.fromJson(j, JsonObject.class);
		
		List<JsonObject> comments = CommentService.getComments(jsonObject.get("pub_id").getAsString());
		System.out.println(comments.size());
		response.getWriter().println(comments);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}