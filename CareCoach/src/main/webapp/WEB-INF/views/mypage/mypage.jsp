<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %> 

<div class="my_main">
      <!-- h1을 h2로 수정 -->
      <h2 class="title">홍길동의 페이지</h2>
      <section class="profile-section">
        <div class="profile-info">
          <div class="profile-picture-container">
            <div class="profile-picture">
              <img
                src="../resources/images/default_profile.png"
                alt="Profile Picture"
                id="profilePic"
              />
            </div>
            <button class="profile-button" id="changePicButton">변경</button>
            <input type="file" id="profilePicInput" style="display: none" />
          </div>
          <div class="profile-details">
            <p>이름: 홍길동</p>
            <p>
              아이디: aassdw112
              <button id="changePasswordButton" onclick="location='/chgpw';">
                비밀번호 변경
              </button>
            </p>
            <p>이메일: mydreamJavaking@gmail.com</p>
          </div>
        </div>
        <div class="profile-intro">
          <h2 class="title">자기소개글</h2>
          <textarea id="introText" rows="4" cols="50">
운동을 사랑하는 남자 김헐창 입니다람쥐</textarea
          >
          <div class="resize-buttons"></div>
        </div>
      </section>
      <div class="action-buttons">
        <button id="saveButton">저장</button>
        <button id="withdrawButton" onclick="location='/del_mem';">
          회원 탈퇴
        </button>
      </div>
    </div>

<%@ include file="../footer.jsp" %>  