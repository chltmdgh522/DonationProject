# 🤷‍♂️Donation_Project🤷‍♂️
스프링 부트 + MySql


## ⭐ 개발일지
[https://www.notion.so/e1fc13b62bdd4360817e4e44b198d417](https://cord-may-a5c.notion.site/e1fc13b62bdd4360817e4e44b198d417?pvs=4)


## 🖥️ 시연 화면
https://youtu.be/jTv9dnZsMHU?si=Wh4IGrgmM7nrS1gb


## 📖 실행법
- git clone을 하여 원하는 파일에 배치!
- 인텔리제이로 그 파일을 실행!
- 소스코드에 sql 파일을 열어 코드대로 테이블을 설정!
- applaction.properties에서 본인에 맞게 sql 서버 수정

## 🌠 프로젝트 소개
- 이 프로젝트는 스프링 프레임워크를 기반으로 제작되었으며 주제는 자선 기부 서비스입니다.
- 사용자들은 자신의 기부 사연을 게시할 수 있고 다른 사용자들은 이에 맞게 포인트를 기부하는 방식으로 참여할 수 있습니다. 
- 또한 자유롭게 소통할 수 있는 자유 게시판과 실질적인 도움을 필요로 하는 후원 게시판으로 나뉘어져 있습니다.
- 더불어 후원을 많이 한 사용자들은 랭킹 순위에 등록되어 다른 사용자들에게 볼 수 있습니다.
<br>

## 🕰️ 개발 기간
* 23.11.2일 - 24.1.11일 (시험기간 제외 사실상 3주)

### 🧑‍🤝‍🧑 맴버구성
 -  최승호 - 백엔드, 디자이너, UI 개발, 기획, 포트포워딩(AWS 할려 했으나 비용 부족!)
   
### ⚙️ 개발 환경
- **Language** : `Java 17, JavaScript`
- **Framework** : `Springboot(3.0.2)`
  - `MySQL Driver, Lombok, Spring Data JdbcTemplate, Spring Data Jpa, Spring Security, Spring Web, Thymeleaf`
- **Database** : `MySql 8.0.34`
- **ORM, SQLMapper** : `JdbcTemplate, MyBatis, JPA, Spring Data Jpa`
- **IDE** : `IntellJ`

## 📌 주요 기능
#### 로그인/회원가입 
- DB값 검증
- 비밀번호 찾기 및 변경 
- 로그인 시 쿠키(Cookie) 및 세션(Session) 생성

#### 마이 페이지 
- 프로필 사진, 이름, 소개 변경, 포인트 점수

#### 게시판 
- CRUD 제공
- 댓글 기능 제공(CRUD)
- 페이징 기법
- 게시글 사진 첨부 가능
  
#### 후원 및 포인트 
- 후원 목표액 설정
- 회원가입/로그인/게시물 작성 할 때마다 포인트 증가
- 후원 목표액 달성하면 후원하기 버튼 안뜸

#### 부가 기능
- 방문자수(총 방문자, 오늘 방문자)
- 후원랭킹 
  
#### 관리자 페이지 
- 회원관리, 댓글관리, 게시물관리, 관리자 계정

## 🌠 느낀점
- 필자가 지금까지 학습한 내용으로 개발을 했다. 
- 자세한 느낀점은 https://cord-may-a5c.notion.site/a007d8a55c1d465eab688d7971b33946 참고!
- 추가하고 싶은 기능이 많았다.. 시간이 남으면 차차 추가해보겠다.
- 서버 배포는 AWS로 할려했으나 비용 때문에 개인 노트북으로 포트포워딩해서 실제로 다른 사용자들이 서비스를 이용했다.
  
### ⭐추가하고 싶은 기능
- 게시글/댓글 공감
- 게시글 스크랩
- 미니 퀴즈
- 관리자랑 실시간 채팅
- 유저 공감도 
