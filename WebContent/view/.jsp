<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/MyProject/css/style.css">
	</head>
	<body>
		<div class="row height-200" style="background-color: #D5D5D5;">
		</div>
		<article class="container">
            <div class="page-header">
                <div class="col-md-6 col-md-offset-3"><h3>관리자 로그인</h3></div>
            </div>
            <div class="col-sm-6 col-md-offset-3">
                <form role="form" action="">
                    <div class="form-group">
                        <label for="inputName">아이디</label>
                        <input type="text" class="form-control" id="inputName" placeholder="아이디를 입력하세요">
                    </div>
                    
                    <div class="form-group">
                        <label for="InputEmail">비밀번호</label>
                        <input type="password" class="form-control" id="InputEmail" placeholder="비밀번호를 입력하세요">
                    </div>
                    
                    <div class="form-group text-center">
                        <button type="submit" id="join-submit" class="btn btn-primary">회원가입<i class="fa fa-check spaceLeft"></i></button>                                               
                        <button type="submit" class="btn btn-warning">가입취소<i class="fa fa-times spaceLeft"></i></button>                        
                    </div>
                </form>
            </div>
		</article>


	</body>
</html>