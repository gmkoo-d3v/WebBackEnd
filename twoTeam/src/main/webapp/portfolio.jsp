<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="Top.jsp" %>

<div class="container-fluid">
  <div class="row">
    <%@ include file="Left.jsp" %>
    <div class="col-9 p-3">
      <h2>Portfolio</h2>
      <p>제가 지금까지 진행한 프로젝트들입니다.</p>

      <div class="row row-cols-1 row-cols-md-2 g-4">
        <!-- 프로젝트 카드 예시 -->
        <div class="col">
          <div class="card h-100 shadow-sm">
            <img src="images/project1.png" class="card-img-top" alt="프로젝트1">
            <div class="card-body">
              <h5 class="card-title">도서 관리 시스템</h5>
              <p class="card-text">
                Java + JSP + Oracle을 활용한 CRUD 기반의 도서 관리 웹 애플리케이션.
              </p>
              <a href="#" class="btn btn-primary">자세히 보기</a>
            </div>
          </div>
        </div>
        
        <div class="col">
          <div class="card h-100 shadow-sm">
            <img src="images/project2.png" class="card-img-top" alt="프로젝트2">
            <div class="card-body">
              <h5 class="card-title">쇼핑몰 웹사이트</h5>
              <p class="card-text">
                Spring Boot + React를 활용한 쇼핑몰 프로젝트 (상품 목록, 장바구니, 결제 기능 포함).
              </p>
              <a href="#" class="btn btn-primary">자세히 보기</a>
            </div>
          </div>
        </div>
        
      </div>
    </div>
  </div>
</div>

<%@ include file="Bottom.jsp" %>
