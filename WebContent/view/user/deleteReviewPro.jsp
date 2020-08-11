<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공구 프로젝트</title>
	</head>
	<body>
		<c:if test="${result eq 0}">
			<script type="text/javascript">
				<!--
				alert('리뷰 삭제에 실패하였습니다');
				history.back();
				//-->
			</script>
		</c:if>
		<c:if test="${result eq 1}">
			<script type="text/javascript">
				<!--
				alert('리뷰가 삭제되었습니다');
				//-->
			</script>
			<meta http-equiv="refresh" content="0.5; url='reviewBoard.do">
		</c:if>
	</body>
</html>