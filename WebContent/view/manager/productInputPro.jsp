<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<c:if test="${result eq 1 }">
			<script type="text/javascript">
				alert("제품 등록 완료");
			</script>
			<meta http-equiv="refresh" content="1; url='productList.do">
		</c:if>
		<c:if test="${result ne 1 }">
			<script type="text/javascript">
				alert("제품 등록 실패");
			</script>
			<meta http-equiv="refresh" content="1; url='productForm.do">
		</c:if>
	</body>
</html>
