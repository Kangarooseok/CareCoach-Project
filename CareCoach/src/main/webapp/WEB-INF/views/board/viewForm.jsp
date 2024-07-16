<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/viewboard.css" />
<div class="page-contents">

<!--  상단 카레고리  -->
<c:if test="${result.categoryId != 6}">
	<div class="category-container">
	  	<div class="section notice" onclick="moveBoardPage(2)">공지사항</div>
		<div class="section freeboard" onclick="moveBoardPage(3)">자유게시판</div>
		<div class="section video" onclick="moveBoardPage(4)">헬스 영상</div>
	</div>
	<script>
	document.addEventListener('DOMContentLoaded', function() {
	  const sections = document.querySelectorAll('.category-container .section');
	
	  // 섹션 배경색 변경
	  sections.forEach(section => {
	    section.classList.remove('notice', 'freeboard', 'video');
	  });
	
	  if (${result.categoryId} === 2) {
	    sections[0].classList.add('notice');
	  } else if (${result.categoryId} === 3) {
	    sections[1].classList.add('freeboard');
	  } else if (${result.categoryId} === 4) {
	    sections[2].classList.add('video');
	  }
	});
	</script>
</c:if>
  
<c:if test="${result.categoryId == 6}">
 <div class="category-container">
    <div class="section faq" onclick="moveBoardPage(5)">자주묻는질문</div>
    <div class="section qna" onclick="moveBoardPage(6)">문의게시판</div>
  </div>
  	<script>
  	document.addEventListener('DOMContentLoaded', function() {

  	  // 섹션 요소 선택
  	  const sections = document.querySelectorAll('.category-container .section');

  	  // 섹션 배경색 변경
  	  sections.forEach(section => {
  	    section.classList.remove('faq');
  	  });
  	});
	</script>
</c:if>

    <form id="viewForm" name="viewForm" method="post">
        <div>
            <div style="width: 800px; margin: 0 auto;">
                <div class="view-container">
                  <div class="viewtitle">
                    <span style="font-size: 22px;">
                        ${result.title}
                    <c:if test="${result.categoryId != 2 && loginid==result.userId}">
                      <button onClick='fn_update()'>수정</button>
                    </c:if>
                    <c:if test="${(result.categoryId == 3 || result.categoryId == 4 ) && loginid==result.userId}">
                      <button onClick='fn_delete()'>삭제</button>
                    </c:if>
                    </span>
                    <br>
                    👤 ${result.userId}
                  </div>
                  <div class="dateview">
                    ${result.updatedDt} <br> 조회수 : ${result.viewCnt}
                  </div>
                </div>
                <c:if test="${result.categoryId == 4}">
                <iframe id="videoUrl" width="560" height="315" src=""
                     frameborder="0" allow="accelerometer; autoplay;
                     encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                </iframe>
                </c:if>
                <div>${result.content}</div>
                <c:if test="${loginid!=null}">
                    <div class="likeDiv">
                        <c:if test="${is_liked!=1}">
                            <button onclick='fn_addlike(${result.id})'>🤍</button>${result.likeCnt}
                        </c:if>
                        <c:if test="${is_liked==1}">
                            <button onclick='fn_dellike(${result.id})'>💗</button>${result.likeCnt}
                        </c:if>
                    </div>
                </c:if>
            </div>
        </div>
        <input type='hidden' id='id' name='id' value='${result.id}' />
        <input type='hidden' id='categoryId' name='categoryId' value='${result.categoryId}' />
    </form>
<%@ include file="comment.jsp" %>

</div>

<script>

// URL에서 비디오 ID 추출
var url = '<c:out value="${result.url}"/>';
var videoId = url.split('v=')[1];
var ampersandPosition = videoId.indexOf('&');

if (ampersandPosition != -1) {
    videoId = videoId.substring(0, ampersandPosition);
}

// 비디오 URL 생성
var videoUrl = 'https://www.youtube.com/embed/' + videoId;

// 비디오 URL를 설정
document.getElementById('videoUrl').src = videoUrl;

//수정
function fn_update(){
    
    var form = document.getElementById("viewForm");
    
    form.action = "<c:url value='/board/updateForm.do'/>";

    form.submit();
}
 
function fn_delete(){
	var form = document.getElementById("viewForm");

    form.action = "<c:url value='/board/delete.do'/>";

    form.submit();
}

function fn_addlike(postId){
	
	var form = document.getElementById("viewForm");
	
    form.action = "<c:url value='/board/addlike.do'/>";

    form.submit();
}

function fn_dellike(postId){
	
	var form = document.getElementById("viewForm");
	
    form.action = "<c:url value='/board/deletelike.do'/>";

    form.submit();
}


</script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</body>
<%@ include file="../footer.jsp" %>  