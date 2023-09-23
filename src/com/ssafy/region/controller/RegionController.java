package com.ssafy.region.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.enjoytrip.model.AttractionInfoDto;
import com.ssafy.enjoytrip.model.service.AttractionService;
import com.ssafy.enjoytrip.model.service.AttractionServiceImpl;
import com.ssafy.region.model.RegionDto;
import com.ssafy.region.model.service.RegionService;
import com.ssafy.region.model.service.RegionServiceImpl;

@WebServlet("/region")
public class RegionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static RegionService service = RegionServiceImpl.getRegionService();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String path = "";
		if ("sido".equals(action)) {
			path = getAllSido(request, response);
			forward(request, response, path);
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}
	
	private String getAllSido(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<RegionDto> list =  service.getAllSido();
			request.setAttribute("sido", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "목록 로드 실패");
			return "/error/error.jsp";
		}
		return "index.jsp";
	}
}
