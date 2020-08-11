<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공구 푸로젝투</title>
	</head>
	<body>
		모디파이리뷰프로
		<c:if test="${result eq 0}">
			<script type="text/javascript">
				<!--
				alert("리뷰수정에 실패했습니다.");
				history.back();
				//-->
			</script>
		</c:if>
		<c:if test="${result eq 1}">
			<script type="text/javascript">
				<!--
				alert("리뷰수정이 완료되었습니다.");
				location.href="/MyProject/reviewContent.do?review_num=${review_num}"
				//-->
			</script>
		</c:if>
	</body>
</html>