<%@page import="java.util.HashMap"%>
<%@page import="DTO.Emp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%
	Emp emp = new Emp(2000,"kosauser");
	// 사용 request 담을 것이냐 , session 에 담을것이냐
    request.setAttribute("e", emp);
    
    HashMap<String ,String> hp = new HashMap<>();
    hp.put("data","1004");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h3>기존방식</h3>
  	<%=emp %><br>
  	<%=emp.getEmpno() %><br>
  	<%=emp.getEname() %><br>
  	
  	<h3>EL</h3>
  	EL() : 자바 객체 출력(자바 객체에 직접 접근 불가) : ${emp}<br>
  	
  	<h3>해결사</h3> 
	1. 객체 접근 : 해결 > request, session 담아서 사용 : Good <br>
	EL: ${requestScope.e}<br>
	EL: ${e.empno}<br> <!-- 속성명으로 바로 접근하면 멤버필드로 바로 접근 가능 -->
	<!-- 속성명(meber field 바로 접근:getEmpno() 호출 -->
	EL: ${requestScope.e.empno}<br>
	
	<h3>JSTL (core) 변수 ,제어문( 자바코드 없이 ) : 서버에서 실행</h3>
	<c:set var="m" value="<%=emp %>" />
	EL을 통해서 출력: ${m}<br>
		EL(권장하지 않아요) ${m.getEmpno()}<br>
	
	
	EL 출력 : ${m.empno}<br>
	EL 출력 : ${m.ename}<br>
	
	<hr>
	<h3>JSTL 변수 만들고 scope</h3>
	<c:set var="job" value="농구선수" scope="request" />
	당신의 직업은 : ${job}<br>
	include, forward 사용하면 다른 페이지에서도 job 출력가능
	<hr>
	JAVA API 활용한 객체는 EL 직접 접근 불가능 <br>
	******************<br>
	권장방법 : JAVA 객체를 EL 사용하고 싶다면 : request 혹은 session 에 담아서 사용 <br>
	******************<br>
	
	<hr>
	<c:set var="vhp" value="<%=hp%>" />
	hp객체: ${vhp}<br>
	hp객체: ${vhp.data}<br>
	
</body>
</html>