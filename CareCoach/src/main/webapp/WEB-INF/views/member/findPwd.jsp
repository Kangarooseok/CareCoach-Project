<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <script src="../resources/js/jquery.js"></script>
    <script>
    function write_check() {
        if ($.trim($("#id").val()) == "") {
            alert("아이디를 입력하세요");
            $("#id").val("").focus();
            return false;
        }
        if ($.trim($("#email").val()) == "") {
            alert("이메일을 입력하세요");
            $("#email").val("").focus();
            return false;
        }
    }

    $(document).ready(function() {
        // Show modal on page load
        $("#myModal").show();
        
        // Close modal on 'x' click
        $("#closeModal").click(function() {
            $("#myModal").hide();
        });

        // Close modal when clicking outside of modal-content
        $(window).click(function(event) {
            if ($(event.target).closest(".modal-content").length === 0) {
                $("#myModal").hide();
            }
        });
    });
    </script>
    
    <script>
    function write_check2(){
        var newPassword = $.trim($("#newPassword").val());
        var confirmPassword = $.trim($("#confirmPassword").val());

        if(newPassword == "" || confirmPassword == ""){
            alert("새 비밀번호와 새 비밀번호 확인 모두 입력하세요");
            return false;
        }

        if(newPassword != confirmPassword){
            alert("입력된 새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다. 비밀번호가 다릅니다. 다시 입력해주세요.");
            return false;
        }

        if(newPassword.length < 6 || newPassword.length > 12){
            alert("비밀번호는 6자리에서 12자리 사이여야 합니다. 다시 입력해주세요.");
            return false;
        }
    }

</script>
    
    <!-- 비밀번호 찾기 화면 -->
    <c:if test="${resetMode == null || !resetMode}">
        <div class="find_body">
            <div class="modal" id="myModal">
                <div class="modal-content">
                    <span class="close" id="closeModal">×</span>
                    <h2>비밀번호 변경</h2>
                    <form method="post" id="login-form" class="loginForm" action="/findPwdResult" onsubmit="return write_check();">
                        <label for="user_id">아이디</label>
                        <input type="text" name="user_id" placeholder="아이디를 입력해 주세요." id="id" />
                        <label for="email">이메일</label>
                        <input type="email" name="email" placeholder="이메일을 입력해 주세요." id="email" />
                        <button class="button" type="submit">비밀번호 찾기</button>
                    </form>
                </div>
            </div>
        </div>
    </c:if>

    <!-- 비밀번호 재설정 화면 -->
    <c:if test="${resetMode}">
      <%@ include file="./login.jsp" %>  
      <div class="find_body">
            <div class="modal" id="myModal">
                <div class="modal-content">
                    <span class="close" id="closeModal">×</span>
                    <h2>비밀번호 재설정</h2>
                    <form method="post" id="reset-form" class="resetForm" action="/resetPwd" onsubmit="return write_check2();">
                        <label for="newPassword">새 비밀번호</label>
                        <input type="password" name="newPassword" placeholder="새 비밀번호를 입력해 주세요." id="newPassword" />
                        <label for="confirmPassword">새 비밀번호 확인</label>
                        <input type="password" name="confirmPassword" placeholder="비밀번호를 한 번 더 입력해 주세요." id="confirmPassword" />
                        <button class="button" type="submit">비밀번호 변경</button>
                    </form>
                </div>
            </div>
        </div>
    </c:if>
