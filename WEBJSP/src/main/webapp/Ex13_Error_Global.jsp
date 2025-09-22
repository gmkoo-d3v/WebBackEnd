<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String data = request.getParameter("name");
	String data2 = data.toLowerCase(); //null 데이터 소문자로 x
	// web-inf/web.xml 에러 주석처리한거 풀고 실습할것
	
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	전달받은 내용 <%=data2%>
</body>
</html>