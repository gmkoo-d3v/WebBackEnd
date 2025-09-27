package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.empDao;

public class EmpEmpnoCheckerService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward =null;
		String idCheck = null;
		
		try {
			String id=request.getParameter("empno");
			empDao empDao = new empDao();
			idCheck = empDao.isCheckById(empno);
			
			System.out.println("idCheck : " + idCheck);
			
			request.setAttribute("idCheck", idCheck);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/uservalid.jsp"); // 비동기 값(페이지가 하나 있어야 된다)
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}