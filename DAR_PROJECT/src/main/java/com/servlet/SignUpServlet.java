package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.services.SignUpService;

/**
 * Servlet implementation class SignUpServlet
 * 
 * @author Lachachi charaf
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected SignUpService signUpService;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		signUpService = new SignUpService();

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
		
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }
		  System.out.println(jb.toString());
		JsonObject created_abonne_response_json = signUpService.createAbonne(request.getParameter("username"),
				request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("email"),
				request.getParameter("password"));

		//created_abonne_response_json.addProperty("message", 200);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		//response.getWriter().print(created_abonne_response_json);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

		PrintWriter out = res.getWriter();

		res.setContentType("text/plain");

		Enumeration<String> parameterNames = req.getParameterNames();
		
		while (parameterNames.hasMoreElements()) {

			String paramName = parameterNames.nextElement();
			out.write(paramName);

			out.write("n");

			String[] paramValues = req.getParameterValues(paramName);

			for (int i = 0; i < paramValues.length; i++) {

				String paramValue = paramValues[i];
				System.out.println("*******"+paramName+"   "+ paramValue);
				out.write("t" + paramValue);

				out.write("n");

			}

		}
		out.close();

	}
	
	public static String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}

}
