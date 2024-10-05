# [🏋️ **CareCoach**](https://youtu.be/WC4MhYIFZfk)
### 🏋️ "CareCoach는 운동 정보 제공 및 헬스 영상 공유를 통해 커뮤니티와 챗봇으로 운동 관련 정보를 제공하는 플랫폼입니다."
![logo](https://github.com/user-attachments/assets/26e942fa-6685-42d9-824f-309beef33069)

<br>

## 📃 프로젝트 개요
운동 관련 정보, 현재 진행중인 헬스 프로그램을 커뮤니티를 통해 정보를 받을 수 있고

챗봇을 통해 운동 관련 정보를 받을 수 있고 커뮤니티를 통해 후기를 공유하고 다른 사용자와 의견을 주고 받을 수 있습니다.



**📆 프로젝트 기간 : 2024.06.13 ~ 2023.07.26**
<br><br>

## 👨‍💻 업무분배 
|**이름**|**역할**|**업무**|
|:---:|:---:|:---|
|**[강민석](https://github.com/minseokkang8609)**|팀장|로그인 기능 담당, 메인페이지 기능 담당, 마이페이지 기능 당담, 챗봇 기능 담당, 프로젝트 발표|
|**[임창규](https://github.com/)**|부팀장|커뮤니티 기능 담당, 챗봇 기능 당담, 깃 관리 당담|
|**[김시현](https://github.com/)**|팀원|로그인 기능 담당, 메인페이지 기능 담당, 마이페이지 기능 당담, 챗봇 기능 담당|
|**[이민재](https://github.com/)**|팀원|회원가입 기능 당담, 챗복 기능 당담, 시연영상 제작|

<br><br>

## ✳️ 주요 기술
1. Naver Cloud CLOVA ChatBot : CLOVA ChatBot API를 사용하여 헬스케어 챗봇 구현

<br><br>

## 💻 개발환경
**Java : 11**
**Java : 18 SE**

**IntelliJ : 2024.1.4**

**Oracle DB : xe 11g**

**Tomcat : 9.0**

프로젝트 진행 중 자바 11과 STS3를 사용하여 기본 구조를 개발하고,
추후에 자바 18과 IntelliJ를 도입하여 최신 기능을 활용해 성능과 생산성을 향상시켰습니다.
두 가지 환경에서의 경험을 통해 다양한 개발 도구와 버전에 대한 이해도를 높였습니다.

<br><br>

## 📝 ERD
![ERD](https://github.com/user-attachments/assets/a402101e-7c5c-40b5-aa0e-e7d9319b5301)

<br><br>

## 🎯 구현 결과
### CareCoach   
- [X] **메인 페이지**   
+ 메인 배너 호출 및 슬라이드
+ 최근 작성된 공지사항 게시글 5개 호출
+ 최근 작성된 커뮤니티 게시글 5개 호출
+ 최근 업로드된 유튜브 영상 2개 호출

## 추후에 사진 업로드
<p>
![CareCoach 메인 페이지 상단](https://github.com/user-attachments/assets/9c764263-1144-42ba-83ed-3df7bca014a6)
</p>
<p>
![CareCoach 메인 페이지 하단](https://github.com/user-attachments/assets/dbc282d0-94de-4f52-bbf2-23568120a04a)
</p>

- [X] **회원가입 페이지**   
+ 약관 동의
+ 회원가입 : (이름, 아이디/아이디 중복확인, 비밀번호/비밀번호 확인, 이메일)
     <p>    
![CareCoach 회원가입 페이지](https://github.com/user-attachments/assets/8e8c6924-0a69-472e-8afa-a5cd79af308b)
     </p>

- [X] **로그인 페이지**   
+ 로그인 : (아이디/비밀번호)
+ 아이디 찾기
+ 아이디 비밀번호 찾기/재설정
  
     <p>    
![CareCoach 로그인 페이지](https://github.com/user-attachments/assets/abe55d59-979e-4319-9479-ce832eace246)
     </p>
   
### 커뮤니티  
- [X] **공지사항 페이지**   
+ 최근 작성된 공지사항 게시글 10개 호출
+ Admin에 한하여 공지사항 작성 페이지 호출
+ 공지사항 상세 페이지 호출 (내용, 좋아요 표시, 댓글 기능, 조회 표시)
+ Admin에 한하여 수정 및 삭제 가능

     <p>    
![공지사항 메인 페이지](https://github.com/user-attachments/assets/3d17ef15-02f3-4d04-9c77-f267916ad98b)
     </p>

- [X] **자유게시판 페이지**   
+ 최근 작성된 자유게시판 게시글 10개 호출
+ 자유게시판 상세 페이지 호출 (내용, 좋아요 표시, 댓글 기능, 조회 표시)
+ 게시글 수정 및 삭제 가능

     <p>    
![자유게시판 상세 페이지](https://github.com/user-attachments/assets/b99e43dc-f542-4018-8522-5ae91b3dd0c9)
     </p>
     <p>    
![자유게시판 상세 페이지](https://github.com/user-attachments/assets/26b4dbef-cdd8-4f49-91d9-ecd9bff7355a)
     </p>

- [X] **헬스 영상 페이지**   
+ 최근 작성된 자유게시판 게시글 10개 호출
+ 영상 상세 페이지 호출 (영상 내용, 좋아요 표시, 댓글 기능, 조회 표시)
+ 게시글 수정 및 삭제 가능

     <p>    
![헬스 영상 상세 페이지](https://github.com/user-attachments/assets/f8f42da2-9fb2-4f9b-b4a9-8ef22e1eb318)
     </p>
     <p>    
![헬스 영상 상세 페이지](https://github.com/user-attachments/assets/d3698f02-9a5d-464a-ab5f-5b6e2b3aafc3)
     </p>

- [X] **문의 게시판 페이지**   
+ 사용자가 게시글 입력 , Admin에 한해서  답변 가능  / 수정 및 삭제 가능

     <p>    
![문의 게시판 상세 페이지](https://github.com/user-attachments/assets/a8001989-816c-4de7-88c8-05552bfcbe12)
     </p>
     <p>    
![문의 게시판 상세 페이지](https://github.com/user-attachments/assets/2cb7d649-7506-4df4-90b7-a639f68e1779)
     </p>

- [X] **자주 묻는 질문 페이지**   
+ 자주 묻는 질문 10개 출력 (하단에 '원하는 답이 없으신가요? 케어코치를 이용해보세요!' 링크 출력 > 챗봇으로 이동)

     <p>    
![자주 묻는 질문 상세 페이지](https://github.com/user-attachments/assets/ff6cd788-c590-4db8-9e55-92e691cb53ac)
     </p>

- [X] **글 작성 페이지**   
+ 글 카테고리(영상, 자유) 선택 가능   
+ 영상 카테고리 선택 시 url 삽입이 가능한 주소창 추가
+ 이미지 첨부 가능

     <p>    
![글 작성 상세 페이지](https://github.com/user-attachments/assets/c253d107-f8b3-4fb3-af06-592172af6460)
     </p>
     <p>    
![글 작성 상세 페이지](https://github.com/user-attachments/assets/e4cf399e-f309-4fa6-90dc-8108d2de6d06)
     </p>

- [X] **챗봇 페이지**   
+회원가입 후 이용가능 
+ 객관식 답변 제공 / 네이버 클라우드 API 사용

     <p>    
![글 작성 상세 페이지](https://github.com/user-attachments/assets/266a7a3f-fbd7-4c65-b173-fdab3645fe6d)
     </p>

### **마이 페이지**
- [X] **메인 페이지**
+ 프로필 사진 수정/설정
+ 프로필 문구 수정/설정
+ 비밀번호 변경 및 로그아웃
+ 회원 탈퇴

     <p>    
![글 작성 상세 페이지](https://github.com/user-attachments/assets/e939d3a7-6263-41c2-a305-cb3f106564c7)
     </p>


<br><br>

## ❗️ 힘들었던 점
<p>
1. ChatBot 학습 데이터 부족

ChatBot의 학습 데이터가 부족하여 대화 품질이 저하되었습니다.
이를 해결하기 위해 대화 정보를 200개 추가하고, 키워드를 도입하며 사용자의 대화 실패 목록을 분석하여 재학습을 실시했습니다.
그 결과, 기존 254건 중 대화 성공률이 25%에서 54%로 향상되어, 135건의 성공 사례를 기록하게 되었습니다.

2. ChatBot API Secret Key 노출 문제

기존 코드에 API Secret Key가 포함되어 있어 보안이 취약한 상황이었습니다.
이를 해결하기 위해 API Secret Key를 데이터베이스에 저장하여 노출 문제를 방지했습니다.
이러한 조치를 통해 보안 수준을 강화하고 사용자 데이터를 보다 안전하게 보호할 수 있었습니다.

3. API Gateway 연계 문제

ChatBot을 생성하고 시나리오를 작성한 후, API Gateway와의 연계에서 어려움이 있었습니다.
이를 해결하기 위해 API Gateway를 생성하고 필요한 설정을 완료했습니다.
사용자 키를 생성하여 Authorization 헤더에 포함시켰고, 이후 시나리오 테스트 기능을 통해 요청과 응답이 정상적으로 동작하는지 확인했습니다.
최종적으로 API Gateway와의 연동 작업을 성공적으로 완료했습니다.</p>
<br><br>

## 💬 팀원들의 한마디
👨‍🎓 **강민석** : 이번 핵심인 챗봇 개발을 통해 사용자와의 실시간 소통의 중요성을 느꼈습니다. 사용자 질문에 자연스럽게 하게 답변할 수 있도록 학습시키며, 기술이 사용자 경험을 어떻게 향상하게 시킬 수 있는지를 실감했습니다. <br><br>

🧑‍🎓 **임창규** : 커뮤니티 부분 개발을 하면서 느낀 점은 세션과 쿠키에 관한 공부가 덜 이루어진 걸 깨닫고 공부할 수 있어 유익했습니다.<br><br>

🧑‍🎓 **김시현** : 이번 프로젝트를 통해 웹 개발 기술뿐만 아니라 팀워크의 중요성도 깊이 이해하게 되었습니다. 팀원들과 함께 목표를 설정하고 협력하여 문제를 해결하는 과정이 매우 뜻깊었습니다. <br><br>

🧑‍🎓 **이민재** :협업을 이번에 처음 하게 되었는데, 팀원들과 협업하면서 어떤 방식으로 소통, 개발하지를 공부할 수 있어서 재미있고, 어떤 거를 개발할 때 사전에 설계, 기획이 중요한 거를 깨달았습니다. <br><br>
