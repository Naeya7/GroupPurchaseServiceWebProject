<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>     
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>공구 프로젝트</title>
		<script type="text/javascript">
			$(document).ready(function(){	
				$('body').on('click', '.replyContent', function(){
					var review_num = $(this).attr('id');
					window.document.location="reviewContent.do?review_num=" + review_num;
				});		
			});
		</script>
	</head>
	<body>
		<!-- navCss 컨트롤 -->
		<input type="hidden" class="navIndex" value="4">
		
		<div class="max-width-1500 div-center">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 border-under-a">	
						<div class="col-md-12">
							<h3 class="font-DH mar-top-50 ">내가 쓴 댓글</h3>
						</div>
					</div>
				</div>
				
				<!-- 내역이 없을경우 -->
				<c:if test="${replyDto[0].review_num eq null }">
					<div class="row">
						<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">	
							<div class="col-md-12">
								<h4 class="font-DH mar-top-50 text-color-g2">댓글이 없습니다. 첫 댓글을 달아보세요!</h4>
							</div>
						</div>
					</div>
				</c:if>
				
				
				<!-- 리스트 박스 -->
				<c:forEach var="replyDto" items="${replyDto}">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">
						<div class="col-md-12 col-xs-12">
							<div id="${replyDto.review_num}" class="replyListBox replyContent mar-top-10">
							<h5 class="etcOneLine ">${replyDto.content}</h5>	
							<h5 class="etcOneLine text-color-g2">${replyDto.upload_date}</h5>
							</div>
						</div>	
					</div>
				</div>
				</c:forEach>
			</div>
		</div>		
	</body>
</html>