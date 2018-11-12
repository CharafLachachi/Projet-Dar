package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
 * Servlet implementation class GetProfilePicture
 */
@WebServlet("/GetProfilePicture")
public class GetProfilePicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProfilePicture() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String user_id = request.getParameter("user_id");		
		
		if(user_id == null) {
			
			StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(jb.toString(), JsonObject.class);
			user_id = jsonObject.get("user_id").getAsString();
			
		}
		
		OutputStream os = response.getOutputStream();
		byte[] my_image = ShowProfileService.getProfilePicture(user_id);
		if(my_image!= null)
		os.write(my_image);
		os.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
