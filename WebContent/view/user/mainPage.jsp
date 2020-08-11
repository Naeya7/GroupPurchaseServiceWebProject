<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAIN PAGE</title>
	</head>
	<body>
		
		<!-- 상단 이미지 -->
		<div class="container-fluid">
			<div class="row">
				<div class="cropping">
					<img src="/MyProject/img/tamplate1.jpg">
				</div>
			</div>
		</div>
		
		<!-- 모듈1, 마감임박 게시글 5개 -->
		<div class="container-fluid">
			<div class="row max-width-1300 div-center">
				<c:import url="main-module-one.jsp" />
			</div>
		</div>
		
		<!-- 경계 이미지 -->
		<div class="container-fluid">
			<div class="row max-width-1300 div-center">
				<c:import url="cotent-test.jsp" />
			</div>
		</div>
		
		<!-- 모듈2, 최근 게시글 6개 -->
		<div class="container-fluid">
			<div class="row max-width-1300 div-center">
				<c:import url="main-module-two.jsp" />
			</div>
		</div>
		
	</body>
</html>