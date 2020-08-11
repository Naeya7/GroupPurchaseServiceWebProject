<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			body {
				min-width: 1400;
			}
		</style>
	</head>
	<body>
		<!-- 네이게이션 -->
		<div class="container-fluid">
			<div class="row">
				<c:import url="mainNavPage.jsp" />
			</div>
		</div>
		
		<!-- 메인 콘텐츠 -->
		<div class="container-fluid">
			<div class="row">
				<c:import url="${pageControl}.jsp" />
			</div>
		</div>	
			
			
		<!-- 테스트용 아래여백 -->
		<div class="height-300 mar-top-50"></div>
		
		
		<!-- 푸터 페이지 -->
		<div class="container-fluid">
			<div class="row">
				<c:import url="footer.jsp" />
			</div>
		</div>
			
	</body>
</html>