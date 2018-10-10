package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.services.SignService;
import com.utils.PasrseJsonUtility;

import helpers.models.SignupModel;

/**
 * Servlet implementation class SignUpServlet
 * 
 * @author Lachachi charaf
 */
@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected SignService SignService;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		SignService = new SignService();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
		  
		  System.out.println(jb.toString());
		  
		  SignupModel SignUpObject = (new Gson().fromJson(jb.toString(), SignupModel.class));
		  
		JsonObject created_abonne_response_json = SignService.createAbonne(
				SignUpObject.getUsername(),
				SignUpObject.getFirstname(),
				SignUpObject.getFirstname(), 
				SignUpObject.getEmail(),
				SignUpObject.getPassword(),
				SignUpObject.getCities());

		created_abonne_response_json.addProperty("message", 200);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(created_abonne_response_json);
	}


}
