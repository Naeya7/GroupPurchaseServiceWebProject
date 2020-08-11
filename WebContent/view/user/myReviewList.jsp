<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>   

<!DOCTYPE html>


<html>
	<head>
		<meta charset="UTF-8">
		<title>공구 프로젝트</title>
		<script type="text/javascript">
			$(document).ready(function(){	
				$('body').on('click', '.reviewContent', function(){
					var review_num = $(this).attr('id');
					window.document.location="reviewContent.do?review_num=" + review_num;
				});		
			});
		</script>
	</head>
	<body>
		<!-- navCss 컨트롤 -->
		<input type="hidden" class="navIndex" value="3">
		
		<div class="max-width-1500 div-center">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 border-under-a">	
						<div class="col-md-12">
							<h3 class="font-DH mar-top-50 ">내가 쓴 리뷰</h3>
						</div>
					</div>
				</div>
				
			<!-- 내역이 없을경우 -->
			<c:if test="${reviewDto[0].review_num eq null }">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">	
						<div class="col-md-12">
							<h4 class="font-DH mar-top-50 text-color-g2">작성한 리뷰가 없습니다. 리뷰를 작성해주세요!</h4>
						</div>
					</div>
				</div>
			</c:if>	
				
				
				<!-- 리스트 박스 -->
				<c:forEach var="reviewDto" items="${reviewDto}">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">
						<div class="col-md-12 col-xs-12">
							<div id="${reviewDto.review_num}" class="reivewListBox reviewContent">
							<h4 class="mar-top-20">${reviewDto.title}</h4>
							<h5 class="etcOneLine text-color-g2">${reviewDto.content}</h5>
								<div class="text-right">
									<img src="img/like.png" width="15px" height="15px">
									<span>${reviewDto.likecount}</span>
									<img src="img/reply.png" width="15px" height="15px">
									<span>${reviewDto.replycount}</span>
								</div>
							</div>
						</div>	
					</div>
				</div>
				</c:forEach>
			</div>
		</div>		
	</body>
</html>