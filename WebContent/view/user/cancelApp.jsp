<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공구프로젝뚜</title>
	</head>
	<body>
		<c:if test="${result eq 0}">
			<script type="text/javascript"> 
				alert("신청 취소에 실패했습니다");
				history.back();
			</script>
		</c:if>
		<c:if test="${result eq 1}">
			<script type="text/javascript">
				alert("신청 취소 완료");
				location.href="/MyProject/notPayablePost.do";
			</script>
		</c:if>
	</body>
</html>