<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>공구 프로젝트</title>
	</head>
	<body>
		<!-- navCss 컨트롤 -->
		<input type="hidden" class="navIndex" value="2">
				
		<div class="max-width-1500 div-center">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 border-under-a">	
						<div class="col-md-12">
							<h3 class="font-DH mar-top-50 ">구매 내역</h3>
						</div>
					</div>
				</div>
				
				<!-- 내역이 없을경우 -->
				<c:if test="${payedPostDto[0].post_id eq null }">
					<div class="row">
						<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">	
							<div class="col-md-12">
								<h4 class="font-DH mar-top-50 text-color-g2">구매 내역이 없습니다. 첫 구매를 해보세요!</h4>
							</div>
						</div>
					</div>
				</c:if>
				
				<!-- 리스트 박스 -->
				<c:forEach var="payedPostDto" items="${payedPostDto}">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">
						<div class="postListBox">
							<div class="col-md-4 col-xs-4">
								<a href="postContent.do?post_id=${payedPostDto.post_id}">
									<img class="postListImage" src="${payedPostDto.thumbnail}">
								</a>
								<input class="postList-amount-absolute" type="button" value="${payedPostDto.current_amount}명 신청"
									 			onclick="location='postContent.do?post_id=${payedPostDto.post_id}'">
							</div>
							<div class="col-md-8 col-xs-8">
								<div class="postListInfo">
									<h6 class="text-main-color">[${payedPostDto.product_name}] [${payedPostDto.total_price}원 결제]</h6>
									<h4>${payedPostDto.post_title}</h4>	
									<h6 class="text-main-color"></h6>		
									<div class="etc text-color-g2">${payedPostDto.content}</div>
									<c:if test="${payedPostDto.review_writable eq 0}">
										<input type="button" value="리뷰쓰기" class="btn btn-default" onclick="location='writeReview.do?payment_id=${payedPostDto.payment_id}'">
									</c:if>
								</div>							
							</div>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<!-- 페이지 넘버 -->
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-2 col-md-offset-5 hf font-DH text-center mar-top-20">
						<div class="row">
						<c:if test="${info.cnt gt 0}">
							<div class="col-md-2 text-center">
							<c:if test="${info.startPage gt info.pageBlock}">
								<a href="myPayedList.do?pageNum=${info.startPage-info.pageBlock}">◀</a>
							</c:if>
							</div>
							<div class="col-md-8">
							<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">
								<c:if test="${i eq info.currentPage}">
									<span style="color: #5BC0DE;">[${i}]</span>
								</c:if>
								<c:if test="${i ne info.currentPage}">
									<a href="myPayedList.do?pageNum=${i}">[${i}]</a>
								</c:if>
							</c:forEach>
							</div>
							<div class="col-md-2">
							<c:if test="${info.pageCount gt info.endPage}">
								<a href="myPayedList.do?pageNum=${info.startPage+info.pageBlock}">▶</a>	
							</c:if>
							</div>
						</c:if>
						</div>
					</div>
				</div>
			</div>	
		</div>			
	</body>
</html>