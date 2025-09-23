<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf" %>

<%
/*
Controller 가 넘겨주는 모델 가정:
- list: List<Post> (id, title, writer, createdAt, viewCnt)
- page: PageDto  (currentPage, pageSize, totalCount, startPage, endPage, lastPage, hasPrev, hasNext)
- cond: SearchCond (keyword, type, sort)
*/
%>

<c:set var="q" value="${not empty cond.keyword ? cond.keyword : ''}"/>
<c:set var="t" value="${not empty cond.type ? cond.type : 'title'}"/>
<c:set var="s" value="${not empty cond.sort ? cond.sort : 'recent'}"/>

<h2>게시판</h2>

<form method="get" action="${ctx}/board/list">
  <select name="type">
    <option value="title"  ${t=='title' ? 'selected' : ''}>제목</option>
    <option value="writer" ${t=='writer' ? 'selected' : ''}>작성자</option>
    <option value="content"${t=='content' ? 'selected' : ''}>내용</option>
  </select>
  <input type="text" name="keyword" value="${fn:escapeXml(q)}" placeholder="검색어"/>
  <select name="sort">
    <option value="recent" ${s=='recent' ? 'selected' : ''}>최신순</option>
    <option value="view"   ${s=='view' ? 'selected' : ''}>조회순</option>
  </select>
  <button type="submit">검색</button>
</form>

<table border="1" width="100%" cellpadding="6">
  <thead>
    <tr>
      <th>#</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회</th>
    </tr>
  </thead>
  <tbody>
  <c:choose>
    <c:when test="${empty list}">
      <tr><td colspan="5" style="text-align:center">게시글이 없습니다.</td></tr>
    </c:when>
    <c:otherwise>
      <c:forEach var="post" items="${list}" varStatus="st">
        <tr>
          <td>${(page.currentPage-1) * page.pageSize + st.index + 1}</td>
          <td>
            <c:url var="viewUrl" value="${ctx}/board/view">
              <c:param name="id" value="${post.id}"/>
              <c:param name="page" value="${page.currentPage}"/>
              <c:param name="type" value="${t}"/>
              <c:param name="keyword" value="${q}"/>
              <c:param name="sort" value="${s}"/>
            </c:url>
            <a href="${viewUrl}">${fn:escapeXml(post.title)}</a>
            <c:if test="${post.commentCnt > 0}">
              <span style="color:#777">[${post.commentCnt}]</span>
            </c:if>
          </td>
          <td>${fn:escapeXml(post.writer)}</td>
          <td><fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm"/></td>
          <td><fmt:formatNumber value="${post.viewCnt}" groupingUsed="true"/></td>
        </tr>
      </c:forEach>
    </c:otherwise>
  </c:choose>
  </tbody>
</table>

<%@ include file="/WEB-INF/views/includes/pagination.jspf" %>

<p style="text-align:right">
  <c:url var="writeUrl" value="${ctx}/board/write">
    <c:param name="page" value="${page.currentPage}"/>
    <c:param name="type" value="${t}"/>
    <c:param name="keyword" value="${q}"/>
    <c:param name="sort" value="${s}"/>
  </c:url>
  <a href="${writeUrl}">글쓰기</a>
</p>