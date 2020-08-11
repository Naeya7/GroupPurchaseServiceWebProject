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
		<h2>상태변경 프로</h2>
		<c:if test="${result eq 0}">
			<script type="text/javascript">
				<!--
				alert("로그인 정보를 다시한번 확인해주세요");
				history.back();				
				//-->
			</script>
		</c:if>
		<c:if test="${result eq 1}">
			<script type="text/javascript">
					<!--
					alert("상태가 변경되었습니다");
					//-->
				</script>
			<c:redirect url="loginForm.do"/>
		</c:if>
	</body>
</html>