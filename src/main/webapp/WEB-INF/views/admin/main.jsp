<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value='${pageContext.request.contextPath }' />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<!-- 	부트스트랩코드 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<!-- 	부트스트랩코드 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>


<c:import url="/WEB-INF/views/top.jsp"></c:import>

<hr>
<hr>
<div class="list-group">
	<a href="${root }/"
		class="list-group-item list-group-item-action active"
		aria-current="true"> 이전으로 가기 </a>
</div>

<!-- ==================================================== -->

<div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        1. 회원관리
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      
<!--       회원관리 메뉴 -->
<!-- ==================================================== -->
		<div class="list-group">
		  <a href="${root }" class="list-group-item list-group-item-action"> 1. 회원 리스트 </a>
		  <a href="${root }" class="list-group-item list-group-item-action"> 2. 회원 등록 </a>
		  <a href="${root }" class="list-group-item list-group-item-action"> 3. 회원 삭제 </a>
		</div>
<!-- ==================================================== -->

      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingTwo">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
       2. 제품관리
      </button>
    </h2>
    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      
<!--       제품관리 메뉴 -->
<!-- ==================================================== -->
		<div class="list-group">
		  <a href="${root }/admin/list" class="list-group-item list-group-item-action"> 1. 제품 리스트 </a>
		  <a href="${root }/admin/ins?b_category=${b_category}" class="list-group-item list-group-item-action"> 2. 제품 등록 </a>
		</div>
<!-- ==================================================== -->
      
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingThree">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
        3. 결제관리
      </button>
    </h2>
    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      
<!--       결제관리 메뉴 -->
<!-- ==================================================== -->
		<div class="list-group">
		  <a href="${root }" class="list-group-item list-group-item-action"> 1. ㅇㅅㅇ </a>
		  <a href="${root }" class="list-group-item list-group-item-action"> 2. ㅇㅅㅇ </a>
		  <a href="${root }" class="list-group-item list-group-item-action"> 3. ㅇㅅㅇ </a>
		</div>
<!-- ==================================================== -->
       
      </div>
    </div>
  </div>
</div>



</body>
</html>