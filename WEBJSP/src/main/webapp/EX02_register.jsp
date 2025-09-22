<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	// jsp 페이지는
    	// request 객체 , response 객체 내장 (사용가능)
    	/*
    		1. 한글처리 (한글 깨짐)
    		2. 데이터 받기
    		3. 로직 수행
    		4. 결과 응답하기
    			
    			1. 클라이언트로부터 전송된 데이터 받기
    				1.1 Tomcat (내장객체) : request 자동 생성된 객체 ( 요청된 페이지마다 )
    				1.2 HttpServeltRequest request = new Http..... (자동으로 생성됨 리퀘스트라는 이름으로 즉 참조변수 request.하면 무언가가 연결됨 String str = new String 같은거)
    				1.3 클라이언트 서버 (회원가입할꺼야 , 게시판에 글 쓸꺼야 ) > 요청 페이지당 한개의 request를 자동 생성한다. 
    				    자동생성된 request 에서 필요한것만 뽑아내면된다.
    				1.4 request 객체 (입력 데이터 , ip , 브라우저 정보)
    				1.5 request 필요한 정보만 가져오면 된다.
    	
    				
    				
    				request.setCharacterEncoding <- 한글처리 인코딩
    	*/
    	
    	request.setCharacterEncoding("UTF-8"); //한글처리
    	
    	String uid = request.getParameter("userid");
    	String pwd = request.getParameter("pwd");
    	
    	// hobby 는 여러개의 데이터 = 배열로 본다
    	// request.getParameterValeus("hobby")
    	String[] hobbys = request.getParameterValues("hobby");
    	
    	// 로직 처리
    	// db연결 dto 객체 담기
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	서버에서 클라이언트 응답 <br>
	ID:<%=uid %> <br>
	PWD:<%=pwd %> <br>
	<%
		for(String str : hobbys){
	%>					
		<b>hobby:</b><%=str%><br>
	<%
		}	
	%>
	
	
	
</body>
</html>