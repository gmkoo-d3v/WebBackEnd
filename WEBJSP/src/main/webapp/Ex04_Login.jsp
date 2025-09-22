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

%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>