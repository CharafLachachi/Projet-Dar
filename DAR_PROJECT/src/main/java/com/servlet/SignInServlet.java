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

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.beans.Abonne;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.services.SignService;
import com.utils.PasrseJsonUtility;

import helpers.models.LoginModel;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignIn")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected SignService SignService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

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

		LoginModel loginObject = (new Gson().fromJson(jb.toString(), LoginModel.class));

		Abonne abonne = SignService.loginAbonne(loginObject.getEmail(), loginObject.getPassword());

		JsonObject created_abonne_response_json = new JsonObject();
		System.err.println(abonne);
		created_abonne_response_json.addProperty("token", getJWT_Token(abonne.getUsername(), abonne.getABONNE_id(),
				abonne.getFirstname(), abonne.getEmail(), abonne.getLastname()));
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(created_abonne_response_json);
	}

	public String getJWT_Token(String username, int userId, String firstName, String email, String lastName) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			Builder token = JWT.create();
			token.withClaim("id", userId);
			token.withClaim("email", email);
			token.withClaim("firstname", firstName);
			token.withClaim("username", username);
			token.withClaim("lastname", lastName);

			return token.sign(algorithm);
		} catch (JWTCreationException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
		}
		return null;
	}

}
