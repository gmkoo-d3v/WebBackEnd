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
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// URL 방식으로 요청 처리
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlCommand = requestUri.substring(contextPath.length());
		
		System.out.println("요청 URL: " + urlCommand);
		
		String viewPage = "";
		EmpDao empDao = new EmpDao();
		
		// 요청에 따른 처리
		if(urlCommand.equals("/emp.do")) {
			// 사원 등록 화면
			viewPage = "/WEB-INF/views/emp/emp_insert.jsp";
		
		} else if(urlCommand.equals("/empdetail.do")) {
			// 사원 상세보기
			String empnoStr = request.getParameter("empno");
			if(empnoStr != null) {
				int empno = Integer.parseInt(empnoStr);
				EmpDto empDetail = empDao.getEmpById(empno);
				request.setAttribute("empDetail", empDetail);
			}
			viewPage = "/WEB-INF/views/emp/emp_detail.jsp";
			
		} else if(urlCommand.equals("/emplist.do")) {
			// 사원 목록
			try {
				List<EmpDto> empList = empDao.getEmpList();
				request.setAttribute("empList", empList);
				viewPage = "/WEB-INF/views/emp/emp_list.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "사원 목록을 불러오는데 실패했습니다.");
				viewPage = "/WEB-INF/views/error.jsp";
			}
			
		} else if(urlCommand.equals("/empinsert.do")) {
			// 사원 등록 처리
			System.out.println("=== 사원 등록 처리 시작 ===");
			try {
				String empnoStr = request.getParameter("empno");
				String ename = request.getParameter("ename");
				String job = request.getParameter("job");
				String mgrStr = request.getParameter("mgr");
				String hiredateStr = request.getParameter("hiredate");
				String salStr = request.getParameter("sal");
				String commStr = request.getParameter("comm");
				String deptnoStr = request.getParameter("deptno");
				
				System.out.println("받은 파라미터들:");
				System.out.println("empno: " + empnoStr);
				System.out.println("ename: " + ename);
				System.out.println("job: " + job);
				System.out.println("mgr: " + mgrStr);
				System.out.println("hiredate: " + hiredateStr);
				System.out.println("sal: " + salStr);
				System.out.println("comm: " + commStr);
				System.out.println("deptno: " + deptnoStr);
				
				EmpDto emp = new EmpDto();
				emp.setEmpno(Integer.parseInt(empnoStr));
				emp.setEname(ename);
				emp.setJob(job);
				
				if(mgrStr != null && !mgrStr.trim().isEmpty()) {
					emp.setMgr(Integer.parseInt(mgrStr));
				}
				
				if(hiredateStr != null && !hiredateStr.trim().isEmpty()) {
					emp.setHiredate(LocalDate.parse(hiredateStr));
				}
				
				emp.setSal(Double.parseDouble(salStr));
				
				if(commStr != null && !commStr.trim().isEmpty()) {
					emp.setComm(Double.parseDouble(commStr));
				}
				
				emp.setDeptno(Integer.parseInt(deptnoStr));
				
				System.out.println("생성된 EmpDto: " + emp);
				
				int result = empDao.insertEmp(emp);
				System.out.println("DB 삽입 결과: " + result);
				
				if(result > 0) {
					request.setAttribute("empDetail", emp);
					request.setAttribute("message", "사원이 성공적으로 등록되었습니다.");
					viewPage = "/WEB-INF/views/emp/emp_detail.jsp";
					System.out.println("성공 - detail 페이지로 이동");
				} else {
					request.setAttribute("errorMessage", "사원 등록에 실패했습니다.");
					viewPage = "/WEB-INF/views/error.jsp";
					System.out.println("실패 - error 페이지로 이동");
				}
			} catch (Exception e) {
				System.out.println("예외 발생: " + e.getMessage());
				e.printStackTrace();
				request.setAttribute("errorMessage", "사원 등록 중 오류가 발생했습니다: " + e.getMessage());
				viewPage = "/WEB-INF/views/error.jsp";
			}
			
		} else if(urlCommand.equals("/empdelete.do")) {
			// 사원 삭제
			try {
				String empnoStr = request.getParameter("empno");
				int empno = Integer.parseInt(empnoStr);
				
				int result = empDao.deleteEmp(empno);
				if(result > 0) {
					request.setAttribute("message", "사원이 삭제되었습니다.");
				}
				
				List<EmpDto> empList = empDao.getEmpList();
				request.setAttribute("empList", empList);
				viewPage = "/WEB-INF/views/emp/emp_list.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "사원 삭제에 실패했습니다.");
				viewPage = "/WEB-INF/views/error.jsp";
			}
			
		} else if(urlCommand.equals("/empupdate.do")) {
			// 사원 수정 화면
			String empnoStr = request.getParameter("empno");
			if(empnoStr != null) {
				int empno = Integer.parseInt(empnoStr);
				EmpDto empDetail = empDao.getEmpById(empno);
				request.setAttribute("empUpdate", empDetail);
			}
			viewPage = "/WEB-INF/views/emp/emp_update.jsp";
			
		} else if (urlCommand.equals("/empupdateok.do")) {
			// 사원 수정 처리
			try {
				String empnoStr = request.getParameter("empno");
				String ename = request.getParameter("ename");
				String job = request.getParameter("job");
				String mgrStr = request.getParameter("mgr");
				String hiredateStr = request.getParameter("hiredate");
				String salStr = request.getParameter("sal");
				String commStr = request.getParameter("comm");
				String deptnoStr = request.getParameter("deptno");
				
				EmpDto emp = new EmpDto();
				emp.setEmpno(Integer.parseInt(empnoStr));
				emp.setEname(ename);
				emp.setJob(job);
				
				if(mgrStr != null && !mgrStr.trim().isEmpty()) {
					emp.setMgr(Integer.parseInt(mgrStr));
				}
				
				if(hiredateStr != null && !hiredateStr.trim().isEmpty()) {
					emp.setHiredate(LocalDate.parse(hiredateStr));
				}
				
				emp.setSal(Double.parseDouble(salStr));
				
				if(commStr != null && !commStr.trim().isEmpty()) {
					emp.setComm(Double.parseDouble(commStr));
				}
				
				emp.setDeptno(Integer.parseInt(deptnoStr));
				
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
			// 사원 검색
			String searchName = request.getParameter("searchName");
			if(searchName != null && !searchName.trim().isEmpty()) {
				List<EmpDto> empList = empDao.searchEmpByName(searchName);
				request.setAttribute("empList", empList);
				request.setAttribute("searchName", searchName);
			}
			viewPage = "/WEB-INF/views/emp/emp_list.jsp";
			
		} else {
			// 기본 페이지
			viewPage = "/default.jsp";
		}
		
		// 뷰 지정 및 forward
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