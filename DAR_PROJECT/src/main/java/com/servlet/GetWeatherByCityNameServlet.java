package com.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.services.GetWeatherByCityService;

/**
 * Servlet implementation class GetWeatherByCityNameServlet
 */
@WebServlet(name = "GetWeatherByCityName", urlPatterns = {"/GetWeatherByCityName"})
public class GetWeatherByCityNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GetWeatherByCityService get_weather_by_name_service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWeatherByCityNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------"+request.getParameter("city_name"));
		JsonObject json_wheater_by_name= new JsonObject();
		try {
			if (!request.getParameter("city_name").equals("")) {
				get_weather_by_name_service = new GetWeatherByCityService();
				json_wheater_by_name = get_weather_by_name_service
						.getWeatherByCityName(request.getParameter("city_name"));
			} else {
				json_wheater_by_name = null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json_wheater_by_name);
			//response.getWriter().append("Served Successful at: ").append(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace(); // local debug
			request.setAttribute("error", e); // remote debug
			response.getWriter().print(e);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
