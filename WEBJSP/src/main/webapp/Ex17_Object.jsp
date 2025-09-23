<%@page import="kr.or.kosa.Emp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//session.getAttribute("empobj"); 
    //map처럼 키값쓴다. 예전에 만든것들이라서 제네릭 안됨  예전기술이라 다운캐스팅필요
	Emp emp = (Emp)session.getAttribute("empobj");

%>

	emp 정보출력 : <%=emp.toString()	 %>
</body>
</html>