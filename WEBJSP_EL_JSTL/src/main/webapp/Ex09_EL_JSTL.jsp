<%@page import="DTO.Dept"%>
<%@page import="java.util.List"%>
<%@page import="DAO.DeptDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
/*
	servlet 요청 받아서 db 작업이 필요하구나  그판단에 따라서 DeptDao 실행
	그 결과를 다시 가져와서 view 의 역활을 하는 JSP 에게 뿌리도록 
	전달하는것 	

*/
	DeptDao dao = new DeptDao();
	List<Dept> deptlist = dao.getDeptList();
	// 무조건 담아라 .... session , request ...
	request.setAttribute("list", deptlist);
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <div class="container">
    <table> 
        <thead>
           <tr>
		      <th>deptno</th>
		      <th>dname</th>
		      <th>loc</th>
	       </tr>
        </thead>
        <tbody>
			<c:forEach var="dept" items="${requestScope.list}">
				<tr>
					<td>${dept.deptno}</td>
					<td>${dept.dname}</td>
					<td>${dept.loc}</td>
				</tr>
			</c:forEach>
	    </tbody>
	 </table>   
   </div>
   
</body>
<style>
    .container {
       width: 100vw;
       height: 100vh;
       display: flex;
       align-items : center;
       justify-content: center;
       
    }
    
	table {
	  border-collapse: collapse;
	  width: 50vw;
	}
	
	th, td {
	  padding-top : 2vh;
	  padding-bottom: 2vh;
	  text-align: center;
	}
	
	tbody > th, td {
	  border-bottom: 1px solid #444444;
	}
	
	thead {
	  background-color: #D5DEDC;
	  color: #5F7065;
	}
</style>
</html>