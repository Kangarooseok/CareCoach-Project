<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="./header.jsp" %>
<%@ page import="java.util.regex.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
  <div class="slider-container">
    <button class="arrow left">&#9664;</button>
    <!-- Left arrow -->
    <div class="slider">
      <div class="slide">
        <img src="../resources/images/banner1.png" alt="Banner 1" />
      </div>
      <div class="slide">
        <img src="../resources/images/banner2.png" alt="Banner 2" />
      </div>
      <div class="slide">
        <img src="../resources/images/banner3.png" alt="Banner 3" />
      </div>
      <div class="slide">
        <img src="../resources/images/banner4.png" alt="Banner 4" />
      </div>
    </div>
    <button class="arrow right">&#9654;</button>
    <!-- Right arrow -->
  </div>
</main>

<form id="indexForm" name="indexForm" method="post">
<div class="main-content">
  <!-- Notices Section -->
  <div class="section notices">
    <h2>공지사항</h2>
    <c:choose>
      <c:when test="${empty recentPosts1}">
        <tr height="10">
          <td colspan="4">
            <p>
              <b><span>등록된 글이 없습니다.</span></b>
            </p>
          </td>
        </tr>
      </c:when>

      <c:when test="${!empty recentPosts1}">
        <c:forEach var="post" items="${recentPosts1}" varStatus="postNum">
          <li onclick='fn_view(${post.id})' style="margin-bottom: 15px">
            <a class="cls1 posts" href="#"> ${postNum.count}. ${post.title} </a>
          </li>
        </c:forEach>
      </c:when>
    </c:choose>
  </div>

  <!-- Board Section -->
  <div class="section board">
    <h2>최근 게시물</h2>
    <c:choose>
      <c:when test="${empty recentPosts2}">
        <tr height="10">
          <td colspan="4">
            <p>
              <b><span>등록된 글이 없습니다.</span></b>
            </p>
          </td>
        </tr>
      </c:when>

      <c:when test="${!empty recentPosts2}">
        <c:forEach var="post" items="${recentPosts2}" varStatus="postNum">
          <li onclick='fn_view(${post.id})' style="margin-bottom: 15px">
            <a class="cls1 posts" href="#"> ${postNum.count}. ${post.title} </a>
          </li>
        </c:forEach>
      </c:when>
    </c:choose>
  </div>

  <!-- Health Videos Section -->
  <div class="section videos">
    <h2>헬스 영상</h2>
    <c:choose>
        <c:when test="${empty recentPosts3}">
            <div class="video-item">
                <a href="#">
                    <img src="https://via.placeholder.com/1200x628" alt="해당 페이지가 없습니다." />
                </a>
                <div class="video-description">
                    <p>해당 페이지가 없습니다.</p>
                    <div class="video-meta">
                        <span>&#x2764; 0</span> <span>&#128100; 관리자</span>
                    </div>
                </div>
            </div>
            <div class="video-item">
                <a href="#">
                    <img src="https://via.placeholder.com/1200x628" alt="해당 페이지가 없습니다." />
                </a>
                <div class="video-description">
                    <p>해당 페이지가 없습니다.</p>
                    <div class="video-meta">
                        <span>&#x2764; 0</span> <span>&#128100; 관리자</span>
                    </div>
                </div>
            </div>
        </c:when>
	<c:when test="${!empty recentPosts3}">
    <c:forEach var="post" items="${recentPosts3}" varStatus="postNum">
        <div onclick='fn_view(${post.id})' class="video-item">
            <a href="#">
                <img id="${postNum.index}" src="" alt="${post.title}" />
            </a>
            <div class="video-description">
                <p>${post.title}</p>
                <div class="video-meta">
                    <span>&#x2764;${recentPosts4[postNum.index]}</span> <span>&#128100; ${post.userId}</span>
                </div>
            </div>
        </div>
    	<script>
           // URL에서 비디오 ID 추출
           var url = '<c:out value="${post.url}"/>';
           var videoId = url.split('v=')[1];
           var ampersandPosition = videoId.indexOf('&');
           if (ampersandPosition != -1) {
               videoId = videoId.substring(0, ampersandPosition);
           }
           // 썸네일 URL 생성
           var thumbnailUrl = 'https://img.youtube.com/vi/' + videoId + '/mqdefault.jpg';
           // 썸네일 이미지를 설정
           document.getElementById('${postNum.index}').src = thumbnailUrl;
       </script>
    </c:forEach>
</c:when>
    </c:choose>
    
</div>
</div>
  </form>
<script type="text/javascript" src="../resources/js/bannerSlide.js"></script>
<script>
//글조회
function fn_view(id){
    
    var form = document.getElementById("indexForm");
    var url = "<c:url value='/board/viewContent.do'/>";
    url = url + "?id=" + id;
    
    form.action = url;    
    form.submit(); 
}
</script>



<%@ include file="./footer.jsp" %>
