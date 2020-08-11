<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GongGu</title>
	</head>
	<body>
		<c:if test="${result eq 0}">
			<script type="text/javascript">
				//<!--
				alert("정보수정에 실패했습니다.");
				history.back();
				//-->
			</script>
		</c:if>
		<c:if test="${result eq 1}">
			${sessionScope.nickname = nickname}
			<script type="text/javascript">
				//<!--
				//alert("정보수정이 완료되었습니다.");
				//location.href="/MyProject/myInfor.do"
				//-->
			</script>
			<c:redirect url="myInfor.do"/>
		</c:if>
		
	</body>
</html>