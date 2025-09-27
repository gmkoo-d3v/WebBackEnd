package kr.or.kosa.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.dao.MemoDao;

import java.io.IOException;
import java.io.PrintWriter;

// 여기서 정의 하면 클라이언트에게 직접 던질수 있다 즉 컨트롤러서 복잡하게 타고 가는것 생략
// 조건 이있다.
@WebServlet("/MemoId")
public class MemoId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public MemoId() {
        super();
      
    }
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		 String id=request.getParameter("id");
		 MemoDao memoDao = new MemoDao();
		 String  idCheck =  memoDao.isCheckById(id);
		 System.out.println(idCheck);
		 out.print(idCheck);
    	
		 //별도의 view 없이...
		 // GSON 으로 객체 받아서 out.print etc
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
