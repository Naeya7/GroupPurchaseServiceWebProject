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
		<script type="text/javascript">
			$(document).ready(function(){	
				$('body').on('click', '.searchBtn', function(){
					var url = "serchProductForm.do";
					open(url, 'serchProductForm', 'menubar=no,statusbar=no,scrollbar=no,width=1000,height=600');
				});		
			});
			
			$(function(){
				CKEDITOR.replace( 'editor', {
					filebrowserUploadUrl: 'fileupload.do',
					height : 300,
					language: 'ko'
				});
			});
			
			$(document).ready(function(){	
				$('body').on('click', '.crawlingPrice', function(){
					var product_model = $('input[name=product_model]').val();				
					var url = "crawlingPricePro.do?product_model=" + product_model;
					open(url, 'crawlingPricePro', 'menubar=no,statusbar=no,scrollbar=no,width=1000,height=600');
				});
			});
		</script>	
	</head>
	<body>
		<article class="container">
            <div class="page-header">
            	<div class="row">
	                <div class="col-md-10 col-md-offset-1">
	                <h3>게시글 작성 페이지</h3>
	                </div>
                </div>
            </div>
			<!-- 게시글 작성 폼 -->
            <div class="row">
	            <div class="col-md-10 col-md-offset-1">
	                <form name="managerPostForm" role="form" action="postInputPro.do" onsubmit="return managerPostFormCheck()" method="post">	
	                	<input type="hidden" name="product_id" value="0">	
	                    <div class="form-group">
	                        <label>제목</label>
	                        <input type="text" class="form-control" name="post_title" placeholder="제목을 입력하세요">
	                    </div>
	                    <div class="form-group">
	                   		<label>제품명</label><br>
		                    <div class="form-inline">  
		                        <input type="text" class="form-control" name="product_name" readonly="readonly" placeholder="제품명">    
		                        <input type="text" class="form-control" name="product_model" readonly="readonly" placeholder="모델명"> 
		                        <input type="text" class=" form-control" name="category_name" readonly="readonly" placeholder="카테고리">
		                        <button type="button" class="searchBtn btn btn-info">검색</button>
		                    </div> 
		                </div>             
	                    <div class="form-group">
	                        <label>모집개수</label>
	                        <input type="text" class="form-control" name="minimum_amount" placeholder="모집 개수를 입력하세요">
	                    </div>
	                    <div class="form-group">
	                        <label>최저가</label>
	                        <input type="text" class="form-control" name="default_price" placeholder="최저가를 입력하세요">
	                    </div>
	                    <div class="form-group text-right">
	                        <button type="button" class="crawlingPrice btn btn-info" name="crawlingPrice" disabled="disabled">크롤링</button>
	                    </div>
	                    <div class="form-group">
	                        <textarea id="editor" name="content"></textarea>
	                    </div>
	                    <div class="form-group text-right">
	                        <button type="submit" class="btn btn-info">등록</button>
	                    </div>
	                </form>
	            </div>
	       </div>
		</article>
	</body>
</html>