<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
  request.setCharacterEncoding("UTF-8");
%>
<%@ include file="../header.jsp" %>

<div class="page-contents">

  <div class="chatbot-container" style="margin-left: 20px; margin-right: 20px; padding: 10px">
    <div class="chatbot-window">
      <div class="chat-messages" style="width: 100%; height: calc(70vh - 100px - 50px); overflow-y: auto;">
        <!-- 여기에 채팅 메시지가 표시됩니다. -->
      </div>
    </div>
	<div class="chatbot-input" style="display: flex; flex-direction: column; align-items: center; justify-content: center; padding: 20px;  border: 1px solid #ccc; border-radius: 10px;">
	  <div style="display: flex; align-items: center; width: 100%;">
	    <i class="fa-solid fa-user"></i>
	    <input type="text" id="chatbot-input-field" style="  outline: none;
  box-shadow: none; border: none; flex-grow: 1; height: 100%;" placeholder="메시지를 입력하세요" class="expanded-input" onkeydown="handleKeydown(event)">
	  <div style="margin-left: auto;">
	    <button id="chatbot-send-button" class="send-button" style="border-radius:50%;
	  width: 30px; height: 30px;  text-align:center;
	     background-color:transparent; ">
	    <i class="fa-solid fa-arrow-up"></i>
	  	</button>
	  </div>
	  </div>
	</div>
  </div>
</div>

<%@ include file="../footer.jsp" %>


<script>
  const chatInputField = document.getElementById('chatbot-input-field');
  const chatSendButton = document.getElementById('chatbot-send-button');
  const chatMessagesContainer = document.querySelector('.chat-messages');

  function handleKeydown(event) {
    if (event.key === 'Enter') {
      sendMessage();
    }
  }

  function sendMessage() {
    const message = chatInputField.value.trim();
    if (message) {
      const messageElement = document.createElement('div');
      messageElement.textContent = message;
      messageElement.classList.add('chat-message');
      chatMessagesContainer.appendChild(messageElement);
      chatInputField.value = '';
      chatMessagesContainer.scrollTop = chatMessagesContainer.scrollHeight;
    }
  }

  chatSendButton.addEventListener('click', sendMessage);
</script>
