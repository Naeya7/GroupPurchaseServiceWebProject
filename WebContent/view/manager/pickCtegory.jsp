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
				//inputProduct
				$('table').on('click', '.input_pickBtn', function(){
					var pickBtn = $(this);
					var tr = pickBtn.parent().parent();
					var td = tr.children();
					
					opener.document.managerProductForm.category_id.value = td.eq(0).text();
					opener.document.managerProductForm.category_name.value = td.eq(1).text();
					window.close();
				});
				
				//modifyProduct
				$('table').on('click', '.modify_pickBtn', function(){
					var pickBtn = $(this);
					var tr = pickBtn.parent().parent();
					var td = tr.children();
					
					opener.document.managerModifyProductForm.category_id.value = td.eq(0).text();
					opener.document.managerModifyProductForm.category_name.value = td.eq(1).text();
					window.close();
				});
				
			});
		</script>
	</head>
	<body>
		<form name="pickCategoryForm" role="form">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">카테고리 아이디</th>
						<th scope="col">카테고리 이름</th>
					</tr>	
				</thead>
				<tbody>
				<c:forEach var="categoryDto" items="${categoryDtos}">
					<tr>
						<td>${categoryDto.category_id}</td>
						<td>${categoryDto.category_name}</td>
						<td><button type="button" class="${control}_pickBtn btn btn-info">선택</button></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
	</body>
</html>

