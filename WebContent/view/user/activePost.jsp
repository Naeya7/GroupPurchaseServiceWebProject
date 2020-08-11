<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>공구 프로젝트</title>
	</head>
	<body>
	
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 border-under-a">	
					<div class="col-md-12">
						<h3 class="font-DH mar-top-50 ">전체보기</h3>
					</div>
				</div>
			</div>
			<!-- 리스트 박스 -->
			<c:forEach var="payablePostDto" items="${payablePostDto}">
			<div class="row">
				<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">
					<div class="postListBox">
						<div class="col-md-4 col-xs-4">
							<img class="postListImage" src="${payablePostDto.thumbnail}">
							<input class="postList-amount-absolute" type="button" value="${payablePostDto.current_amount}명 신청">
						</div>
						<div class="col-md-8 col-xs-8">
							<div class="postListInfo">
								<h6 class="text-main-color">[${payablePostDto.product_name}]</h6>
								<h4>${payablePostDto.post_title}</h4>			
								<div class="text-color-g2">${payablePostDto.content}</div>
							</div>							
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
		<!-- 컨테이너  -->
		</div>
	</body>
</html>