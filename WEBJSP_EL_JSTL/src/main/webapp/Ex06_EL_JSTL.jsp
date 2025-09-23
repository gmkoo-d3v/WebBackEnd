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
	</c:forEach>
	sum:누적 ${sum}<br>
	
	<ul>
		<c:forEach var="i" begin="1" end="9">
			<li>3*${i}=${3*i}</li>
		</c:forEach>
	</ul>
	
	<h3>forEach Choose</h3>
	<c:forEach var="i" begin="2"  end="9">
		<c:forEach var="j" begin="0" end="9">
			<c:choose>
				<c:when test="${j == 0}">
					${i}단<br>
				</c:when>
				<c:otherwise>
					${i}*${j}=${i*j}&nbsp;&nbsp;
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<br>
	</c:forEach>
	<%
		//JAVA 제공하는 자원 
		int[] arr = new int[]{10,20,30,40,50};
		for(int i : arr){
			out.print("출력값 <b><i>" + i + "</i></b><br>");
		}
	   
		//KEY POINT : 객체를 누가 EL 접근 불가 
		//객체를 담아라 ....
		request.setAttribute("iarr", arr);
	%>
	<c:forEach var="arr" items="${iarr}">
		배열값 : ${arr}<br>
	</c:forEach>
	
	<h3>권장사항이 아닌것 하지만 가능</h3>
	<c:set var="array" value="<%=arr%>" />
	<c:forEach var="arr" items="${array}">
		배열값 : ${arr}<br>
	</c:forEach>
	
	
</body>
</html>