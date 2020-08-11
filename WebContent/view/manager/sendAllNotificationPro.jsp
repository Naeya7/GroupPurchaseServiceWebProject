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
		<div id="${cnt}" class="cnt"></div>
		<c:if test="${cnt eq 0 or cnt eq null}">
			<script type="text/javascript">
				alert("신청한 멤버가 없습니다");
				setTimeout(function(){
					window.close();
					}, 1);
			</script>
		</c:if>
		<c:if test="${cnt ne 0 and cnt ne null}">
			<script type="text/javascript">
				var cnt = $('.cnt').attr('id');
				alert(cnt + "개의 알림 보내기 성공");
				setTimeout(function(){
					window.close();
					}, 1);
			</script>
		</c:if>
	</body>
</html>