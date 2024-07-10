<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>

<div class="page-contents">
    <form id="viewForm" name="viewForm" method="post">
        <div>
            <h2>글쓰기</h2>
            <div>
                <table>
                    <tr>
                        <th>제목</th>
                        <td>${result.title}</td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>${result.content}</td>
                        <c:if test="${result.category_id == 4}">
                        <td>
	        	         <iframe id="videoUrl" width="560" height="315" src=""
		                 frameborder="0" allow="accelerometer; autoplay; 
		                 encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                        </td>
                        </c:if>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>${result.user_id}</td>
                    </tr>
                </table>
                <div>
                    <a href='#' onClick='fn_update()'>수정</a>
                    <a href='#' onClick='fn_cancel()'>뒤로가기</a>
                    <a href='#' onClick='fn_relay()'>댓글작성</a> 
                    <a href='#' onClick='fn_delete()'>삭제</a> 
                
                </div>
            </div>
        </div>
        <input type='hidden' id='id' name='id' value='${result.id}' />
        <input type='hidden' id='category_id' name='category_id' value='${result.category_id}' />
    </form>
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

//돌아가기
function fn_cancel(){
	history.back();
}
 
//수정
function fn_update(){
    
    var form = document.getElementById("viewForm");
    
    form.action = "<c:url value='/board/updateForm.do'/>";
    form.submit();
}
 
//답변
function fn_relay(){
    
    var form = document.getElementById("viewForm");
    
    form.action = "<c:url value='/board/relayForm.do'/>";
    form.submit();
    
}

function fn_delete(){
	var form = document.getElementById("viewForm");
    form.action = "<c:url value='/board/delete.do'/>";
    form.submit();
	
}


</script>

</body>
<%@ include file="../footer.jsp" %>  