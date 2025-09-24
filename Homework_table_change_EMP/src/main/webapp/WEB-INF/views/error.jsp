<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류 발생</title>
<jsp:include page="/header.jsp"/>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card border-0 shadow">
                <div class="card-body text-center py-5">
                    <div class="mb-4">
                        <i class="fas fa-exclamation-triangle fa-4x text-danger"></i>
                    </div>
                    <h3 class="text-danger mb-3">오류가 발생했습니다</h3>
                    <p class="text-muted mb-4">
                        <c:choose>
                            <c:when test="${not empty errorMessage}">
                                ${errorMessage}
                            </c:when>
                            <c:otherwise>
                                시스템 처리 중 오류가 발생했습니다.
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <div>
                        <a href="emplist.do" class="btn btn-primary me-2">
                            <i class="fas fa-home me-1"></i>목록으로
                        </a>
                        <button onclick="history.back()" class="btn btn-secondary">
                            <i class="fas fa-arrow-left me-1"></i>이전 페이지
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>