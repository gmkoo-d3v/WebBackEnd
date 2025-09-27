package kr.or.kosa.service;

import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.empDao;
import kr.or.kosa.dto.empDto;

public class EmpListService  implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		empDao dao = new empDao();
		
		try {
			 List<empDto> empList = dao.getEmpList();
			 
			 //POINT (View 출력한 데이터 담아서)
			 request.setAttribute("empList", empList);
			 
			 forward = new ActionForward();
			 forward.setRedirect(false);
			 forward.setPath("/WEB-INF/views/list.jsp");
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return forward;
	}

}
