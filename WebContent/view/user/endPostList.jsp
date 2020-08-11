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
			<div class="row">
				<div class="cropping" style="height: 100px;">
					<img src="/MyProject/img/tamplate2.jpg">
				</div>
			</div>
		</div>
		
		<div class="max-width-1500 div-center">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 border-under-a">	
						<div class="col-md-12">
							<h3 class="font-DH mar-top-50 ">판매종료</h3>
						</div>
					</div>
				</div>
				
				<!-- 리스트 박스 -->
				<c:if test="${info.cnt eq 0}">
					<br>
					<div style="text-align: center">
						종료된 게시글이 없습니다
					</div>
				</c:if>
				<c:if test="${info.cnt ne 0}">
				<c:forEach var="post" items="${posts}">
				
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">
						<div class="postListBox">
							<div class="col-md-4 col-xs-4">
								<div class="div-center">
									<a href="postContent.do?post_id=${post.post_id}">
										<img class="postListImage" style="opacity:0.2" src="${post.thumbnail}">
									</a>
									<div style="position:20px 30px; text-align:center;">판매가 종료된 상품입니다</div>									
								</div>
								<input class="postList-amount-absolute" type="button" value="${post.current_amount}명 신청"
									 			onclick="location='postContent.do?post_id=${post.post_id}'">
							</div>
							<div class="col-md-8 col-xs-8">
								<div class="postListInfo">
									<h6 class="text-main-color">[${post.product_name}]</h6>
									<h4>${post.post_title}</h4>			
									<div class="etc text-color-g2">${post.fixed_price}원</div>
									<div class="etc text-color-g2">${post.post_time}</div>
								</div>							
							</div>
						</div>
					</div>
				</div>
				</c:forEach>
				</c:if>
			</div>
			
			<!-- 검색창 -->
			<div class="row">
				<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 mar-top-20">
					<form method="post" action="endPostList.do">
						<div class="col-md-2 col-xs-2 pd-right-0">
				        	<select class="form-control" name="select">
								<option value="post_title">제목</option>
								<option value="content">내용</option>
							</select>
						</div>
						<div class="col-md-8 col-xs-8">
				           	<input type="text" class="form-control" name="keyword" value="${keyword}">
						</div>				
						<div class="col-md-2 col-xs-2">
							<button type="submit" class="btn btn-info width-100p">검색</button>	
						</div>	
					</form>	
				</div>
			</div>
			
			<!-- 페이지 넘버 -->
			<div style="position:relative;text-align:center;">
					<c:if test="${info.cnt gt 0}">
						<c:if test="${info.startPage gt info.pageBlock}">
							<a href="endPostList.do">[◀◀]</a>
							<a href="endPostList.do?pageNum=${info.startPage-info.pageBlock}">[◀]</a>
						</c:if>
						<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">			
							<c:if test="${i eq info.currentPage}">
								<b>[${i}]</b>
							</c:if>
							<c:if test="${i ne info.currentPage}">
								<a href="endPostList.do?pageNum=${i}">[${i}]</a>
							</c:if>
						</c:forEach>
						<c:if test="${info.pageCount gt info.endPage}">
							<a href="endPostList.do?pageNum=${info.startPage + info.pageBlock}">[▶]</a>
							<a href="endPostList.do?pageNum=${info.pageCount}">[▶▶]</a>		
						</c:if>					
					</c:if>
				</div>
		</div>
	</body>
</html>