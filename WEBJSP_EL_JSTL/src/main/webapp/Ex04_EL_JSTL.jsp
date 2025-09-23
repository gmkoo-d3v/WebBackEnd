<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id = request.getParameter("id");
	if(id.equals("hong")){
%>
	<%=id %><img src="images/img_5terre.jpg" style="width:100px; height:100px;" />
<%				
	}

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- http://localhost:8081/WEBJSP_EL_JSTL/Ex04_EL_JSTL.jsp?id=hong 해야 출력됨 -->
	<h3> EL & JSTL</h3>
	<!-- 
		param 객체 > request.getParameter()
		String id - request.getParameter("id");
	
	 -->
	
	 <c:if test="${param.id == 'hong' }">
	${param.id}<img src="images/img_5terre.jpg" style="width:100px; height:100px;" />
	 </c:if> 
	<!-- 
	http://localhost:8081/WEBJSP_EL_JSTL/Ex04_EL_JSTL.jsp?id=hong&age=25
	해야 출력됨
	 -->
	<c:if test="${param.age >20 }" var="result">
		param.value : ${param.age}<br>
	</c:if>
		if문을 사용했을때 var 변수를 result로 만든것
		var 변수는 result : ${result}<br>
</body>
</html>