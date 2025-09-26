<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="Top.jsp" %>

<div class="container-fluid">
  <div class="row">
    <%@ include file="Left.jsp" %>
    <div class="col-9 p-3">
      <h2>My Skills</h2>
      <p>제가 보유한 주요 기술 스택은 아래와 같습니다.</p>
      
      <h5>프로그래밍 언어</h5>
      <ul class="list-group mb-3">
        <li class="list-group-item">Java</li>
        <li class="list-group-item">JavaScript (ES6+)</li>
        <li class="list-group-item">SQL</li>
      </ul>
      
      <h5>프레임워크 & 라이브러리</h5>
      <ul class="list-group mb-3">
        <li class="list-group-item">Spring / Spring Boot</li>
        <li class="list-group-item">React</li>
        <li class="list-group-item">Bootstrap</li>
      </ul>
      
      <h5>기타</h5>
      <ul class="list-group">
        <li class="list-group-item">Git & GitHub</li>
        <li class="list-group-item">MySQL / Oracle</li>
        <li class="list-group-item">Linux 기본</li>
      </ul>
    </div>
  </div>
</div>

<%@ include file="Bottom.jsp" %>
