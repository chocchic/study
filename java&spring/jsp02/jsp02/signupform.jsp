<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sign up form</title>
<link href="signupstyle.css" rel="stylesheet" type="text/css"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header>
        <div class = "hdr-title">회원가입</div>
    </header>
    <section>
        <div class="signup-info">
            <form action="signupPro.jsp" method="post">
            <span class="asterisk">*는 필수 입력 정보입니다.<br/></span>
            <table>
                <th>회원가입 정보 입력</th>
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" name="name" autofocus required placeholder="실명을 적어주세요"/></td>
                </tr>
                <tr>
                    <td>성별</td>
                    <td>
                    남 <input type = radio name = gender value = "남성"/>
                    여 <input type = radio name = gender value = "여성"/>
                    </td>
                </tr>
                <tr>
                    <td>* 사용자 아이디</td>
                    <td><input type="text" name="id" required placeholder="아이디를 적어주세요"/> 
                        <button>ID중복확인</button>
                    </td>
                </tr>
                <tr>
                    <td>* 비밀번호</td>
                    <td><input type="password" required name="pw" /> 영문/숫자4~10자 이내</td>
                </tr>
                <tr>
                    <td>* 비밀번호 확인</td>
                    <td><input type="password" required name="pw"/></td>
                </tr>
                <tr>
                    <td>생일</td>
                    <td><input type="date" name="bday"/></td>   
                </tr>
                <tr>
                    <td>주소</td>
                    <td><input type="text" name="addr" class="input-addr">
                        <button>우편번호 검색</button>
                        <input type="text" > <!--우편 번호 검색으로 자동 채우기 -->
                        <br/>
                        <input type="text" name="addr2" placeholder="세부 주소 정보 ">
                    </td>
                </tr>
                <tr>
                    <td>전화 번호</td>
                    <td>
                        <select name="localphone">
                            <option value="" selected="selected">지역 번호</option>
                            <option value="02">02</option>
                            <option value="031">031</option>
                            <option value="032">032</option>
                            <option value="033">033</option>
                            <option value="041">041</option>
                            <option value="042">042</option>
                            <option value="043">043</option>
                            <option value="044">044</option>
                            <option value="051">051</option>
                            <option value="052">052</option>
                            <option value="053">053</option>
                            <option value="054">054</option>
                            <option value="054">054</option>
                            <option value="061">061</option>
                            <option value="062">062</option>
                            <option value="063">063</option>
                            <option value="070">070</option>
                        </select> - 
                        <input type="text" name="telephone2" class="input-phone"> -
                        <input type="text" name="telephone3" class="input-phone">
                    </td>
                </tr>
                <tr>
                    <td>휴대폰 번호</td>
                    <td>
                        <select name="isp">
                            <option value="" selected="selected">통신사</option>
                            <option value="skt">SKT</option>
                            <option value="kt">KT</option>
                            <option value="lg">LG</option>
                            <option value="etc">알뜰폰</option>
                        </select>
                        <select name="phone1">
                            <option value="010">010</option>
                            <option value="011">011</option>
                        </select> - 
                        <input type="text" name="phone2" class="input-phone"> -
                        <input type="text" name="phone3" class="input-phone">
                    </td>
                </tr>
                <tr>
                    <td>* 이메일</td>
                    <td><input type="text" name="email1"/> @
                        <select name="email2" id = "email2">
                            <option value="null" selected="selected">이메일 선택</option>
                            <option value="naver.com">naver.com</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="daum.net">daum.net</option>
                            <option value="">[ 직접 입력 ]</option>
                        </select>
                        <div class="selfinput"><input type="text" name="emailself"> 
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>관심 정보</td>
                    <td>
                        <div class="cbox-interest">
                        movie<input type = "checkbox" name="interest" value="movie"/>
                        book<input type = "checkbox" name="interest"value="book"/>
                        netfilx<input type = "checkbox" name="interest" value="netfilx"/>
                        studying<input type = "checkbox" name="interest" value="studying"/>
                        cooking<input type = "checkbox" name="interest" value="cooking"/>
                        game<input type = "checkbox" name="interest" value="game"/>
                        cartoon<input type = "checkbox" name="interest" value="cartoon"/>
                        youtube<input type = "checkbox" name="interest" value="youtube"/>
                        fashion<input type = "checkbox" name="interest" value="fashion"/>
                        makeup<input type = "checkbox" name="interest" value="makeup"/>
                        cosmetic<input type = "checkbox" name="interest" value="cosmetic"/>
                        medical<input type = "checkbox" name="interest" value="medical"/>
                        etc : <input type = text" name ="interest"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>메일링 리스트 가입</td>
                    <td>메일링리스트에 가입하시면 WebCafe의 뉴스레터를 받아보실 수 있습니다.<br/>
                        <input type = radio name = "mailing" value = "mailingY" checked/>예
                        <input type = radio name = "mailing" value = "mailingN"/>아니오 </td>
                </tr>
            </table>
            
        </div>
        <div class="btn">
            <div class="signup-btn"><button type="submit">회원가입</button></div>
            <button class = "back"><a href ="loginform.jsp">뒤로가기</a></button>
        </div>
        </form>
    </section>
    <script>
        $(document).ready(function() {
            $('#email2').change(function() {
                var result = $('#email2 option:selected').val();
                if (result == '') {
                $('.selfinput').show();
                } else {
                $('.selfinput').hide();
                }
            }); 
        }); 

    </script>
</body>
</html>