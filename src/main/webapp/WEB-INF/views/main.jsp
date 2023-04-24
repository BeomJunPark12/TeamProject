<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value='${pageContext.request.contextPath }/' />
<%
	String id = (String) session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	function addCart() {
		var b_name = $("#aaa").val();
		$.ajax({
			type : "post",
			async : true,
			url : "${contextPath}/cart/addCart.do",
			dataType : "text",
			data : {
				b_name : b_name
			},
			success : function(result) {
				if (result == 1) {
					alert("장바구니 1개추가 완료");
				} else if (result == 0) {
					alert("로그인 후 이용하세요");
					$(location).attr("href",
							"${contextPath}/member/loginPage.do");
				} else if (result == 2) {
					alert("책 재고가 없습니다");
				}
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다.");
			}
		});
	}
	function addCart1() {
		var b_name = $("#bbb").val();
		$.ajax({
			type : "post",
			async : true,
			url : "${contextPath}/cart/addCart.do",
			dataType : "text",
			data : {
				b_name : b_name
			},
			success : function(result) {
				if (result == 1) {
					alert("장바구니 1개추가 완료");
				} else if (result == 0) {
					alert("로그인 후 이용하세요");
					$(location).attr("href",
							"${contextPath}/member/loginPage.do");
				} else if (result == 2) {
					alert("책 재고가 없습니다");
				}
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다.");
			}
		});
	}
</script>
</head>
<body>
	<c:import url="/WEB-INF/views/top.jsp"></c:import>
	<hr>
</body>
</html>