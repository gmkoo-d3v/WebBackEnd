package com.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class FrontBoardController
 * 
 * urlPatterns = { "/Board" } 
 * 이것은 annotation
 * 이게 web.xml에서의 
 *   <servlet>
  	<servlet-name>simplecontroller</servlet-name>
  	<servlet-class>com.SimpleController</servlet-class>
  </servlet>
  <servlet-mapping>
  	  <servlet-name>simplecontroller</servlet-name>
  	  <url-pattern>/simple</url-pattern>
  </servlet-mapping>
 * 와 같다. 
 * 
 */
@WebServlet(
		description = "게시판Front 역할을 하는 servlet 입니다", 
		urlPatterns = { "/Board" }
   )
public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontBoardController() {
        super();

    }
	private void doProcess(HttpServletRequest request, HttpServletResponse response, String method) throws ServletException, IOException {
		// doGet, doPost의 논리가 동일하다면 doProcess
		System.out.println("클라이언트의 요청 방식 : " + method);
		
		//1.  한글처리 나중에는 filter에 통합시킬꺼임 
		request.setCharacterEncoding("UTF-8");
		
		//2. 데이터 받기
		// 2.1 데이터 받는 방식 2가지 : command 방식 , url 방식 (선호)
		// data.do?cmd=write cmd값이 write 면 글쓰기로 판단
		// list.do 하면 전체 주소값을 뽑아냄 파라메터가 없기에
		// list.do 마지막 주소를 추출해서 의미를 부여
		
		String cmd = request.getParameter("cmd");
		
		String viewPage=null;
		// cmd > null       > error.jsp
		// cmd > boardlist  > list.jsp
		// cmd > boardwrite > write.jsp 이런 규칙은 개발자가 정의 하는 것
		
		//3. 비지니스 로직 수행( 요구사항 판단) : 암호화, 관리자 페이지, db 연결해서 CRUD 작업
		
		if(cmd == null ) {
			viewPage = "/error/error.jsp";
		}else if(cmd.equals("boardlist")) {
			/*
			 Model 만들어져 있다면 
			 DTO Board
			 DAO BoardDao > CRUD 함수
			 
			 BoardDao boardDao = new BoardDao();
			 List<Board> list = boardDao.getAllBoardList();
			 이게 오면 view에 전달
			 forward > view > jsp : request.setAttribute("list");
			 */
    		viewPage = "/board/boardlist.jsp";
			
    	}else if(cmd.equals("boardwrite")) {
    		viewPage = "/board/boardwrite.jsp";
    	}else if(cmd.equals("boarddelete")) {
    		//viewPage = "board/boardwrite.jsp"; 가정하고
    	}else if(cmd.equals("login")) {
    		viewPage = "/WEB-INF/views/login/login.jsp";
    	}else {
    		viewPage = "/error/error.jsp";
    	}
		//4. 결과 저장( 데이터 담기)
		// 전체 페이지 : session
		// 현재 forward  : request
		//request.setAttribute("list",list); 담았다고 치고
		
		//5. veiw 지정
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		
		//6. forward (request , response view 전달 ) 화면 출력
		dis.forward(request, response);
    	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response,"GET");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response,"POST");
	}

}