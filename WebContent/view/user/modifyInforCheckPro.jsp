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
		<c:if test="${result eq 0}">
			<script type="text/javascript">
				alert("비밀번호가 올바르지 않습니다");
				history.back();
			</script>
		</c:if>
		
		<c:if test="${result eq 1}">
		<script type="text/javascript">
			location.href="/MyProject/modifyInfor.do";
		</script>
		</c:if>
	</body>
</html>