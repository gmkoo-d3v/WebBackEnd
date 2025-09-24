package kr.or.kosa.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.dao.MemoDao;
import kr.or.kosa.dto.Memo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/MemoList")
public class MemoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemoList() {
        super();
        // TODO Auto-generated constructor stub
    }
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("목록보기");

    	//요청에 따른 판단이 필요없다 (FrontController 필요한데)
    	
    	//목록보기
    	MemoDao memoDao = new MemoDao();
    	
    	try {
				List<Memo> memoList = memoDao.getMemoList();
				
				//데이터 저장
				request.setAttribute("memoList", memoList);
				
				//view 지정
				RequestDispatcher dis = request.getRequestDispatcher("/memolist.jsp");
				
				//forward
				dis.forward(request, response);
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
