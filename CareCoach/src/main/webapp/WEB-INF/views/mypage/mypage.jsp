<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 필요한 JavaScript 추가 -->
<script type="text/javascript">
    function openFileExplorer() {
        document.getElementById('profilePicInput').click();
    }

    function handleProfilePicChange() {
        var fileInput = document.getElementById('profilePicInput');
        var file = fileInput.files[0];
        var reader = new FileReader();

        reader.onload = function(e) {
            document.getElementById('profilePic').src = e.target.result;
        };

        reader.readAsDataURL(file);
    }
</script>

<div class="my_main">
    <h2 class="title">${name}의 페이지</h2>
    <section class="profile-section">
        <div class="profile-info">
            <div class="profile-picture-container">
                <div class="profile-picture">
                    <img src="${not empty profile_image ? profile_image : '../resources/images/default_profile.png'}"
                         alt="Profile Picture" id="profilePic" />
                </div>
                <button class="profile-button" id="changePicButton" onclick="openFileExplorer();">변경</button>
                <input type="file" name="profile_image" id="profilePicInput" style="display: none" onchange="handleProfilePicChange();" />
            </div>
            <div class="profile-details">
                <p>이름: ${name}</p>
                <p>아이디: ${id}
                    <button id="changePasswordButton" onclick="location='/chgpw';">비밀번호 변경</button>
                </p>
                <p>이메일: ${email}</p>
            </div>
        </div>
        <div class="profile-intro">
            <h2 class="title">자기소개글</h2>
            <form action="/saveProfile" method="post" enctype="multipart/form-data">
                <textarea id="introText" name="bio" rows="4" cols="50">${not empty bio ? bio : ''}</textarea>
                <div class="resize-buttons"></div>
                <div class="action-buttons">
                    <button type="submit" id="saveButton">저장</button>
                    <button type="button" id="withdrawButton" onclick="location='/del_mem';">회원 탈퇴</button>
                </div>
            </form>
        </div>
    </section>
</div>

<%@ include file="../footer.jsp"%>
