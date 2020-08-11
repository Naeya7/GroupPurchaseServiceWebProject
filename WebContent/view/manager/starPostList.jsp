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
	
				//가격 수정하기
				$('body').on('click', '.inputFixedPrice', function(){
					var post_id = $(this).attr('id');
					var url = "inputFixedPriceForm.do?post_id=" + post_id;
					open(url, 'inputFixedPriceForm', 'menubar=no,statusbar=no,scrollbar=no,width=500,height=300');
				});
				
				//게시글 수정
				$('body').on('click', '.modifyPost', function(){
					var post_id = $(this).attr('id');
					window.document.location = "modifyPostForm.do?post_id=" + post_id;
				});
				
				//게시글 삭제하기
				$('body').on('click', '.deletePost', function(){
					var post_id = $(this).attr('id');
					window.document.location = "deletePostPro.do?post_id=" + post_id;
				});
				
				//알림 보내기
				$('body').on('click', '.sendAllNotification', function(){
					var post_id = $(this).attr('id');
					var url = "sendAllNotificationForm.do?post_id=" + post_id;
					open(url, 'sendAllNotificationForm', 'menubar=no,statusbar=no,scrollbar=no,width=500,height=500');
				});

			});	
		</script>		
	</head>
	<body style="min-width:1200px">
		<div class="container" style="position:relative;">
			<form name="postListForm" role="form-control">
				<table class="table table-sm;">
					<thead>
						<tr>
							<th scope="col">이미지<br></th>
							<th scope="col">글번호</th>
							<th scope="col">타이틀</th>
							<th scope="col">예상가격<br></th>
							<th scope="col">모집갯수</th>
							<th scope="col">신청갯수</th>
							<th scope="col">최종수량</th>
							<th scope="col">제품명</th>
							<th scope="col">모델명</th>
							<th scope="col">작성일<br></th>
							<th scope="col">협상가격</th>
							<th scope="col">게시글상태</th>
							<th scope="col">관리<br></th>
						</tr>	
					</thead>
					<tbody>
						<c:if test="${info.cnt eq 0}">
							<tr>
								<td> 진행중인 게시글이 없습니다 </td>
							</tr>
						</c:if>
						<c:if test="${info.cnt ne 0}">
							<c:forEach var="post" items="${posts}">
								<tr>
									<td><img src="${post.thumbnail}" width="150" height="100"></td>
									<td>${post.post_id}</td>
									<td style="width:600px;">${post.post_title}</td>
									<td>${post.default_price}</td>
									<td>${post.minimum_amount}</td>
									<td>${post.current_amount}</td>
									<td>${post.payment_amount}</td>
									<td style="width:600px;">${post.product_name}</td>
									<td>${post.product_model}</td>
									<td>${post.post_time}</td>
									<td id="${post.post_id}" class="inputFixedPrice"><a href="">${post.fixed_price}</a></td>			
									<td>
										<a onclick="openPostStatus(${post.post_id})">
											<c:if test="${post.post_status eq 'A' }">모집중</c:if>
											<c:if test="${post.post_status eq 'C' }">모집완료</c:if>
											<c:if test="${post.post_status eq 'N' }">협상중</c:if>
											<c:if test="${post.post_status eq 'P' }">결제중</c:if>
											<c:if test="${post.post_status eq 'S' }">결제완료</c:if>
										</a>
									</td>			
									<td width="200" style="white-space:nowrap;">
										<button id="${post.post_id}" type="button" class="modifyPost btn btn-light">수정</button>
										<button id="${post.post_id}" type="button" class="deletePost btn btn-light">삭제</button>
										<button id="${post.post_id}" type="button" class="sendAllNotification btn btn-info">알림</button>
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
					<a href="starPostList.do">[◀◀]</a>
					<a href="starPostList.do?pageNum=${info.startPage-info.pageBlock}">[◀]</a>
				</c:if>
				<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">			
					<c:if test="${i eq info.currentPage}">
						<b>[${i}]</b>
					</c:if>
					<c:if test="${i ne info.currentPage}">
						<a href="starPostList.do?pageNum=${i}">[${i}]</a>
					</c:if>
				</c:forEach>
				<c:if test="${info.pageCount gt info.endPage}">
					<a href="starPostList.do?pageNum=${info.startPage + info.pageBlock}">[▶]</a>
					<a href="starPostList.do?pageNum=${info.pageCount}">[▶▶]</a>		
				</c:if>					
			</c:if>
		</div>
	</body>
</html>