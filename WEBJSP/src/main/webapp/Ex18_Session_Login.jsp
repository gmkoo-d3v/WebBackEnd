<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		순수한 JSP 사이트 제작
		Model1 방식 개발 (JSP 요청받고 처리 ...) >> 동생에게 게시판 만들어 줄때 ...
		
		
		UI + 처리 (2개 설계)
		login.jsp -> loginok.jsp
		
		action="Ex20_Session_ok.jsp"
 -->
	<form action="Ex18_Session_ok.jsp"  method="post">
		<input type="text" name="uid"><br>
		<input type="password" name="pwd"><br>
		<!-- admin admin  -->
		<input type="submit" value="로그인">
		<input type="reset"  value="취소">
	</form>
	<hr>
	<h3>회원 전용 페이지</h3>
	<a href="Ex18_Session_Member.jsp">회원접속</a>
</body>
</html>