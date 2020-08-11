<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공구 프로젝투</title>
	</head>
	<body>
		<c:if test="${result eq -1}">
			<script type="text/javascript">
				alert("진행 수량보다 초과한 수량입니다");
				history.back();
			</script>
		</c:if>
		<c:if test="${result eq 0}">
			<script type="text/javascript">
				alert("신청에 실패하셨습니다");
				history.back();
			</script>
		</c:if>
		<c:if test="${result eq 1}">
			<c:if test="${resultUpdate eq 0}">
				<script type="text/javascript">
					alert("신청에 실패하셨습니다");
					history.back();
				</script>
			</c:if>
			<c:if test="${resultUpdate eq 1}">
				<script type="text/javascript">
					alert('신청이 완료되었습니다');	
					location.href="/MyProject/notPayablePost.do";
				</script>
			</c:if>
		</c:if>
	</body>
</html>