<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/error/commonError.jsp" %>    
<!-- 
예외 상황이 발생 >> 특정 페이지가 처리하도록 (다른 화면 보여주기)
1. 해당 페이지에 설정하기 page errorPage
2. 전역적인 방법 (웹 사이트 전체) > web.xml 
3. 최종 배포 시점에 설정한다 ...
 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int data = 0/0;
%>
</body>
</html>