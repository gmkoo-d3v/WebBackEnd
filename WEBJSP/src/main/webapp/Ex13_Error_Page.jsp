<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/error/commonError.jsp" %>    
<!-- 
	page erroPage="/error/commonError.jsp" 에러가 나면 해당주소의 페이지를 보여주겠다. 
	
   예외 상황이 발생 하더라도 >> 특정 페이지가 처리하도록(다른 화면 보여주기)
   
   1. 해당 페이지에 설정하기
   2. 전역적인 방법 (웹 사이트 전체) > web-inf/web.xml 스프링에선 yamel파일?
   3. 최종 배포 시점에 설정하는것 .. 개발할때면 예외 안보이니까
//500번 에러 내기 코드를 잘못짜서 생기는것
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