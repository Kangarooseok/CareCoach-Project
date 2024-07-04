<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../resources/js/jquery.js"></script>


<script>
    
function write_check(){
	  if($.trim($("#password").val())== "" || $.trim($("#ckpassword").val())== ""){
		  alert("비밀번호와 비밀번호 확인 모두 입력하세요");
		  return false;
	  }
	  
	  if($.trim($("#password").val()) != $.trim($("#ckpassword").val())){
		  alert("입력된 비밀번호와 비밀번호 확인이 일치하지 않습니다");
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
				action="/del_mem_ok" onsubmit="return write_check();">
				<label for="password">비밀번호</label>
				<input type="password" name="password" id="password" placeholder="비밀번호" />
				<label for="ckpassword">비밀번호 확인</label>
				<input type="password" name="ckpassword" id="ckpassword" placeholder="비밀번호 확인." />
				<button class="button" type="submit">회원 탈퇴</button>
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
