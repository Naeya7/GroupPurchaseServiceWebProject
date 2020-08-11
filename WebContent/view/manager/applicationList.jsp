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
				$('body').on('click', '.deleteApplication', function(){
					var application_id = $(this).attr('id');
					window.document.location="deleteApplicationPro.do?application_id=" + application_id;
				});		
			});
		</script>
	</head>
	<body style="min-width:1200px">
		<div class="container" style="position:relative;">
			<form name="paymentListForm" role="form">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">신청 번호</th>
							<th scope="col">멤버 번호</th>
							<th scope="col">게시글 번호</th>
							<th scope="col">수량</th>
							<th scope="col">신청 시간</th>
							<th scope="col"></th>
		
						</tr>	
					</thead>
					<tbody>
						<c:if test="${info.cnt eq 0}">
							<tr>
								<td> 신청 내역이</td>
							</tr>
						</c:if>
						<c:if test="${info.cnt ne 0}">	
							<c:forEach var="application" items="${applications}">			
								<tr>
									<td>${application.application_id }</td>
									<td>${application.nickname}</td>
									<td>${application.post_title}</td>
									<td>${application.amount}</td>
									<td>${application.applytime}</td>		
									<td>
										<button id="${application.application_id}" type="button" class="deleteApplication btn btn-light">삭제</button>
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
					<a href="applicationList.do">[◀◀]</a>
					<a href="applicationList.do?pageNum=${info.startPage-info.pageBlock}">[◀]</a>
				</c:if>
				<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">			
					<c:if test="${i eq info.currentPage}">
						<b>[${i}]</b>
					</c:if>
					<c:if test="${i ne info.currentPage}">
						<a href="applicationList.do?pageNum=${i}">[${i}]</a>
					</c:if>
				</c:forEach>
				<c:if test="${info.pageCount gt info.endPage}">
					<a href="applicationList.do?pageNum=${info.startPage + info.pageBlock}">[▶]</a>
					<a href="applicationList.do?pageNum=${info.pageCount}">[▶▶]</a>		
				</c:if>					
			</c:if>
		</div>
	</body>
</html>