<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		1. 한글처리
		2. 데이터 받기
		3. 로직처리 (비지니스로직 즉 업무) > db작업(CRUD)
	
		내장객체(WAS 톰캣)
			1. request (클라이언트 정보를 담고 있는 객체 : 요청 페이지당 1개 (웹서버 메모리에 즉 웹서버에서 생성))
			2. response ( 웹서버에서 가지는 객체. 응답객체)
			3. session (클라이언트 마다 고유하게 생성되는 고유객체 : 접속자 100명이면 100개의 객체를 생성함)
				3.1 세선id값을 만들어서 쿠키게 저장한다
			4. application (전역자원 : 모든 접속자(session가 공유하는 객체)
			5. out (출력객체 out.print) 
			
			이 5개의 객체는 JSP에서는 new 없이 그냥 사용 가능하다.
				미리 생성 되어 있기에 
	
			
	*/
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	/*
		model .... mvc 흉내 jsp도 가능하니 
		JSP 요청을 받으면
		>> model 단을 나름 쓰니 
		 DTO DAO를 쓴다는 소리
		 	MemberDao memberDao = new MemberDao();
			MemberDto memeberDto = new MemberDto();
			
			memberDto.setId(id);
			
		//	memberDao.insertDate(memberDto);
			- > int result = memberDao.insertDate(memberDto);
			클라이언트에게 가입성공 메세지 전달			
	*/
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	1. 입력값 : <%=id %><br>
	2. 접속한 클라이언트 IP : <%= request.getRemoteAddr() %><br>
	3. 포트 : <%= request.getServerPort() %><br>
	4. 경로 : <%= request.getContextPath() %><br>
	<!-- 
		웹에서 경로 :                   가상디렉토리                 실제  경로
		클라이언트가 보는 주소 => 192.168.0.12/Web/a.jsp             c:\\Web\\WebApp\a.jsp 
	-->
	 
	
</body>
</html>