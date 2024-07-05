<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>
<body>
<div class="page-contents">
    <form id="writeForm" name="writeForm" method="post">
        <div>
            <h2>글쓰기</h2>
            <div>
                <table>
                
                    <tr>
                        <th>카테고리</th>                       
                        <td>
                            <select id="category_id" name="category_id">
                                <option value="3">게시판</option>
                                <option value="4">헬스 영상</option>
                                <option value="6">문의 게시판</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td><input style="width: 500px" type="text" id="title" name="title" /></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td><textarea style="width: 500px" rows="10" cols="10" id="content" name="content"></textarea></td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td><input style="width: 500px" type="text" id="user_id" name="user_id" /></td>
                    </tr>
                </table>
                <div>
                    <a href='#' onClick='fn_addtoBoard()'>등록</a>
                    <a href='#' onClick='fn_cancel()'>취소</a>
                </div>
            </div>
        </div>
    </form>
<script>
//글쓰기
function fn_addtoBoard(){

	
    var form = document.getElementById("writeForm");
    
    form.action = "<c:url value='/board/write.do'/>";
    form.submit();
    
}writeForm.jsp
 
//취소
function fn_cancel(){
	 window.history.back();
}
</script>
</div>
</body>


<%@ include file="../footer.jsp" %>  