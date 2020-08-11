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
				$('table').on('click', '.setPostStatus', function(){
					var clickBtn = $(this);
					var tr = clickBtn.parent().parent();
					var td = tr.children();
					var post_status = td.eq(0).text();
					var post_id = $('input[name=post_id]').val();
						 
					opener.document.location="setPostStatusPro.do?" + "post_status=" + post_status + "&post_id=" + post_id;
					window.close();
				});
			});
		</script>
	</head>
	<body>
	<form name="setPostStatusForm" role="form">
	<input type="hidden" name="post_id" value="${post_id}">
			<table class="table">
				<thead>
					<tr>
						<th scope="col" colspan="2">상태</th>
						<th scope="col">선택</th>
					</tr>	
				</thead>
				<tbody>
					<tr>
						<td>A</td>
						<td>모집중</td>
						<td><a class="setPostStatus">[변경]</a></td>
					</tr>
					<tr>
						<td>C</td>
						<td>모집완료</td>
						<td><a class="setPostStatus">[변경]</a></td>
					</tr>
					<tr>
						<td>N</td>
						<td>협상중</td>
						<td><a class="setPostStatus">[변경]</a></td>
					</tr>
					<tr>
						<td>P</td>
						<td>결제중</td>
						<td><a class="setPostStatus">[변경]</a></td>
					</tr>
					<tr>
						<td>S</td>
						<td>결제완료</td>
						<td><a class="setPostStatus">[변경]</a></td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>