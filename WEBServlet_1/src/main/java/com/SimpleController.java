package com;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
/*
1. servlet 정의 : java 파일인데 웹에 사용할 수 있는 자바 파일 (request, response 객체활용)
1.1 java 파일에 extends HttpServlet 상속관계 (일반자바 웹 객체(request, response) 사용가능)
1.2 웹용 사용할수 있는 java 파일 : servlet 

Tip) 자바 웹 개발언어가 아니고 ... 웹 ..... java .... request, response ... 상속 ... servlet 웹서비스
     servlet 자바코드 편하고 UI 만드는 작업 힘들어요 
     >> 그래서 탄생 
     >> jsp 
     
     servlet 형 -> jsp 동생 (UI 구성 편하고 + JAVA 코드 제어 힘들어 졌어요)
     servlet은 자바와 웹을 동시에 사용이 강점 
     jsp는 ui가 강점 

    HttpServletRequest request,   자동으로 request 생성되고 그 주소를 할당   //HttpServletRequest 인터페이스
    HttpServletResponse response  자동으로 response  생성되고 그 주소를 할당


	 1. 요청
	 JSP
	 http://192.168.4.150:8090/default.jsp
	 이제 jsp는 뷰로만 쓸꺼임 위 주소는 더이상 안쓴다.
	 
	 servlet
	 http://192.168.4.150:8090/SimpleController.java (x) 요청
	 
	 web.xml 
	 또는
	 @WebServlet 
	 어떤 요청 주소가 들어어면 SimpleController.java 컴파일하고 실행 ... 정의
	 http://192.168.4.150:8090/Simple
	 
	 web.xml 
	 어떤 요청이 오면 어떤 servlet 파일 컴파일 하고 실행 결정 ....url_pattern 정의
	 <servlet>
		<servlet-name>simplecontroller</servlet-name>
		<servlet-class>com.SimpleContoller</servlet-class>
  </servlet>
  <servlet-mapping>
		<servlet-name>simplecontroller</servlet-name>
		<url-pattern>/simple</url-pattern>
  </servlet-mapping>
  
  컴파일 하고 실행할 거야 ...
  
  주소 창에 : http://localhost:8090/simple 엔터
  web.xml 설정된 servlet-mapping  근거로 SimpleContoller.java 컴파일 하고 실행
  
  protected void doGet() 함수 아니면
  protected void doPost() 둘중에 하나가 자동 실행
  
  동기식 .... 둘중에 하나의 방식으로 요청 보내요 
  GET   >>  void doGet() 실행 (자동)
  POST  >>  void doPost() 실행(자동)
*/

@WebServlet("/SimpleController")
public class SimpleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public SimpleController() {
        super();
        System.out.println("SimpleController 생성자 함수 실행 : 초기화");
   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("클라이언트의 요청이 GET 자동실행 함수");
		// 클라이언트가  전송한 방식이 get 방식이면 이놈이 호출
		// JSP 와 동일
		/*
			 1. 한글처리
			 2. 데이터 받기
			 3. 비지니스 로직( 요구사항 )
		 	 4. 데이터 담기 requestAttribuet sessionAttribute
		 	 5. view 지정
		 	 6. 그 view 에 forward 

	 	*/
	 	 
		//1. 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//2. 데이터 받기
		String type = request.getParameter("type");
		
		//3. 비지니스 로직(요구사항)
		Object resultObj = null;
		if(type == null || type.equals("greeting")) {
			resultObj = "hello world";
		}else if(type.equals("date")) {
			resultObj = new Date();
		}else {
			resultObj="invalid String type";
		}
		
		//4.데이터 담기 (request(include or forward , session , application)
		//UI > JSP 전달
		request.setAttribute("result", resultObj);
		//requset xx번지
 	 	
 	 	//5. view 정의
 	 	// web-inf가 보안폴더 
 	 	// 여기에 views 만듦
 	 	// 그안에 게시판이면 board 어드민페이지면 admin
 	 	// board > css, image, js, simpleview.jsp
 	 	// admin > css, image, js, simpleview.jsp
 	 	
 	 	//request.getRequestDispatcher("/simpleview.jsp");
		RequestDispatcher dis = request.getRequestDispatcher("/simpleview.jsp");
		
		dis.forward(request, response);  //requset xx  번지 
 	 	// forward 된 페이지에서는 request 객체를 공유할수 있다.
 	 	// forward 현재 SimpleController 가지는 buffer 를 비우고
 	 	// simpleview.jsp 의 내용을 버퍼에 채워서 서비스 하겠다.
 	 	// (주소는 바뀌지 않고 ... 내용만 )
 	 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 클라이언트가  전송한 방식이 post 방식이면 이놈이 호출
		
	}

}
