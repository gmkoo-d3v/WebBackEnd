package kr.or.kosa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.dao.MemoDao;
import kr.or.kosa.dto.Memo;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/MemoServlet")
public class MemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public MemoServlet() {
        super();
    }    
        
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
    	  	1. 한글처리
    	  	2. 데이터 받기
  	3. 요구사항 판단 (frontController : 글쓰기, 목록보기 판단) 을해야되는데 지금 여기서 sublet이 글쓰기니 필요없다.
    	  	4. DB 연결하자
    	  	5. DAO 객체 생성
    	  	6. DAO 함수 실행
    	  	7. 그 결과에 대한 처리
    	  	 
    	 */
    	System.out.println("MemoServlet");
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF8");
    	PrintWriter out = response.getWriter();
    	
    	String id = request.getParameter("id");
    	String email = request.getParameter("email");
    	String content = request.getParameter("memo");
    	
    	try {
			 
    		MemoDao memoDao = new MemoDao();
			int row = memoDao.insertMemo(new Memo(id,email,content)); 
			
			if(row > 0) {
				  out.print("<script>");
	    	     	out.print("alert('등록성공..');");
	    	     	out.print("location.href='MemoList';");  //jsp  >>   /MemoList
	    	      out.print("</script>");
			}else {
				// 문제 처리 여기서 하면 바보 왜냐면  문제가 터진다면 catch로 던져지지 여기로 오지도 않음
			}
			
		} catch (Exception e) {
			//insert 하다 문제 생기면 (null, pk)
			  out.print("<script>");
	  		      out.print("alert('등록실패..');");
	  		      out.print("location.href='memo.html';");
	  		      out.print("</script>");
    	      out.print("<b> 오류 :" +  e.getMessage()  + "</b>");
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}