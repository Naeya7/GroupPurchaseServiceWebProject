<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
	<head>	
		<meta charset="UTF-8">
		<title> 회원가입 </title>
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){	
				
				//이메일 검사
				$('input[name=member_email]').keyup(function(){
					var email=$(this).val();
					var reg=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
					if(reg.test(email)){
					  	$("#emailErr").hide();
					  	$('#mailConfirmForm').removeAttr('disabled');
					}else{
					    $("#emailErr").show().css('color', 'red');
					    $("#email_check").html('');	
					    $('#mailConfirmForm').attr('disabled', 'disabled');
					}
				});
				
				$('input[name=member_email]').keyup(function(){
					//이메일 중복 검사
					var member_email = $('input[name=member_email]').val();
					$.ajax({
						url : 'checkEmail.do',
						type : 'get',
						data : {'member_email' : member_email},
						dataType : 'text',
						success : function(result) {
							if(result != 0){	
								msg = "<h6 id='checkMsg' style='color: red;'>이미 등록된 이메일 입니다</h6>";
								$("#email_check").html(msg);	
								$('#mailConfirmForm').attr('disabled', 'disabled');
								signUpForm.checkEmail.value = '0';
							}else{
								signUpForm.checkEmail.value = '1';
							}
						},
						error : function(e) {
							$('#email_check').html(e+'에러');								
						}				
					});	
				
				});
						
				//패스워드 검사
				$('input[name=password]').keyup(function(){
				    var pwd=$(this).val();
				    var reg=/^(?=.*?[a-zA-z])(?=.*?[0-9])(?=.*?[#?!@\\$%^&*-]).{8,}$/;
				    if(reg.test(pwd)){
				    	$("#pwdRegErr").hide();
				    }else{
				         $("#pwdRegErr").show().css('color', 'red');
				    }
				});
				
				//핸드폰
				$('input[name=tel]').keyup(function(){
				    var tel=$(this).val();
				    var reg=/^\d{3}-\d{3,4}-\d{4}$/;
				    if(reg.test(tel)){
				    	$("#telErr").hide();
				    	signUpForm.checkTel.value = '1';
				    	
				    }else{
				         $("#telErr").show().css('color', 'red');
				         signUpForm.checkTel.value = '0';
				    }
				});

				//리패스워드 검사
				$('input[name=repassword]').keyup(function(){
				    var rePwd=$(this).val();
				    var pwd=$('input[name=password]').val();
				    if(rePwd==pwd){
				        $("#rePwdErr").hide();
				    }else{
				        $("#rePwdErr").show().css('color', 'red');
				    }
				});
						
				//이메일 인증
				$('#mailConfirmForm').click(function(){
				   var member_email = $('input[name=member_email]').val();
				   
				   var w = 500;
				   var h = 500;
				   
				   var popupY= (window.screen.height/2) - (h/2);
				   var popupX = (window.screen.width/2) - (w/2);
				   
				   var url = "mailConfirmForm.do?member_email=" + member_email;
				   open(url, 'mailConfirmForm', 'menubar=no,statusbar=no,scrollbar=no, width=' + w + ', height=' + h + ', left=' + popupX + ', top=' + popupY);		
						   			
				});	
				
				
				//닉네임 중복 체크
				$('input[name=nickname]').keyup(function(){
					var nickname = $('input[name=nickname]').val();	
					
					$.ajax({
						url : 'checkNickname.do',
						type : 'get',
						data : {'nickname' : nickname},
						dataType : 'text',
						success : function(result) {
							if (result != 0) {
								msg = "<h6 id='checkMsg' style='color: red;'>이미 사용중인 닉네임 입니다.</h6>";
								$("#nickname_check").html(msg);
								signUpForm.checkNickname.value = '0';						
	
							} else {
								msg = "<h6 id='checkMsg' style='color: blue;'>사용 가능한 닉네임 입니다</h6>";
								$("#nickname_check").html(msg);
								signUpForm.checkNickname.value = '1';
							}
						},
						error : function(e) {
							$('#nickname_check').html(e+'에러');			
						}				
					});	
				
				});
									
			});//ready	
			
			
		</script>		
	</head>
	<body>
		<!-- 컨테이너 -->
		<div class="container-fluid">
		
			<!-- 상단 메뉴바-->
			<div class="row">
				<div class="height-60 line-a pd-top-15 hf">
					<div class="col-xs-6 col-md-6">
						<div class="mar-left-100"><h4><a href="mainPage.do">GongGu</a></h4></div>
					</div>
					<div class="col-xs-6 col-md-6">
						<div class="text-right mar-right-150">
							<h4>
								<span><a href="loginForm.do">로그인</a></span>
								<span><a href="signUpForm.do">회원가입</a></span>
							</h4>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 회원 가입 -->
			<div class="row">
	 			<div class="col-md-4 col-md-offset-4 col-xs-4 col-xs-offset-4">
	 				<div class="div-center max-width-400">
		 				<div class="mar-top-100">
			                <h2>회원가입</h2>
			            </div>	
		            	<!-- 회원가입 폼 -->
		           		<form method="post" name="signUpForm" role="form" action="signUpPro.do" onsubmit="return signUpFormCheck()">
		           			<input type="hidden" id="checkEmail" value="0">
		           			<input type="hidden" id="emailConfirm" value="0">
		           			<input type="hidden" id="checkNickname" value="0">      			
		           			<input type="hidden" id="checkTel" value="0">      			
		                    <div class="form-group">
		                    	<div class="row">
			                    	<div class="col-md-8">
			                       		<input type="text" class="form-control" name="member_email" placeholder="이메일 계정">
			                        </div>
			                    	<div class="col-md-4">
			                       		<input type="button" id="mailConfirmForm" class="btn btn-info width-100p" value="인증하기" disabled="disabled">
			                        </div>
		                        </div>
		                    </div>	                  
		                    <div class="form-group">
		                    	<h6> 위 이메일로 인증번호가 발송됩니다.</h6>
		                    	<h6 id="emailErr" class="help-block">올바른 이메일 형식이 아닙니다. 다시 입력해 주세요.</h6>
		                    	<div id="email_check"></div>
		                    </div>
		                    <div class="form-group">
		                        <input type="text" class="form-control" name="nickname" placeholder="닉네임" onkeyup="checkNiname()">
		                        <div id="nickname_check"></div>
		                    </div>
		                    <div class="form-group">
		                        <input type="password" class="form-control" name="password" placeholder="비밀번호 입력">
		                        <h6 id="pwdRegErr" class="help-block">8글자 이상, 특수문자를 조합하여 입력하세요.</h6>
		                    </div>
		                    <div class="form-group">
		                        <input type="password" class="form-control" name="repassword" placeholder="비밀번호 확인">
		                        <h6 id="rePwdErr" class="help-block">비밀번호와 일치하지 않습니다. 다시 입력해 주세요.</h6>
		                    </div>
		                    <div class="form-group mar-top-30">
		                    	<div class="row">
			                    	<div class="col-md-5">
			                       		<input type="text" class="form-control" name="zipcode" id="zipcode" placeholder="우편번호" readonly="readonly">
			                        </div>
			                    	<div class="col-md-7">
			                       		<input type="text" class="form-control" name="address1" id="address1" placeholder="도로명주소" readonly="readonly">
			                        </div>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<div class="row">
			                    	<div class="col-md-8">
			                       		<input type="text" class="form-control" name="address2" id="address2" placeholder="상세주소">
			                        </div>
			                    	<div class="col-md-4">
			                       		<input type="button" class="btn btn-info width-100p" value="찾기" onclick="sample4_execDaumPostcode();">
			                        </div>
		                        </div>
		                    </div>                    
		                     <div class="form-group">
		                    	<input type="text" class="form-control" name="tel" placeholder="전화번호">
		                    	<h6 id="telErr" class="help-block">'-'를 포함해서 입력해주세요.</h6>
		                    </div>
		                    <div class="mar-top-30">
		                    	<h4>상세 정보(선택사항)</h4>
		                    </div>
						    <div class="form-group">
		                    	<div class="row">
			                    	<div class="col-md-4 text-center">
			                       		<label class="form-control">성별</label>
			                        </div>
			                    	<div class="col-md-8">
			                       		<select class="form-control" id="inputGender" name="gender" >
								        	<option value=3>선택</option>
								        	<option value=0>남자</option>
								        	<option value=1>여자</option>
								        </select>
			                        </div>
		                        </div>
		                    </div>
						    <div class="form-group">
		                    	<div class="row">
			                    	<div class="col-md-4 text-center">
			                       		<label class="form-control">생년월일</label>
			                        </div>
			                    	<div class="col-md-8">
			                       		<input class="form-control" type="date" name="birth_date">
			                        </div>
		                        </div>
		                    </div>
		                     <div class="form-group">
		                    	<div class="row">
			                    	<div class="col-md-4 text-center">
			                       		<label class="form-control">방문경로</label>
			                        </div>
			                    	<div class="col-md-8">	
			                       		<select class="form-control" name="visit_path" >
								        	<option value=0>선택</option>
								        	<option value=1>검색</option>
								        	<option value=2>카페</option>
								        	<option value=3>블로그</option>
								        	<option value=4>SNS</option>
								        </select>
			                        </div>
		                        </div>
		                    </div>
		                    <div class="form-group mar-bottom-300">
		                        <button type="submit" class="btn btn-info width-100p height-40">가입</button>
		                    </div>
		                </form>        
	                </div>
	            </div>    			
			</div>
		</div>
	</body>
</html>