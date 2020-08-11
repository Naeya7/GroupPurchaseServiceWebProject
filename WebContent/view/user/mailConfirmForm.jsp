<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript">				
			$(document).ready(function(){	
				$('body').on('click', '.confirmBtn', function(){
					var codeMsg = $(this).attr('id');
					var inputCode = $('.inputCode').val();
					
					if(codeMsg == inputCode){
						alert('인증 완료');
						opener.document.getElementById('mailConfirmForm').value = "인증 완료";
						opener.document.getElementById('emailConfirm').value = '1';
						window.close();
					}else{
						alert('코드를 정확히 입력해 주세요');
					}
				});		
			});
		</script>
	</head>
	<body>
		<div class="container-fluid">	
	         <!-- 로그인 폼 -->   
             <div class="row">       
	            <div class="col-md-10 col-md-offset-1 ">
					<div class="div-center">
						<div>
			                <h3>메일인증</h3>
			            </div>
		                <form name="mailConfirmForm" role="form">
		                    <div class="form-group">
		                        <input type="text" class="inputCode form-control height-40" placeholder="인증 번호를 입력하세요">
		                    </div>               
		                    <div class="form-group">
		                        <button type="button" id="${codeMsg}" class="confirmBtn btn btn-info width-100p height-40">인증하기</button>
		                    </div>
		                </form>
	                </div>
	            </div>
            </div>	   
		</div>
	</body>
</html>