<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@page import="com.gdj59.bookmall.beans.BookVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value='${pageContext.request.contextPath }' />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookListOne</title>
<!-- 	부트스트랩코드 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<!-- 	부트스트랩코드 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<main>
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">책번호</th>
      <th scope="col">책이름</th>
      <th scope="col">책가격</th>
      <th scope="col">재고</th>
      <th scope="col">카테고리</th>
      <th scope="col">누적구매회수</th>
      <th scope="col">수정</th>
    </tr>
  </thead>
  <tbody>
     <tr> 
      <th scope="row">${listOne.b_num}</th>
      <td>${listOne.b_name}</td>
      <td>${listOne.b_price}</td>
      <td>${listOne.b_stock}</td>
      <td>${listOne.b_category}</td>
      <td>${listOne.b_purchase}</td>
      <td><a href="${root }/admin/book_update?b_num=${listOne.b_num}">글 수정</a></td>
    </tr>
  </tbody>
</table>
</main>

</body>
</html>