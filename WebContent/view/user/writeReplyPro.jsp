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
		<c:if test="${resultUpdate eq 0}">
			<script type="text/javascript">
				<!-- 
				alert("댓글 작성에 실패했습니다");
				history.back();
				 -->
			</script>
		</c:if>
		<c:if test="${resultUpdate eq 1}">
			<script type="text/javascript">
				<!--
				alert("댓글 작성 완료");
				location.href="/MyProject/reviewContent.do?review_num=${review_num}";
				-->
			</script>
		</c:if>
	</body>
</html>