<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header.jsp" />

	<div class="card" style="margin:10rem;">
		<div class="card-body">
			ENAME <h5 class="card-title">${requestScope.empdetail.ename}</h5>
			<hr>
			EMPNO <p class="card-text">${requestScope.empdetail.empno}</p>
			<hr>
			JOB <p class="card-text">${requestScope.empdetail.job}</p>
			<hr>
			MGR <p class="card-text">${requestScope.empdetail.mgr}</p>
			<hr>
			HIREDATE <p class="card-text">${requestScope.empdetail.hiredate}</p>
			<hr>
			SAL <p class="card-text">${requestScope.empdetail.sal}</p>
			<hr>
			COMM <p class="card-text">${requestScope.empdetail.comm}</p>
			<hr>
			DEPTNO <p class="card-text">${requestScope.empdetail.deptno}</p>
			<a class="btn btn-primary" href="empdelete.do?ename=${requestScope.empdetail.ename}">삭제하기</a>
		<a class="btn btn-primary" href="empupdate.do?ename=${requestScope.empdetail.ename}">수정하기</a>
		</div>
	</div>
</body>
</html>