<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="../resources/js/jquery.js"></script>
<script>
function write_check(){
	  if($.trim($("#name").val())== ""){
		  alert("이름을 입력하세요");
		  $("#name").val("").focus();
		  return false;
	  }
	  
	  if($.trim($("#email").val())== ""){
		  alert("이메일을 입력하세요");
		  $("#email").val("").focus();
		  return false;
	  }
}
</script>


  	<div class="find_body">
  		<div class="modal" id="myModal">
      <div class="modal-content">
        <span class="close" id="closeModal">&times;</span>
        <h2>아이디 찾기</h2>
        <form
          method="post"
          id="login-form"
          class="loginForm"
          action="/findIdResult"
          onsubmit="return write_check();"
        >
          <label for="nickname">이름</label>
          <input
            type="text"
            name="nickname"
            placeholder="이름을 입력해 주세요."
            id="name"
          />
          <label for="email">이메일</label>
          <input
            type="email"
            name="email"
            placeholder="이메일을 입력해 주세요."
            id="email"
          />
          <button class="button" type="submit">Submit</button>
        </form>
      </div>
    </div>
  	</div>
  	
  	<div class="modal-result-content"></div>
  
    

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
    <script type="text/javascript" src="../resources/js/post_findidresult.js"></script>