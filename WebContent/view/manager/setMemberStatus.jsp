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
				$('body').on('click', '.setMemberStatus', function(){
					var clickBtn = $(this);
					var tr = clickBtn.parent().parent();
					var td = tr.children();
					var member_status = td.eq(0).text();
					var member_id = $('input[name=member_id]').val();
						 
					opener.document.location="setMemberStatusPro.do?" + "member_status=" + member_status + "&member_id=" + member_id;
					window.close();
				});
			});
			
		</script>		
	</head>
	<body>
	<form name="setMemeberStatusForm" role="form">
	<input type="hidden" name="member_id" value="${member_id}">
			<table class="table">
				<thead>
					<tr>
						<th scope="col" colspan="2">상태</th>
						<th scope="col">선택</th>
					</tr>	
				</thead>
				<tbody>
					<tr>
						<td>active</td>
						<td>활성화</td>
						<td><a class="setMemberStatus">[변경]</a></td>
					</tr>
					<tr>
						<td>suspended</td>
						<td>정지</td>
						<td><a class="setMemberStatus">[변경]</a></td>
					</tr>
					<tr>
						<td>dormant</td>
						<td>휴먼</td>
						<td><a class="setMemberStatus">[변경]</a></td>
					</tr>
					<tr>
						<td>inactive</td>
						<td>탈퇴</td>
						<td><a class="setMemberStatus">[변경]</a></td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>