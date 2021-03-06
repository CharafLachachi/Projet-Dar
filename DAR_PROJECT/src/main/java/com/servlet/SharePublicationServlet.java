package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.SharePublicationService;
import com.utils.PasrseJsonUtility;

/**
 * Servlet implementation class SharePublication
 */
@WebServlet(name = "sharePublication", urlPatterns = { "/sharePublication" })
public class SharePublicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SharePublicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuffer pubRequestAsJson = PasrseJsonUtility.getRequestJson(request);
		System.out.println(pubRequestAsJson);
		boolean resp = SharePublicationService.addPublictaion(pubRequestAsJson);
		response.getWriter().append("{\"Served at:\" : \"hello \" }");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
