0. 유용한 사이트  
	w3schools.com  
	caniuse.com  

1. 개발 툴  
	https://code.visualstudio.com/  
	구글 > vscode 검색 > 다운 받기  
	아이콘 무료 생성 : squarespace.com  
2. HTML  
	HyperText Markup Launuage : 웹에서 사용하는 웹문서  

	확장자명 : .html  
	웹문서 : 작성 프로그램 "웹 편집기", 웹 문서 보는 프로그램 "웹 브라우저"  
	웹표준 : 웹사이트를 만들 때 지켜야 하는 약속들을 정리한 것.  
	W3C : World Wide Web Consortium 단체 설립. 웹 표준을 정의하는 단체  

3. 태그 tag  
	마크업할 때 사용하는 약속된 표기법  
	<>를 이용해 구분 <html>  
	태그는 소문자로 사용  
	여는 태그와 닫는 태그 정확히  
		태그 : 쌍 태그로 이루어짐 <html> ... </html> <h1> ... </h1>  
		홀태그 : 하나의 태그로 이루어짐 <img /> <br />  
	들여쓰기 잘하기  
	태그는 속성과 함께 작성 가능  
		<태그명 속성명="속성값" 속성명="속성값" > ... </태그명>  
	vscode에선 ! tab시 head 자동 완성  

4. HTML 문서 구조  
	- head : 브라우저에게 정보를 주는 태그 : 화면상에는 안보임  
	- body : 화면상에 보이는 부분  

5. 텍스트 관련 태그  
	<h1> </h1> : h태그, h1~h6 제목태그. 자동 줄바꿈(한 줄 모두 차지)  
	<p> </p> : 단락 만들기, 앞뒤 줄바꿈 있는 텍스트 덩어리  
	<br /> : 줄내림  
	<hr /> : 수평선 넣기  
	<blockquote></blockquote> : 인용문 넣기, 양끝 들여쓰기 적용됨  
	<pre></pre> : 입력하는 그대로 화면에 표시  
	<span></span> : 줄바꿈 없이 영역 묶기, 일부 텍스트만 묶어서 스타일 적용할 때 주로 사용  
	<strong></strong> : 볼드체  
	<u></u> : 밑 줄  

6. 목록 태그  
	- <ul></ul>, <li></li> : 순서없는 목록 만들기 unorder list  
	- <ol></ol>, <li></li> : 순서있는 목록 만들기 ordered list  
	- 속성  
		- type 속성 : 숫자/영문/로마 숫자 변경 가능  
			1 = 숫자, a = 영문 소문자, A = 영문대문자, i = 로마숫자소문자, I = 로마숫자대문자  
		- start 속성 : 중간 번호부터 시작 가능  
	- vscode 자동완성 기능  
		ol>lis*4하면 li가 4개 있는 ol 자동 완성  
	
7. 표 만드는 태그  
	표 : table은 행(row), 열(column)으로 이루어져 있다.  
	<table></table>  
	<tr></tr> : 행 지정  
	<td></tdL : 셀 만들기  
	<th></th> : 제목 셀  
	
	- 행 / 열 합치기  
		<th>,<th>에 작성  
			colspan : 열 합치기 <td colspan="합칠 열의 개수">  
			rowspan : 행 합치기 <td rowspan ="합칠 행의 개수">  

8. 이미지 태그  
	- 이미지 파일  
		GIF : 색상수 256가지 뿐, 파일크기 적음. 아이콘, 불릿 등 작은 이미지에 주로 사용.  
		       움직이는 이미지 만들 수 있음.  
		JPG : 사진  
		PNG : 투명 배경  
	- 태그  
		<img />  
		scr 속성 : (필수 기입 속성) 이미지 파일 경로 지정해줘야 함. (상대경로, 절대경로)  
		alt 속성 : 이미지 설명글  
		width, height 속성 : 이미지 가로, 세로 크기 지정 (px)  

9. 링크  
	- 태그  
		<a></a> : herf 속성을 이용하여 다른 페이지로 이동할 수 있게.  

		<a herf="이동할 주소">텍스트</a>  
		<a herf="이동할 주소"><img /></a>   
		중간에 있는 요소를 클릭하면 이동됨  
	- 속성  
		herf : 링크 주소  
		target : 새 창  : _blank  
			현재 창으로 이동 : _self (default)  
	- 앵커  
10. 폼 관련 태그  
	아이디와 비번 입력, 로그인 버튼, 회원가입 창 등 사용자가 웹사이트(서버)로 정보를 보낼 수 있는 요소들을 모두 폼form이라 한다  

	- form 태그  
		<form 속성 = "값" .. > ... 여러 폼 요소 ... </form>  
	- form 태그의 속성  
		method:  사용자가 입력한 내용을 서버 쪽으로 어떤 방식으로 전달할 지 지정  
			get : 주소 표시되는 부분에 사용자가 입력한 내용이 그대로 드러남  
			post : 입력 내용이 url 드러나지 않고, 내부에 담아서 전송  
		name : 태그에 이름 부여. 폼에 이름 부여.  
			폼 태그 요소(input)에 name 속성을 지정하면 넘겨주는 데이터의 이름을 name 속성 값으로 지정해줌.  
		action : form 태그 안에 내용들을 처리해 줄 서버 상의 경로 작성  

11. input 태그  
	- 태그  
		<input 속성="값" .. >  
		폼에서 사용자가 입력하는 부분을 만들 때 사용  
		<input type = "type 속성값">에 따라 여러 형태로 입력받을 수 있다.  
	- type 속성 값  
		text : 한줄 짜리 텍스트 입력할 수 있는 텍스트 상자  
		hidden : 화면 상에는 보이지 않지만 서버로 데이터를 전송할 때 사용  
		password : 비밀번호 입력란. 브라우저에 따라 *나 점으로 표시  
		radio : 하나의 항목만 선택하게 해줄 수 있는 체크박스 계열. 선택 해제가 안됨  
			name 속성값이 동일해야 동일 그룹으로 인식하고 한 개만 선택 가능하게 됨.  
		checkbox : 중복 선택 가능. 체크해제 가능  
		submit : 사용자가 입력한 정보를 서버로 전송하는 버튼. value 속성 값이 버튼 글자로 표시.  
		reset : 입력한 모든 정보를 초기화해주는 버튼.  
		image : 이미지 버튼 넣기. submit 동일한 기능. src로 이미지 경로 지정  
		color : 색상 선택 상자  
		date, month, week : 날짜 선택 박스  
		time, datetime, datetime-local : 시간  
		file : 파일 첨부 시  
	- input의 다른 속성  
		autofocus : 입력 커서 지정  
		placeholder : 입력 박스에 기입할 내용 힌트로 표시하기  
		readonly : 읽기 전용  
		required : 필수 기입 요소로 지정  
