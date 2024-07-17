<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/comment.css" />

<div class="container">
    <form id="commentForm" name="commentForm" method="post">
    <br><br>
        <div>
            <div>
                <span><strong>Comments</strong></span> <span id="cCnt"></span>
            </div>
            <div>
                <table class="table">                    
                    <tr>
                        <td>
                            <textarea style="width: 1100px" rows="3" cols="30" id="content" name="content" placeholder="댓글을 입력하세요"></textarea>
                            <br>
                            <div>
                                <a href='#' onClick="fn_comment('${result.id}')" class="btn pull-right btn-success">등록</a>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <input type="hidden" id="postId" name="postId" value="${result.id}" />
    </form>
</div>
<div class="container">
    <form id="commentListForm" name="commentListForm" method="post">
        <div id="commentList" class="commentList">
        </div>
    </form>
</div>

<script>
/*
* 댓글 등록하기(Ajax)
*/
function fn_comment(code){
  if(${empty sessionScope.id}){
      alert("로그인해주세요");
      return;
  }
  $.ajax({
      type: 'POST',
      url: "<c:url value='/board/addComment.do'/>",
      data: $("#commentForm").serialize(),
      success: function(data){
          if(data == "success") {
              getCommentList();
              $("#content").val("");
          }
      },
      error: function(request, status, error){
          alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
      }
  });
}

/**
* 초기 페이지 로딩시 댓글 불러오기
*/
$(function(){
  getCommentList();
});

/**
* 댓글 불러오기(Ajax)
*/
function getCommentList(){
  $.ajax({
      type: 'GET',
      url: "<c:url value='/board/commentList.do'/>",
      dataType: "json",
      data: {postId: $("#postId").val()},
      contentType: "application/x-www-form-urlencoded; charset=UTF-8",
      success: function(data){
          var html = "";
          var cCnt = data.length;
          if(data.length > 0){
              for(var i = 0; i < data.length; i++){
                  html += "<div class='comments' id='comment" + data[i].id + "'>";
                  html += "<div><h6><strong>👤 " + data[i].userId;
                  if ('${loginid}' == data[i].userId) {
                    html += " (본인)";
                  }
                  html += "</strong></h6>";
                  html += data[i].content;
                  html += "<div class='datecomment'>"+data[i].updatedDt+"<br>";
                  if ('${loginid}' == data[i].userId) {
                  html += "<button onclick=\"editItem(" + data[i].id + ",'" + data[i].content + "')\">수정</button>";
                  html += "<button onclick='deleteItem(" + data[i].id + ")'>삭제</button>";
                  };
                  html += "</div>";
                  html += "</div>";
                  html += "</div>";
              }
          } else {
              html += "<div>";
              html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
              html += "</table></div>";
              html += "</div>";
          }

          $("#cCnt").html(cCnt);
          $("#commentList").html(html);
      },
      error: function(request, status, error){
          alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
      }
  });
}

// 댓글 수정 기능
function editItem(commentId, content) {
    // 기존 댓글 내용을 숨기고 수정 가능한 입력 칸과 저장 버튼을 생성
    var editHtml = "<textarea id='editContent" + commentId + "' rows='3' cols='30'>" + content + "</textarea>";
    editHtml += "<button onclick='updateComment(" + commentId + ")'>저장</button>";
    editHtml += "<button onclick='cancelEdit()'>취소</button>";

    // 해당 댓글 영역을 수정 입력 영역으로 대체
    $("#comment" + commentId).html(editHtml);
}

// 댓글 삭제 기능
function deleteItem(commentId) {
  if(confirm("정말 삭제하시겠습니까?")) {
      $.ajax({
          type: 'POST',
          url: "<c:url value='/board/delComment.do'/>",
          data: {id: commentId},
          success: function(data){
              if(data == "success") {
                  getCommentList();
              } else {
                  alert("댓글 삭제에 실패했습니다.");
              }
          },
          error: function(request, status, error){
              alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
          }
      });
  }
}

// 댓글 수정 요청
function updateComment(commentId) {
    var updatedContent = $("#editContent" + commentId).val();
    if (updatedContent === "") {
                alert("내용을 입력해 주세요.");
                event.preventDefault();
                return;
    }

    $.ajax({
        type: 'POST',
        url: "<c:url value='/board/updateComment.do'/>",
        data: {
            id: commentId,
            content: updatedContent
        },
        success: function(data){
            if(data == "success") {
                getCommentList();
            } else {
                alert("댓글 수정에 실패했습니다.");
            }
        },
        error: function(request, status, error){
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

// 수정 취소 클릭시 댓글 다시 불러옴
function cancelEdit() {
    getCommentList();
}
</script>

 
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

