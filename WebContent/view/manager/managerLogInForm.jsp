<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공동구매 프로젝트</title>
		<link rel="stylesheet" type="text/css" href="/MyProject/css/managerCss.css">	
	</head>
	<body>
		<!-- 상단 여백 -->
		<div class="row height-200 " style="background-color: #D5D5D5;"></div>
		
		<!-- 로그인 폼 -->
		<article class="container">
            <div class="page-header">
                <div class="col-md-6 col-md-offset-3">
                <h3>관리자 로그인</h3>
                </div>
            </div>
            
            <!-- 로그인 폼 -->
            <div class="col-md-4 col-md-offset-4 col-xs-4 col-xs-offset-4">
                <form name="managerLoginForm" role="form" action="managerLogInPro.do" onsubmit="return managerLoginFormCheck()">
                    <div class="form-group">
                        <label for="inputId">아이디</label>
                        <input type="text" class="form-control" name="manager_id" placeholder="아이디를 입력하세요">
                    </div>
                    <div class="form-group">
                        <label for="InputPasswd">비밀번호</label>
                        <input type="password" class="form-control"  name="password" placeholder="비밀번호를 입력하세요">
                    </div>
                    <div class="form-group text-right">
                        <button type="submit" class="btn btn-info">로그인</button>
                    </div>
                </form>
            </div>
		</article>
	</body>
</html>