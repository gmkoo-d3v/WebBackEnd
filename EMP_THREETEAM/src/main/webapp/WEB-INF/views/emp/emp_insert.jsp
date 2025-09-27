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
	<div style="margin: 100px;">
		<form action="${pageContext.request.contextPath}/empinsert.do"
			method="post">
			<div class="mb-3">
				<label for="formGroupExampleInput" class="form-label">ENAME</label> <input
					type="text" class="form-control" name="ename" placeholder="사원명 입력"
					required="required">
			</div>
			<div class="mb-3">
				<label for="formGroupExampleInput" class="form-label">EMPNO</label>
				<input type="text" class="form-control" name="empno"
					placeholder="사원번호 입력" required="required">
			</div>
			<div class="mb-3">
				<label for="formGroupExampleInput" class="form-label">JOB</label>
				<input type="text" class="form-control" name="job"
					placeholder="직종 입력" required="required">
			</div>
			<div class="mb-3">
				<label for="formGroupExampleInput" class="form-label">MGR</label>
				<input type="text" class="form-control" name="mgr"
					placeholder="매니저번호 입력" required="required">
			</div>
			<div class="mb-3">
				<label for="formGroupExampleInput" class="form-label">HIREDATE</label>
				<input type="date" class="form-control" name="hiredate"
					placeholder="입사날짜 입력" required="required">
			</div>
			<div class="mb-3">
				<label for="formGroupExampleInput" class="form-label">SAL</label>
				<input type="text" class="form-control" name="sal"
					placeholder="급여 입력" required="required">
			</div>
			<div class="mb-3">
				<label for="formGroupExampleInput" class="form-label">COMM</label>
				<input type="text" class="form-control" name="comm"
					placeholder="수당 입력" required="required">
			</div>
			<div class="mb-3">
				<label for="formGroupExampleInput" class="form-label">deptno</label>
				<input type="text" class="form-control" name="deptno"
					placeholder="직종번호 입력" required="required">
			</div>
			<div class="col-12">
				<button type="submit" class="btn btn-primary">사원 추가</button>
			</div>
			<div class="col-12">
				<button onclick="location.href = 'emplist.do'" type="button"
					class="btn btn-light">취소</button>
			</div>
		</form>
	</div>

</body>
</html>