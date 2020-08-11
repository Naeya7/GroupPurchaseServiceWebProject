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
				alert("입력 정보가 올바르지 않습니다");
			</script>
			<meta http-equiv="refresh" content="1; url='managerLogInForm.do">
		</c:if>
		<c:if test="${result eq 1}">
			<c:redirect url="starPostList.do"></c:redirect>
		</c:if>
	</body>
</html>

