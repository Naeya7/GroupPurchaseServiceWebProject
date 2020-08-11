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
				
				//수정 버튼 이벤트
				$('body').on('click', '.modifyProduct', function(){
					var product_id = $(this).attr('id');
					window.document.location="modifyProductForm.do?product_id=" + product_id;
				});
				
				//삭제 버튼 이벤트
				$('body').on('click', '.deleteProduct', function(){
					var product_id = $(this).attr('id');
					window.document.location="deleteProductPro.do?product_id=" + product_id;
				});			
			});
		</script>		
	</head>
	<body style="min-width:1200px">
		<div class="container" style="position:relative;">
			<form name="productListForm" role="form">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">이미지</th>
							<th scope="col">제품 아이디</th>
							<th scope="col">제품 이름</th>
							<th scope="col">모델 이름</th>
							<th scope="col">카테고리</th>
							<th scope="col">관리</th>
						</tr>	
					</thead>
					<tbody>
						<c:if test="${info.cnt eq 0}">
							<tr>
								<td> 등록된 제품이 없습니다 </td>
							</tr>
						</c:if>
						<c:if test="${info.cnt ne 0}">
							<c:forEach var="product" items="${products}">
								<tr>
									<td><img src="${product.image}" width="150" height="100"></td>
									<td>${product.product_id}</td>
									<td>${product.product_name}</td>
									<td>${product.product_model}</td>
									<td>
										<c:if test="${product.category_id eq 1}">핸드폰</c:if>
										<c:if test="${product.category_id eq 2}">PC/노트북</c:if>
										<c:if test="${product.category_id eq 3}">카메라</c:if>
										<c:if test="${product.category_id eq 4}">웨어러블</c:if>
										<c:if test="${product.category_id eq 5}">태블릿</c:if>
									</td>
									<td>
										<button id="${product.product_id}" type="button" class="modifyProduct btn btn-light">수정</button>
										<button id="${product.product_id}" type="button" class="deleteProduct btn btn-light">삭제</button>
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
					<a href="productList.do">[◀◀]</a>
					<a href="productList.do?pageNum=${info.startPage-info.pageBlock}">[◀]</a>
				</c:if>
				<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">			
					<c:if test="${i eq info.currentPage}">
						<b>[${i}]</b>
					</c:if>
					<c:if test="${i ne info.currentPage}">
						<a href="productList.do?pageNum=${i}">[${i}]</a>
					</c:if>
				</c:forEach>
				<c:if test="${info.pageCount gt info.endPage}">
					<a href="productList.do?pageNum=${info.startPage + info.pageBlock}">[▶]</a>
					<a href="productList.do?pageNum=${info.pageCount}">[▶▶]</a>		
				</c:if>					
			</c:if>
		</div>
	</body>
</html>