<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %> 

<script src="../resources/js/jquery.js"></script>

<script>
function write_check(){
	  if($.trim($("#userId").val())== ""){
		  alert("아이디를 입력하세요.");
		  $("#userId").val("").focus();
		  return false;
	  }
	  
	  if($.trim($("#password").val())== ""){
		  alert("비밀번호를 입력하세요.");
		  $("#password").val("").focus();
		  return false;
	  }
}

</script>
    <div class="loginWrapper">
      <h2>로그인</h2>
      <form method="post" id="login-form" class="loginForm" action="/member_login_ok" onsubmit="return write_check();">
        <div class="inputWrapper">
          <input type="text" name="userId" id="userId" placeholder="아이디" />
          <input type="password" name="password" id="password" placeholder="비밀번호" />
        </div>
        <div class="loginNavigation">
          <input type="button" value="아이디 찾기" onclick="openLoginModal()">
          <input type="button" value="비밀번호 변경" onclick="openLoginModal2()">
          <input type="button" value="회원가입" onclick="location.href='${pageContext.request.contextPath}/terms'">
        </div>
        <input type="submit" value="로그인" />
       </form>
    </div>
    
    <div class="modal-contents"></div>
    
    
<script type="text/javascript" src="../resources/js/post_findId.js"></script>
<%@ include file="../footer.jsp" %>