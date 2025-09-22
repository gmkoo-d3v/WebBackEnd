<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		table , tr , td {border: 2px solid black; border-collapse: collapse; }
	</style>
</head>
<body>
	<table style="width: 700px">
		<tr>
			<td colspan="2">
				<jsp:include page="/common/Top.jsp"/>
			</td>
		</tr>
		<tr>
			<td style="width: 200px">
				<jsp:include page="/common/Left.jsp"/>
			</td>	
			<td style="width:500px">
					<h3>소개 페이지</h3>
					..<br>
					..<br>
					여기는 내 마음대로
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="/common/Bottom.jsp"/>
			</td>
		</tr>
	</table>
</body>
</html>