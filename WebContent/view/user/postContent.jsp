<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript">
			$(document).ready(function(){								
				$('body').on('click', '.applyBtn', function(){
					
					//세선 받기
					if('${sessionScope.member_id}' == ''){
						alert("로그인을 해주세요");
						return false;
					}					
					
					var post_id = $('input[name=post_id]').val();
					var amount = $('input[name=amount]').val();
					
					if(amount == ""|| amount == 0){
						alert("신청 개수를 입력해 주세요");
					}else{
						window.document.location="applyPro.do?post_id=" + post_id + "&amount=" + amount;	
					}
					
				});		
			});
		</script>
	</head>
	<body>
	
		<!-- 상단 이미지 -->
		<div class="container-fluid">
			<div class="row">
				<div class="cropping" style="height: 100px;">
					<img src="/MyProject/img/tamplate2.jpg">
				</div>
			</div>
		</div>
		<!-- 콘텐트 테스트 -->
		<div class="container-fluid font-DH">	
			
			<!-- 게시글 -->
			<div class="row max-width-1300 div-center">
				<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2 mar-top-50">	
					<div class="row postContentTop">
						<div class="col-md-8 col-xs-8 ">	
							<img class="postContentImg" src="${postContentDto.image }">
						</div>
						<div class="col-md-4 col-xs-4">
							<c:if test="${postContentDto.post_status ne 'A'}">
								<h3 class="mar-top-10">${postContentDto.current_amount}개 모집완료</h3>		
							</c:if>
							<c:if test="${postContentDto.post_status eq 'A'}">
								<h3 class="mar-top-10">${postContentDto.current_amount}개 신청중</h3>		
							</c:if>
							<hr class="hr-color-main">
							<div>
								<h4>${postContentDto.product_name}</h4>
								<hr>
							</div>
	
							<div class="mar-top-20">
								<h6 class="text-color-g2">인터넷 최저가</h6>
								<h4>${postContentDto.default_price}원</h4>
								<hr>
							</div>
							<div class="mar-top-20">
								<h6 class="text-color-g2">상품 모델명(${postContentDto.category_name})</h6>
								<h4>${postContentDto.product_model}</h4>
								<hr>
							</div>
							<c:if test="${postContentDto.post_status ne 'A'}">
									<button type="button" class="btn btn-info width-100p">마감된 제품</button>
							</c:if>
							<c:if test="${postContentDto.post_status eq 'A'}">
							<div class="row">
								<div class="col-md-4">
									<input type="text" class="form-control" name="amount" placeholder="개수"
												onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
									<!-- 파라미터 값 -->
									<input type="hidden" name="post_id" value="${postContentDto.post_id}">
								</div>
								<div class="col-md-8">
									<button type="button" class="applyBtn btn btn-info width-100p">신청하기</button>
								</div>
							</div>
							</c:if>
						</div>
					</div>
				</div>				
			</div>
			
			<!-- 탭 패널-->
			<div class="row max-width-1300 div-center">
				<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2 mar-top-50">
					<div class="row">
						<ul class="nav nav-tabs nav-justified">
						  <li class="nav-item">
						    <a class="nav-link active" data-toggle="tab" href="#home">상품상세</a>
						  </li>
						  <li class="nav-item">
						    <a class="nav-link" data-toggle="tab" href="#review">리뷰보기</a>
						  </li>
						</ul>
					</div>
				</div>
			</div>
			
			<!-- 패널 콘텐트 -->
			<div class="tab-content">
				<div class="tab-pane container active" id="home"><!-- 상품상세 -->		
					<!-- 리뷰 작성 폼 -->
					<div class="row max-width-1300 div-center">
						<div class="row">
							<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2 border-under-a">	
								<div class="col-md-12 com-xs-12">
									<h3 class="font-DH mar-top-100 text-center ">${postContentDto.post_title}</h3>
								</div>
							</div>
						</div>
					</div>
					<!-- 게시글 콘텐트 -->
					<div class="row max-width-1300 div-center">
						<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2 mar-top-50">
							<div class="col-md-12 col-xs-12">
								<div class="font-GD">
									${postContentDto.content}
								</div>
							</div>
						</div>
					</div>	
				</div>	
				<div class="tab-pane container fade" id="review"><!-- 리뷰보기 -->		
					<!-- 리뷰 리스트 3개 -->
					<c:if test="${postContentReview[0].title eq null}">
					<div class="row">
						<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">
							<div class="col-md-12">
								<h4 class="font-DH mar-top-50 text-color-g2 text-center">리뷰가 없습니다. 첫 리뷰를 남겨주세요!</h4>
							</div>
						</div>
					</div>
					</c:if>
					<c:if test="${postContentReview[0].title ne null}">	
					<div class="row max-width-1300 div-center">
						<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2 border-under-a">	
							<h3 class="font-DH mar-top-50 ">인기 리뷰 보기</h3>
						</div>
						<!-- 리뷰박스-->
						<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2">	
							<c:forEach var="postContentReview" items="${postContentReview}">
							<div class="reivewListBox">
								<h4 class="mar-top-20 hf">
								<a href="reviewContent.do?review_num=${postContentReview.review_num}">${postContentReview.title}</a>
								</h4>
								
								<h5 class="etcOneLine text-color-g2">${postContentReview.content}</h5>
								<div class="text-right">
									<img src="img/like.png" width="15px" height="15px">
									<span>${postContentReview.likecount}</span>
									<img src="img/reply.png" width="15px" height="15px">
									<span>${postContentReview.replycount}</span>
								</div>
							</div>
							</c:forEach>
						</div>				
					</div>	
					</c:if>	
				</div>
			</div>		
		<!-- 컨테이너 -->	
		</div>
	</body>
</html>