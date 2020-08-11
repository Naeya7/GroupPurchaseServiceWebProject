<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공구공구</title>
		<script type="text/javascript">
			$(function(){
				CKEDITOR.replace( 'editor', {
					filebrowserUploadUrl: 'fileupload.do',
					height : 300,
					language: 'ko'
				});
			});
			function reviewFormCheck(){
				var title = writeReviewForm.title.value;
				var content = writeReviewForm.content.value;
				if (title=="" || title == null){
					alert("제목을 입력해주세요");
					return false;
				}
				if (content=="" || content==null){
					alert("내용을 입력해주세요")
					return false;
				}
			}
		</script>
	</head>
	<body>
		<!-- 상단 이미지 -->
		<div class="container-fluid">
			<div class="row">
				<div class="cropping" style="height: 100px;">
					<img src="/MyProject/img/tamplate3.jpg">
				</div>
			</div>
		</div>		
	
		<div class="container-fluid font-DH">	
			<!-- 게시글 -->
			<div class="row max-width-1300 div-center">
				<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2 border-under-a">	
					<div class="col-md-12">
						<h3 class="font-DH mar-top-50 ">리뷰수정</h3>
					</div>
				</div>	
			</div>
			<div class="row max-width-1300 div-center">
				<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2">	
					<form method="post" role="form" action="modifyReviewPro.do" name="writeReviewForm" onclick="return reviewFormCheck()">
						<input type="hidden" name="review_num" value="${reviewDto.review_num}"> 
						<div class="form-group">
							<label class="mar-top-20">글제목</label>
							<input class="form-control" name="title" value="${reviewDto.title}">
						</div>
						<div class="form-group">
							<textarea id="editor" name="content">${reviewDto.content}</textarea>
						</div>
						<div class="form-group text-right">
							<input type="submit" class="btn btn-info" value="수정하기">
						</div>
					</form>	
				</div>
			</div>
		</div>	
	</body>
</html>