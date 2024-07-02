<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>아이디 확인</title>
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
	text-align: center;
}

.popup-container p {
	font-size: 16px;
	margin-bottom: 20px;
}

.popup-container button {
	width: 100px;
	padding: 10px;
	background-color: #f8f8f8;
	border: 1px solid #ccc;
	border-radius: 3px;
	cursor: pointer;
}

.popup-container button:hover {
	background-color: #e0e0e0;
}
</style>
<script>
      function closePopup() {
    	window.close();
	   }

    </script>
</head>
<body>
	<div class="body">
		<div class="popup-container">
			<c:choose>
				<c:when test="${empty sessionScope.loginUser or empty sessionScope.loginUser.user_id or empty sessionScope.loginUser.nickname}">
					<p>해당 이메일과 이름으로 등록된 아이디가 없습니다.</p>
				</c:when>
				<c:otherwise>
					<p>${sessionScope.loginUser.nickname}님의</p>
					<p>아이디는 "${sessionScope.loginUser.user_id}"입니다.</p>
				</c:otherwise>
			</c:choose>
			<button type="button" onclick="closePopup()">확인</button>
		</div>
	</div>



</body>
</html>
