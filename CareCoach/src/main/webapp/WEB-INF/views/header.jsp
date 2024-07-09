<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>CareCoach</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/careCoach.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/CareCoachMypage.css" />
    
  </head>
 <header>
      <div class="container">
        <div class="logo">
          <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/resources/images/CareCoach-logo.png" alt="CareCoach" />
          </a>
        </div>
        <nav>
          <ul class="nav-links">
            <li>
              <a href="#" onClick='moveBoardPage(1);'>About Us</a>
            </li>
            <li>
              <a href="#">Community</a>
              <ul class="dropdown">
                <li><a href="#" onClick='moveBoardPage(2);'>공지사항</a></li>
                <li><a href="#" onClick='moveBoardPage(3);'>게시판</a></li>
                <li><a href="#" onClick='moveBoardPage(4);'>헬스영상</a></li>
              </ul>
            </li>
            <li>
              <a href="#">QnA</a>
              <ul class="dropdown">
                <li><a href="#" onClick='moveBoardPage(5);'>자주 묻는 질문</a></li>
                <li><a href="#" onClick='moveBoardPage(6);'>문의 게시판</a></li>
                </ul>
            </li>
            <li>
              <a href="#" onClick='moveBoardPage(7);'>CareCoach</a>
            </li>
          </ul>
        </nav>

        <div class="auth-links">
          <c:if test="${empty id}">
              <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
              <li class="contour">|</li>
              <li><a href="${pageContext.request.contextPath}/terms">Join</a></li>
           </c:if>
            <c:if test="${!empty id}">
              <li>
               <a href="/member_logout">Logout |</a>
              </li>
              <li><a href="/mypage">마이페이지</a></li>
          </c:if>
        </div>
      </div>
     
 </header>
  <script>
   function moveBoardPage(category_id){
       var href = "${pageContext.request.contextPath}/board/"+category_id;
       console.log(href);
       location.href=href;
   }
  </script>
    
    
    

