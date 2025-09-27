<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 상세정보</title>
<jsp:include page="/header.jsp"/>
<style>
    .card {
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        border: none;
    }
    .info-label {
        font-weight: 600;
        color: #6c757d;
    }
    .info-value {
        font-size: 1.1rem;
        color: #495057;
    }
    .salary-highlight {
        background: linear-gradient(135deg, #28a745, #20c997);
        color: white;
        padding: 10px;
        border-radius: 8px;
    }
</style>
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <c:if test="${not empty message}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="fas fa-check-circle me-2"></i>${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>
            
            <div class="card">
                <div class="card-header bg-info text-white">
                    <h4 class="mb-0">
                        <i class="fas fa-user me-2"></i>사원 상세정보
                        <c:if test="${not empty empDetail}">
                            - ${empDetail.ename}
                        </c:if>
                    </h4>
                </div>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${not empty empDetail}">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-4">
                                        <div class="info-label">사원번호</div>
                                        <div class="info-value">
                                            <span class="badge bg-primary fs-6">${empDetail.empno}</span>
                                        </div>
                                    </div>
                                    <div class="mb-4">
                                        <div class="info-label">사원명</div>
                                        <div class="info-value">
                                            <i class="fas fa-user-circle me-2 text-primary"></i>
                                            <strong>${empDetail.ename}</strong>
                                        </div>
                                    </div>
                                    <div class="mb-4">
                                        <div class="info-label">직무</div>
                                        <div class="info-value">
                                            <span class="badge bg-secondary fs-6">${empDetail.job}</span>
                                        </div>
                                    </div>
                                    <div class="mb-4">
                                        <div class="info-label">관리자</div>
                                        <div class="info-value">
                                            <c:choose>
                                                <c:when test="${not empty empDetail.mgr}">
                                                    <i class="fas fa-user-tie me-2 text-warning"></i>
                                                    ${empDetail.mgr}
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="text-muted">
                                                        <i class="fas fa-minus me-2"></i>없음
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">
                                    <div class="mb-4">
                                        <div class="info-label">입사일</div>
                                        <div class="info-value">
                                            <c:choose>
                                                <c:when test="${not empty empDetail.hiredate}">
                                                    <i class="fas fa-calendar-alt me-2 text-success"></i>
                                                    ${empDetail.hiredate}
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="text-muted">
                                                        <i class="fas fa-minus me-2"></i>없음
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="mb-4 salary-highlight">
                                        <div class="info-label text-white-50">급여</div>
                                        <div class="info-value text-white">
                                            <i class="fas fa-dollar-sign me-2"></i>
                                            <strong>${empDetail.sal}</strong>
                                        </div>
                                    </div>
                                    <div class="mb-4">
                                        <div class="info-label">커미션</div>
                                        <div class="info-value">
                                            <c:choose>
                                                <c:when test="${not empty empDetail.comm}">
                                                    <i class="fas fa-percentage me-2 text-info"></i>
                                                    ${empDetail.comm}
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="text-muted">
                                                        <i class="fas fa-minus me-2"></i>없음
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="mb-4">
                                        <div class="info-label">부서번호</div>
                                        <div class="info-value">
                                            <span class="badge bg-info fs-6">
                                                <i class="fas fa-building me-1"></i>
                                                ${empDetail.deptno}
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <hr>
                            
                            <div class="d-flex justify-content-between">
                                <a href="emplist.do" class="btn btn-secondary">
                                    <i class="fas fa-arrow-left me-1"></i>목록으로
                                </a>
                                <div>
                                    <a href="empupdate.do?empno=${empDetail.empno}" class="btn btn-success me-2">
                                        <i class="fas fa-edit me-1"></i>수정
                                    </a>
                                    <button type="button" class="btn btn-danger" 
                                            onclick="deleteEmp(${empDetail.empno}, '${empDetail.ename}')">
                                        <i class="fas fa-trash me-1"></i>삭제
                                    </button>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="text-center py-5">
                                <i class="fas fa-user-slash fa-3x text-muted mb-3"></i>
                                <h5 class="text-muted">사원 정보를 찾을 수 없습니다.</h5>
                                <p class="text-muted">요청하신 사원이 존재하지 않거나 삭제되었습니다.</p>
                                <a href="emplist.do" class="btn btn-primary">목록으로 돌아가기</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 삭제 확인 모달 -->
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
    if(deleteEmpno) {
        window.location.href = 'empdelete.do?empno=' + deleteEmpno;
    }
});
</script>
</body>
</html>