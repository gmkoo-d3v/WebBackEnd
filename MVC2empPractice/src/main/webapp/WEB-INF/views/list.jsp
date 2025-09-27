<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp"%>
<h3>Department List</h3>
<div class="text-end mb-2">
	<a class="btn btn-primary"
		href="${pageContext.request.contextPath}/emp/write.do">신규등록</a>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>EMPNO</th>
			<th>ENAME</th>
			<th>JOB</th>
			<th>MGR</th>
			<th>HIREDATE</th>
			<th>SAL</th>
			<th>COMM</th>
			<th>DEPTNO</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="d">
			<tr>
				<td>${e.empno}</td>
				<td>${e.ename}</td>
				<td>${e.job}</td>
				<td>${e.sal}</td>
				<td>${e.mgr}</td>
				<td>${e.hiredate}</td>
				<td>${e.sal}</td>
				<td>${e.comm}</td>
				<td><a class="btn btn-sm btn-outline-secondary"
					href="${pageContext.request.contextPath}/emp/view.do?empno=${e.empno}">보기</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@ include file="/WEB-INF/views/common/bottom.jsp"%>