<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<c:set var='root' value='${pageContext.request.contextPath }' />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 리스트</title>
<!-- 	부트스트랩코드 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<!-- 	부트스트랩코드 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<c:import url="/WEB-INF/views/top.jsp"></c:import>
<br>
<br>
<hr>
<main>


<a href="${root}/admin/listCategory?b_category=1">카테고리1</a>
<a href="${root}/admin/listCategory?b_category=2">카테고리2</a>
<a href="${root}/admin/listCategory?b_category=3">카테고리3</a>
<a href="${root}/admin/listCategory?b_category=4">카테고리4</a>
<a href="${root}/admin/listCategory?b_category=5">카테고리5</a>

<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">책번호</th>
      <th scope="col">책이름</th>
      <th scope="col">카테고리</th>
      <th scope="col">내용보기</th>
      <th scope="col">삭제</th>
      <th scope="col">수정</th>
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
	        <td><a href="${root }/admin/listOne?b_num=${bookVo.b_num}">✔️</a></td>
	        <td><a href="${root }/admin/del?b_num=${bookVo.b_num}">✔️</a></td>
	        <td><a href="${root }/admin/update?b_num=${bookVo.b_num}">✔️</a></td>
	      </tr>
	    </c:forEach>
	  </c:when>
	  
	  <c:otherwise>
	    <c:forEach items="${list}" var="bookVo">
	      <tr>
	        <th scope="row">${bookVo.b_num}</th>
	        <td>${bookVo.b_name}</td>
	        <td>${bookVo.b_category}</td>
	        <td><a href="${root }/admin/listOne?b_num=${bookVo.b_num}">✔️</a></td>
	        <td><a href="${root }/admin/del?b_num=${bookVo.b_num}">✔️</a></td>
	        <td><a href="${root }/admin/update?b_num=${bookVo.b_num}">✔️</a></td>
	      </tr>
	    </c:forEach>
	  </c:otherwise>
	</c:choose>
  </tbody>
</table>
	
<div class="list-group">
  <a href="${root}/admin/main" class="list-group-item list-group-item-action">
	관리자 메인페이지로
  </a>
</div>
	
</main>
</body>
</html>