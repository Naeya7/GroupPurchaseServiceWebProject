<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>GongGu</title>
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
							<h3 class="font-DH mar-top-50 "> 
							<c:if test="${category_id eq 1}">핸드폰</c:if>
							<c:if test="${category_id eq 2}">PC/노트북</c:if>
							<c:if test="${category_id eq 3}">카메라</c:if>
							<c:if test="${category_id eq 4}">웨어러블</c:if>
							<c:if test="${category_id eq 5}">태블릿</c:if>
							</h3>
						</div>
					</div>
				</div>
				<!-- 리스트 박스 -->
				<c:forEach var="catePostListDto" items="${catePostListDto}">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">
						<div class="postListBox">
							<div class="col-md-4 col-xs-4">						
								<a href="postContent.do?post_id=${catePostListDto.post_id}">
									<img class="postListImage" src="${catePostListDto.thumbnail}">
								</a>
								<input class="postList-amount-absolute" type="button" value="${catePostListDto.current_amount}개 신청"
											onclick="location='postContent.do?post_id=${catePostListDto.post_id}'">
							</div>
							<div class="col-md-8 col-xs-8">
								<div class="postListInfo">
									<h6 class="text-main-color">[${catePostListDto.product_name}]</h6>
									<h4>${catePostListDto.post_title}</h4>			
									<div class="etc text-color-g2">${catePostListDto.content}</div>
								</div>							
							</div>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			
			<!-- 검색창 -->
			<div class="row">
				<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 mar-top-20">
					<form method="post" action="catePostList.do">
						<input type="hidden" name="category_id" value="${category_id}">			
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
			
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-2 col-md-offset-5 hf font-DH text-center mar-top-20">
						<div class="row">
						<c:if test="${info.cnt gt 0}">
							<div class="col-md-2 text-center">
							<c:if test="${info.startPage gt info.pageBlock}">
								<a href="catePostList.do?category_id=${category_id}&pageNum=${info.startPage-info.pageBlock}">◀</a>
							</c:if>
							</div>
							<div class="col-md-8">
							<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">
								<c:if test="${i eq info.currentPage}">
									<span style="color: #5BC0DE;">[${i}]</span>
								</c:if>
								<c:if test="${i ne info.currentPage}">
									<a href="catePostList.do?category_id=${category_id}&pageNum=${i}">[${i}]</a>
								</c:if>
							</c:forEach>
							</div>
							<div class="col-md-2">
							<c:if test="${info.pageCount gt info.endPage}">
								<a href="catePostList.do?category_id=${category_id}&pageNum=${info.startPage+info.pageBlock}">▶</a>					
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