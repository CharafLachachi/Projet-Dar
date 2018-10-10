package com.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.services.SearchHotelsService;
import com.utils.PasrseJsonUtility;

import helpers.models.SearchRequestModel;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "search", urlPatterns = { "/search" })
public class SearchHotelsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected SearchHotelsService searchHotelService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchHotelsServlet() {
        super();
        searchHotelService = new SearchHotelsService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
		System.err.println(jb.toString());
		SearchRequestModel searchObject = (new Gson().fromJson(jb.toString(), SearchRequestModel.class));
		
		
		System.out.println(searchObject.getCities().toString());
		JsonObject hoteslResponse = searchHotelService.getHotels(searchObject);
		
		response.getWriter().print(hoteslResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
