<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
        <div id="commentList">
        </div>
    </form>
</div>
<div id="editModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>댓글 수정</h2>
        <textarea id="editContent" style="width: 100%" rows="3"></textarea>
        <br>
        <button id="updateButton" class="btn btn-success">수정 완료</button>
    </div>
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
  $("#editModal").hide();
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
                  html += "<div>";
                  html += "<div><table class='table'><h6><strong>" + data[i].userId + "</strong></h6>";
                  html += data[i].content + "<tr><td></td></tr>";
                  html += "<button onclick='editItem("+ data[i].id + ","+ data[i].content+")'>수정</button>";
                  html += "<button onclick='deleteItem(" + data[i].id + ")'>삭제</button>";
                  html += "</table></div>";
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
  $("#editModal").show();
  $("#editContent").val(content);
  $("#updateButton").attr("onclick", "updateComment(" + commentId + ")");
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
  $.ajax({
      type: 'POST',
      url: "<c:url value='/board/updateComment.do'/>",
      data: {
          id: commentId,
          content: $("#editContent").val()
      },
      success: function(data){
          if(data == "success") {
              getCommentList();
              $("#editModal").hide();
          } else {
              alert("댓글 수정에 실패했습니다.");
          }
      },
      error: function(request, status, error){
          alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
      }
  });
}

// 모달 닫기 기능
$(document).ready(function(){
  $(".close").click(function(){
      $("#editModal").hide();
  });
});
</script>

 
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

