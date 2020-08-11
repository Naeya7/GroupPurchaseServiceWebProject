<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<c:if test="${result eq 0 or result eq null}">
			<script type="text/javascript">
				alert("수정 실패");
			</script>
			<meta http-equiv="refresh" content="0.5; url='productList.do">
		</c:if>
		<c:if test="${result eq 1}">
			<script type="text/javascript">
				alert("수정 성공");
			</script>
			<meta http-equiv="refresh" content="0.5; url='productList.do">
		</c:if>
	</body>
</html>

