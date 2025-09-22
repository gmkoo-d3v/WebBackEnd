<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="Top.jsp" %>

<div class="container-fluid">
  <div class="row">
    <%@ include file="Left.jsp" %>
    <div class="col-9 p-3">
      <h2>Contact Me</h2>
      <p>아래 폼을 작성하시면 제가 확인 후 연락드리겠습니다.</p>
      
      <form action="sendMessage.jsp" method="post" class="needs-validation" novalidate>
        <div class="mb-3">
          <label for="name" class="form-label">이름</label>
          <input type="text" class="form-control" id="name" name="name" required>
          <div class="invalid-feedback">이름을 입력하세요.</div>
        </div>
        
        <div class="mb-3">
          <label for="email" class="form-label">이메일</label>
          <input type="email" class="form-control" id="email" name="email" required>
          <div class="invalid-feedback">올바른 이메일을 입력하세요.</div>
        </div>
        
        <div class="mb-3">
          <label for="message" class="form-label">메시지</label>
          <textarea class="form-control" id="message" name="message" rows="4" required></textarea>
          <div class="invalid-feedback">메시지를 입력하세요.</div>
        </div>
        
        <button type="submit" class="btn btn-success">보내기</button>
      </form>
    </div>
  </div>
</div>

<%@ include file="Bottom.jsp" %>
