<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" />
<div class="page-contents">
    <form id="writeForm" name="writeForm" method="post">
        <div>
            <div>
                <table>
                
                    <tr>
                        <th style="width:200px;">카테고리</th>
                        <td style="text-align: left;">
                            <select id="categoryId" name="categoryId"onChange="toggleUrlField()" >
                                <option value="3" ${result.categoryId == 3 ? 'selected' : ''}>자유게시판</option>
                                <option value="4" ${result.categoryId == 4 ? 'selected' : ''}>헬스영상</option>
                                <option value="6" ${result.categoryId == 6 ? 'selected' : ''}>문의게시판</option>
                            </select>
                            <button style="margin-left: 20px;" onClick='fn_addPost()'>등록</button><button style="margin-left: 20px;" onClick='history.back()'>취소</button>
                        </td>
                    </tr>
                        <tr id="urlRow" style="display:none;">
                        <th style="width:150px;">영상 주소</th>
                        <td style="text-align: left;"><input style="width: 500px" type="text" id="url" name="url"  placeholder="동영상 URL을 입력해주세요."/></td>
                    </tr>
                    <tr>
                        <th style="width:150px;">제목</th>
                        <td style="text-align: left;"><input style="width: 500px" type="text" id="title" name="title"  placeholder="제목을 입력해 주세요." /></td>
                    </tr>
                    <tr>
                        <th style="width:150px; vertical-align: top; text-align: center;">내용</th>
                        <td style="text-align: left; width:400px; height:600px"><textarea style="width: 1100px; height:600px"; rows="10" cols="10" id="content" name="content"  placeholder="내용을 입력해 주세요."></textarea></td>
                    </tr>
              </table>
            </div>
        </div>
         <input type='hidden' style="width: 500px;" type="text" id="userId" name="userId"  value="${sessionScope.id}"/>
    </form>
</div>
<script>

//작성 완료
function fn_addPost(){
        var title = document.getElementById("title").value.trim();
        var content = document.getElementById("content").value.trim();
        var categoryId = document.getElementById("categoryId").value;
        var url = document.getElementById("url").value.trim();

        if (title === "") {
            alert("제목을 입력해 주세요.");
            event.preventDefault();
            return;
        }

        if (categoryId == 4 && url === "") {
            alert("동영상 URL을 입력해 주세요.");
            event.preventDefault();
            return;
        }

        if (content === "") {
            alert("내용을 입력해 주세요.");
            event.preventDefault();
            return;
        }
    var form = document.getElementById("writeForm");
    
    form.action = "/board/write.do";
    form.submit();
    
}
 

//URL 입력란 표시 토글
function toggleUrlField() {
    var category = document.getElementById("categoryId").value;
    var urlRow = document.getElementById("urlRow");

    if (category == "4") { // '헬스 영상' 카테고리가 선택되었을 때
        urlRow.style.display = "";
    } else {
        urlRow.style.display = "none";
    }
}

// 페이지 로드 시 초기 상태 설정
window.onload = function() {
    toggleUrlField();
};
</script>


<%@ include file="../footer.jsp" %>  