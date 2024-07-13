<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	 <div class="result_body">
      <div class="modal2" id="idModal2">
        <div class="modal-content2">
          <span class="close2" onclick="closeModal()">&times;</span>
          <c:choose>
            <c:when test="${empty id}">
              <p>해당 이메일과 이름으로 등록된 아이디가 없습니다.</p>
            </c:when>
            <c:otherwise>
              <p>${id.name}님의</p>
              <p>아이디는 "${id.user_id}"입니다.</p>
            </c:otherwise>
          </c:choose>
          <button type="button" onclick="closeModal()">확인</button>
        </div>
      </div>
    </div>

    <script>
      // Close modal function
      function closeModal() {
        var modal2 = document.getElementById("idModal2");
        modal2.style.display = "none";
      }

      // Close modal when clicking outside of modal
      window.onclick = function (event) {
        var modal2 = document.getElementById("idModal2");
        if (event.target == modal2) {
          modal2.style.display = "none";
        }
      };
    </script>