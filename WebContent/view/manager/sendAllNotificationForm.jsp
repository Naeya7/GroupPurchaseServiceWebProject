<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>	
		<link rel="stylesheet" type="text/css" href="/MyProject/css/managerCss.css">	
	</head>
	<body>
		<article class="container">
            <div class="page-header">
            	<div class="row">
	                <div class="col-md-6">
	                <h3>알림 보내기</h3>
	                </div>
                </div>
            </div>
            <div class="row">
	            <div class="col-md-6">
	                <form method="post" name="sendNotificationForm" role="form" action="sendAllNotificationPro.do" onsubmit="return sendNotificationFormCheck()">
	                   	<input type="hidden" name="post_id" value="${post_id}">
	                    <div class="form-group">
	                        <label for="InputPasswd">내용</label>
	                        <textarea name="content" class="form-control" rows="10" cols="20"></textarea>
	                    </div>
	                    <div class="form-group text-right">
	                        <button type="submit" class="btn btn-info">보내기</button>
	                    </div>
	                </form>
	            </div>
	       </div>
		</article>
	</body>
</html>