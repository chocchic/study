<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Test03 Page</h2>
	<%-- request.setCharacterEcoding("UTF-8"); 과 동일 --%>
	<fmt:requestEncoding value="UTF-8"/>
	
	<%--<p>date객체 바로 프린트 : ${day}</p>--%>
	<%-- *** 날짜와 관련된 *** 
	<fmt:formatDate value="${day}"/><br/>
	<fmt:formatDate value="${day}" type="date"/><br/>
	<fmt:formatDate value="${day}" type="time"/><br/>
	<fmt:formatDate value="${day}" type="both"/><br/>
	<br/>--%>
	
	<%-- 출력 스타일을 미리 지정해놓은 옵션 
	<fmt:formatDate value="${day}" type="both" dateStyle="short"/><br/>
	<fmt:formatDate value="${day}" type="both" dateStyle="medium"/><br/>
	<fmt:formatDate value="${day}" type="both" dateStyle="long"/><br/>
	<fmt:formatDate value="${day}" type="both" dateStyle="full"/><br/>
	<br/>
	<fmt:formatDate value="${day}" type="both" dateStyle="short" timeStyle="short"/><br/>
	<fmt:formatDate value="${day}" type="both" dateStyle="medium" timeStyle="medium"/><br/>
	<fmt:formatDate value="${day}" type="both" dateStyle="long" timeStyle="long"/><br/>
	<fmt:formatDate value="${day}" type="both" dateStyle="full" timeStyle="full"/><br/>
	<br/>--%>
	<%-- 위처럼 만들어진 포맷 말고 내가 정한 포맷 지정 
	<fmt:formatDate value="${day}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/><br/>
	--%>
	<%-- *** 숫자와 관련된 *** --%>
	<%-- 자릿수 구별. groupingUsed default : true --%>
	<fmt:formatNumber value="10000000"  groupingUsed ="true"/>
	<fmt:formatNumber value="10000000"  groupingUsed ="false"/>
	<br/>
	<%-- 통화 : type은 number가 default값 
			브라우저 설정에 따라서 currency의 default 단위가 정해진다--%>
	<fmt:formatNumber value="10000000"  type="number"/>
	<fmt:formatNumber value="10000000"  type="currency"/>
	<fmt:formatNumber value="10000000"  type="currency" currencySymbol="$"/>
	<br/>
	<%-- 퍼센트 --%>
	<fmt:formatNumber value="0.3" type="percent"/><br/>
	<%-- 소숫점 자리수 표현 지정 : 반올림--%>
	<fmt:formatNumber value="1200.146" pattern=".00"/><br/>
	
	<%-- 10/3 -> 3.333333 .. 3만 원하면 (int)(10/3)
		var를 붙여서 변수 선언한 것처럼 사용 가능하다 하지만 출력은 안되게 됨--%>
	<fmt:parseNumber value="1000.123" integerOnly="true"/>
	<fmt:parseNumber var="result" value="1000.123" integerOnly="true"/>
	result : ${result}
	<br/>
	
	<%-- timeZone --%>
	<fmt:timeZone value="GMT">
		GMT 영국 : <fmt:formatDate value="${day}" type="both"/><br/>
	</fmt:timeZone>
	<fmt:timeZone value="GMT-4">
		<fmt:formatDate value="${day}" type="both"/><br/>
	</fmt:timeZone>
	
	<%-- *****EL***** --%>
	<h4>${arr}</h4>
	<h4>${arr[0]}</h4>
	
	<h4>${list}</h4>
	<h4>${list[0]}</h4> <%-- ArrayList는 배열처럼 사용가능 --%>
	<h4>${list.get(0)}</h4> <%-- 메서드 호출해서 사용가능 --%>
	
	<h4>${dto }</h4>
	<h4>${dto.id }</h4> <%--get메서드 호출해서 출력하는 격 : 자바 빈 형태 --%>
	<h4>${dto.age }</h4>
	<h4>${dto.getId() }</h4>
	<h4>${dto.getAge() }</h4>
	
		<h4>tv : ${tv}</h4>
	<h4>tv.vol : ${tv.vol}</h4>
	<h4>tv.ch : ${tv.ch }</h4>
	<h4>tv.color : ${tv.color}</h4>
</body>
</html>