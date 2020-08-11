
function sample4_execDaumPostcode() {
    new daum.Postcode(
            {
                oncomplete : function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                    var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== ''
                            && /[동|로|가]$/g.test(data.bname)) {
                        extraRoadAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== ''
                            && data.apartment === 'Y') {
                        extraRoadAddr += (extraRoadAddr !== '' ? ', '
                                + data.buildingName : data.buildingName);
                    }
                    // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (extraRoadAddr !== '') {
                        extraRoadAddr = ' (' + extraRoadAddr + ')';
                    }
                    // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                    if (fullRoadAddr !== '') {
                        fullRoadAddr += extraRoadAddr;
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
                    document.getElementById('address1').value = fullRoadAddr;

                    // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                    if (data.autoRoadAddress) {
                        //예상되는 도로명 주소에 조합형 주소를 추가한다.
                        var expRoadAddr = data.autoRoadAddress
                                + extraRoadAddr;
                        document.getElementById('guide').innerHTML = '(예상 도로명 주소 : '
                                + expRoadAddr + ')';
                    } 
                }
            }).open();
}

//유저 로그인 체크
function loginCheck(){
	var idValue = loginForm.member_email.value;
	var passwdValue = loginForm.password.value;
	if(!idValue){
		alert( "이메일을 입력하세요" );
		loginForm.member_email.focus();
		return false;
	}else if(!passwdValue){
		alert( "비밀번호를 입력하세요" ); 
		loginForm.password.focus();
		return false;
	}
}	

//유저 회원가입 폼 체크
function signUpFormCheck(){ 
	var password = signUpForm.password.value;
	var checkEmail = signUpForm.checkEmail.value;
	var emailConfirm = signUpForm.emailConfirm.value;
	var checkNickname = signUpForm.checkNickname.value;
	var address1 = signUpForm.address1.value;
	var address2 = signUpForm.address2.value;
	var tel = signUpForm.tel.value;
	
	if(checkEmail == 0){
		alert("이메일 중복확인을 해주세요");
		return false;
	}else if(emailConfirm == 0){
		alert("이메일 인증을 해주세요");
		return false;
	}else if(checkNickname == 0){
		alert("닉네임 중복 확인을 해주세요");
		return false;
	}else if(password == ""){
		alert("비밀번호를 입력해 주세요");
		return false;
	}else if(address1 == ""){
		alert("주소를 입력해 주세요");
		return false;
	}else if(address2 == ""){
		alert("상세 주소를 입력해 주세요");
		signUpForm.address2.focus();
		return false;
	}else if(tel == ""){
		alert("전화번호를 입력해 주세요");
		signUpForm.tel.focus();
		return false;
	}
}

//유저 회원정보 수정 폼 체크
function modifyFormCheck(){ 
	var password = modifyForm.password.value;
	var repassword = modifyForm.repassword.value;
	var checkPassword = modifyForm.checkPassword.value;
	var nickname = modifyForm.nickname.value
	var checkNickname = modifyForm.checkNickname.value;
	var address1 = modifyForm.address1.value;
	var address2 = modifyForm.address2.value;
	var tel = modifyForm.tel.value;
	var checkTel = modifyForm.checkTel.value
	
	$("#rePwdErr").hide();
	
	if(!nickname){
		alert("닉네임을 입력해주세요");
		modifyForm.nickname.focus();
		return false;
	}else if(checkNickname == 0){
		alert("닉네임 중복 확인을 해주세요");
		modifyForm.nickname.focus();
		return false;
	}else if(!password){
		alert("비밀번호를 입력해 주세요");
		modifyForm.password.focus();
		return false;
	}else if(checkPassword==0){
		return false;
	}else if(!repassword){
		alert("비밀번호를 다시 입력해 주세요");
		modifyForm.repassword.focus();
		return false;
	}else if(password!=repassword){
		$("#rePwdErr").show().css('color', 'red');
		return false;
	}else if(!address1){
		alert("주소를 검색해 주세요");
		return false;
	}else if(!address2){
		alert("상세 주소를 입력해 주세요");
		modifyForm.address2.focus();
		return false;
	}else if(!tel){
		alert("전화번호를 입력해 주세요");
		modifyForm.tel.focus();
		return false;
	}else if(checkTel==0){
		return false;
	}
}

//세션 체크
function sessionCheck(){
	var isSession = sessionStorage.getItem("member_id");
	if(isSession== null || isSession==""){
		return false;
	}else{
		return true;
	}
}

//결제취소 설문폼 체크
function pollcheck() {
	var priceValue = poll.cancel_price.value;
	
	var functionValue = poll.cancel_function.value;
	var productValue = poll.cancel_product.value;
	var siteValue = poll.cancel_site.value;
	var mindValue = poll.cancel_mind.value;
	var contentValue = poll.cancel_content.value;
	
	if(!priceValue || !functionValue || !productValue || !siteValue || !mindValue || !contentValue ){
		alert( "신청취소한 이유를 선택주세요!" );
		return false;
	}
}
	



