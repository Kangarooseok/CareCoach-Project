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
      <div id="chat-messages" class="chat-messages" style="width: 100%; height: calc(70vh - 100px - 50px); overflow-y: auto;">
        <!-- 여기에 채팅 메시지가 표시됩니다. -->
      </div>
    </div>
    <form id="chatbotMessage" name="chatbotMessage" method="post" onsubmit="return false;">
      <div class="chatbot-input" style="display: flex; flex-direction: column; align-items: center; justify-content: center; padding: 20px; border: 1px solid #ccc; border-radius: 10px;">
      
        <div style="display: flex; align-items: center; width: 100%;">
          <i class="fa-solid fa-user"></i>
          <input type="text" id="chatbot-input-field" style="outline: none; box-shadow: none; border: none; flex-grow: 1; height: 100%;" placeholder="메시지를 입력하세요" class="expanded-input" onkeydown="handleKeydown(event)">
          <div style="margin-left: auto;">
            <button id="chatbot-send-button" class="send-button" style="border-radius:50%; width: 30px; height: 30px; text-align:center; background-color:transparent;" onclick="chatbotMessage()">
              <i class="fa-solid fa-arrow-up"></i>
            </button>
          </div>
        </div>
      </div>
    </form>
  </div>
</div>

<%@ include file="../footer.jsp" %>

<script>
function handleKeydown(event) {
  if (event.key === 'Enter') {
    event.preventDefault(); // 기본 폼 제출 방지
    chatbotMessage();
  }
}

function chatbotMessage() {
  var inputField = document.getElementById("chatbot-input-field");
  var message = inputField.value.trim();
  
  if (message === "") {
    return;
  }

  // 메시지를 div 형식으로 추가
  var chatMessages = document.getElementById("chat-messages");
  var newMessageDiv = document.createElement("div");
  newMessageDiv.textContent = message;
  newMessageDiv.classList.add("chat-message"); // 스타일을 위한 클래스 추가 (필요 시)
  chatMessages.appendChild(newMessageDiv);

  // 입력 필드 초기화
  inputField.value = "";

  // 폼 제출
  var form = document.getElementById("chatbotMessage");
  form.action = "<c:url value='/board/chatbot.do'/>";
  form.submit();
}
</script>
