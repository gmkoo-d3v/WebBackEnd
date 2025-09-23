<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="i" begin="0" end="10">
		<c:set var="sum" value="${sum+i}" />
		${i}<br>
		<!-- i는 블럭변수 아님 -->
	</c:forEach>
	sum:누적 ${sum}<br>
	
	<ul>
		<c:forEach var="i" begin="1" end="9">
			<li>3*${i}=${3*i}</li>
		</c:forEach>
	</ul>
</body>
</html>