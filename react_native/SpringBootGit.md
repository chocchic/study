# Spring Boot Git 연동  
## 1.업로드  
### 1) Spring Boot Gradle Project 생성  

### 2) Git에서 토큰 번호를 추출해서 복사를 해놓습니다.  
* git hub에서 로그인  
* 오른쪽 상단의 이미지 아이콘을 눌러서 settings를 선택  
* 하단으로 화면을 내려서 developer settings를 클릭  
* Personal access Tokens 클릭
* Generate new token을 선택하고 비밀번호 입력  
* 권한 설정 수행  
* 키가 발급됩니다.

## 2. spring boot application 업로드 - gradle 기반  
* Git hub에서 Repository를 생성하고 url 복사  

* sts에서 git repository view를 출력  
[windows] - [show view] - [others]를 선택하고 [git] - [git repositories]

* sts의 git repository에서 clone a git repository and add the clone to this view를 선택  

* 앞에서 생성한 repository의 uri를 복사  
원격 repository와 로컬 repository가 연결됨  

* 프로젝트를 선택하고 마우스 오른쪽을 눌러서 [Team] - [Share Project]를 선택해서 생성한 repository와 연결  

* unstaged Changes에서 업로드할 파일을 선택해서 staged Changes로 이동시키고 commit message를 작성한 후 Commit and Push 버튼을 누르고 로그인

## 3. gradle 기반의 spring Boot application download  
* Git hub에서 Repository를 생성하고 url 복사  
* sts의 git repository에서 clone a git repository and add the clone to this view를 선택  
* 앞에서 생성한 repository의 uri를 복사  
원격 repository와 로컬 repository가 연결됨  
* package explorer창에서 마우스 오른쪽 클릭해서 [import] - [gradle] - [existing gradle project]를 선택한 후 local git을 선택한 후 연결된 로컬 git 안의 애플리케이션을 선택해야함(git 디렉터리 내의 프로젝트를 선택할 것!)  
