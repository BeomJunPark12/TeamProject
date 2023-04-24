<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty msg}">
<script>
        alert("${msg}");
    </script>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style>
h1{
text-align: center ;
}
body{
margin-top: 200px;
}
#log {
	max-width: 400px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
}

input[type="text"], input[type="password"] {
	font-size: 16px;
	padding: 10px;
	margin-bottom: 10px;
	border-radius: 5px;
	border: 1px solid #ccc;
}

.button {
	padding: 10px 30px;
	text-align: center;
}
</style>
</head>
<body>
<h1>bookmall</h1>
	<div id="log">
		<form action="login_proc.do" method="post">
			<!-- <label for="username">아이디:</label> -->
			아이디: <input type="text" id="user_id" name="user_id" required> <br>
			<!--  <label for="password">비밀번호:</label>-->
			비밀번호: <input type="password" id="user_pw" name="user_pw" required> <br>
			<button class="button" type="submit">
				<span>로그인</span>
			</button>
			<a href="join.do" class="button"> <span>회원가입</span>
			</a>
			<a href="https://kauth.kakao.com/oauth/authorize?client_id=6b6b2d261e1bd0e80490d3c31e576b12&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"><img src="../image/kakao_login_medium (1).png"></a>
		</form>
	</div>
	<br>
</body>
</html>