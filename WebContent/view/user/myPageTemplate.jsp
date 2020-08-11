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
		<script type="text/javascript">
			$(document).ready(function(){	
				var navIndex = $('.navIndex').val();
				var li = $('.nav-myPage').children();
				li.eq(navIndex).children().css('color', '#5BC0DE');
				
			});
		</script>
	</head>
	<body>
		<!-- 네이게이션 -->
		<div class="container-fluid border-bottom-g4">
			<div class="row">
				<c:import url="mainNavPage.jsp" />
			</div>
		</div>
		
		<!-- 마이페이지 네비게이션 -->
		<div class="container-fluid">
			<div class="row max-width-1500 div-center">
				<c:import url="myPageNavPage.jsp" />
			</div>
		</div>
		
		<hr>
				
		<!-- 마이페이지 콘텐트 -->
		<div class="container-fluid">
			<div class="row max-width-1500 div-center">
				<c:import url="${myPageControl}.jsp" />
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