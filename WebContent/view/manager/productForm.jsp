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
	                <div class="col-md-10 col-md-offset-1">
	                <h3>제품 등록 페이지</h3>
	                </div>
                </div>
            </div>
            
            <!-- 로그인 폼 -->
            <div class="row">
	            <div class="col-md-10 col-md-offset-1">
	                <form name="managerProductForm" role="form" action="productInputPro.do" 
	                		onsubmit="return managerProductFormCheck('input')" method="post" enctype="multipart/form-data">
	                   	<input type="hidden" name="category_id" value="1">
	                    <div class="form-group">
	                        <label for="inputId">제품명</label>
	                        <input type="text" class="form-control" name="product_name" placeholder="제품명을 입력하세요">
	                    </div>
	                    <div class="form-group">
	                        <label for="InputPasswd">모델명</label>
	                        <input type="text" class="form-control"  name="product_model" placeholder="모델명을 입력하세요">
	                    </div>
	                    <div class="form-group">
	                        <label for="InputPasswd">카테고리</label>
	                        <input type="text" class="form-control"  name="category_name" readonly="readonly" placeholder="선택해서 입력하세요">         
	                    </div>
	                    <div class="form-group text-right">
	                        <button type="button" class="btn btn-info" onclick="openCtegory('input')">선택</button>
	                    </div>
	                    <div class="form-group">
	                        <label for="InputPasswd">이미지 업로드</label>
							<input type="file" name="image">                 	        
	                    </div>
	                    <div class="form-group text-right">
	                        <button type="submit" class="btn btn-info">등록<i class="fa fa-check spaceLeft"></i></button>
	                    </div>
	                </form>
	            </div>
	       </div>
		</article>
	</body>
</html>