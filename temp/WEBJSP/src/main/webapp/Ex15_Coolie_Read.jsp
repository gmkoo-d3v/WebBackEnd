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
		서버에 접속한 Client 가 가지고 있는 (domain) 별로 기록되어 있는 cookie read
	-->
	<%
		Cookie[] cs = request.getCookies();
		//현재 도메인에 해당되는 모든 쿠키 정보 가지고 와서
		if(cs != null || cs.length > 0){
			for(Cookie c : cs){
				out.print(c.getName() + " > c.getName()<br>");
				out.print(c.getValue() + " > c.getValue()<br>");
				out.print(c.getMaxAge() + " > c.getMaxAge()<br>");  //소멸타임 > -1 session 쿠키
				out.print(c.getDomain() + " > c.getDomain()<br>");
				out.print("<hr>");
			}
		}
	
	%>
</body>
</html>