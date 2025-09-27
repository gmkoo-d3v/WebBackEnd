package kr.or.kosa.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.EmpAddService;
import kr.or.kosa.service.EmpListService;

@WebServlet("*.do")
public class FrontEmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontEmpController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlCommand = requestUri.substring(contextPath.length());
    	
        System.out.println("urlCommand = " + urlCommand);
       
    	
        Action action = null;
        ActionForward forward = null;
        
        if(urlCommand.equals("/add.do")) { //글쓰기처리 
        	//UI + 로직
        	 action = new EmpAddService(); //다형성
        	 forward = action.execute(request, response); //execute
       
        }else if(urlCommand.equals("/list.do")) { //목록보기
        	//UI + 로직
        	 action = new EmpListService(); //다형성
        	 forward = action.execute(request, response); //execute
        
        }else if(urlCommand.equals("/empno.do")) { //목록보기
        	//TODAY POINT
        	//UI + 로직
        	//비동기 동일한 서비스 ... 별도의 서비스 (별도의 서비스 권장)
        	//고민 (페이지를 만들어서 .... 데이터 만들어서)
        	//데이터 처리 (ajax > 별도의 servlet 만들어서 편하게 처리)
        	//이러면 별도의 페이지 처리가 필요
	       	 action = new EmpListService(); //다형성
	       	 forward = action.execute(request, response); //execute
	       	 
        }else if(urlCommand.equals("/empView.do")) {
        	//만약 로직 처리가 필요없다
        	//UI
        	forward = new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("/WEB-INF/views/empview.jsp");
        }
        
        if(forward != null) {
        	if(forward.isRedirect()) { //true location.href ="페이지"
        		response.sendRedirect(forward.getPath());
        	}else {
        		  //forward (데이터 처리)
        		  RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
        		  dis.forward(request, response);
        	}
        }
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
