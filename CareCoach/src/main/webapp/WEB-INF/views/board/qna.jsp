<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" />

<div class="page-contents">
 <div class="category-container">
    <div class="section faq" onclick="moveBoardPage(5)">자주묻는질문</div>
    <div class="section qna" onclick="moveBoardPage(6)">문의게시판</div>
  </div>
    <form id="boardForm" name="boardForm" method="post">
 		
        <table>
          	 <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>날짜</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="result" items="${list}" varStatus="status">
                    <tr>
                        <td><c:out value="${result.id}"/></td>
                        <td><a href='#' onClick='fn_view(${result.id})'><c:out value="${result.title}"/></a></td>
                        <td><c:out value="${result.user_id}"/></td>           
                        <td><c:out value="${result.created_dt}"/></td>
                        <td><c:out value="${result.view_cnt}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
         <div>
		    <c:if test="${pagination.curRange ne 1}">
		        <a href="#" onClick="fn_paging(1)">[처음]</a>
		    </c:if>
		    <c:if test="${pagination.curPage ne 1}">
		        <a href="#" onClick="fn_paging('${pagination.prevPage}')">[이전]</a>
		    </c:if>
		    <c:forEach var="pageNum" begin="${pagination.startPage}" end="${pagination.endPage}">
		        <c:choose>
		            <c:when test="${pageNum eq pagination.curPage}">
		                <span style="font-weight: bold;"><a href="#" onClick="fn_paging('${pageNum}')">${pageNum}</a></span>
		            </c:when>
		            <c:otherwise>
		                <a href="#" onClick="fn_paging('${pageNum}')">${pageNum}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		    <c:if test="${pagination.curPage ne pagination.pageCnt && pagination.pageCnt > 0}">
		        <a href="#" onClick="fn_paging('${pagination.nextPage}')">[다음]</a>
		    </c:if>
		    <c:if test="${pagination.curRange ne pagination.rangeCnt && pagination.rangeCnt > 0}">
		        <a href="#" onClick="fn_paging('${pagination.pageCnt}')">[끝]</a>
		    </c:if>
		</div>
    </form>
    </div>
    
<script src="${pageContext.request.contextPath}/resources/js/board.js"></script>
<script>
//글쓰기
function fn_write(){
    
    var form = document.getElementById("boardForm");
    
    form.action = "<c:url value='/board/writeForm.do'/>";
    form.submit();
    
}
 
//글조회
function fn_view(id){
    
    var form = document.getElementById("boardForm");
    var url = "<c:url value='/board/viewContent.do'/>";
    url = url + "?id=" + id;
    
    form.action = url;    
    form.submit(); 
}

function moveBoardPage(category_id){
    var href = "${pageContext.request.contextPath}/board/"+category_id;
    console.log(href);
    location.href=href;
}

//JavaScript 코드
document.addEventListener('DOMContentLoaded', function() {
  // URL 정보 파싱
  const url = new URL(window.location.href);
  const path = url.pathname.split('/');
  const boardId = path[path.length - 1];

  // 섹션 요소 선택
  const sections = document.querySelectorAll('.category-container .section');

  // 섹션 배경색 변경
  sections.forEach(section => {
    section.classList.remove('faq', 'qna');
  });

  if (boardId === '5') {
    sections[0].classList.add('faq');
  } else if (boardId === '6') {
    sections[1].classList.add('qna');
  }
});
</script>
<%@ include file="../footer.jsp" %>  