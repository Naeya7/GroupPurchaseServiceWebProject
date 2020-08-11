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
		<c:if test="${result eq 0 or result eq null}">
			<script type="text/javascript">
				alert("변경에 실패했습니다");
			</script>
			<meta http-equiv="refresh" content="0.5; url='memberList.do">
		</c:if>
		<c:if test="${result eq 1}">
			<script type="text/javascript">
				alert("변경에 성공했습니다");
			</script>
			<meta http-equiv="refresh" content="0.5; url='memberList.do">
		</c:if>
	</body>
</html>