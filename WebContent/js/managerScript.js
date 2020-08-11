
//관리자 로그인 체크
function managerLoginFormCheck(){
	var idValue = managerLoginForm.manager_id.value;
	var passwdValue = managerLoginForm.password.value;
	
	if(idValue == "" || idValue.search(" ") != -1){
		alert("아이디를 다시 입력해 주세요");
		managerLoginForm.manager_id.focus();
		return false;
	}else if(passwdValue == "" || passwdValue.search(" ") != -1){
		alert("비밀번호를 다시 입력해 주세요"); 
		managerLoginForm.password.focus();
		return false;
	}
}

//알림 보내기 체크
function sendNotificationFormCheck(){
	var content = sendNotificationForm.content.value;
	
	if(content == ""){
		alert("내용을 입력해 주세요");
		sendNotificationForm.content.focus();
		return false;
	}
}

//협상 가격 변경 체크
function inputFixedPriceFormCheck(){
	var fixed_price = inputFixedPriceForm.fixed_price.value;
	
	if(fixed_price == ""){
		alert("가격을 입력해 주세요");
		inputFixedPriceForm.fixed_price.focus();
		return false;
	}
}

//매니저 게시글 입력 체크
function managerPostFormCheck(){
	var post_title = managerPostForm.post_title.value;
	var product_name = managerPostForm.product_name.value;
	var minimum_amount = managerPostForm.minimum_amount.value;
	var default_price = managerPostForm.default_price.value;
	var content = managerPostForm.content.value;
	
	if(post_title == ""){
		alert("게시글 제목을 입력해 주세요");
		return false;	
	}else if(product_name == ""){
		alert("제품을 검색해 주세요"); 
		return false;	
	}else if(minimum_amount == "" || minimum_amount.search(" ") != -1){
		alert("모집 수량을 공백 없이 입력해 주세요"); 
		return false;	
	}else if(default_price == "" || default_price.search(" ") != -1){
		alert("예상 가격을 공백 없이 입력해 주세요"); 
		return false;	
	}
	
}

//카테고리 선택 창
function openCtegory(control) {
	url = 'pickCtegory.do?control=' + control;
	open( url, 'pickCtegory', 'menubar=no,statusbar=no,scrollbar=no,width=500,height=500' );
}

//카테고리 선택
function managerProductFormCheck(control){
	if(control == 'input'){
		var pName = managerProductForm.product_name.value;
		var pModel = managerProductForm.product_model.value;
		var cName = managerProductForm.category_name.value;
		var img = managerProductForm.image.value;
	}else if(control == 'modify'){
		var pName = managerModifyProductForm.product_name.value;
		var pModel = managerModifyProductForm.product_model.value;
		var cName = managerModifyProductForm.category_name.value;
	}
	
	if(pName == ""){
		alert("제품명을 입력해 주세요");
		return false;	
	}else if(pModel == "" || pModel.search(" ") != -1){
		alert("모델명을 입력해 주세요"); 
		return false;	
	}else if(cName == ""){
		alert("카테고리 선택을 해주세요"); 
		return false;	
	}else if(img == ""){
		alert("이미지를 업로드 해주세요"); 
		return false;
	}
}

//게시글 상태 변경 창
function openPostStatus(post_id){
	url = 'setPostStatus.do?post_id='+post_id;
	open( url, 'setPostStatus', 'menubar=no,statusbar=no,scrollbar=no,width=500,height=500' );
}







