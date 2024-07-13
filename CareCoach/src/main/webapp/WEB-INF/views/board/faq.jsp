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
    <h1>자주 묻는 질문(FAQ)</h1>
	    <div class="faq-container">
	        <c:forEach var="result" items="${list}" varStatus="status">
		        <div class="faq-item">
		        	<div class="faq-question" style="cursor: pointer;"><span><c:out value="${result.title}"/></span><span>▼</span></div>
		         	<div class="faq-answer"><c:out value="${result.content}"/></div>
	       		</div> 
	         </c:forEach>
   </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/board.js"></script>
<script>
var faqQuestions = document.querySelectorAll(".faq-question");
faqQuestions.forEach(function(question) {
    question.addEventListener("click", function() {
        this.classList.toggle("active");
        var answer = this.nextElementSibling;
        if (answer.style.display === "none") {
            this.querySelector("span:last-child").textContent = "▲";
            answer.style.display = "block";
        } else {
            this.querySelector("span:last-child").textContent = "▼";
            answer.style.display = "none";
        }
    });
});

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

  function moveBoardPage(categoryId){
      var href = "${pageContext.request.contextPath}/board/"+categoryId;
      console.log(href);
      location.href=href;
  }

</script>
<%@ include file="../footer.jsp" %>  