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
				alert("게시글 등록에 실패했습니다");
			</script>
			<meta http-equiv="refresh" content="0.5; url='starPostList.do">
		</c:if>
		<c:if test="${result eq 1}">
			<script type="text/javascript">
				alert("게시글 등록 성공");
			</script>
			<meta http-equiv="refresh" content="0.5; url='starPostList.do">
		</c:if>
	</body>
</html>

