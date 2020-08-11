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
		writeReview Pro
		<c:if test="${result eq 0}">
			<script type="text/javascript">
				<!-- 
				alert("리뷰 작성에 실패했습니다");
				history.back();
				 -->
			</script>
		</c:if>
		<c:if test="${result eq 1}">
			<c:if test="${resultUpdate eq 0}">
				<script type="text/javascript">
					<!--
					alert("리뷰 작성에 실패했습니다");
					location.href="/MyProject/reviewBoard.do";
					-->
				</script>
			</c:if>
			<c:if test="${resultUpdate eq 1}">
				<script type="text/javascript">
					<!--
					alert("리뷰 작성 완료");
					location.href="/MyProject/reviewBoard.do";
					-->
				</script>
			</c:if>
		</c:if>
	</body>
</html>