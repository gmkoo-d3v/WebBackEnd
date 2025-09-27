package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.empDao;
import kr.or.kosa.dto.empDto;

public class EmpAddService implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	    
		String empno = request.getParameter("empno");
        String ename = request.getParameter("ename");
        String job = request.getParameter("job");
       
        String mgr = request.getParameter("mgr");
        
        String hiredate = request.getParameter("hiredate");
        
        String sal = request.getParameter("sal");
        
        String comm = request.getParameter("comm");
        
        String deptno = request.getParameter("deptno");
        
        
        
        		
        
        empDto emp = new empDto();
        empDao empDao = new empDao();
        
        int result = empDao.insertEmp(emp);

        //규칙 (성공 , 실패하던 msg 작성하고 이동하는 페이지 생성)
        String msg = "";
        String url = "";
        
        if(result > 0) {
        	msg = "등록성공";
        	url = "index.memo"; //요청 서블릿 주소
        }else {
        	msg = "등록실패";
        	url = "index.html";
        }
        
        
        request.setAttribute("board_msg", msg);
        request.setAttribute("board_url", url);
        
       
        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/redirect.jsp");
	
        return forward;
}

}
