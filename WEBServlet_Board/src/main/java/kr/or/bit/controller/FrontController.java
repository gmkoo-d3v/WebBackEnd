package kr.or.bit.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;
import kr.or.bit.service.BoardService;
import kr.or.bit.utils.ThePager;

@WebServlet("*.do")

public class FrontController extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	
	 
	    public void init() throws ServletException {
	        super.init();
	      
	    }
	
	
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String viewPage="/WEB-INF/views/list.jsp";
		 RequestDispatcher dis = request.getRequestDispatcher(viewPage);
	        dis.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
	
	
}

	