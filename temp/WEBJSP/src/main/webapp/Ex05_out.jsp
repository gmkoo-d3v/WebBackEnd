<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* 
	  servlet 형
	  out.print("<html>")
	  ...
	  out.print("</html>")
	  
	  jsp 동생	
	  out 활용 
	*/

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>스파게티 코드 (UI 서버 + 정적(html)</h3>
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
	<h3>out객체 (서버코드 작업 : servlet 에서 화면... jsp 없던 시절에 servlet 있던 시절)</h3>
	<%
		boolean b2 = true;
	    if(10 > 5){
	    	out.print("if(true)<font color='red'>" + b2 + "</font>");
	    }else{
	    	b2 = false;
	    	out.print("if(false)<font color='blue'>" + b2 + "</font>");
	    }
	
	%>
</body>
</html>


