<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	
		DBCP 커넥션 풀 ( 미리 연결 생성해 놓고 사용하고 반환) 
		커넥션 풀 종류 ( Hikari CP , 연습 : Tomcat 제공 Pool (tomcat-dbcp.jar))
		
		context.xml 설정 가져와서 사용
		
		1. Tomcat이  가지고 있는 context.xml 설정
		2. 프로젝트 단위로 설정 context.xml을 끌고와서 META-INF 폴더에 context.xml을 넣으면 프로젝트마다 따로 가능
		
		
		// JNDI ( Java Naming and Directory Interface ) : 윈도우 탐색기 검색처럼 찾는 방식
		
		
	
	
	
	
	*/
	Context context = new InitialContext();
	// 현재 프로젝트에서 검색 (이름으로)
	//context.lookup("java:comp/env/jdbc/oracle"); // 어떤걸 검색할때 쓰는것
	// 약속된 표현 java:comp/env/ + 이름 >>  name ="jdbc/oracle"
	// 컴파일되면 이 html 파일(context.xml)이 리소스로 받아들여짐
	DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");

	// 웹 서버 메모리 가게 오픈 (튜브 가게 : 20개) > conn 객체 빌려쓰고 다 쓰면 반환 이것을 connection pool 이라고한다
	// 스프링도 히카리풀사용

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