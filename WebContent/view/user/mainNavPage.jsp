<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/setting.jsp" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

      <script type="text/javascript">
      $(document).ready(function(){
         if('${sessionScope.member_id}' != ''){
            $.ajax({
               url : 'checkNotification.do',
               type : 'get',
               dataType : 'text',
               success : function(result) {
                  if(result == 1 ){   
                     $('#notify1').removeClass('dispNotify');
                     $('#notify1').addClass('nonDispNotify');
                     $('#notify2').removeClass('nonDispNotify');
                     $('#notify2').addClass('dispNotify');
                  }else{
                     $('#notify2').removeClass('dispNotify');
                     $('#notify2').addClass('nonDispNotify');
                     $('#notify1').removeClass('nonDispNotify');
                     $('#notify1').addClass('dispNotify');
                  }
               },
               error : function(e) {
         
               }            
            });   
         }
         
         
         $(".notifycation").click(function() {
            $(".notifyBox").toggleClass("notifyBox-toggled");
            
            if($('.notifyBox').hasClass("notifyBox-toggled")){
               
               $.ajax({
                  type : 'get',
                   url : "getNotification.do",
                   dataType : "text",
                   success : function(data){
                      $('#notify2').removeClass('dispNotify');
                     $('#notify2').addClass('nonDispNotify');
                     $('#notify1').removeClass('nonDispNotify');
                     $('#notify1').addClass('dispNotify');
                      $(".notifyBox").html(data);   
                   },
                   error : function(e){
                       alert("에러입니다");
                    }
               });
            }
         });//클릭시 알림 가져오기
         
         
         if('${sessionScope.member_id}' != ''){
            setInterval(function(){
               $.ajax({
                  url : 'checkNotification.do',
                  type : 'get',
                  dataType : 'text',
                  success : function(result) {
                     if(result == 1 ){   
                        $('#notify1').removeClass('dispNotify');
                        $('#notify1').addClass('nonDispNotify');
                        $('#notify2').removeClass('nonDispNotify');
                        $('#notify2').addClass('dispNotify');
                     }else{
                        $('#notify2').removeClass('dispNotify');
                        $('#notify2').addClass('nonDispNotify');
                        $('#notify1').removeClass('nonDispNotify');
                        $('#notify1').addClass('dispNotify');
                     }
                  },
                  error : function(e) {
            
                  }            
               });   
            }, 3000);
         }//end if
      }); 
      </script>
  
   </head>
   <body>
      <!-- 내비게이션 -->
      <div class="container-fluid">
      	 <!-- 최상단 노티스 -->
         <div class="row">
            <div class="col-md-12 col-xs-12 height-50 bg-black text-center text-white font-DH pd-top-15" >
              <span class="text-main-color">모두에게 드리는 혜택!</span>
               <span>지금 바로 신청 마감이 종료되는 제품을 찾아보세요</span>              
            </div>        
         </div>
         <!-- 상단 메뉴바-->
         <div class="row">
           <div class="nav-bar height-50 hf">
              <div class="col-md-2 col-xs-2">
                 <ul class="list-inline text-right mar-right-15 pd-top-15">
                    <li><a href="mainPage.do">GongGu</a></li>
                 </ul>               
              </div>            
              <div class="col-md-5 col-xs-5 pd-top-15">
                 <ul class="list-inline ">
                    <li><a href="mainPostList.do">전체보기</a></li>
                    <li><a href="catePostList.do?category_id=1">핸드폰</a></li>
                    <li><a href="catePostList.do?category_id=2">PC/노트북</a></li>
                    <li><a href="catePostList.do?category_id=3">카메라</a></li>
                    <li><a href="catePostList.do?category_id=4">웨어러블</a></li>
                    <li><a href="catePostList.do?category_id=5">태블릿</a></li>
                    <li><a href="reviewBoard.do" style="color: #3DB7CC;">리뷰보기</a></li>
                 	 <li><a href="endPostList.do" style="color: #FF0000;">판매종료</a></li>	
                 </ul> 
              </div>
              <!-- 검색 -->
              <div class="col-md-2 col-xs-2 pd-top-10">
              	<form style="padding:0; margin:0;" name="searchForm" method="get" action="mainPostList.do">
              		<input style="border-color:#5BC0DE;" type="text" class="form-control" placeholder="어떤 제품을  찾고 계신가요?" name="nav_search" 
					    onkeyup="if(this.form.keyCode==13){ searchForm.submit();}">					    
              	</form>
              </div> 

              <div class="col-md-3 col-xs-3 pd-top-15" style="text-align: center">
                  <ul class="list-inline nav-my-menu hf">
                     <c:if test="${sessionScope.member_id eq null}">
                        <li><a href="loginForm.do">로그인</a></li>
                        <li><a href="signUpForm.do">회원가입</a></li>
                     </c:if>
                     <c:if test="${sessionScope.member_id ne null}">
                        <li>
                           <a class="notifycation"><img src="img/notify1.png" id="notify1" class="dispNotify"></a>
                           <a class="notifycation"><img src="img/notify2.png" id="notify2" class="nonDispNotify"></a>
                        </li>
                        <li><a href="payablePost.do">마이페이지</a></li>
                        <li><a href="logout.do">로그아웃</a></li>
                     </c:if>   
                  </ul>
               </div>   
            </div>
         </div>
        </div>
        
      <!-- 알림박스 -->
      <div class="container-fluid">
         <div class="row">
            <div class="col-md-4 col-md-offset-8 col-xs-4 col-xs-offset-8">
               <div class="notifyBox text-center font-DH">
               </div>
            </div>
         </div>
      </div>
   </body>
</html>