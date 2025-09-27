package kr.or.kosa.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.dao.EmpDao;
import kr.or.kosa.dto.Emp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FrontController() { super(); }

    private Integer toNullableInt(String s) {
        return (s == null || s.isBlank()) ? null : Integer.valueOf(s);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 인코딩
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // URL 파싱
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlCommand = requestUri.substring(contextPath.length());
        System.out.println(urlCommand);

        String viewPage = "";
        EmpDao empDao = new EmpDao();

        if (urlCommand.equals("/emp.do")) {
            viewPage = "/WEB-INF/views/emp/emp_insert.jsp";

        } else if (urlCommand.equals("/empdetail.do")) {

            String ename = request.getParameter("ename");
            Emp empDetail = empDao.getEmpListById(ename);
            request.setAttribute("empdetail", empDetail);
            viewPage = "/WEB-INF/views/emp/emp_detail.jsp";

        } else if (urlCommand.equals("/emplist.do")) {

            try {
                List<Emp> empList = empDao.getEmpList();
                request.setAttribute("empList", empList);
                viewPage = "/WEB-INF/views/emp/emp_list.jsp";
            } catch (Exception e) {
                e.printStackTrace();
                viewPage = "/WEB-INF/views/emp/emp_list.jsp";
            }

        } else if (urlCommand.equals("/empinsert.do")) {
            // 입력값 수집
            String ename = request.getParameter("ename");
            String empno = request.getParameter("empno");
            String job = request.getParameter("job");
            String mgrStr = request.getParameter("mgr");
            String hiredate = request.getParameter("hiredate"); // YYYY-MM-DD 권장
            String sal = request.getParameter("sal");
            String commStr = request.getParameter("comm");
            String deptno = request.getParameter("deptno");

            // NULL 가능한 값 처리
            Integer mgr = toNullableInt(mgrStr);
            Integer comm = toNullableInt(commStr);

            // DTO 채우기 (setter 방식이 안전)
            Emp emp = new Emp();
            emp.setEname(ename);
            emp.setEmpno(Integer.parseInt(empno));
            emp.setJob(job);
            emp.setMgr(mgr);                  // ← null 가능
            emp.setHiredate(hiredate);
            emp.setSal(Integer.parseInt(sal));
            emp.setComm(comm);                // ← null 가능
            emp.setDeptno(Integer.parseInt(deptno));

            empDao.insertEmp(emp);

            request.setAttribute("empdetail", emp);
            viewPage = "/WEB-INF/views/emp/emp_detail.jsp";

        } else if (urlCommand.equals("/empdelete.do")) {

            String ename = request.getParameter("ename");
            empDao.deleteEmp(ename);

            try {
                List<Emp> empList = empDao.getEmpList();
                request.setAttribute("empList", empList);
                viewPage = "/WEB-INF/views/emp/emp_list.jsp";
            } catch (SQLException e) {
                e.printStackTrace();
                viewPage = "/WEB-INF/views/emp/emp_list.jsp";
            }

        } else if (urlCommand.equals("/empupdate.do")) {

            String ename = request.getParameter("ename");
            Emp empDetail = empDao.getEmpListById(ename);

            request.setAttribute("empupdate", empDetail);
            viewPage = "/WEB-INF/views/emp/emp_update.jsp";

        } else if (urlCommand.equals("/empupdateok.do")) {
            // 변경된 내용
            String ename = request.getParameter("ename");
            String empno = request.getParameter("empno");
            String job = request.getParameter("job");
            String mgrStr = request.getParameter("mgr");
            String hiredate = request.getParameter("hiredate"); // YYYY-MM-DD
            String sal = request.getParameter("sal");
            String commStr = request.getParameter("comm");
            String deptno = request.getParameter("deptno");

            Integer mgr = toNullableInt(mgrStr);
            Integer comm = toNullableInt(commStr);

            Emp emp = new Emp();
            emp.setEname(ename);
            emp.setEmpno(Integer.parseInt(empno));
            emp.setJob(job);
            emp.setMgr(mgr);                  // ← null 가능
            emp.setHiredate(hiredate);
            emp.setSal(Integer.parseInt(sal));
            emp.setComm(comm);                // ← null 가능
            emp.setDeptno(Integer.parseInt(deptno));

            empDao.updateEmp(emp);

            request.setAttribute("empdetail", emp);
            viewPage = "/WEB-INF/views/emp/emp_detail.jsp";
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
