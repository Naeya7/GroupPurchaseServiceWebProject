<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>GongGu</title>
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){	
				if($('input[name=password]').val()==$('input[name=repassword]').val()){
					modifyForm.checkPassword.value=1;
				}
				//패스워드 검사
				$('input[name=password]').keyup(function(){
				    var pwd=$(this).val();
				    var reg=/^(?=.*?[a-zA-z])(?=.*?[0-9])(?=.*?[#?!@\\$%^&*-]).{8,}$/;
				    if(reg.test(pwd)){
				    	$("#pwdRegErr").hide();
				    	modifyForm.checkPassword.value=1;
				    }else{
				        $("#pwdRegErr").show().css('color', 'red');
				        modifyForm.checkPassword.value=0;
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
				
				
				//닉네임 중복 체크
				if('${memberDto.nickname}'== $('input[name=nickname]').val()){
					modifyForm.checkNickname.value = 1;
				}
				$('input[name=nickname]').keyup(function(){
					var nickname = $('input[name=nickname]').val();	
					
					$.ajax({
						url : 'checkNickname.do',
						type : 'post',
						data : {'nickname' : nickname},
						dataType : 'text',
						success : function(result) {
							if (result != 0) {
								if(nickname){	
									msg = "<h6 id='checkMsg' style='color: red;'>이미 사용중인 닉네임 입니다.</h6>";
									$("#nickname_check").html(msg);
									modifyForm.checkNickname.value = 0;						
								}	
							} else {
								if(nickname){
									msg = "<h6 id='checkMsg' style='color: blue;'>사용 가능한 닉네임 입니다</h6>";
									$("#nickname_check").html(msg);
									modifyForm.checkNickname.value = 1;
								}else{
									$("#nickname_check").html('');
									modifyForm.checkNickname.value = 0;
								}
							}
						},
						error : function(e) {
							$('#nickname_check').html(e+'에러');			
						}				
					});	
				
				});
				
				//핸드폰
				if((modifyForm.tel.value).search('-')){
					modifyForm.checkTel.value=1;
				}
				$('input[name=tel]').keyup(function(){
				    var tel=$(this).val();
				    var reg=/^\d{3}-\d{3,4}-\d{4}$/;
				    if(reg.test(tel)){
				    	$("#telErr").hide();
				    	modifyForm.checkTel.value = 1;
				    	
				    }else{
				         $("#telErr").show().css('color', 'red');
				         modifyForm.checkTel.value = 0;
				    }
				});
									
			});//ready		
		</script>
	</head>
	<body>
		<!-- navCss 컨트롤 -->
		<input type="hidden" class="navIndex" value="5">	
		<!-- 컨테이너 -->
		<div class="max-width-1500 div-center">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-4 col-md-offset-4 col-xs-4 col-xs-offset-4 border-under-a">	
						<div class="col-md-12">
							<h3 class="font-DH mar-top-50 ">내 정보 수정</h3>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-4 col-md-offset-4 col-xs-4 col-xs-offset-4 border-under-a">
						<!-- 회원정보 수정 폼 -->
		           		<form method="post" name="modifyForm" role="form" action="modifyInforPro.do" onsubmit="return modifyFormCheck()">			
		           			<input type="hidden" id="checkPassword" value="0"> 
		           			<input type="hidden" id="checkNickname" value="0">      			
		           			<input type="hidden" id="checkTel" value="0"> 
		           			     			
		                    <div class="form-group">
			                	<input type="text" class="form-control mar-top-30" name="member_email" value="${memberDto.member_email }" readonly="readonly">                    
		                    </div>	                  
		                    <div class="form-group">
		                        <input type="text" class="form-control" name="nickname" placeholder="닉네임" value="${memberDto.nickname}">
		                        <div id="nickname_check"></div>
		                    </div>
		                    <div class="form-group">
		                        <input type="password" class="form-control" name="password" value="${memberDto.password}" placeholder="비밀번호 입력">
		                        <h6 id="pwdRegErr" class="help-block">8글자 이상, 특수문자를 조합하여 입력하세요.</h6>
		                    </div>
		                    <div class="form-group">
		                        <input type="password" class="form-control" name="repassword" value="${memberDto.password}" placeholder="비밀번호 확인">
		                        <h6 id="rePwdErr" class="help-block">비밀번호와 일치하지 않습니다. 다시 입력해 주세요.</h6>
		                    </div>
		                    <div class="form-group mar-top-30">
		                    	<div class="row">
			                    	<div class="col-md-5 col-xs-5">
			                       		<input type="text" class="form-control" name="zipcode" id="zipcode"  value="${memberDto.zipcode}" placeholder="우편번호" readonly="readonly">
			                        </div>
			                    	<div class="col-md-7 col-xs-7">
			                       		<input type="text" class="form-control" name="address1" id="address1" value="${memberDto.address1}" placeholder="도로명주소" readonly="readonly">
			                        </div>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<div class="row">
			                    	<div class="col-md-8 col-xs-8">
			                       		<input type="text" class="form-control" name="address2" id="address2" value="${memberDto.address2}" placeholder="상세주소">
			                        </div>
			                    	<div class="col-md-4 col-xs-4">
			                       		<input type="button" class="btn btn-info width-100p" value="찾기" onclick="sample4_execDaumPostcode();">
			                        </div>
		                        </div>
		                    </div>                    
		                     <div class="form-group">
		                    	<input type="text" class="form-control" name="tel" placeholder="전화번호" value="${memberDto.tel}">
		                    	<h6 id="telErr" class="help-block">'-'를 포함해서 입력해주세요.</h6>
		                    </div>  
		                    <div class="form-group">
		                    	<div class="row">
		                    		<div class="col-md-6 col-xs-6">
		                    			<button type="submit" class="btn btn-info width-100p height-40">수정하기</button>
		                    		</div>
		                    		<div class="col-md-6 col-xs-6">
		                    			<button type="button" class="btn btn-danger width-100p height-40" onclick="location='signOutForm.do'">탈퇴하기</button>
		                    		</div>
		                    	</div>
		                        
		                    </div>
		                </form>        
					</div>
				</div>		
			<!-- 컨테이너 -->	
			</div>
		</div>		
	</body>
</html>