<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	servlet 형
	JSP     동생
	
	servlet떄는
	out.print("<html>")
	...
	out.print("</html>")

	JSP는 out활용해서 스파게티코드를 조금 탈출한다.
	
	
*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>스파게티 코드 (UI)</h3>
	<%
		boolean b = true;
		if(10 > 5){
	%>
		if(true)<font color="red"><%=b%></font>
	<%		
		}else{
			b = false;
	%>
		if(false)<font color="blue"><%=b%></font>
	<%		
		}
	%>
	
<!--
위 방식 html 코드 사용가능한게 가능 대신 스파게티화
아래는 열었다 닫았다는 없는데신 html  코드를 문자로 만들어야됨 자동완성 못받음
둘다 단점이 심하다.
현대에서는 mvc 패턴이 있으니 자바가 가져가게 변함
-->	
	<h3>out객체 (서버코드 작업 : servlet 에서 화면... jsp 없던 시절에 servlet 있던 시절)</h3>
	<%
	boolean b2 = true;
	if(10 > 5) {
		out.print("if(true)<font color='red'>" +b2+"</font>");
	} else {
		b2 = false;
		out.print("if(false)<font color='blue'>" +b2+"</font>");
	}
	 %>
</body>
</html>