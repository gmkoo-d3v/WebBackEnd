<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf" %>

<%
/*
Model:
- post: Post (id, title, content, writer, createdAt, updatedAt, viewCnt)
*/
%>

<h2>${fn:escapeXml(post.title)}</h2>
<div style="color:#666; font-size:0.9em">
  작성자: ${fn:escapeXml(post.writer)}
  | 등록: <fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm"/>
  <c:if test="${not empty post.updatedAt}">
    | 수정: <fmt:formatDate value="${post.updatedAt}" pattern="yyyy-MM-dd HH:mm"/>
  </c:if>
  | 조회: <fmt:formatNumber value="${post.viewCnt}"/>
</div>

<hr/>
<div style="white-space:pre-wrap">
  <c:out value="${post.content}"/>
</div>
<hr/>

<!-- 버튼 영역 (파라미터 유지) -->
<c:url var="listUrl" value="${ctx}/board/list">
  <c:param name="page" value="${param.page}"/>
  <c:param name="type" value="${param.type}"/>
  <c:param name="keyword" value="${param.keyword}"/>
  <c:param name="sort" value="${param.sort}"/>
</c:url>

<c:url var="editUrl" value="${ctx}/board/edit">
  <c:param name="id" value="${post.id}"/>
  <c:param name="page" value="${param.page}"/>
  <c:param name="type" value="${param.type}"/>
  <c:param name="keyword" value="${param.keyword}"/>
  <c:param name="sort" value="${param.sort}"/>
</c:url>

<form method="post" action="${ctx}/board/delete" style="display:inline"
      onsubmit="return confirm('정말 삭제하시겠습니까?');">
  <input type="hidden" name="id" value="${post.id}"/>
  <input type="hidden" name="page" value="${param.page}"/>
  <input type="hidden" name="type" value="${param.type}"/>
  <input type="hidden" name="keyword" value="${param.keyword}"/>
  <input type="hidden" name="sort" value="${param.sort}"/>
  <button type="submit">삭제</button>
</form>

<a href="${editUrl}">수정</a>
<a href="${listUrl}">목록</a>