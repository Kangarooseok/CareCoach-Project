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
        }
        header {
            background-color: white;
            padding: 20px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            position: relative;
        }
        header img {
            height: 50px;
        }
        header nav {
            position: absolute;
            right: 20px;
            top: 20px;
        }
        header nav a {
            text-decoration: none;
            color: black;
            margin-left: 20px;
        }
        .container {
            width: 60%;
            margin: 30px auto;
            background-color: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            position: relative;
        }
        .title {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 20px;
        }
        .title img {
            height: 50px;
            margin-right: 20px;
        }
        .agreement {
            margin-bottom: 30px;
        }
        .agreement p {
            text-align: left;
            font-weight: bold;
            margin-bottom: 10px;
            font-size: 18px;
        }
        .agreement textarea {
            width: 100%;
            height: 200px;
            margin-bottom: 15px;
            resize: none;
            background-color: #e0e0e0;
            border: none;
            padding: 15px;
            box-sizing: border-box;
            border-radius: 5px;
            font-family: "NanumBarunGothic", "돋움", Dotum, "굴림", Gulim, Arial, "Trebuchet MS", Verdana, "Sans-serif";
            font-size: 16px
        }
        .agreement label {
            margin-right: 20px;
            font-size: 16px;
            
        }
        .btn {
            background-color: #f0c040;
            color: black;
            padding: 10px 20px;
            border: none;
            font-size: 18px;
            font-weight: 600;
            border-radius:5px;
            
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #ebd79a;
        }
        
        footer {
            background-color: #e0f7fa;
            padding: 20px;
            text-align: center;
            margin-top: 20px;
        }
        footer p {
            margin: 5px 0;
        }
        footer a {
            color: black;
            text-decoration: none;
            margin: 0 10px;
        }
        .button-container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 30px;
            position: relative;
        }

        .btn {
            background-color: #f0c040;
            color: black;
            padding: 12px 24px;
            border: none;
            font-size: 20px;
            font-weight: 600;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .next-button-container {
            text-align: center;
            margin-top: 20px;
        }
        .agree-all-container {
            text-align: right;
            margin-top: 10px;
        }
        .agree-all-label {
            position: absolute;
            right: 0;
            display: flex;
            align-items: center;
            font-weight: bold;
            font-size: 16px;
        }
        #agreeAll {
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <header>
        <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/resources/images/CareCoach-logo.png" alt="CareCoach"/>
        </a>
        <nav>
            <a href="${pageContext.request.contextPath}/login">Login</a> <a href="${pageContext.request.contextPath}/join">Join</a>
        </nav>
    </header>
    <div class="container">
        <div class="title">
            <img src="${pageContext.request.contextPath}/resources/images/CareCoach-logo.png" alt="CareCoach">
            <h1>CareCoach 회원가입</h1>
        </div>
        <c:forEach var="terms" items="${termsList}">
			<!-- 첫 번째 약관 -->
			<div class="agreement">
			    <p>※ ${terms1.title}</p>
			    <textarea readonly>${terms1.content}</textarea>
			    <label><input type="radio" name="agreement1" value="동의함"> 동의함</label>
			    <label><input type="radio" name="agreement1" value="비동의"> 비동의</label>
			</div>
			
			<!-- 두 번째 약관 -->
			<div class="agreement">
			    <p>※ ${terms2.title}</p>
			    <textarea readonly>${terms2.content}</textarea>
			    <label><input type="radio" name="agreement2" value="동의함"> 동의함</label>
			    <label><input type="radio" name="agreement2" value="비동의"> 비동의</label>
			</div>
        </c:forEach>
        <div class="button-container">
            <button id="nextButton" class="btn">다음</button>
            <label class="agree-all-label"><input type="checkbox" id="agreeAll"> 전체동의</label>
        </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const agreeAllCheckbox = document.getElementById('agreeAll');
            const agreementRadios = document.querySelectorAll('input[type="radio"][name^="agreement"]');
            const nextButton = document.querySelector('.btn');

            function checkAllAgreed() {
                const allAgreed = Array.from(agreementRadios)
                    .filter(r => r.value === "동의함")
                    .every(r => r.checked);
                
                agreeAllCheckbox.checked = allAgreed;
            }

            agreeAllCheckbox.addEventListener('change', function() {
                agreementRadios.forEach(radio => {
                    if (radio.value === "동의함") {
                        radio.checked = this.checked;
                    } else {
                        radio.checked = !this.checked;
                    }
                });
                checkAllAgreed();
            });

            agreementRadios.forEach(radio => {
                radio.addEventListener('change', checkAllAgreed);
            });

            nextButton.addEventListener('click', function(e) {
                e.preventDefault();
                
                const allAgreed = Array.from(agreementRadios)
                    .filter(r => r.value === "동의함")
                    .every(r => r.checked);

                if (!allAgreed) {
                    alert("이용약관을 모두 동의해주세요.");
                } else {
                    const form = document.createElement('form');
                    form.method = 'GET';
                    form.action = '/join';
                    document.body.appendChild(form);
                    form.submit();
                }
            });
        });
    </script>
</body>
</html>
