<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:include page="../header.jsp" />

<style>
  body, html {
    height: 100%;
    margin: 0;
    font-family: Arial, sans-serif;
  }
  .chat-container {
    max-width: 800px;
    height: 80vh;
    margin: 20px auto;
    border: 1px solid #ddd;
    border-radius: 8px;
    display: flex;
    flex-direction: column;
  }
  .chat-messages {
    flex: 1;
    overflow-y: auto;
    padding: 20px;
    background-color: #f0f0f0;
  }
  .message {
    max-width: 70%;
    margin: 10px 0;
    padding: 10px;
    border-radius: 20px;
  }
  .user-message {
    background-color: #dcf8c6;
    align-self: flex-end;
    margin-left: auto;
  }
  .bot-message {
    background-color: #fff;
    align-self: flex-start;
  }
  .message-sender {
    font-weight: bold;
    margin-bottom: 5px;
  }
  .input-area {
    display: flex;
    padding: 10px;
    background-color: #f9f9f9;
    border-top: 1px solid #ddd;
  }
  #chatbot-input-field {
    flex: 1;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 20px;
    margin-right: 10px;
  }
  #chatbot-send-button {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 20px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  #chatbot-send-button:hover {
    background-color: #45a049;
  }
  .login-message {
    text-align: center;
    margin-top: 20px;
    font-size: 18px;
    color: #666;
  }
</style>
<!-- 로그인 상태를 표시하는 부분 -->
<div style="text-align: center; margin-top: 10px;">
  <c:choose>
    <c:when test="${not empty sessionScope.id}">
      로그인 상태: 로그인됨 
    </c:when>
    <c:otherwise>
      로그인 상태: 로그인되지 않음
    </c:otherwise>
  </c:choose>
</div>

<div class="chat-container">
  <c:choose>
    <c:when test="${not empty sessionScope.id}">
      <div class="chat-messages" id="chat-messages">
        <!-- 메시지들이 여기에 추가됩니다 -->
      </div>
      <div class="input-area">
        <input type="text" id="chatbot-input-field" placeholder="메시지를 입력하세요">
        <button id="chatbot-send-button">전송</button>
      </div>
    </c:when>
    <c:otherwise> <!-- 로그인 되어 있지 않을 때 -->
      <div class="login-message">
        챗봇을 사용하려면 로그인이 필요합니다. <a href="/login">로그인하기</a>
      </div>
    </c:otherwise>
  </c:choose>
</div>

<script>
		/* 로그인 상태와 사용자 ID를 콘솔에 출력 오류확인
  console.log("Login status: ${not empty sessionScope.id}");
  console.log("User ID: ${sessionScope.id}"); */

  <c:if test="${not empty sessionScope.id}">
    const chatInputField = document.getElementById('chatbot-input-field');
    const chatSendButton = document.getElementById('chatbot-send-button');
    const chatMessages = document.getElementById('chat-messages');
    
 	// 엔터키를 누를 때 메시지를 전송하는 함수
    function handleKeydown(event) {
      if (event.key === 'Enter') {
        sendMessage();
      }
    }

    function sendMessage() {
      const message = chatInputField.value.trim();
      if (message) {
        displayMessage(message, 'user'); // 사용자의 메세지표시
        
        fetch('/controller/chatbot/send', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ message: message })
        })
        .then(response => response.text())
        .then(data => {
          console.log("Raw response:", data); // json형식으로 서버에 메시지 전송
          try {
            const jsonData = JSON.parse(data);
            if (jsonData.bubbles && jsonData.bubbles.length > 0) {
              const encodedMessage = jsonData.bubbles[0].data.description;
              const decodedMessage = atob(encodedMessage);
              const botMessage = decodeURIComponent(escape(decodedMessage));
              displayMessage(botMessage, 'bot'); // 챗봇 메세지 표시
            } else {
              displayMessage("챗봇 응답을 처리할 수 없습니다.", 'bot');
            }
          } catch (error) {
            console.error('Error parsing response:', error);
            displayMessage('응답을 처리할 수 없습니다.', 'bot');
          }
        })
        .catch(error => {
          console.error('Error:', error);
          displayMessage('오류가 발생했습니다.', 'bot');
        });
        chatInputField.value = '';
      }
    }
    
    function displayMessage(message, sender) {
      const messageElement = document.createElement('div');
      messageElement.classList.add('message', sender + '-message');
      
      const senderElement = document.createElement('div');
      senderElement.classList.add('message-sender');
      senderElement.textContent = sender === 'user' ? '나' : '케어코치'; // 발신자 이름 설정
      
      const contentElement = document.createElement('div');
      contentElement.textContent = message;
      
      messageElement.appendChild(senderElement);
      messageElement.appendChild(contentElement);
      
      chatMessages.appendChild(messageElement);
      chatMessages.scrollTop = chatMessages.scrollHeight;
    }

    
    chatInputField.addEventListener('keydown', handleKeydown);
    chatSendButton.addEventListener('click', sendMessage);

    console.log("Chat event listeners added");
  </c:if>
</script>

<jsp:include page="../footer.jsp" />