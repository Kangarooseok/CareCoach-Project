<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CareCoach 회원가입</title>
    <script>
    function openCheckId() {
        var userId = document.getElementById('user_id').value;
        if (userId) {
            window.name = "parentForm";
            window.open("/checkId?user_id=" + encodeURIComponent(userId), "chkForm", "width=500, height=300, resizable=no, scrollbars=no");
        } else {
            alert('아이디를 입력해주세요.');
        }
    }

    function setIdChecked(userId) {
        document.getElementById("user_id").value = userId;
        document.getElementById("user_id").readOnly = true;
        document.getElementById("idDuplication").value = "checked";
    }
    </script>
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
</head>
<body>
    <header>
    	<a href ="${pageContext.request.contextPath}/">
        <img src="${pageContext.request.contextPath}/resources/images/CareCoach-logo.png" alt="CareCoach">
        </a>
        <nav>
            <a href="#">Login</a> 
            <a href="#">Join</a>
        </nav>
    </header>
    <h1>회원가입 페이지</h1>
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/join_process">
            <h4>회원가입 양식</h4>
            <div class="form-group">
                <label for="user_id">아이디</label>
                <input class="form-control" type="text" id="user_id" name="user_id" maxlength="20" placeholder="아이디를 입력해 주세요">
                <button class="btn-primary" type="button" onclick="openCheckId()">중복확인</button>
                <input type="hidden" id="idDuplication" name="idDuplication" value="">
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input class="form-control" type="password" id="password" name="password" maxlength="20" placeholder="비밀번호를 입력해 주세요">
            </div>
            <div class="form-group">
                <label for="password2">비밀번호 확인</label>
                <input class="form-control" type="password" id="password2" name="password2" maxlength="20" placeholder="비밀번호 확인을 입력해 주세요">
                <span id="passwordMatch" style="color: red;"></span>
            </div>
            <div class="form-group">
                <label for="name">이름</label>
                <input class="form-control" type="text" id="name" name="name" maxlength="20" placeholder="이름을 입력해 주세요">
            </div>
            <div class="form-group">
                <label for="email">이메일</label>
                <input class="form-control" type="email" id="email" name="email" maxlength="30" placeholder="이메일을 입력해 주세요">
            </div>
            <button class="btn-finish" type="submit">회원가입 완료</button>
        </form>
    </div>

    <script>
    function validateUserId(userId) {
        var regex = /^[a-zA-Z0-9]{6,12}$/;
        return regex.test(userId);
    }

    function validatePassword(password) {
        return password.length >= 6 && password.length <= 12;
    }

    function openCheckId() {
        var userId = document.getElementById('user_id').value;
        if (!userId) {
            alert('아이디를 입력해주세요.');
            return;
        }
        if (!validateUserId(userId)) {
            alert('아이디는 영문과 숫자로 6~12자리여야 합니다.');
            return;
        }
        window.name = "parentForm";
        window.open("/checkId?user_id=" + encodeURIComponent(userId), "chkForm", "width=500, height=300, resizable=no, scrollbars=no");
    }

    function setIdChecked(userId) {
        document.getElementById("user_id").value = userId;
        document.getElementById("user_id").readOnly = true;
        document.getElementById("idDuplication").value = "checked";
    }

    document.addEventListener('DOMContentLoaded', function() {
        var password1 = document.getElementById('password');
        var password2 = document.getElementById('password2');
        var passwordMatch = document.getElementById('passwordMatch');
        var submitButton = document.querySelector('.btn-finish');

        function checkPasswordMatch() {
            if (!validatePassword(password1.value)) {
                passwordMatch.style.color = 'red';
                passwordMatch.textContent = '비밀번호는 6~12자리여야 합니다.';
                submitButton.disabled = true;
                return;
            }
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
            var userId = document.getElementById('user_id').value;
            
            if (!validateUserId(userId)) {
                e.preventDefault();
                alert('아이디는 영문과 숫자로 6~12자리여야 합니다.');
                return;
            }

            if (!validatePassword(password1.value)) {
                e.preventDefault();
                alert('비밀번호는 6~12자리여야 합니다.');
                return;
            }

            if (password1.value !== password2.value) {
                e.preventDefault();
                alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.');
                return;
            }
            
            if (document.getElementById('idDuplication').value !== 'checked') {
                e.preventDefault();
                alert('아이디 중복 확인을 해주세요.');
                return;
            }
        });
    });
</script>
</body>
</html>