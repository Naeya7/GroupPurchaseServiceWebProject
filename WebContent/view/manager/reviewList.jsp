<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/MyProject/css/managerCss.css">
		<script type="text/javascript">
			$(document).ready(function(){	
				$('body').on('click', '.deleteReview', function(){
					var review_num = $(this).attr('id');
					window.document.location="deleteReviewPro.do?review_num=" + review_num;
				});		
			});
		</script>
	</head>
	<body style="min-width:1200px">
		<div class="container" style="position:relative;">
			<form name="reviewListForm" role="form">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">리뷰 번호</th>
							<th scope="col">리뷰 제목</th>
							<th scope="col">리뷰 내용</th>						
							<th scope="col">작성자</th>
							<th scope="col">게시글 제목</th>
							<th scope="col">댓글수</th>
							<th scope="col">좋아요수</th>
							<th scope="col">조회수</th>
							<th scope="col">작성일</th>
							<th scope="col">관리</th>
						</tr>	
					</thead>
					<tbody>
						<c:if test="${info.cnt eq 0}">
							<tr>
								<td> 리뷰가 없습니다 </td>
							</tr>
						</c:if>
						<c:if test="${info.cnt ne 0}">
							<c:forEach var="review" items="${reviews}">
								<tr>
									<td>${review.review_num}</td>
									<td>${review.title}</td>
									<td>${review.content}</td>
									<td>${review.nickname}</td>						
									<td>${review.post_title}</td>						
									<td>${review.replycount}</td>						
									<td>${review.likecount}</td>						
									<td>${review.readcount}</td>						
									<td>${review.upload_date}</td>	
									<td >
										<button id="${review.review_num}" type="button" class="deleteReview btn btn-light">삭제</button>
									</td>					
								</tr>
							</c:forEach>
						</c:if>				
					</tbody>
				</table>
			</form>
		</div>
		<div style="position:relative;text-align:center;">
			<c:if test="${info.cnt gt 0}">
				<c:if test="${info.startPage gt info.pageBlock}">
					<a href="reviewList.do">[◀◀]</a>
					<a href="reviewList.do?pageNum=${info.startPage-info.pageBlock}">[◀]</a>
				</c:if>
				<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">			
					<c:if test="${i eq info.currentPage}">
						<b>[${i}]</b>
					</c:if>
					<c:if test="${i ne info.currentPage}">
						<a href="reviewList.do?pageNum=${i}">[${i}]</a>
					</c:if>
				</c:forEach>
				<c:if test="${info.pageCount gt info.endPage}">
					<a href="reviewList.do?pageNum=${info.startPage + info.pageBlock}">[▶]</a>
					<a href="reviewList.do?pageNum=${info.pageCount}">[▶▶]</a>		
				</c:if>					
			</c:if>
		</div>
	</body>
</html>