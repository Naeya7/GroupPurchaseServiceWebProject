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
	
		<!-- 상단 이미지 -->
		<div class="container-fluid">
	
			<!-- 네비게이션 -->
			<div class="row">
				<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2 mar-top-50 font-GD">
					<!-- 고정 정보 -->
					<div class="col-md-8 col-xs-8">
						<h2>${sessionScope.nickname}님<span style="font-size: 20px;"> 마이페이지</span></h2>
						<h6 class="text-color-g2">개인 회원</h6>	
					</div>
					<!-- 이미지 -->
					<div class="col-md-4 col-xs-4">
						<img src="img/profile.jpg" class="myPageProfile"><br>
					</div>
				</div>
			</div>		
			<div class="row">
				<div class="nav-bar height-30 hf font-SP mar-top-40">			
					<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2">
						<div class="col-md-12 com-xs-12">
							<ul class="list-inline nav-myPage">
								<li><a href="payablePost.do" id="0">결제 가능</a></li>
								<li><a href="notPayablePost.do" id="1">신청 제품</a></li>
								<li><a href="myPayedList.do" id="2">구매내역</a></li>
								<li><a href="myReviewList.do" id="3">내가 쓴 리뷰</a></li>
								<li><a href="myReplyList.do" id="4">내가 쓴 댓글</a></li>
								<li><a href="myInfor.do" id="5">내정보</a></li>
							</ul>
						</div>	
					</div>	
				</div>
			</div>			
		<!-- 컨테이너 -->	
		</div>
	</body>
</html>