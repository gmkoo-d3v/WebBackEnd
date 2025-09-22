<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	공통 페이지를 만들고 싶다
		JSP:include 현제 페이지의 공통의 페이지를 붙일수 있다.
	
 -->








<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<b>SITE MAIN PAGE 상단</b>
	<br>
		<jsp:include page="/common/sub.jsp" />
	<br>
	<b>SITE MAIN PAGE 하단</b>
</body>
</html>