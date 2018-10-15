package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.DashboardService;
import com.services.SearchHotelsService;
import com.utils.PasrseJsonUtility;

@WebServlet(name = "dashboard", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet{


	public DashboardServlet() {
		super();
		this.dashboardService = new DashboardService();
	}
	private static final long serialVersionUID = -5391021665344599461L;
	protected DashboardService dashboardService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
		System.out.println(jb.toString());
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
