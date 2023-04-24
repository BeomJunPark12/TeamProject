<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${contextPath }" style="font-size: 35px"><b>BookMall</b></a>
	<a href="${contextPath }/book/bookList">전체보기</a>
	<a href="${contextPath }/book/bookListCategory?b_category=1">카테고리1</a>
	<a href="${contextPath }/book/bookListCategory?b_category=2">카테고리2</a>
	<a href="${contextPath }/book/bookListCategory?b_category=3">카테고리3</a>
	<a href="${contextPath }/book/bookListCategory?b_category=4">카테고리4</a>
	<a href="${contextPath }/book/bookListCategory?b_category=5">카테고리5</a>
	<br>
<!-- -------------------------------------------------	 -->
<c:choose>
  <c:when test="${grade == 'admin'}">
	<a href="${contextPath }/admin/main">※관리자 페이지※</a>
  	반갑습니다 ${userVO.user_id } 회원님
  </c:when>
  <c:when test="${grade != null }">
  	반갑습니다 ${userVO.user_id } 회원님
  </c:when>
  <c:otherwise>
  	ㅎㅇㅎㅇ
  </c:otherwise>
</c:choose>
<br>
<br>
<!-- -------------------------------------------------	 -->
	
	<c:choose>
		<c:when test="${isLogOn==true and not empty userVO}">
			<li><a href="${contextPath}/member/logout.do">로그아웃</a></li>
			<li><a href="${contextPath}/member/myPage.do">마이 페이지</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${contextPath}/member/loginPage.do">로그인</a></li>
			<li><a href="${contextPath}/member/join.do">회원가입</a></li>
		</c:otherwise>
	</c:choose>

<a href="${contextPath }/admin/main">※관리자 페이지※</a><br>

	<input type="text" name="search" value="검색~">
	<button onclick="location.href='#'">검색</button>
</body>
</html>