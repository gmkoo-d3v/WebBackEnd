<%@page import="kr.or.kosa.Emp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--  
		Tomcat제공(WAS) : 기본적인 내장 객체
		1. request  (요청: 클라이언트 정보 브라우저 ip 등등)
		2. response (응답: 특정 페이지 이동 , 정보 출력 , 쿠키 쓰기 send redirect 등등)
		3. application(전역객체: web.xml 자원 접근 read , 전역변수)
		4. session(고유객체(접속한 사용자에게 부여되는): 고유한 변수)
		5. out (출력)
		....

​

	*session객체 (활용도 ^^)
	[내부에 session변수 생성]
	scope : a.jsp(session.setAtt.."member" 변수 생성)(서버의 메모리를 쓰니 필요한것만 쓰는게 맞다.)
	        b.jsp(session.getAtt.. "member" 사용가능) 
	    session 변수는
	    **************************************************************
	     (현재 Webapp  폴더 안에 있는 모든 페이지에서 사용 가능 getAttribute로)
	   ****************************************************************  
	    Life-cycle : 1. session변수는 session객체와 동일 
	                 2. session 소멸 : 서버 리부팅 , Tomcat 재실행 , 로그아웃 session.invaildate()

    *application 객체
    [내부에 application변수 생성 : 전역변수 >>모든 session (접속한 모든 사용자) 공유
    scope :   (현재 Webapp  폴더 안에 있는 모든 페이지에서 사용 가능) >> java >> static이랑 비슷한것이라 생각하면된다 
    Life-cycle : 소멸 (서버 리부팅)   , Tomcat 재실행  

     

    *request 객체
    [내부에 변수 생성] : request.setAttribute("type","A"); *****~~~~~
    scope: 요청 페이지(login.jsp >> 요청당 request 생성 >> scope >> page
    예외적으로 ^^^^^^^^^
    login.jsp >> ok.jsp(include or forward) request 객체는 ok.jsp 공유된다
    요청된 jps 에서만 사용가능하거나 forward include 한것에서 사용가능하다.

    

              

-->      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		 Emp emp = new Emp();
		 emp.setEmpno(2000);
		 emp.setEname("홍길동");
		 
		//emp 객체변수 scope (page 한정)
		//요점 : emp객체를 다른 페이지에서도 사용 ...
		session.setAttribute("empobj",emp );
		//Webapp 안에 있는 모든 페이지에서empobj 사용 가능
		
		//정보를 모든 페이지가 접근 아니고 include , forward 페이지서만 접근하고 싶어요
		request.setAttribute("who", "king");
		//현재 페이지 접근가능
		//forward 했어요 (a.jsp) > a.jsp (request.getAttribute("who"))
	
	
		/*
			클라이언트 서버 요청(a.jsp) ->  서버 요청당 한개의 -> request 메모리_1 생성, (접속당) session 메모리 (같은)
			클라이언트 서버 요청(b.jsp) ->  서버 요청당 한개의 -> request 메모리_2 생성, (접속당) session 메모리 (같은)
			클라이언트 서버 요청(c.jsp) ->  서버 요청당 한개의 -> request 메모리_3 생성, (접속당) session 메모리 (같은)
			클라이언트 서버 요청(d.jsp) ->  서버 요청당 한개의 -> request 메모리_4 생성, (접속당) session 메모리 (같은)
			
			개발자 고민 a.jsp > 생성된 > request 메모리_1 > 다른 페이지 공유 >  include , forward  > aaa.jsp
			개발자 고민 a.jsp > 에서 만든 자원 > 모든 페이지 사용하게 하고 싶어 >   session.setAttribute 
			
		*/
	
	%>
	
	<%= emp.getEmpno()%><hr>
	<%= request.getAttribute("who")%><hr>
	<a href="./Ex17_Object.jsp">Ex17</a>
		<!-- 
		request 객체 유효범위 : 요청한 페이지에서 단) include, forward 그 페이지도 사용가능
		session 객체는 유효범위 webApp 안에 있는 모든 페이지 
	      -->
</body>
</html>
