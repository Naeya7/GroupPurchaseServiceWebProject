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
	
		<!-- 최신 게시물 -->
		<div class="container-fluid">
			<!-- 1열 -->
			<div class="row max-width-1300 div-center">
				<div class="col-md-10 col-md-offset-1 col-xs-10 col-xs-offset-1 mar-top-20">
					<div class="row">
						<div class="col-md-4 col-xs-4">
							<div class="div-center max-width-400">
								<h3 class="font-DH mar-bottom-20">최근에 등록된 공동구매 상품</h3>
							</div>
						</div>
					</div>
					<div class="row">
						<c:forEach var="mainPagePostDto" items="${mainPagePostDto}">
						<div class="col-md-2 col-xs-2">
							<div class="div-center max-width-400">
								
								<div class="recentlyPostImag">
									<a href="postContent.do?post_id=${mainPagePostDto.post_id}">		
										<img src="${mainPagePostDto.image}" class="recentlyPostImag">	
									</a>		
								</div>					
								<div class="font-GD mar-top-5"> 
									<span class="mainProdName etcOneLine">[${mainPagePostDto.product_name}]<br></span>
									<span class="mainPostMount">${mainPagePostDto.current_amount}개 <span style="color: grey;">신청</span></span>
								</div>				
							</div>			
						</div>
						</c:forEach>		
					</div>	
				</div>				
			</div>	
		</div>
	</body>
</html>