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
				$('table').on('click', '.searchProduct', function(){
					var pickBtn = $(this);
					var tr = pickBtn.parent().parent();
					var td = tr.children();
					
					opener.document.managerPostForm.product_id.value = td.eq(1).text();
					opener.document.managerPostForm.product_name.value = td.eq(2).text();
					opener.document.managerPostForm.product_model.value = td.eq(3).text();
					
					if(pickBtn.attr('id') == 1 ){
						opener.document.managerPostForm.category_name.value = '핸드폰';
					}
					if(pickBtn.attr('id') == 2 ){
						opener.document.managerPostForm.category_name.value = 'PC/노트북';
					}
					if(pickBtn.attr('id') == 3 ){
						opener.document.managerPostForm.category_name.value = '카메라';
					}
					if(pickBtn.attr('id') == 4 ){
						opener.document.managerPostForm.category_name.value = '웨어러블';
					}
					if(pickBtn.attr('id') == 5 ){
						opener.document.managerPostForm.category_name.value = '태블릿';
					}
					
					var crawlBtn = opener.document.managerPostForm.crawlingPrice;
					if(crawlBtn != null){
						crawlBtn.removeAttribute('disabled');
					}
					
				/*
					 <div class="form-group text-right">
                     <button type="button" class="crawlingPrice btn btn-info" name="crawlingPrice" disabled="disabled">크롤링</button>
                 </div>
				*/
					//opener.document.managerPostForm.crawlingPrice.removeAttribute('disabled');
							
					window.close();
				});
			});
		</script>			
	</head>
	<body>
	<!-- searchProduct -->
		<article class="container">
            <div class="page-header">
            	<div class="row">
	                <div class="col-md-6">
	                <h3>검색페이지</h3>
	                </div>
                </div>
            </div>
            <div class="row">
	            <div class="col-md-12">
	                <form name="searchProductForm" role="form" action="serchProductForm.do">
	                    <div class="form-group">
	                        <label for="inputId">검색하기</label>
	                        <input type="text" class="form-control" name="product_name" placeholder="제품명을 입력하세요">
	                    </div>
	                    <div class="form-group text-right">
	                        <button type="submit" class="btn btn-light">검색<i class="fa fa-check spaceLeft"></i></button>
	                    </div>
	        			<!-- 리스트테이블 -->            
						<table class="table">
							<thead>
								<tr>
									<th scope="col">이미지</th>
									<th scope="col">제품 아이디</th>
									<th scope="col">제품 이름</th>
									<th scope="col">모델 이름</th>
									<th scope="col">카테고리</th>
									<th scope="col">선택</th>
								</tr>	
							</thead>
							<tbody>
								<c:if test="${info.cnt eq 0}">
									<tr>
										<td> 검색된 제품이 없습니다 </td>
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
												<button id="${product.category_id}" type="button" class="searchProduct btn btn-light">선택</button>
											</td>						
										</tr>
									</c:forEach>
								</c:if>											
						</table>	                                 
	                </form>
					     <c:if test="${info.cnt gt 0}">
							<c:if test="${info.startPage gt info.pageBlock}">
								<a href="serchProductForm.do?product_name=${info.param}">[◀◀]</a>
								<a href="serchProductForm.do?pageNum=${info.startPage-info.pageBlock}&product_name=${info.param}">[◀]</a>
							</c:if>
							<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">			
								<c:if test="${i eq info.currentPage}">
									<b>[${i}]</b>
								</c:if>
								<c:if test="${i ne info.currentPage}">
									<a href="serchProductForm.do?pageNum=${i}&product_name=${info.param}">[${i}]</a>
								</c:if>
							</c:forEach>
							<c:if test="${info.pageCount gt info.endPage}">
								<a href="serchProductForm.do?pageNum=${info.startPage + info.pageBlock}&product_name=${info.param}">[▶]</a>
								<a href="serchProductForm.do?pageNum=${info.pageCount}&product_name=${info.param}">[▶▶]</a>		
							</c:if>					
						</c:if>
	            </div>
	       </div>
		</article>
	</body>
</html>