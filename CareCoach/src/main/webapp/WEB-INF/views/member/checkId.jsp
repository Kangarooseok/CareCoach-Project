<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 중복확인</title>
    <style>
        body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.container {
    background-color: #ffffff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 300px;
}

h2 {
    color: #ffffff;
    background-color: #8cd3c5;
    padding: 10px;
    margin: -20px -20px 15px -20px;
    border-radius: 5px 5px 0 0;
}

p {
    font-size: 12px;
    line-height: 1.4;
    margin-bottom: 15px;
}

.input-group {
    display: flex;
    margin-bottom: 15px;
}

input[type="text"] {
    flex-grow: 1;
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 3px;
}

.check-btn {
    background-color: #8cd3c5;
    color: white;
    border: none;
    padding: 5px 10px;
    margin-left: 5px;
    cursor: pointer;
    border-radius: 3px;
}

.warning, .sub-warning {
    color: #666;
    font-size: 11px;
    margin-bottom: 5px;
}
QQQ
.use-btn {
    background-color: #f0f0f0;
    border: none;
    color: #333;
    padding: 8px 15px;
    width: 100%;
    cursor: pointer;
    border-radius: 3px;
}

.use-btn:hover {
    background-color: #e0e0e0;
}
    </style>
</head>
<body>
    <div class="container">
        <h2>아이디 중복확인</h2>
        <p>아이디는 영문(대소문자), 숫자로 6~12자 이내로 입력해주세요.</p>
        <div class="input-group">
            <input type="text" id="userId" placeholder="아이디 입력">
            <button class="check-btn" onclick="checkId()">중복 확인</button>
        </div>
        <p class="warning">공백 또는 특수문자 및 한글이 포함된 아이디는 사용할 수 없습니다.</p>
        <p class="sub-warning">숫자로만 이루어진 아이디는 사용할 수 없습니다.</p>
        <button class="use-btn" onclick="useId()">사용 하기</button>
    </div>

    <script>
    window.onload = function() {
        var urlParams = new URLSearchParams(window.location.search);
        var userId = urlParams.get('user_id');
        if (userId) {
            document.getElementById('userId').value = userId;
            checkId(); // 옵션: 페이지 로드 시 자동으로 중복 체크 수행
        }
    }

    function validateUserId(userId) {
        var regex = /^[a-zA-Z0-9]{6,12}$/;
        return regex.test(userId);
    }

    function checkId() {
        var userId = document.getElementById('userId').value;
        if (!userId) {
            alert('아이디를 입력해주세요.');
            return;
        }
        if (!validateUserId(userId)) {
            alert('아이디는 영문과 숫자로 6~12자리여야 합니다.');
            return;
        }
        fetch('${pageContext.request.contextPath}/checkIdAvailability?user_id=' + encodeURIComponent(userId), {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);  // 응답 데이터 로깅
            if (data.available) {
                alert('사용 가능한 아이디입니다.');
            } else {
                alert('이미 사용 중인 아이디입니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('서버 오류가 발생했습니다.');
        });
    }

    function useId() {
        var userId = document.getElementById('userId').value;
        if (!userId) {
            alert('아이디를 입력해주세요.');
            return;
        }
        if (!validateUserId(userId)) {
            alert('아이디는 영문과 숫자로 6~12자리여야 합니다.');
            return;
        }
        fetch('${pageContext.request.contextPath}/checkIdAvailability?user_id=' + encodeURIComponent(userId), {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);  // 응답 데이터 로깅
            if (data.available) {
                alert('사용 하시겠습니까 ?');
                window.opener.setIdChecked(userId);
                window.close();
            } else {
                alert('이미 사용 중인 아이디입니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('서버 오류가 발생했습니다.');
        });
    }
</script>
</body>
</html>