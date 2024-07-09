<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./header.jsp" %> 
<c:set var="articlesList" value="${articlesMap.articlesList}" />
<c:set var="noticesList" value="${noticesMap.noticesList}" />
    
    <main>
      <div class="slider-container">
        <button class="arrow left">&#9664;</button>
        <!-- Left arrow -->
        <div class="slider">
          <div class="slide"><img src="${pageContext.request.contextPath}/resources/images/banner1.png" alt="Banner 1"/></div>
          <div class="slide"><img src="${pageContext.request.contextPath}/resources/images/banner2.png" alt="Banner 2" /></div>
          <div class="slide"><img src="${pageContext.request.contextPath}/resources/images/banner3.png" alt="Banner 3" /></div>
          <div class="slide"><img src="${pageContext.request.contextPath}/resources/images/banner4.png" alt="Banner 4" /></div>
        </div>
        
        <button class="arrow right">&#9654;</button>
        <!-- Right arrow -->
      </div>
    </main>

    <div class="main-content">
      <!-- Notices Section -->
      <div class="section notices">
        <h2>공지사항</h2>
        <c:choose>
          <c:when test="${empty noticesList}">
            <tr height="10">
              <td colspan="4">
                <p>
                  <b><span>등록된 글이 없습니다.</span></b>
                </p>
              </td>
            </tr>
          </c:when>

          <c:when test="${!empty noticesList}">
            <c:forEach
              var="notice"
              items="${noticesList}"
              varStatus="noticeNum"
            >
              <tr>
                <td>${noticeNum.count}</td>
                <td>
                  <a
                    class="cls1"
                    href="${contextPath}/board/viewNotice.do?noticeNO=${notice.noticeNO}"
                    >${notice.title}</a
                  >
                </td>
              </tr>
            </c:forEach>
          </c:when>
        </c:choose>
      </div>

      <!-- Board Section -->
      <div class="section board">
        <h2>게시판</h2>
        <c:choose>
          <c:when test="${empty articlesList}">
            <tr height="10">
              <td colspan="4">
                <p>
                  <b><span>등록된 글이 없습니다.</span></b>
                </p>
              </td>
            </tr>
          </c:when>

          <c:when test="${!empty articlesList}">
            <c:forEach
              var="article"
              items="${articlesList}"
              varStatus="articleNum"
            >
              <tr>
                <td>${articleNum.count}</td>
                <td>
                  <a
                    class="cls1"
                    href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}"
                    >${article.title}</a
                  >
                </td>
              </tr>
            </c:forEach>
          </c:when>
        </c:choose>
      </div>

      <!-- Health Videos Section -->
      <div class="section videos">
        <h2>헬스 영상</h2>
        <div class="video-item">
          <a href="#">
            <img
              src="https://via.placeholder.com/1200x628"
              alt="헬스 초보자 어깨운동? 딱 '2가지'만 하세요!!"
            />
            <div class="video-description">
              <p>헬스 초보자 어깨운동? 딱 '2가지'만 하세요!!</p>
              <div class="video-meta">
                <span>&#x2764; 1035</span>
                <span>&#128100; 김머머</span>
              </div>
            </div>
          </a>
        </div>
        <div class="video-item">
          <a href="#">
            <img src="https://via.placeholder.com/1200x628" alt="덱스 죽이기" />
            <div class="video-description">
              <p>덱스 죽이기</p>
              <div class="video-meta">
                <span>&#x2764; 1022</span>
                <span>&#128100; 헬스해</span>
              </div>
            </div>
          </a>
        </div>
      </div>
    </div>
<script type="text/javascript" src="../resources/js/bannerSlide.js"></script>
<%@ include file="./footer.jsp" %>  