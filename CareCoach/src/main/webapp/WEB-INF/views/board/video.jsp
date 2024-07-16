<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" />

<div class="page-contents">
<div class="category-container">
    <div class="section notice" onclick="moveBoardPage(2)">공지사항</div>
    <div class="section freeboard" onclick="moveBoardPage(3)">자유게시판</div>
    <div class="section video" onclick="moveBoardPage(4)">헬스 영상</div>
  </div>

   <form id="boardForm" name="boardForm" method="post">
 		<div>            
            <a href='#' onClick='fn_write()'>작성</a>            
        </div>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>URL</th>
                    <th>작성자</th>
                    <th>날짜</th>
                    <th>조회수</th>
                    <th>좋아요</th>
                </tr>
            </thead>
            <tbody>
			    <c:forEach var="result" items="${list}" varStatus="status">
		      
			        <tr onClick='fn_view(${result.id})'>
			            <td><c:out value="${status.count} "/></td>
			            <td><c:out value="${result.title}"/></td>
			            <td><img id="${status.index}" src="" alt="썸네일" style="width: 100px; height: 60px;"/></td>
			            <td><c:out value="${result.userId}"/></td>
			            <td><c:out value="${result.createdDt}"/></td>
			            <td><c:out value="${result.viewCnt}"/></td>
			            <td><c:out value="${result.likeCnt}"/></td>
			        </tr>
		   
			        	
			        <script>
			            // URL에서 비디오 ID 추출
			            var url = '<c:out value="${result.url}"/>';
			            var videoId = url.split('v=')[1];
			            var ampersandPosition = videoId.indexOf('&');
			            if (ampersandPosition != -1) {
			                videoId = videoId.substring(0, ampersandPosition);
			            }
			            // 썸네일 URL 생성
			            var thumbnailUrl = 'https://img.youtube.com/vi/' + videoId + '/mqdefault.jpg';
			            // 썸네일 이미지를 설정
			            document.getElementById('${status.index}').src = thumbnailUrl;
			        </script>
			    </c:forEach>
            </tbody>
        </table>
		<div style="margin: auto">
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
      if (${empty sessionScope.id}) {
          alert("로그인해주세요.");
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


</script>

<%@ include file="../footer.jsp" %>  