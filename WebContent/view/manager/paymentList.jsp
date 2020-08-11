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
				$('body').on('click', '.deletePayment', function(){
					var payment_id = $(this).attr('id');
					window.document.location="deletePaymentPro.do?payment_id=" + payment_id;
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
							<th scope="col">결제 번호</th>
							<th scope="col">신청 번호</th>
							<th scope="col">받는 사람</th>
							<th scope="col">우편 번호</th>
							<th scope="col">주소</th>
							<th scope="col">상세 주소</th>
							<th scope="col">결제 금액</th>
							<th scope="col">결제 방법</th>
							<th scope="col">결제 시간</th>
							<th scope="col">리뷰 작성</th>
							<th scope="col"></th>
						</tr>	
					</thead>
					<tbody>
						<c:if test="${info.cnt eq 0}">
							<tr>
								<td> 알림 내역이 없습니다 </td>
							</tr>
						</c:if>
						<c:if test="${info.cnt ne 0}">	
							<c:forEach var="payment" items="${payments}">			
								<tr>
									<td>${payment.payment_id }</td>
									<td>${payment.application_id}</td>
									<td>${payment.receiver_name}</td>
									<td>${payment.zipcode}</td>
									<td>${payment.address1}</td>
									<td>${payment.address2}</td>
									<td>${payment.total_price}</td>
									<td>${payment.method}</td>
									<td>${payment.paytime}</td>		
									<td>${payment.review_writable}</td>		
									<td>
										<button id="${payment.payment_id}" type="button" class="deletePayment btn btn-light">삭제</button>
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
					<a href="paymentList.do">[◀◀]</a>
					<a href="paymentList.do?pageNum=${info.startPage-info.pageBlock}">[◀]</a>
				</c:if>
				<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">			
					<c:if test="${i eq info.currentPage}">
						<b>[${i}]</b>
					</c:if>
					<c:if test="${i ne info.currentPage}">
						<a href="paymentList.do?pageNum=${i}">[${i}]</a>
					</c:if>
				</c:forEach>
				<c:if test="${info.pageCount gt info.endPage}">
					<a href="paymentList.do?pageNum=${info.startPage + info.pageBlock}">[▶]</a>
					<a href="paymentList.do?pageNum=${info.pageCount}">[▶▶]</a>		
				</c:if>					
			</c:if>
		</div>
	</body>
</html>