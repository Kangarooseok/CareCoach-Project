<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>아이디 찾기</title>
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

      .popup-container input[type="text"],
      .popup-container input[type="email"] {
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
    
  </head>
  <body>
    <div class="body">
      <div class="popup-container">
        <h2>아이디 찾기</h2>
        <form method="post" action="CareCoach?command=find_id_result" id="login-form" class="loginForm">
	        <label for="nickname">이름</label>
	        <input type="text" name="nickname" placeholder="이름을 입력해 주세요." />
	        <label for="email">이메일</label>
	        <input type="email" name="email" placeholder="이메일을 입력해 주세요." />
	        <input class="button" type="submit" value="아이디 찾기" onclick="post_findId()" />
	   </form>
      </div>
    </div>
  </body>
</html>
    