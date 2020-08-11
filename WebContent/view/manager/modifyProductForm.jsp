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
	<body>
		<article class="container">
            <div class="page-header">
            	<div class="row">
	                <div class="col-md-6">
	                <h3>제품 수정 페이지</h3>
	                </div>
                </div>
            </div>
            
            <!-- 로그인 폼 -->
            <div class="row">
	            <div class="col-md-6">
	                <form name="managerModifyProductForm" role="form" action="modifyProductPro.do" 
	                		onsubmit="return managerProductFormCheck('modify')" method="post" enctype="multipart/form-data">
	                   	<input type="hidden" name="category_id" value="${product.category_id}">
	                   	<input type="hidden" name="product_id" value="${product.product_id}">
	                    <div class="form-group">
	                        <label for="inputId">제품명</label>
	                        <input type="text" class="form-control" name="product_name" value="${product.product_name}">
	                    </div>
	                    <div class="form-group">
	                        <label for="InputPasswd">모델명</label>
	                        <input type="text" class="form-control"  name="product_model" value="${product.product_model}">
	                    </div>
	                    <div class="form-group">
	                        <label for="InputPasswd">카테고리</label>
	                        <c:if test="${product.category_id eq 1}"> 
	                        	<input type="text" class="form-control"  name="category_name" readonly="readonly" value="핸드폰">
	                        </c:if>
	                        <c:if test="${product.category_id eq 2}"> 
	                        	<input type="text" class="form-control"  name="category_name" readonly="readonly" value="PC/노트북">
	                        </c:if>
	                        <c:if test="${product.category_id eq 3}"> 
	                        	<input type="text" class="form-control"  name="category_name" readonly="readonly" value="카메라">
	                        </c:if>
	                        <c:if test="${product.category_id eq 4}"> 
	                        	<input type="text" class="form-control"  name="category_name" readonly="readonly" value="웨어러블">
	                        </c:if>
	                        <c:if test="${product.category_id eq 5}"> 
	                        	<input type="text" class="form-control"  name="category_name" readonly="readonly" value="태블릿">
	                        </c:if>           
	                    </div>
	                    <div class="form-group text-right">
	                        <button type="button" class="btn btn-light" onclick="openCtegory('modify')">선택</button>
	                    </div>
	                    <div class="form-group">
	                        <label for="InputPasswd">이미지</label><br>
	                        <img src="${product.image}" width="300" height="200">
	                    </div><br><br>
	                    <div class="form-group">
	                        <label for="InputPasswd">이미지 재업로드</label>
							<input type="file" name="image">                 	        
	                    </div>
	                    <div class="form-group text-right">
	                        <button type="submit" class="btn btn-light">수정<i class="fa fa-check spaceLeft"></i></button>
	                    </div>
	                </form>
	            </div>
	       </div>
		</article>
	</body>
</html>