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
		<div id="default_price">${default_price}</div>
		크롤링 ${default_price}
		결과 ${result}
		
		<c:if test="${result eq 0 or result eq null}">
			<script type="text/javascript">
				alert("해당 모델로는 크롤링이 불가능 합니다");
				setTimeout(function(){
					window.close();
					}, 1);
			</script>
		</c:if>
		<c:if test="${result eq 1}">
			<script type="text/javascript">
				alert("크롱링 성공");
				opener.document.managerPostForm.default_price.value = $('#default_price').text();
				setTimeout(function(){
					window.close();
					}, 1)
			</script>
		</c:if>
		
	</body>
</html>