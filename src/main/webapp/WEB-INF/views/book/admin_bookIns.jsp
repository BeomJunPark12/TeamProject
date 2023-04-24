<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.gdj59.bookmall.beans.BookVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value='${pageContext.request.contextPath }/' />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookIns</title>

<!-- 	부트스트랩코드 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<!-- 	부트스트랩코드 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>


<form method="post" action="${root }book/bookIns">
	<label for="b_name">책이름</label>
	<input type="text" id="b_name" name="b_name" value="${bookVo.b_name }">
	<c:if test="${not empty duplicateMsg}">
 	<div class="alert alert-danger">${duplicateMsg}</div>
	</c:if>
	<br>
	<label for="b_price">가격</label>
	<input type="number" id="b_price" name="b_price" min="0" value="${bookVo.b_price }">
	<br>
	<label for="b_stock">재고</label>
	<input type="number" id="b_stock" name="b_stock" min="0" value="${bookVo.b_stock }">
	<br>
	
	<label for="b_category">카테고리</label>
	<input type="radio" name="b_category" value="1" ${b_category eq '1' ? 'checked' : ''}>
	<label for="category1">1</label>
	<input type="radio" name="b_category" value="2" ${b_category eq '2' ? 'checked' : ''}>
	<label for="category2">2</label>
	<input type="radio" name="b_category" value="3" ${b_category eq '3' ? 'checked' : ''}>
	<label for="category3">3</label>
	<input type="radio" name="b_category" value="4" ${b_category eq '4' ? 'checked' : ''}>
	<label for="category4">4</label>
	<input type="radio" name="b_category" value="5" ${b_category eq '5' ? 'checked' : ''}>
	<label for="category5">5</label>
	<br>
	<label for="b_purchase">누적구매횟수</label>
	<input type="number" id="b_purchase" name="b_purchase" min="0" value="0" readonly> 

		
<div class="inputArea">
   <label for="gdsImg">이미지</label>
   <input type="file" id="gdsImg" name="file" />
   <div class="select_img"><img src="" /></div>
   
   <script>
    $("#gdsImg").change(function(){
     if(this.files &amp;&amp; this.files[0]) {
      var reader = new FileReader;
      reader.onload = function(data) {
       $(".select_img img").attr("src", data.target.result).width(500);          
      }
      reader.readAsDataURL(this.files[0]);
     }
    });
   </script>

   
</div>
	<br>
	<input type="submit" value="등록">
</form>
		
		
		
</body>
</html>