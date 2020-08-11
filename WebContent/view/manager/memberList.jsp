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
				$('body').on('click', '.openMemberStatus', function(){
					var member_id = $(this).attr('id');
					
					var width = 500;
					var height = 500;
					   
					var popupY= (window.screen.height/2) - (height/2);
					var popupX = (window.screen.width/2) - (width/2);
					
					var url = "setMemberStatus.do?member_id=" + member_id;
					open(url, 'setMemberStatus', 'menubar=no,statusbar=no,scrollbar=no, width=' + width + ', height=' + height + ', left=' + popupX + ', top=' + popupY);
				});	
				$('body').on('click', '.sendNotification', function(){
					var member_id = $(this).attr('id');
					
					var width = 500;
					var height = 500;
					   
					var popupY= (window.screen.height/2) - (height/2);
					var popupX = (window.screen.width/2) - (width/2);
					
					var url = "sendNotificationForm.do?member_id=" + member_id;
					open(url, 'sendNotificationForm', 'menubar=no,statusbar=no,scrollbar=no, width=' + width + ', height=' + height + ', left=' + popupX + ', top=' + popupY);
				});	
				
				
				
			});
	
		</script>
	</head>
	<body style="min-width:1200px">
		<div class="container" style="position:relative;">
		<form name="memberListForm" role="form">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">멤버 번호</th>
						<th scope="col">멤버 이메일</th>
						<th scope="col">닉네임</th>						
						<th scope="col">우편번호</th>
						<th scope="col">주소</th>
						<th scope="col">상세주소</th>
						<th scope="col">전화번호</th>
						<th scope="col">활동상태</th>
						<th scope="col">가입날짜</th>
						<th scope="col">관리</th>
					</tr>	
				</thead>
				<tbody>
					<c:if test="${info.cnt eq 0}">
						<tr>
							<td> 멤버가 없습니다 </td>
						</tr>
					</c:if>
					<c:if test="${info.cnt ne 0}">
						<c:forEach var="member" items="${members}">
							<tr>
								<td>${member.member_id}</td>
								<td>${member.member_email}</td>
								<td>${member.nickname}</td>
								<td>${member.zipcode}</td>						
								<td>${member.address1}</td>						
								<td>${member.address2}</td>						
								<td>${member.tel}</td>
								<td>
									<c:if test="${member.member_status eq 'active'}">활성화</c:if>
									<c:if test="${member.member_status eq 'suspended'}">정지</c:if>
									<c:if test="${member.member_status eq 'dormant'}">휴먼</c:if>
									<c:if test="${member.member_status eq 'inactive'}">탈퇴</c:if>
								</td>							
								<td>${member.join_date}</td>
								<td colspan="2">
									<button id="${member.member_id}" type="button" class="openMemberStatus btn btn-light">상태</button>
									<button id="${member.member_id}" type="button" class="sendNotification btn btn-light">알림</button>
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
					<a href="memberList.do">[◀◀]</a>
					<a href="memberList.do?pageNum=${info.startPage-info.pageBlock}">[◀]</a>
				</c:if>
				<c:forEach var="i" begin="${info.startPage}" end="${info.endPage}">			
					<c:if test="${i eq info.currentPage}">
						<b>[${i}]</b>
					</c:if>
					<c:if test="${i ne info.currentPage}">
						<a href="memberList.do?pageNum=${i}">[${i}]</a>
					</c:if>
				</c:forEach>
				<c:if test="${info.pageCount gt info.endPage}">
					<a href="memberList.do?pageNum=${info.startPage + info.pageBlock}">[▶]</a>
					<a href="memberList.do?pageNum=${info.pageCount}">[▶▶]</a>		
				</c:if>					
			</c:if>
		</div>
	</body>
</html>