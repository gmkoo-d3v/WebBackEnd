<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//EL 자바코드가 아니고  출력 스크립트 언어
	//<%= 대체 EL  ${}
	// ${}쓰겠다라는것
	//이런건 자바코드니 여전히 <//%가 필요함

	Date today = new Date();
	
	request.setAttribute("day", today);
	session.setAttribute("day2", today);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>EL 화면출력 (서버쪽 자원 접근 출력)</h3>
	EL: ${requestScope.day}<br>
	EL: ${day}<br><!--  session , request 판단이 어려워요 -->
	EL: ${day2}<br>
	 <!-- session, request  이것들은 서버쪽 스크립트임 클라이언트에서 해석하는게 아님-->
	EL권장: ${sessionScope.day2}<br>
</body>
</html>




