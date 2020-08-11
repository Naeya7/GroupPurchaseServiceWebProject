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
				alert("댓글 삭제에 실패했습니다");
				history.back();
				 -->
			</script>
		</c:if>
		<c:if test="${result eq 1}">
			<c:if test="${resultUpdate eq 0}">
				<script type="text/javascript">
					<!--
					alert('댓글 삭제에 실패하였습니다');
					history.back();
					//-->
				</script>
			</c:if>
			<c:if test="${resultUpdate eq 1}">
				<script type="text/javascript">
				<!--
				alert("댓글 삭제 완료");
				location.href="/MyProject/reviewContent.do?review_num=${review_num}";
				-->
				</script>
			</c:if>
		</c:if>
	</body>
</html>