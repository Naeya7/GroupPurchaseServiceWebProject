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
				$('body').on('click', '.deleteNotification', function(){
					var notify_id = $(this).attr('id');
					window.document.location="deleteNotificationPro.do?notify_id=" + notify_id;
				});		
			});
		</script>
	</head>
	<body style="min-width:1200px">
		<div class="container" style="position:relative;">
			<form name="notificationListForm" role="form">
				<table class="table table-condensed">
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">보낸 관리자</th>
							<th scope="col">닉네임</th>
							<th scope="col">이메일</th>						
							<th scope="col">내용</th>
							<th scope="col">종류</th>
							<th scope="col">보낸시간</th>
							<th scope="col">관리</th>
						</tr>	
					</thead>
					<tbody>
						<c:if test="${info.cnt eq 0}">
							<tr>
								<td> 알림 내역이 없습니다 </td>
							</tr>
						</c:if>
						<c:if test="${info.cnt ne 0}">	
							<c:set var="number" value="${info.number}"/> 	
							<c:forEach var="notification" items="${notifications}">			
								<tr>
									<td>${number}</td><c:set var="number" value="${number-1}"/>
									<td>${notification.manager_name}</td>
									<td>${notification.nickname}</td>
									<td>${notification.member_email}</td>
									<td>${notification.content}</td>
									<td>
										<c:if test="${notification.post_id eq 0}">개인</c:if>
										<c:if test="${notification.post_id ne 0}">단체</c:if>
									</td>
									<td>${notification.notify_time}</td>
									<td >
										<button id ="${notification.notify_id}" type="button" class="deleteNotification btn btn-light">삭제</button>
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
					<a href="notificationList.do">[◀◀]</a>
					<a href="notificationList.do?pageNum=${info.startPage-info.pageBlock}">[◀]</a>
				</c:if>
				<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">			
					<c:if test="${i eq info.currentPage}">
						<b>[${i}]</b>
					</c:if>
					<c:if test="${i ne info.currentPage}">
						<a href="notificationList.do?pageNum=${i}">[${i}]</a>
					</c:if>
				</c:forEach>
				<c:if test="${info.pageCount gt info.endPage}">
					<a href="notificationList.do?pageNum=${info.startPage + info.pageBlock}">[▶]</a>
					<a href="notificationList.do?pageNum=${info.pageCount}">[▶▶]</a>		
				</c:if>					
			</c:if>
		</div>
	</body>
</html>