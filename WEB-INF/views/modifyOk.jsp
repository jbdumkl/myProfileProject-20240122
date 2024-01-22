<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				
				<form>
				
					<tr>
						<td class="con text">아 이 디 : </td>
						<td><input class="input box01" type="text" name="mid" value="${memberDto.mid}" readonly="readonly"></td>
					</tr>
					<tr>
						<td class="con text">비밀번호 : </td>
						<td><input class="input box01" type="password" name="mpw" value="${memberDto.mpw}" readonly="readonly"></td>
					</tr>
					<tr>
						<td class="con text">이  름 : </td>
						<td><input class="input box01" type="text" name="mname" value="${memberDto.mname }" readonly="readonly"></td>
					</tr>
					<tr>
						<td class="con text">이 메 일 : </td>
						<td><input class="input box01" type="text" name="memail" value="${memberDto.memanl }" readonly="readonly"></td>
					</tr>
					<tr>
						<td class="con text">가 입 일 : </td>
						<td><input class="input box01" type="text" name="mdate" value="${memberDto.mdate }" readonly="readonly"></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input class="con_btn01" type="button" value="정보수정" onclick="javascript:window.location.href='modify'">&nbsp;&nbsp;
							<input class="con_btn01" type="button" value="수정완료" onclick="javascript:window.location.href='index'">
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