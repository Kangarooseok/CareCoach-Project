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
         <div>            
            <a href='#' onClick='fn_write()'>글쓰기</a>            
        </div>
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
                        <td><c:out value="${result.updated_dt}"/></td>
                        <td><c:out value="${result.view_cnt}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </form>
       
    
</div>
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