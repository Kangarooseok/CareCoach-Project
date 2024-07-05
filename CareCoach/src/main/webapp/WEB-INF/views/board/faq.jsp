<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" />


    <div class="page-contents">
    <h1>자주 묻는 질문(FAQ)</h1>
	    <div class="faq-container">
	        <div class="faq-item">
	            <div class="faq-question"><span>CareCoach란 무엇인가요?</span><span>▼</span></div>
	            <div class="faq-answer">
					테스트 문구 1 <br>
					테스트 문구 1 <br>
					

	            </div>
	        </div>
	        <div class="faq-item">
	          
	            <div class="faq-question">
	              <span>비밀번호를 잊어버렸습니다</span><span>▼</span>
	              </div>
	            <div class="faq-answer">
	               테스트 문구 2 <br>
	               테스트 문구 2 <br>
	               테스트 문구 2 <br>
	               테스트 문구 2 <br>
	               
	            </div>
	        </div>
	        <!-- 추가 FAQ 항목 -->
	    </div>
    </div>

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
</script>
<%@ include file="../footer.jsp" %>  