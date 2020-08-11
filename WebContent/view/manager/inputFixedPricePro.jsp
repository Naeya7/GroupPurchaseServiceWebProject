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
				alert("협상 가격 변경 실패");
				setTimeout(function(){
					window.close();
					}, 1);
			</script>
		</c:if>
		<c:if test="${result eq 1}">
			<script type="text/javascript">
				alert("협상 가격 변경 성공");
				setTimeout(function(){
					opener.document.location="starPostList.do";
					window.close();
					}, 1);
			</script>
		</c:if>
	</body>
</html>