<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/viewboard.css" />
<style>
.backbutton {
    display: inline-block;
    padding: 5px 10px;
    margin-left: 10px;
    font-size: 12px;
    color: #fff;
    background-color: #565656;
    border: solid 1px;
    border-radius: 5px;
    text-align: center;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

/* 버튼 호버 스타일 */
.backbutton:hover {
    background-color: #0056b3;
}
</style>

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
            <div style="width: 1000px; margin: 0 auto;">
                <div class="view-container">
                  <div class="viewtitle">
                    <span style="font-size: 22px;  margin:10px;">
                        ${result.title}
                    <c:if test="${result.categoryId != 2 && loginid==result.userId}">
                      <button class="backbutton" style="background-color: #e0f8eb; color: #565656;" onClick='fn_update()'>수정</button>
                    </c:if>
                    <c:if test="${(result.categoryId == 3 || result.categoryId == 4 ) && loginid==result.userId}">
                      <button class="backbutton" onClick='fn_delete()'>삭제</button>
                    </c:if>
                    </span>
                    <br>
                    <br>
                    <span>
                    👤 ${result.userId}
                    <span>
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
                <div class="likeDiv">
                    <c:if test="${is_liked==0 && result.categoryId != 6}">
                        <button id="serverCallButton" onclick='fn_addlike(${result.id})'>🤍</button>${result.likeCnt}
                    </c:if>
                    <c:if test="${is_liked!=0}">
                        <button id="serverCallButton" onclick='fn_dellike(${result.id})'>💗</button>${result.likeCnt}
                    </c:if>
                </div>
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
    if(confirm("수정하시겠습니까?")){
    var form = document.getElementById("viewForm");
    
    form.action = "<c:url value='/board/updateForm.do'/>";

    form.submit();
    }
}
 
function fn_delete(){
    if(confirm("정말 삭제하시겠습니까?")){

	var form = document.getElementById("viewForm");

    form.action = "<c:url value='/board/delete.do'/>";

    form.submit();
    }
}

function fn_addlike(postId){

    if(${empty sessionScope.id}){
        alert("로그인해주세요");
        return;
    }
    // 버튼 요소
    const serverCallButton = $("#serverCallButton");

    // 버튼이 비활성화되어 있는 경우(이미 클릭된 경우)
    if (serverCallButton.prop("disabled")) {
         console.log("서버 통신 중");
         return;
    }

    // 버튼을 비활성화하여 중복 클릭 방지
    serverCallButton.attr("disabled", true);

	var form = document.getElementById("viewForm");
	
    form.action = "<c:url value='/board/addlike.do'/>";

    form.submit();

    console.log("서버 통신 성공");
}

function fn_dellike(postId){

    if(${empty sessionScope.id}){
        alert("로그인해주세요");
        return;
    }

    // 버튼 요소
    const serverCallButton = $("#serverCallButton");

    // 버튼이 비활성화되어 있는 경우(이미 클릭된 경우)
    if (serverCallButton.prop("disabled")) {
         console.log("서버 통신 중");
         return;
    }
    // 버튼을 비활성화하여 중복 클릭 방지
    serverCallButton.attr("disabled", true);

	var form = document.getElementById("viewForm");
	
    form.action = "<c:url value='/board/deletelike.do'/>";

    form.submit();

    console.log("서버 통신 성공");
}


</script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</body>
<%@ include file="../footer.jsp" %>  