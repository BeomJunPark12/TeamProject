<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.gdj59.bookmall.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
	function mod(obj) {
		document.getElementById("user_pw").disabled = false;
		document.getElementById("user_address").disabled = false;
		document.getElementById("user_phone").disabled = false;
		document.getElementById("tr_btn_modify").style.display = "block";
		document.getElementById("tr_btn").style.display = "none";
	}
	function modifyMemberInfo(obj) {
		var aa = {
			user_id : "${userVO.user_id}",
			user_pw : document.getElementById("user_pw").value,
			user_address : document.getElementById("user_address").value,
			user_phone : document.getElementById("user_phone").value,
			user_maxPrice : "${userVO.user_maxPrice}",
			user_grade : "${userVO.user_grade}"
		};
		$.ajax({
			type : "post",
			url : "${contextPath}/api/updateMember.do",
			contentType : "application/json",
			data : JSON.stringify(aa),
			success : function(data, textStatus) {
				alert("수정성공");
				location.href = '${contextPath}/member/myPage.do';
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다.");
			},
			complete : function(data, textStatus) {
			}
		});
	}
 	function modifyCart(param1,param2,param3) {
		var aa = {
			ordercnt :document.getElementById(param1).value,
			b_name :param2
		};
	 	$.ajax({
			type : "post",
			url : "${contextPath}/api/modifyCart.do",
			contentType : "application/json",
			data : JSON.stringify(aa),
			success : function(data, textStatus) {
				console.log(data);
				if(data < 0 ){
					data = 0;
				document.getElementById(param1).value = data;
				alert("감소할 수량이 없습니다");
				}else{
					console.log(data);
					document.getElementById(param1).value = data.ordercnt;
					document.getElementById(param3).value = data.ordercnt*data.b_price;
					alert("1개 감소");
				}
			},
			error : function(data, textStatus) {
				alert("감소할 수량이 없습니다");
			},
			complete : function(data, textStatus) {
			} 
		}); 
	} 
 	function 완전삭제(param1){
 		console.log(param1);
 	$.ajax({
 		type:"post",
 		url:"${contextPath}/api/완전삭제.do",
 		data:{cart_id:param1}
 	}).done(function(HttpStatus){
 		alert("삭제 성공");
 		location.href="/BookMall/member/myPage.do";
 	}).fail(function(){
 		alert("삭제 실패");
 	}); 
 	}
 	function checkCart() {
 		  var list = ${empty sessionScope.list};
 		  
 		  if (list) {
 		    alert("장바구니가 비어있습니다.");
 		  } else {
 		    location.href = "${contextPath}/payment/form";
 		  }
 		}
 	
</script>
<style>
#tr_btn_modify {
	display: none;
}
</style>
</head>
<body>
	<header id="header">
		<h1>
			<a href="#">&nbsp;my page</a>
		</h1>
		<nav id="nav">
			<ul>
				<li><a href="${contextPath}/member/main.do">메인 페이지로</a></li>
				<li><a
					href="${contextPath}/member/buy.do?user_num=${userVO.user_num}">구매내역보기</a></li>
			</ul>
		</nav>
	</header>
	<form name="frm" method="post">
		<table border="0" align="center">
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">아이디</td>
				<td><input type="text" value="${userVO.user_id }" disabled />
					<input type="hidden" name="user_id" value="${userVO.user_id}" /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">비밀번호</td>
				<td><input type=text value="${userVO.user_pw}" name="user_pw"
					id="user_pw" disabled /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">주소</td>
				<td><input type=text value="${userVO.user_address}"
					name="user_address" id="user_address" disabled /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">휴대폰 번호</td>
				<td><input type=text value="${userVO.user_phone}"
					name="user_phone" id="user_phone" disabled /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933" rowspan="2">누적금액</td>
			</tr>
			<tr>
				<td><input type="text" name="user_maxPrice"
					value="${userVO.user_maxPrice}" disabled /></td>
			</tr>
			<tr>
				<td width=20% align=center bgcolor=#FF9933>등급</td>
				<td><input type=text value="${userVO.user_grade}" disabled /></td>
			</tr>
			<tr id="tr_btn">
				<td colspan=2 align=center><input type="button" value="수정하기"
					onClick="mod()"> <%-- <a href="${contextPath}/member/deleteMember.do?user_id=${memberVO.user_id}"><button>회원탈퇴</button></a> --%>
					<input type="button"
					onclick="location.href='${contextPath}/member/deleteMember.do?user_id=${userVO.user_id}'"
					value="회원 탈퇴">
					<input id="pay" type="button" value="결제하기" onclick="checkCart()">
			</tr>
			<tr id="tr_btn_modify">
				<td colspan="2" align="center"><input type=button
					value="수정반영하기" onClick="modifyMemberInfo(frm)"><br> <a
					href="${contextPath}/member/myPage.do">수정 취소</a></td>
			</tr>
		</table>
	</form>
	<h3>장바구니 내역</h3>
	<table border=1 text-align=center>
		<tr style="background: #33ff00">
			<td>책 제목</td>
			<td>가격</td>
			<td>수량</td>
			<td>카테고리</td>
		</tr>
		<c:choose>
			<c:when test="${empty sessionScope.list}">
				<tr>
					<td colspan="5" style="text-align: center">장바구니 내역이 비어있습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="cart" items="${list}" varStatus="status">
					<tr colspan=8>
						<td><input type=text id="${cart.b_name}"value="${cart.b_name}" disabled/></td>
						<td><input type=text id="${status.index}"value="${cart.b_price}원" disabled/></td>
						<td><input type=text id="${cart.cart_id}"value="${cart.ordercnt}" disabled/>
							<input type="button"onClick="modifyCart(${cart.cart_id},'${cart.b_name}','${status.index}')" value="➖"></td>
						<td><input type=text value="${cart.b_category}" disabled/></td>
						<td><input type="button" onClick="완전삭제(${cart.cart_id})"
							value="완전삭제"></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>