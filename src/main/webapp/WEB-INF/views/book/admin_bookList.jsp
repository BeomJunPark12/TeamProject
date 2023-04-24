<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.gdj59.bookmall.beans.BookVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value='${pageContext.request.contextPath }' />
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookList</title>
<!-- 	부트스트랩코드 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<!-- 	부트스트랩코드 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<c:import url="/WEB-INF/views/top.jsp"></c:import>
<main>
<table class="table table-striped">

  <thead>
    <tr>
      <th scope="col">책번호</th>
      <th scope="col">책이름</th>
      <th scope="col">카테고리</th>
    </tr>
  </thead>
  <tbody>
  
	<c:choose>
	  <c:when test="${not empty listCategory}">
	    <c:forEach items="${listCategory}" var="bookVo">
	      <tr>
	        <th scope="row">${bookVo.b_num}</th>
	        <td>${bookVo.b_name}</td>
	        <td>${bookVo.b_category}</td>
	      </tr>
	    </c:forEach>
	  </c:when>
	  <c:otherwise>
	    <c:forEach items="${list}" var="bookVo">
	      <tr>
	        <th scope="row">${bookVo.b_num}</th>
	        <td>${bookVo.b_name}</td>
	        <td>${bookVo.b_category}</td>
	      </tr>
	    </c:forEach>
	  </c:otherwise>
	</c:choose>
	
	<c:forEach items="${listPaging}" var="paingVO">
	      <tr>
	        <th scope="row">${paingVO.b_num}</th>
	        <td>${paingVO.b_name}</td>
	        <td>${paingVO.b_category}</td>
	      </tr>
	</c:forEach>
	    
  </tbody>
</table>

	
</main>
</body>
</html>