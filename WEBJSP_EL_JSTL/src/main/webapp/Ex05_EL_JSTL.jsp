<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%
/*
	EL * JSTL WAS 에서 해석( 서버쪽 스크립트임 자바스크립트 아님 클라이언트 해석아님)
	
	<c:set 변수 생성 값을 저장>
	<c:if test="조건">
	<c:choose if 보다 활용도가 높다 else if 처리가 가능> switch 문 el에 JSTL 에서 if는 else if 없음
	<c: forEach> 자바에서의 개선된 for문
	<c:froTokens> 자바에서의 split
	<c:out EL과 같은 역활>
	<c:catch 예외처리>
	
	EL & JSTL WAS 해석(서버쪽 스크립트)
	<c:set 변수 생성 값을 저장>
    <c:if test="조건">
    <c:choose if 보다 활용도가 좋아요   else if 처리가 가능
    <c:forEach 
    <c:forTokens   자바 split
    <c:out  EL과 같은 역할
    <c:catch 예외처리
		
*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>JSTL & EL</h3>
	EL parameter : ${param.id}-${param.pwd}<br>
	
	<h3>변수 저장</h3>
	<c:set var="userid" value="${param.id}" />
	<c:set var="userpwd" value="${param.pwd}" />
	
	<h3>변수값 출력</h3>
	id:${userid}<br>
	pwd:${userpwd}<br>
	
	<hr>
	<c:if test="${!empty userpwd}"><!-- 비밀번호가 존재하면 -->
		<h3>not empty password</h3>
		<c:if test="${userpwd == '1004'}">
			<h3>welcome admin^^</h3>
		</c:if>
	</c:if>
</body>
</html>


