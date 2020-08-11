<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공구 프로젝트</title>
		<link rel="stylesheet" type="text/css" href="/MyProject/css/managerCss.css">
	</head>
	<body>	 
		<div class="row">
			<div class="col-xs-12 col-md-12 manager-nav-area">
		  		<div class="text-right mar-top-10">
			  		${sessionScope.manager_name}님 환영합니다
			  		<button type="button" class="btn btn-info" onclick="location='mamgerLogOutPro.do'">로그아웃</button>
		  		</div>
		  	</div>
		</div>
		
		<div class="row" >
			<!-- 관리자 메뉴 -->	  
			<div class="col-xs-2 col-md-2 manager-menu-area">
				<table class="table">
					<thead><tr><th scope="col">[현황 및 알림]</th></tr></thead>		 	
				  	<tbody>
				  		<tr><td><a href="starPostList.do">진행중인 제품 게시글</a></td></tr>
				  	</tbody>
				  
				  	<thead><tr><th scope="col">[등록]</th></tr></thead>		 	
				  	<tbody>
				  		<tr><td><a href="productForm.do">제품 등록</a></td></tr>
				  		<tr><td><a href="postForm.do">게시글 등록</a></td></tr>
				  	</tbody>
				  
				  	<thead><tr><th scope="col">[열람 및 관리]</th></tr></thead>		 	
				  	<tbody>
				  		<tr><td><a href="memberList.do">멤버 정보</a></td></tr>
				  		<tr><td><a href="productList.do">제품 정보</a></td></tr>
				  		<tr><td><a href="postList.do">종료된 공구 게시글</a></td></tr>
				  		<tr><td><a href="reviewList.do">리뷰 게시글 정보</a></td></tr>
				  		<tr><td><a href="applicationList.do">제품 신청 정보</a></td></tr>
				  		<tr><td><a href="paymentList.do">결제 정보</a></td></tr>
				 	</tbody>
				  
				 	 <thead><tr><th scope="col">[알림 내역]</th></tr></thead>		 	
				 	 <tbody>
				  		<tr><td><a href="notificationList.do">보낸 알림</a></td></tr>
				  	</tbody>
				 </table>
			</div>
			  	 
			<!-- 메인 콘텐츠 -->
			<div class="col-xs-10 col-md-10 manager-conent-area">
				<c:import url="${pageControl}.jsp" />
			</div>
			  
		<!-- 중앙 row 끝 -->
		</div>
	</body>
</html>