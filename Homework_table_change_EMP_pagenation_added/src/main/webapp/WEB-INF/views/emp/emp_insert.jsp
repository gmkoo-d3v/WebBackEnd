<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 등록</title>
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
                <div class="card-header bg-success text-white">
                    <h4 class="mb-0">
                        <i class="fas fa-user-plus me-2"></i>새 사원 등록
                    </h4>
                </div>
                <div class="card-body">
                    <form action="empinsert.do" method="post" id="empForm">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="empno" class="form-label">
                                        사원번호 <span class="required">*</span>
                                    </label>
                                    <input type="number" class="form-control" id="empno" name="empno" required>
                                    <div class="form-text">유니크한 사원번호를 입력하세요.</div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="ename" class="form-label">
                                        사원명 <span class="required">*</span>
                                    </label>
                                    <input type="text" class="form-control" id="ename" name="ename" required maxlength="10">
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
                                        <option value="PRESIDENT">PRESIDENT</option>
                                        <option value="MANAGER">MANAGER</option>
                                        <option value="ANALYST">ANALYST</option>
                                        <option value="CLERK">CLERK</option>
                                        <option value="SALESMAN">SALESMAN</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="mgr" class="form-label">관리자번호</label>
                                    <input type="number" class="form-control" id="mgr" name="mgr">
                                    <div class="form-text">선택사항입니다.</div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="hiredate" class="form-label">입사일</label>
                                    <input type="date" class="form-control" id="hiredate" name="hiredate">
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
                                               step="0.01" min="0" required>
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
                                               step="0.01" min="0">
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
                                        <option value="10">10 - ACCOUNTING</option>
                                        <option value="20">20 - RESEARCH</option>
                                        <option value="30">30 - SALES</option>
                                        <option value="40">40 - OPERATIONS</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        
                        <hr>
                        
                        <div class="d-flex justify-content-between">
                            <a href="emplist.do" class="btn btn-secondary">
                                <i class="fas fa-arrow-left me-1"></i>목록으로
                            </a>
                            <div>
                                <button type="reset" class="btn btn-outline-secondary me-2">
                                    <i class="fas fa-redo me-1"></i>초기화
                                </button>
                                <button type="submit" class="btn btn-success">
                                    <i class="fas fa-save me-1"></i>등록
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
// 오늘 날짜를 기본값으로 설정
document.getElementById('hiredate').value = new Date().toISOString().split('T')[0];

// 폼 유효성 검사
document.getElementById('empForm').addEventListener('submit', function(e) {
    console.log('폼 제출 시작');
    
    const empno = document.getElementById('empno').value;
    const ename = document.getElementById('ename').value;
    const job = document.getElementById('job').value;
    const sal = document.getElementById('sal').value;
    const deptno = document.getElementById('deptno').value;
    
    console.log('입력값 확인:', {empno, ename, job, sal, deptno});
    
    if (!empno || !ename || !job || !sal || !deptno) {
        e.preventDefault();
        alert('필수 항목을 모두 입력해주세요.');
        return false;
    }
    
    if (empno <= 0) {
        e.preventDefault();
        alert('사원번호는 양수여야 합니다.');
        return false;
    }
    
    if (sal <= 0) {
        e.preventDefault();
        alert('급여는 0보다 커야 합니다.');
        return false;
    }
    
    console.log('유효성 검사 통과, 폼 제출 진행');
    return true;
});
</script>
</body>
</html>