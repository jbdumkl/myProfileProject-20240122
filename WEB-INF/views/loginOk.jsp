<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" url="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/title.css">
<link rel="stylesheet" href="/resources/css/content.css">
<script type="text/javascript" src="/resources/js/join.js"></script>
<title>## My Profile</title>
</head>
<body>
	<c:if test="${loginFail == 1}">
		<script type="text/javascript">
			alert('아이디 또는 비밀번호가 존재하지 않습니다. 확인 후 다시 로그인 해주세요.');
			history.go(-1);
		</script>
	</c:if>	

	<%@ include file="include/header.jsp" %>
	<center>
	<table border="0" cellpadding="20" cellspacing="0">
		<tr>
			<td align="center">
				<span class="title01">DEVELOPER MKL's PROFILE</span>
			</td>
		</tr>
		<tr>
			<td align="center">	
				<span class="title02">I'm MKL Lee, a developer who wants a development job. Please call me back.</span>
			</td>
		</tr>
		<tr>
			<td class="con_box" align="center">
				<table border="0" cellpadding="10" cellspacing="0">
				<form action="joinOk" method="post" name="joinForm">
					<tr>
						<td class="con text02">
							${memberDto.mname }님 로그인 하셨습니다. 반갑습니다!<br>
							${memberDto.mid }님의 가입일은 ${memberDto.mdate } 입니다.<br><br>
							질문 게시판에 글읅 써주세요.
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="center">
							<input class="con_btn01" type="button" value="질문게시판" onclick="javascript:window.location.href='board'">
						</td>
					</tr>
				</form>
				</table>
			</td>
		</tr>
	</table>
	</center>
	<%@ include file="include/footer.jsp" %>
</body>
</html>