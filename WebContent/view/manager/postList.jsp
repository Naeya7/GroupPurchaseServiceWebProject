<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/MyProject/css/managerCss.css">
	</head>
	<body style="min-width:1200px">
		<div class="container" style="position:relative;">
		<form name="postListForm" role="form-control">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">이미지</th>
						<th scope="col">게시글 아이디</th>
						<th scope="col">게시글 타이틀</th>
						<th scope="col">최종 가격</th>
						<th scope="col">최종 수량</th>
						<th scope="col">제품 이름</th>
						<th scope="col">제품 모델</th>
						<th scope="col">작성일</th>
					</tr>	
				</thead>
				<tbody>
					<c:if test="${info.cnt eq 0}">
						<tr>
							<td> 종료된 게시글이 없습니다 </td>
						</tr>
					</c:if>
					<c:if test="${info.cnt ne 0}">
						<c:forEach var="post" items="${posts}">
							<tr>
								<td><img src="${post.thumbnail}" width="150" height="100"></td>
								<td>${post.post_id}</td>
								<td>${post.post_title}</td>
								<td>${post.fixed_price}</td>
								<td>${post.payment_amount}</td>
								<td>${post.product_name}</td>
								<td>${post.product_model}</td>
								<td>${post.post_time}</td>			
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
					<a href="postList.do">[◀◀]</a>
					<a href="postList.do?pageNum=${info.startPage-info.pageBlock}">[◀]</a>
				</c:if>
				<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">			
					<c:if test="${i eq info.currentPage}">
						<b>[${i}]</b>
					</c:if>
					<c:if test="${i ne info.currentPage}">
						<a href="postList.do?pageNum=${i}">[${i}]</a>
					</c:if>
				</c:forEach>
				<c:if test="${info.pageCount gt info.endPage}">
					<a href="postList.do?pageNum=${info.startPage + info.pageBlock}">[▶]</a>
					<a href="postList.do?pageNum=${info.pageCount}">[▶▶]</a>		
				</c:if>					
			</c:if>
		</div>
	</body>
</html>