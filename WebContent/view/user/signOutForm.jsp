<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>  


<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>공구 프로젝트</title>
		<script type="text/javascript">
			function passwordcheck() {
				if( ! passwordform.password.value ) {
					alert( "비밀번호를 입력해주세요" );
					passwordform.password.focus();
					return false;
				}
			}
		</script>
	</head>
	<body>
		<!-- navCss 컨트롤 -->
		<input type="hidden" class="navIndex" value="5">
	<div class="max-width-1500 div-center">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 border-under-a">	
						<div class="col-md-12">
							<h3 class="font-DH mar-top-50 ">비밀번호 확인</h3>
						</div>
					</div>
				</div>
				
				<!-- 리스트 박스 -->
				<div class="row">
					<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3">
						<form method="post" action="signOutPro.do" name="passwordform" onsubmit="return passwordcheck()">
							<div class="col-md-12 col-xs-12">								
								<div class="mar-top-20">
									<h6 class="text-color-g2">비밀번호 입력</h6>
									<div class="row">
										<div class="col-md-8 col-xs-8">
											<input type="password" name="password" class="form-control">
										</div>
										<div class="col-md-4 col-xs-4">
											<input type="submit" class="btn btn-default" value="탈퇴하기">
										</div>
									</div>
									<hr>
								</div>
							</div>
						</form>	
					</div>
				</div>
			</div>		
		</div>	
	</body>
</html>