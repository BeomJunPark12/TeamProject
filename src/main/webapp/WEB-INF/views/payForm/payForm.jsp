<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value='${pageContext.request.contextPath }/' />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <!-- jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js" type="text/javascript"></script>
</head>
<body>
<div class="topnav">
<%-- <a href="${root }">메인</a> --%>
	<a href="${root}">메인</a>
	<a href="${root}/member/myPage.do">마이페이지</a>
</div>
<hr>

<div class="container">
        <h2>주문자 정보</h2>
        <label for="id">아이디</label>
        	<input type="text" name="id" value="${userVO.user_id}" readonly="readonly"><br>
        <label for="tel">전화번호</label>
        	<input type="text" name="tel" value="${userVO.user_phone}" readonly="readonly"><br>
        <label for="grade">회원등급</label>
       	 	<input type="text" name="grade" value="${userVO.user_grade}" readonly="readonly">
        <h3>배송지</h3>
        <label>배송지</label>
       	 	<input type="text" name="address" value="${userVO.user_address}" readonly="readonly">
 <hr>

        <h2 id="orderInfo">주문 정보</h2>
        <table border="1">
            <tr>
                <th>책번호</th>
                <th>상품명</th>
                <th>수량</th>
                <th>주문금액</th>
            </tr>
            <c:forEach var="cart" items="${cartList}">
                <tr>
                    <td id="bookNum">${cart.b_num}</td>
                    <td id="productName">${cart.b_name}</td>
                    <td id="count">${cart.ordercnt}</td>
                    <td id="price">${cart.b_price}</td>
                </tr>
            </c:forEach>
        </table>
        <h3 id="totalOrder">결제 금액: 
        	<fmt:formatNumber value="${totalPrice == null ? discountedPrice : totalPrice}" pattern="#,##0"/>원
        </h3>
        <button id="payBtn" onclick="requestPay('${totalName}','${totalPrice}', '${discountedPrice}')">결제하기</button>
</div>
<script>
var IMP = window.IMP;
IMP.init("imp87880182"); // 예: imp00000000

function requestPay(totalName , totalPrice, discountedPrice) {
	  // IMP.request_pay(param, callback) 결제창 호출
	  
		var amount = totalPrice || discountedPrice || 0;
	  
	  IMP.request_pay({
	    pg: "kakaopay",	// html5_inicis   kakaopay
	    pay_method: "card",
	    merchant_uid: "merchant_" + new Date().getTime(),
	    name: totalName,
	    amount: amount,
	    buyer_name: "${userVO.user_id}",
	    buyer_tel: "${userVO.user_phone}",
	    buyer_addr: "${userVO.user_address}",
	  },   function (rsp) {
		    if (rsp.success) {
		    	console.log(rsp);
		    	 $.ajax({
		               type : "POST",
		               url : "/BookMall/payment/success/" + rsp.imp_uid,
		               headers: {"content-type": "application/json"},
		        }).done(function (data) {
		          // 가맹점 서버 결제 API 성공시 로직
		          alert('결제가 완료되었습니다.');
		          console.log(data);
		          $("#orderInfo").html('결제 완료');
                  $("#payBtn").hide();
                  
                  // 결제 완료 후 구매내역 버튼 생성
                  var buyButton = $("<button>");
                  buyButton.text("구매내역 보기");
                  buyButton.click(function() { 
                	  var url = "/BookMall/member/buy.do";
                	  window.location.href = url;
                  });

                  // 생성한 버튼을 원하는 위치에 추가
                  $("#totalOrder").after(buyButton);
                  
		        })
		      } else {
		        alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
		      }
		});
}

  </script>
</body>
</html>