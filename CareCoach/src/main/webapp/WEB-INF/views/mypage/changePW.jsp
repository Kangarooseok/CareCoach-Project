<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="../resources/js/jquery.js"></script>

    <!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>비밀번호 재설정</title>
    
    <script>
function write_check(){
	  if($.trim($("#password").val())== ""){
		  alert("현재 비밀번호를 입력하세요");
		  $("#password").val("").focus();
		  return false;
	  }
	  
	  if($.trim($("#newpassword").val())== "" || $.trim($("#newpasswordcheck").val())== ""){
		  alert("새 비밀번호와 새 비밀번호 확인 모두 입력하세요");
		  return false;
	  }
	  
	  if($.trim($("#newpassword").val()) != $.trim($("#newpasswordcheck").val())){
		  alert("입력된 새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다");
		  return false;
	  }
	
}

</script>
    


<div class="find_body">
	<div class="modal" id="myModal">
		<div class="modal-content">
			<span class="close" id="closeModal">&times;</span>
			<h2>회원탈퇴</h2>
			<form method="post" id="login-form" class="loginForm"
				action="/chgpw_ok" onsubmit="return write_check();">
				<label for="password">현재 비밀번호</label>
	        <input type="password" name="password" id="password" placeholder="비밀번호를 입력해 주세요." />
	        <label for="newpassword">새 비밀번호</label>
	        <input type="password" name="newpassword" id ="newpassword" placeholder="새 비밀번호를 입력해 주세요." />
	        <label for="newpasswordcheck">새 비밀번호 확인</label>
	        <input type="password" name="newpasswordcheck" id="newpasswordcheck" placeholder="새 비밀번호 확인." />
	        <input class="button" type="submit" value="비밀번호 변경" />
			</form>
		</div>
	</div>
</div>


<script>
      // Get modal element
      var modal = document.getElementById("myModal");
      // Get close button
      var closeModal = document.getElementById("closeModal");

      // Show modal
      window.onload = function () {
        modal.style.display = "flex";
      };

      // Close modal when 'x' is clicked
      closeModal.onclick = function () {
        modal.style.display = "none";
      };

      // Close modal when clicking outside of modal
      window.onclick = function (event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
      };
    </script>
    