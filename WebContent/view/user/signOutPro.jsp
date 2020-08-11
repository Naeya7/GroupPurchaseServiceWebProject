<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공구 프로젝트</title>
	</head>
	<body>
		<c:if test="${resultCheck eq 0}">
			<script type="text/javascript">
				<!--
				alert("비밀번호가 틀렸습니다");
				history.back();
				//-->
			</script>
		</c:if>
		<c:if test="${resultCheck eq 1}">
			<c:if test="${result eq 0}">
				<script type="text/javascript">
				<!--
				alert("탈퇴에 실패했습니다")
				//-->
				</script>
				<meta http-equiv="refresh" content="0; url='myInfor.do">
			</c:if>
			<c:if test="${result eq 1}">
				<script type="text/javascript">
				<!--
				alert("탈퇴에 성공했습니다")
				//-->
				</script>
				<meta http-equiv="refresh" content="0; url='mainPage.do">
			</c:if>
		</c:if>
	</body>
</html>