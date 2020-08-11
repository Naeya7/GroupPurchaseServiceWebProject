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
				//수정
				$('body').on('click', '.modifyReview', function(){
					var review_num = $(this).attr('id');
					window.document.location="modifyReview.do?review_num=" + review_num;
				});	
				
				//리뷰삭제
				$('body').on('click', '.deleteReview', function(){
					var con_test = confirm("삭제하시겠습니까 ?");
					if(con_test == true){
						var review_num = $(this).attr('id');
						window.document.location="deleteUserReviewPro.do?review_num=" + review_num;	
					}
				});	
						
				//추천
				$('body').on('click', '.likeCountPro', function(){
					
					//세선 받기
					if('${sessionScope.member_id}' == ''){
						alert("로그인을 해주세요");
						return false;
					}
					
					var review_num = $(this).attr('id');
					window.document.location="likeCountPro.do?review_num=" + review_num;
				});	
				
				
				
				//리플삭제
				$('body').on('click', '.deleteReply', function(){
					var reply = confirm("삭제하시겠습니까 ?");
					if( reply == true ) {
					var reply_num = $(this).attr('id');
					var review_num = $(this).parent().attr('id');
					window.document.location="deleteReplyPro.do?reply_num=" + reply_num +"&review_num=" + review_num;
					}
				});		
				
				
			});
			
			function replyCheck(){
				if(!replyForm.content.value){
					alert("댓글을 입력하세요");
					replyForm.content.focus();
					return false;
				}
			}
			
			
		</script>
	</head>
	<body>
		<!-- 상단 이미지 -->
		<div class="container-fluid">
			<div class="row">
				<div class="cropping" style="height: 100px;">
					<img src="/MyProject/img/tamplate3.jpg">
				</div>
			</div>
		</div>	

		<!-- 콘텐트 테스트 -->
		<div class="container-fluid font-DH">	
		
			<div class="row max-width-1300 div-center">
				<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2 border-under-a mar-top-50">	
					<div class="col-md-12">
						<h3 class="font-DH mar-top-50 text-center">${reviewContentDto.title}</h3>
						<div class="col-md-6 col-xs-6 mar-bottom-10 text-color-g2">
							<img src="img/profile.jpg" class="myPageProfile height-30 width-30">
							${reviewContentDto.nickname} 
						</div>
						<div class="col-md-6 col-xs-6 text-right mar-top-10">
							<img src="img/like.png" width="15px" height="15px" class="likeCountPro" id="${reviewContentDto.review_num}">
							<span>${reviewContentDto.likecount}</span>							
							<img src="img/reply.png" width="15px" height="15px">
							<span>${reviewContentDto.replycount}</span>								
						</div>
					</div>
				</div>
			</div>
			
			<!-- 게시글 콘텐트 -->
			<div class="row max-width-1300 div-center">
				<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2 mar-top-50">
					<div class="font-GD">
						${reviewContentDto.content}
						<hr class="mar-top-50">
					</div>
					<div class="text-right">
						<c:if test= "${sessionScope.member_id eq reviewContentDto.member_id}"> 
							<input class="modifyReview btn btn-default" id="${reviewContentDto.review_num}" type="button" value="수정하기">			
						</c:if>
						<c:if test= "${sessionScope.member_id eq reviewContentDto.member_id}"> 
							<input class="deleteReview btn btn-danger" id="${reviewContentDto.review_num}"  type="button" value="삭제하기">
						</c:if>	
						<input class="likeCountPro btn btn-info" id="${reviewContentDto.review_num}"  type="button" value="추천하기">
					</div>
				</div>
			</div>
	
			
			<!-- 댓글 경계 이미지 -->
			<div class="row max-width-1300 div-center ">
				<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2">
					<div class="cropping mar-top-100" style="height: 50px;">
						<img src="/MyProject/img/tamplate3.jpg">
					</div>
				</div>
			</div>
			
			<!-- 리플 작성 폼 -->
			<div class="row max-width-1300 div-center">
			<c:if test= "${sessionScope.member_id ne null }"> 
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 border-under-a">	
						<div class="col-md-12">
							<h3 class="font-DH mar-top-100 ">댓글 쓰기</h3>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 mar-top-10">
						<form method="post" action="writeReplyPro.do" name="replyForm" onsubmit="return replyCheck()">
							<input type="hidden" name="review_num" value="${reviewContentDto.review_num}">
							<div class="form-group">
								<textarea class="form-cotrol" rows="6" cols="95" name="content"></textarea>
							</div>
							<div class="form-group text-right">
								<input type="submit" class="btn btn-default" value="작성">
							</div>
						</form>
					</div>
				</div>	
			</c:if>
				<!-- 리스트 박스 -->
				<c:forEach var="replyContentDto" items="${replyContentDtos}">
					<div class="row">
						<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">
							<div class="col-md-12 col-xs-12 mar-top-20">
								<h5>${replyContentDto.nickname}
									<span style="text-align: right;"><fmt:formatDate value="${replyContentDto.upload_date}" type="both" pattern="MM-dd-hh:mm"/> </span>
								</h5>
								<div id="${replyContentDto.review_num}" class="mar-top-10">
									<h5 class="text-color-g2">${replyContentDto.content}</h5>	
								</div>
								
								<c:if test= "${sessionScope.member_id eq replyContentDto.member_id}">
									<div id="${replyContentDto.review_num}">
										<input id="${replyContentDto.reply_num}" type="button" value="삭제" class="btn btn-default deleteReply">
									</div>
								</c:if>
								<hr>
							</div>	
						</div>
					</div>
				</c:forEach>			
			</div>								
		</div>	
	</body>
</html>