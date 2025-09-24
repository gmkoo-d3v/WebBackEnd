<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 목록</title>
<jsp:include page="/header.jsp"/>
<style>
    .card {
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        transition: transform 0.3s ease;
    }
    .card:hover {
        transform: translateY(-2px);
    }
    .table th {
        background-color: #f8f9fa;
        font-weight: 600;
    }
    .btn-sm {
        padding: 0.25rem 0.5rem;
        font-size: 0.875rem;
    }
    .alert {
        border: none;
        border-radius: 10px;
    }
</style>
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">
                        <i class="fas fa-users me-2"></i>사원 목록
                        <c:if test="${not empty searchName}">
                            <small class="ms-2">- "${searchName}" 검색 결과</small>
                        </c:if>
                    </h4>
                </div>
                <div class="card-body">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <i class="fas fa-check-circle me-2"></i>${message}
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <i class="fas fa-exclamation-triangle me-2"></i>${errorMessage}
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <div>
                            <span class="badge bg-info fs-6">
                                총 ${totalCount}명
                            </span>
                        </div>
                        <form action="empsearch.do" method="get" class="d-flex">
                            <input class="form-control me-2" type="search" placeholder="이름으로 검색" name="searchName" aria-label="Search" value="${searchName}">
                            <button class="btn btn-outline-success" type="submit">검색</button>
                        </form>
                    </div>

                    <c:choose>
                        <c:when test="${not empty empList}">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead class="table-dark">
                                        <tr>
                                            <th>번호</th>
                                            <th>사원번호</th>
                                            <th>이름</th>
                                            <th>직무</th>
                                            <th>입사일</th>
                                            <th>급여</th>
                                            <th>부서번호</th>
                                            <th>액션</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="emp" items="${empList}">
                                            <tr>
                                                <td>${emp.empno}</td>
                                                <td><a href="empdetail.do?empno=${emp.empno}" class="text-decoration-none">${emp.empno}</a></td>
                                                <td>${emp.ename}</td>
                                                <td>${emp.job}</td>
                                                <td>${emp.formattedHiredate}</td>
                                                <td><span class="badge bg-success">${emp.sal}</span></td>
                                                <td><span class="badge bg-secondary">${emp.deptno}</span></td>
                                                <td>
                                                    <a href="empupdate.do?empno=${emp.empno}" class="btn btn-sm btn-outline-warning">
                                                        <i class="fas fa-edit"></i>
                                                    </a>
                                                    <button type="button" class="btn btn-sm btn-outline-danger" onclick="deleteEmp(${emp.empno}, '${emp.ename}')">
                                                        <i class="fas fa-trash-alt"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            
                            <div class="d-flex justify-content-center mt-4">
                                <nav aria-label="Page navigation">
                                    <ul class="pagination">
                                        <c:if test="${cpage > 1}">
                                            <li class="page-item">
                                                <a class="page-link" href="emplist.do?cpage=${cpage - 1}">이전</a>
                                            </li>
                                        </c:if>

                                        <c:forEach var="pageNo" begin="${startPage}" end="${endPage}">
                                            <li class="page-item ${pageNo == cpage ? 'active' : ''}">
                                                <a class="page-link" href="emplist.do?cpage=${pageNo}">${pageNo}</a>
                                            </li>
                                        </c:forEach>

                                        <c:if test="${cpage < pageCount}">
                                            <li class="page-item">
                                                <a class="page-link" href="emplist.do?cpage=${cpage + 1}">다음</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </div>
                            </c:when>
                        <c:otherwise>
                            <div class="alert alert-info text-center" role="alert">
                                <i class="fas fa-info-circle me-2"></i>등록된 사원이 없습니다.
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="d-flex justify-content-end">
                        <a href="emp.do" class="btn btn-primary">
                            <i class="fas fa-user-plus me-1"></i>새 사원 등록
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">
                    <i class="fas fa-exclamation-triangle text-warning me-2"></i>삭제 확인
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p id="deleteMessage"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-danger" id="confirmDelete">삭제</button>
            </div>
        </div>
    </div>
</div>

<script>
let deleteEmpno = null;

function deleteEmp(empno, ename) {
    deleteEmpno = empno;
    document.getElementById('deleteMessage').innerHTML = 
        `정말로 <strong>${ename}(${empno})</strong> 사원을 삭제하시겠습니까?<br><small class="text-muted">이 작업은 되돌릴 수 없습니다.</small>`;
    new bootstrap.Modal(document.getElementById('deleteModal')).show();
}

document.getElementById('confirmDelete').addEventListener('click', function() {
    if (deleteEmpno !== null) {
        window.location.href = 'empdelete.do?empno=' + deleteEmpno;
    }
});
</script>

</body>
</html>