<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Home</title>
<link href="loginstyle.css" rel="stylesheet" type="text/css"/>
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <script src="https://kit.fontawesome.com/844589ebe5.js" crossorigin="anonymous"></script>
</head>
<body>
	<header>
        <div class="inner">
            <div class="head-container">
                <a href="loginForm.html">Logo</a>
                <a href="loginForm.html"><i class="fa-solid fa-right-to-bracket"></i>
                    login
                    </a>
                    <a href ="signupform.jsp">회원가입</a>
            </div>
        </div>
    </header>
    <section>
        <form action="loginPro.html" method="post">
            <div class="inner">
                <div class="form-container">
                    <div class="form-title">LOGIN</div>
                    <div class="form-id">
                    <div class="form-label">ID</div>
                    <div class="form-input">   <input type="text" name="id" autofocus autocomplete="username" placeholder="ID를 입력하세요"/>   </div>
                    </div>
                    <div class ="form-pw">
                    <div class="form-label">PW</div>
                    <div class="form-input">   <input type="password" name="pw" autocomplete="new-password" placeholder="PW를 입력하세요"/>   </div>
                    </div>
                    <div class="form-submit-btn">   <button><input type="submit" value="login"/></button>
                    </div>
                </div>
            </div>
        </form>
    </section>
    <footer>
        <div class="inner">
            <div class="footer-message">
                <span>
                주식회사 메가제이앤씨 <br/>
            </span>
                대표자 : 김대현|주소 : 서울특별시 서초구 강남대로 279, 5층(서초동, 백향빌딩)
                사업자등록번호 : 513-87-00088|통신판매신고번호 : 2018-서울서초-0784<br/>
                마케팅제휴 : 정성채 | 메일문의 : sandy500@careergate.co.kr
            </div>
            <div class="footer-contact">
                더조은컴퓨터아카데미
                대표번호1588.8748
            </div>
            <div class = "footer-copyright">
                ⓒ THEJOEUN ACADEMY Corp.
            </div>
            </div>
        </div>
    </footer>
</body>
</html>