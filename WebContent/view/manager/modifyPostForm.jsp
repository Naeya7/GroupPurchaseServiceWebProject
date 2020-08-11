<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/MyProject/css/managerCss.css">
		<style type="text/css">
			.width-100p{
				width: 100%;
			}
		</style>
		<script src="/MyProject/js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript">
			$(function(){
				CKEDITOR.replace( 'editor', {
					filebrowserUploadUrl: 'fileupload.do',
					height : 300,
					language: 'ko'
				});
			});
			
			$(document).ready(function(){		
				$('body').on('click', '.searchBtn', function(){
					
					var width = 1000;
					var height = 600;
					   
					var popupY= (window.screen.height/2) - (height/2);
					var popupX = (window.screen.width/2) - (width/2);
					
					var url = "serchProductForm.do";
					open(url, 'serchProductForm', 'menubar=no,statusbar=no,scrollbar=no, width=' + width + ', height=' + height + ', left=' + popupX + ', top=' + popupY);
				});			
			});
			
			
		</script>	
	</head>
	<body>
		<article class="container">
            <div class="page-header">
            	<div class="row">
	                <div class="col-md-10 col-md-offset-1">
	                <h3>게시글 수정</h3>
	                </div>
                </div>
            </div>
			<!-- 게시글 작성 폼 -->
            <div class="row">
	            <div class="col-md-10 col-md-offset-1">
	                <form name="managerPostForm" role="form" action="modifyPostPro.do" onsubmit="return managerPostFormCheck()" method="post">	
	                	<input type="hidden" name="product_id" value="${modifyPost.product_id}">	
	                	<input type="hidden" name="post_id" value="${modifyPost.post_id}">	
	                	<input type="hidden" name="current_amount" value="${modifyPost.current_amount}">	
	                    <div class="form-group">
	                        <label>제목</label>
	                        <input type="text" class="form-control" name="post_title" value="${modifyPost.post_title}" placeholder="제목을 입력하세요">
	                    </div>
	                    <div class="form-group">
	                   		<label>제품명</label><br>
		                    <div class="form-inline">  
		                        <input type="text" class="form-control" name="product_name" value="${modifyPost.product_name}" readonly="readonly" placeholder="제품명">    
		                        <input type="text" class="form-control" name="product_model" value="${modifyPost.product_model}" readonly="readonly" placeholder="모델명"> 
		                        <input type="text" class=" form-control" name="category_name" value="${modifyPost.category_name}" readonly="readonly" placeholder="카테고리">
		                        <button type="button" class="searchBtn btn btn-light">검색</button>
		                    </div> 
		                </div>             
	                    <div class="form-group">
	                        <label>모집개수</label>
	                        <input type="text" class="form-control" name="minimum_amount" value="${modifyPost.minimum_amount}" placeholder="모집 개수를 입력하세요">
	                    </div>
	                    <div class="form-group">
	                        <label>최저가</label>
	                        <input type="text" class="form-control" name="default_price" value="${modifyPost.default_price}" placeholder="최저가를 입력하세요">
	                    </div>
	                    <div class="form-group">
	                        <textarea id="editor" name="content">${modifyPost.content}</textarea>
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