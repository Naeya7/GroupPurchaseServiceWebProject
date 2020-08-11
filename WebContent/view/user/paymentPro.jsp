<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공구 프로젝투</title>
	</head>
	<body>
		<c:if test="${result eq 0}">
			<script type="text/javascript">
				<!--
				alert('결제에 실패했습니다. 다시 시도해주세요.');
				location.href="/MyProject/myPage.do";
				//-->
			</script>
		</c:if>
		<c:if test="${result eq 1}">
			<script type="text/javascript">
				<!--
				alert('결제 완료되었습니다');
				location.href="/MyProject/myPayedList.do";
				//-->
			</script>
		</c:if>
	</body>
</html>