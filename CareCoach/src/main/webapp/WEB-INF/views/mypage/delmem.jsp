<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="../resources/js/jquery.js"></script>

    <!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>비밀번호 재설정</title>
    <style>
    
      .body {
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
      }

      .popup-container {
        border: 1px solid #ccc;
        background-color: #fff;
        padding: 20px;
        width: 300px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }

      .popup-container h2 {
        font-size: 18px;
        margin-bottom: 20px;
      }

      .popup-container label {
        display: block;
        margin-bottom: 5px;
      }

      .popup-container input[type="password"],
      .popup-container input[type="password"] {
        width: calc(100% - 10px);
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 3px;
      }

      .popup-container .button {
        width: 100%;
        padding: 10px;
        background-color: #000;
        color: #fff;
        border: none;
        border-radius: 3px;
        cursor: pointer;
      }

      .popup-container .button:hover {
        background-color: #333;
      }
    </style>
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
    
  </head>
  <body>
    <div class="body">
      <div class="popup-container">
        <h2>회원탈퇴</h2>
        <form method="post" action="/del_mem_ok" onsubmit="return write_check();">
        
	        <label for="password">비밀번호</label>
	        <input type="password" name="password" id="password" placeholder="비밀번호" />
	        <label for="ckpassword">비밀번호 확인</label>
	        <input type="password" name="ckpassword" id="ckpassword" placeholder="비밀번호 확인." />
	        <input class="button" type="submit" value="회원탈퇴" />
	        
	   </form>
      </div>
    </div>
  </body>
</html>
    