<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CareCoach 회원가입</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #ffffff;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        header {
            width: 100%;
            background-color: white;
            padding: 20px;
            border-bottom: 1px solid #ddd;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        header img {
            height: 70px;
            margin-left: 20px;
        }
        header nav {
            margin-right: 20px;
        }
        header nav a {
            text-decoration: none;
            color: black;
            margin-left: 20px;
        }
        body {
            font-family: Arial, sans-serif;
             margin: 0;
            padding: 0;
             background-color: #ffffff
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: white;
        }

        .logo {
            height: 40px;
        }

        nav a {
            margin-left: 15px;
            text-decoration: none;
            color: #333;
        }

        .container {
            width: 550px;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .form-control {
            margin-top: 2px;
        }

        .btn-primary {
            width: 100%;
            padding: 10px;
            background-color: #f0f0f0;
            border: none;
            color: black;
            font-weight: bold;
            border-radius: 4px;
            margin-top: 10px;
            cursor: pointer;
        }

        .btn-primary:hover {
            background-color: #ebd79a
        }

        .btn-finish{
            width: 100%;
            padding: 10px;
            background-color: #e6c200;
            border: none;
            color: black;
            font-weight: bold;
            border-radius: 4px;
            margin-top: 10px;
            cursor: pointer;
        }

        .btn-finish:hover {
            background-color: #ebd79a;
        }
    </style>
        <header>
            <img src="image/CareCoach.png" alt="CareCoach Logo">
            <nav>
                <a href="#">Login</a> 
                <a href="#">Join</a>
            </nav>
        </header>
</head>
<body>
    <h1>회원가입 페이지</h1>
    <div class="container">
        <form method="post" action="./userRegister">
            <h4>회원가입 양식</h4>
            <div class="form-group">
                <label for="userID">아이디</label>
                <input class="form-control" type="text" id="userID" name="userID" maxlength="20" placeholder="아이디를 입력해 주세요">
                <button class="btn-primary" onclick="registerCheckFunction();" type="button">중복확인</button>
            </div>
            <div class="form-group">
                <label for="userPassword1">비밀번호</label>
                <input class="form-control" type="password" id="userPassword1" name="userPassword1" maxlength="20" placeholder="비밀번호를 입력해 주세요">
            </div>
            <div class="form-group">
                <label for="userPassword2">비밀번호 확인</label>
                <input class="form-control" type="password" id="userPassword2" name="userPassword2" maxlength="20" placeholder="비밀번호 확인을 입력해 주세요">
                <span id="passwordMatch" style="color: red;"></span>
            </div>
            <div class="form-group">
                <label for="userName">이름</label>
                <input class="form-control" type="text" id="userName" name="userName" maxlength="20" placeholder="이름를 입력해 주세요">
            </div>
            <div class="form-group">
                <label for="userEmail">이메일</label>
                <input class="form-control" type="email" id="userEmail" name="userEmail" maxlength="30" placeholder="이메일을 입력해 주세요">
            </div>
                <button class="btn-finish" onclick="" type="submit">회원가입 완료</button>
        </form>
    </div>

    <script>

document.addEventListener('DOMContentLoaded', function() {
    var password1 = document.getElementById('userPassword1');
    var password2 = document.getElementById('userPassword2');
    var passwordMatch = document.getElementById('passwordMatch');
    var submitButton = document.querySelector('.btn-finish');

    function checkPasswordMatch() {
        if (password1.value === password2.value) {
            passwordMatch.style.color = 'green';
            passwordMatch.textContent = '비밀번호가 일치합니다.';
            submitButton.disabled = false;
        } else {
            passwordMatch.style.color = 'red';
            passwordMatch.textContent = '비밀번호가 일치하지 않습니다.';
            submitButton.disabled = true;
        }
    }

    password1.addEventListener('input', checkPasswordMatch);
    password2.addEventListener('input', checkPasswordMatch);

    document.querySelector('form').addEventListener('submit', function(e) {
        if (password1.value !== password2.value) {
            e.preventDefault(); // 폼 제출을 막습니다
            alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.');
        }
    });
});

    </script>

</body>
</html>
