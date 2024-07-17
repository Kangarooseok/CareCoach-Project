<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ include file="../header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" />

<div class="page-contents">
  <div class="category-container">
    <div class="section notice" onclick="moveBoardPage(2)">공지사항</div>
    <div class="section freeboard" onclick="moveBoardPage(3)">자유게시판</div>
    <div class="section video" onclick="moveBoardPage(4)">헬스 영상</div>
  </div>

    <form id="boardForm" name="boardForm" method="post">
        <div style="text-align: right; padding-right : 100px;">
            <a href='#' onClick='fn_write()'>📝작성</a>
        </div>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>날짜</th>
                    <th>조회수</th>
                    <th>좋아요</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="result" items="${list}" varStatus="status">
                    <tr onClick='fn_view(${result.id})'>
                        <td><c:out value="${status.count}"/></td>
                        <td><c:out value="${result.title}"/></td>
                        <td><c:out value="${result.userId}"/></td>
                        <td><c:out value="${result.updatedDt}"/></td>
                        <td><c:out value="${result.viewCnt}"/></td>
                        <td>
                        <c:if test="${result.isLiked!=1}">
                            🤍<c:out value="${result.likeCnt}"/>
                        </c:if>
                        <c:if test="${result.isLiked==1}">
                            💗<c:out value="${result.likeCnt}"/>
                        </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!--페이징 처리-->
		<div style="text-align: center;">
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
    section.classList.remove('notice', 'freeboard', 'video');
  });

  if (boardId === '2') {
	  sections[0].classList.add('notice');
  } else if (boardId === '3') {
	    sections[1].classList.add('freeboard');
  } else if (boardId === '4') {
    sections[2].classList.add('video');
  } 
});


function moveBoardPage(categoryId){
    var href = "${pageContext.request.contextPath}/board/"+categoryId;
    console.log(href);
    location.href=href;
}
//글쓰기
function fn_write(){
      if (${empty sessionScope.id}) {
          alert("로그인 해주세요.");
          return;
      }

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
</script>
<%@ include file="../footer.jsp" %>  