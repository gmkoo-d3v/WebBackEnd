<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//jSP 페이지는 
	//request 객체 , response 객체 내장 (사용가능)

	/*
		1. 한글처리 (한글깨짐)
		2. 데이터 받기
		3. 로직 수행
		4. 결과 응답하기
		
		1.전송된 데이터 받기
		1.1 Tomcat (내장객체) : request 자동 생성된 객체 (요청된 페이지 마다)
		1.2 HttpServletRequest request = new Http.... (자동생성)
		1.3 클라이언트 서버 (회원가입할꺼야 , 게시판에 글 쓸꺼야) > 요청 페이지당 한개의 request 자동 생성
		1.4 request 객체(입력 데이터 , IP , 브우라져 정보)
		1.5 request 필요한 정보만 가져오면 된다
		
	
	*/
	
	request.setCharacterEncoding("UTF-8"); //한글 처리
	
	String uid = request.getParameter("userid");
	String pwd = request.getParameter("pwd");
	
	//hobby 여러개 데이터
	String[] hobbys = request.getParameterValues("hobby");
	
	//로직처리
	//DB연결 , DTO 객체 (담기)
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	서버에서 클라이언트 응답<br>
	ID:<%=uid %><br>
	PWD:<%=pwd %><br>
	<%
		for(String str : hobbys){
	%>	
	     <b>hobby:</b><%=str%><br>
	<%
		}
	%>
</body>
</html>



