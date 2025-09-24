<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보 수정</title>
<jsp:include page="/header.jsp"/>
<style>
    .card {
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        border: none;
    }
    .form-label {
        font-weight: 600;
        color: #495057;
    }
    .required {
        color: #dc3545;
    }
</style>
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-warning text-dark">
                    <h4 class="mb-0">
                        <i class="fas fa-user-edit me-2"></i>사원 정보 수정
                        <c:if test="${not empty empUpdate}">
                            - ${empUpdate.ename}
                        </c:if>
                    </h4>
                </div>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${not empty empUpdate}">
                            <form action="empupdateok.do" method="post" id="empUpdateForm">
                                <input type="hidden" name="empno" value="${empUpdate.empno}">
                                
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="empno" class="form-label">사원번호</label>
                                            <input type="text" class="form-control" value="${empUpdate.empno}" readonly 
                                                   style="background-color: #f8f9fa;">
                                            <div class="form-text">사원번호는 변경할 수 없습니다.</div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="ename" class="form-label">
                                                사원명 <span class="required">*</span>
                                            </label>
                                            <input type="text" class="form-control" id="ename" name="ename" 
                                                   value="${empUpdate.ename}" required maxlength="10">
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="job" class="form-label">
                                                직무 <span class="required">*</span>
                                            </label>
                                            <select class="form-select" id="job" name="job" required>
                                                <option value="">직무를 선택하세요</option>
                                                <option value="PRESIDENT" ${empUpdate.job == 'PRESIDENT' ? 'selected' : ''}>PRESIDENT</option>
                                                <option value="MANAGER" ${empUpdate.job == 'MANAGER' ? 'selected' : ''}>MANAGER</option>
                                                <option value="ANALYST" ${empUpdate.job == 'ANALYST' ? 'selected' : ''}>ANALYST</option>
                                                <option value="CLERK" ${empUpdate.job == 'CLERK' ? 'selected' : ''}>CLERK</option>
                                                <option value="SALESMAN" ${empUpdate.job == 'SALESMAN' ? 'selected' : ''}>SALESMAN</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="mgr" class="form-label">관리자번호</label>
                                            <input type="number" class="form-control" id="mgr" name="mgr" 
                                                   value="${empUpdate.mgr}">
                                            <div class="form-text">선택사항입니다.</div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="hiredate" class="form-label">입사일</label>
                                            <input type="date" class="form-control" id="hiredate" name="hiredate" 
                                                   value="${empUpdate.hiredate}">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="sal" class="form-label">
                                                급여 <span class="required">*</span>
                                            </label>
                                            <div class="input-group">
                                                <span class="input-group-text">$</span>
                                                <input type="number" class="form-control" id="sal" name="sal" 
                                                       step="0.01" min="0" value="${empUpdate.sal}" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="comm" class="form-label">커미션</label>
                                            <div class="input-group">
                                                <span class="input-group-text">$</span>
                                                <input type="number" class="form-control" id="comm" name="comm" 
                                                       step="0.01" min="0" value="${empUpdate.comm}">
                                            </div>
                                            <div class="form-text">선택사항입니다.</div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="deptno" class="form-label">
                                                부서번호 <span class="required">*</span>
                                            </label>
                                            <select class="form-select" id="deptno" name="deptno" required>
                                                <option value="">부서를 선택하세요</option>
                                                <option value="10" ${empUpdate.deptno == 10 ? 'selected' : ''}>10 - ACCOUNTING</option>
                                                <option value="20" ${empUpdate.deptno == 20 ? 'selected' : ''}>20 - RESEARCH</option>
                                                <option value="30" ${empUpdate.deptno == 30 ? 'selected' : ''}>30 - SALES</option>
                                                <option value="40" ${empUpdate.deptno == 40 ? 'selected' : ''}>40 - OPERATIONS</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                
                                <hr>
                                
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <a href="emplist.do" class="btn btn-secondary me-2">
                                            <i class="fas fa-list me-1"></i>목록으로
                                        </a>
                                        <a href="empdetail.do?empno=${empUpdate.empno}" class="btn btn-info">
                                            <i class="fas fa-eye me-1"></i>상세보기
                                        </a>
                                    </div>
                                    <div>
                                        <button type="button" class="btn btn-outline-secondary me-2" onclick="resetForm()">
                                            <i class="fas fa-undo me-1"></i>되돌리기
                                        </button>
                                        <button type="submit" class="btn btn-warning">
                                            <i class="fas fa-save me-1"></i>수정 완료
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <div class="text-center py-5">
                                <i class="fas fa-user-slash fa-3x text-muted mb-3"></i>
                                <h5 class="text-muted">수정할 사원 정보를 찾을 수 없습니다.</h5>
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

<script>
// 원본 데이터 저장
const originalData = {
    ename: '${empUpdate.ename}',
    job: '${empUpdate.job}',
    mgr: '${empUpdate.mgr}',
    hiredate: '${empUpdate.hiredate}',
    sal: '${empUpdate.sal}',
    comm: '${empUpdate.comm}',
    deptno: '${empUpdate.deptno}'
};

function resetForm() {
    document.getElementById('ename').value = originalData.ename;
    document.getElementById('job').value = originalData.job;
    document.getElementById('mgr').value = originalData.mgr || '';
    document.getElementById('hiredate').value = originalData.hiredate || '';
    document.getElementById('sal').value = originalData.sal;
    document.getElementById('comm').value = originalData.comm || '';
    document.getElementById('deptno').value = originalData.deptno;
}

// 폼 유효성 검사
document.getElementById('empUpdateForm').addEventListener('submit', function(e) {
    const ename = document.getElementById('ename').value;
    const job = document.getElementById('job').value;
    const sal = document.getElementById('sal').value;
    const deptno = document.getElementById('deptno').value;
    
    if (!ename || !job || !sal || !deptno) {
        e.preventDefault();
        alert('필수 항목을 모두 입력해주세요.');
        return false;
    }
    
    if (sal <= 0) {
        e.preventDefault();
        alert('급여는 0보다 커야 합니다.');
        return false;
    }
    
    // 변경사항 확인
    const currentData = {
        ename: ename,
        job: job,
        mgr: document.getElementById('mgr').value,
        hiredate: document.getElementById('hiredate').value,
        sal: sal,
        comm: document.getElementById('comm').value,
        deptno: deptno
    };
    
    let hasChanges = false;
    for (let key in originalData) {
        if (originalData[key] != currentData[key]) {
            hasChanges = true;
            break;
        }
    }
    
    if (!hasChanges) {
        e.preventDefault();
        alert('변경된 내용이 없습니다.');
        return false;
    }
    
    if (!confirm('사원 정보를 수정하시겠습니까?')) {
        e.preventDefault();
        return false;
    }
});
</script>
</body>
</html>