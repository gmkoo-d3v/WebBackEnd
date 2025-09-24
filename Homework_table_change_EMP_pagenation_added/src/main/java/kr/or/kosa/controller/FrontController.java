package kr.or.kosa.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.dao.EmpDao;
import kr.or.kosa.dto.EmpDto;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController() {
        super();
    } 

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlCommand = requestUri.substring(contextPath.length());
		
		System.out.println("요청 URL: " + urlCommand);
		
		String viewPage = "";
		EmpDao empDao = new EmpDao();
		
		if(urlCommand.equals("/emp.do")) {
			viewPage = "/WEB-INF/views/emp/emp_insert.jsp";
		
		} else if(urlCommand.equals("/empdetail.do")) {
			String empnoStr = request.getParameter("empno");
			if(empnoStr != null) {
				int empno = Integer.parseInt(empnoStr);
				EmpDto empDetail = empDao.getEmpById(empno);
				request.setAttribute("empDetail", empDetail);
			}
			viewPage = "/WEB-INF/views/emp/emp_detail.jsp";
			
		} else if(urlCommand.equals("/emplist.do")) {
			// 사원 목록 (페이지네이션 적용)
			
			// 기본 페이지 번호 및 페이지 크기 설정
			String pageStr = request.getParameter("cpage");
			int cpage = 1; // 현재 페이지
			int pageSize = 10; // 한 페이지에 보여줄 항목 수

			if (pageStr != null && !pageStr.isEmpty()) {
				cpage = Integer.parseInt(pageStr);
			}
			
			try {
				// 1. 전체 사원 수 조회
				int totalCount = empDao.getTotalEmpCount();
				
				// 2. 총 페이지 수 계산
				int pageCount = (int) Math.ceil((double) totalCount / pageSize);

				// 3. 현재 페이지의 시작 및 끝 행 번호 계산
				int start = (cpage - 1) * pageSize + 1;
				int end = cpage * pageSize;

				// 4. 페이지에 해당하는 사원 목록 조회
				List<EmpDto> empList = empDao.getEmpList(start, pageSize);
				
				// 5. 페이지네이션 블록 정보 계산
				int pageGroupSize = 5; // 페이지 그룹에 보여줄 페이지 수
				int startPage = ((cpage - 1) / pageGroupSize) * pageGroupSize + 1;
				int endPage = startPage + pageGroupSize - 1;

				// 마지막 페이지 그룹이 총 페이지 수를 초과하는 경우 조정
				if (endPage > pageCount) {
					endPage = pageCount;
				}

				// 6. JSP로 전달할 데이터 설정
				request.setAttribute("empList", empList);
				request.setAttribute("cpage", cpage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("totalCount", totalCount);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				
				viewPage = "/WEB-INF/views/emp/emp_list.jsp";
				
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "사원 목록을 불러오는 중 오류가 발생했습니다.");
				viewPage = "/WEB-INF/views/error.jsp";
			}
		
		} else if(urlCommand.equals("/empinsertok.do")) {
			// 사원 등록 처리
			try {
				int empno = Integer.parseInt(request.getParameter("empno"));
				String ename = request.getParameter("ename");
				String job = request.getParameter("job");
				String mgrStr = request.getParameter("mgr");
				String hiredateStr = request.getParameter("hiredate");
				double sal = Double.parseDouble(request.getParameter("sal"));
				String commStr = request.getParameter("comm");
				int deptno = Integer.parseInt(request.getParameter("deptno"));

				EmpDto emp = new EmpDto();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setJob(job);
				emp.setMgr(mgrStr != null && !mgrStr.isEmpty() ? Integer.parseInt(mgrStr) : null);
				emp.setHiredate(hiredateStr != null && !hiredateStr.isEmpty() ? LocalDate.parse(hiredateStr) : null);
				emp.setSal(sal);
				emp.setComm(commStr != null && !commStr.isEmpty() ? Double.parseDouble(commStr) : null);
				emp.setDeptno(deptno);

				int result = empDao.insertEmp(emp);
				if(result > 0) {
					request.setAttribute("message", "새로운 사원이 성공적으로 등록되었습니다.");
					viewPage = "/WEB-INF/views/emp/emp_list.jsp"; // 등록 후 목록 페이지로 이동
				} else {
					request.setAttribute("errorMessage", "사원 등록에 실패했습니다.");
					viewPage = "/WEB-INF/views/error.jsp";
				}
				
			} catch (NumberFormatException e) { // 이 부분을 수정했습니다.
				e.printStackTrace();
				request.setAttribute("errorMessage", "사원 등록 중 오류가 발생했습니다: " + e.getMessage());
				viewPage = "/WEB-INF/views/error.jsp";
			}
			
		} else if(urlCommand.equals("/empdelete.do")) {
			// 사원 삭제
			try {
				int empno = Integer.parseInt(request.getParameter("empno"));
				int result = empDao.deleteEmp(empno);
				if (result > 0) {
					request.setAttribute("message", empno + "번 사원이 삭제되었습니다.");
				} else {
					request.setAttribute("errorMessage", "사원 삭제에 실패했습니다.");
				}
				// 삭제 후 목록 페이지로 리다이렉트 (PRG 패턴)
				response.sendRedirect(contextPath + "/emplist.do");
				return;
			} catch (NumberFormatException e) { // 이 부분을 수정했습니다.
				e.printStackTrace();
				request.setAttribute("errorMessage", "사원 삭제 중 오류가 발생했습니다: " + e.getMessage());
				viewPage = "/WEB-INF/views/error.jsp";
			}

		} else if(urlCommand.equals("/empupdate.do")) {
			// 사원 정보 수정 화면
			String empnoStr = request.getParameter("empno");
			if(empnoStr != null) {
				int empno = Integer.parseInt(empnoStr);
				EmpDto empUpdate = empDao.getEmpById(empno);
				request.setAttribute("empUpdate", empUpdate);
			}
			viewPage = "/WEB-INF/views/emp/emp_update.jsp";
			
		} else if(urlCommand.equals("/empupdateok.do")) {
			// 사원 정보 수정 처리
			try {
				int empno = Integer.parseInt(request.getParameter("empno"));
				String ename = request.getParameter("ename");
				String job = request.getParameter("job");
				String mgrStr = request.getParameter("mgr");
				String hiredateStr = request.getParameter("hiredate");
				double sal = Double.parseDouble(request.getParameter("sal"));
				String commStr = request.getParameter("comm");
				int deptno = Integer.parseInt(request.getParameter("deptno"));
				
				EmpDto emp = new EmpDto();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setJob(job);
				emp.setMgr(mgrStr != null && !mgrStr.isEmpty() ? Integer.parseInt(mgrStr) : null);
				emp.setHiredate(hiredateStr != null && !hiredateStr.isEmpty() ? LocalDate.parse(hiredateStr) : null);
				emp.setSal(sal);
				emp.setComm(commStr != null && !commStr.isEmpty() ? Double.parseDouble(commStr) : null);
				emp.setDeptno(deptno);
				
				int result = empDao.updateEmp(emp);
				if(result > 0) {
					request.setAttribute("empDetail", emp);
					request.setAttribute("message", "사원 정보가 수정되었습니다.");
					viewPage = "/WEB-INF/views/emp/emp_detail.jsp";
				} else {
					request.setAttribute("errorMessage", "사원 정보 수정에 실패했습니다.");
					viewPage = "/WEB-INF/views/error.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "사원 정보 수정 중 오류가 발생했습니다: " + e.getMessage());
				viewPage = "/WEB-INF/views/error.jsp";
			}
			
		} else if(urlCommand.equals("/empsearch.do")) {
			String searchName = request.getParameter("searchName");
			if(searchName != null && !searchName.trim().isEmpty()) {
				List<EmpDto> empList = empDao.searchEmpByName(searchName);
				request.setAttribute("empList", empList);
				request.setAttribute("searchName", searchName);
			}
			viewPage = "/WEB-INF/views/emp/emp_list.jsp";
			
		} else {
			viewPage = "/default.jsp";
		}
		
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