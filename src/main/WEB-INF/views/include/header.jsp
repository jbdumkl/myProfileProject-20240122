<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/header.css">
<title>header</title>
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr class="headerline">
			<td width="65%">&nbsp;</td>
			<td width="4%" align="center">
				<a href="index"><span class="menutext">home</span></a>
			</td>
			<c:choose>
				<c:when test="${sessionScope.sessionId eq null}">
					<td width="4%" align="center">
						<a href="login"><span class="menutext">login</span></a>
					</td>
					<td width="4%" align="center">
						<a href="join"><span class="menutext">join</span></a>
					</td>
				</c:when>
				<c:otherwise>
					<td width="4%" align="center">
						<a href="logout"><span class="menutext">logout</span></a>
					</td>
					<td width="4%" align="center">
						<a href="modify"><span class="menutext">modify</span></a>
					</td>
				</c:otherwise>
			</c:choose>
			
			<td width="4%" align="center">
				<a href="profile"><span class="menutext">profile</span></a>
			</td>
			
			<td width="4%" align="center">
				<a href="board"><span class="menutext">board</span></a>
			</td>
			<td width="4%" align="center">
				<a href="contact"><span class="menutext">contact</span></a>
			</td>
			<td width="3%">
				&nbsp;
			</td>
		</tr>
		<tr height="20">
			<c:if test="${sessionScope.sessionId != null}">
				<td colspan="9" align="right">
					<span style="font-family: Arial; font-size: 14px; color: #6799FF;">
						member : </span><span style="font-family: Arial; font-size: 14px; color: #0B60B0; font-weight: bold;"><c:out value="${sessionScope.sessionId }"></c:out></span>
				</td>
			</c:if>
		</tr>
	</table>
</body>
</html>