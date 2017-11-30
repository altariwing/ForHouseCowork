<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
  MemVO memVO = (MemVO) session.getAttribute("memVO");
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
    <title>For House</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    
    <!-- 神奇小按鈕的script 開始-->
    <script>
        $(document).ready(function() {
            $("#magicBtn1").click(function() {
                $("#loginid").val("stevejobs@gmail.com");
                $("#password").val("123456");
            });
            $("#magicBtn2").click(function() {
                $("#loginid").val("michaeljordan@gmail.com");
                $("#password").val("123456");
            });
            $("#magicBtn3").click(function() {
                $("#loginid").val("meinagano@gmail.com");
                $("#password").val("123456");
            });
            $("#magicBtn4").click(function() {
                $("#loginid").val("billgates@gmail.com");
                $("#password").val("123456");
            });
            $("#magicBtn5").click(function() {
                $("#loginid").val("gakki@gmail.com");
                $("#password").val("123456");
            });
        });
    </script>
    <!-- 神奇小按鈕的script 結束-->

    <style type="text/css">
    body {
        font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
    }
    
    footer {
        background-color: white;
        padding-top: 4em;
        margin-top: 400px;
    }

    .imgbox {
        position: relative;
        z-index: -1;
        height: 20em;
        width: 20em;
    }

    .ina:hover {
        opacity: 0.85;
    }

    .imgbox img {
        position: relative;
        z-index: -10;
        max-height: 100%;
        box-shadow: 1px 1px 1px #bdbdbd;
    }

    .textbox {
        position: absolute;
        top: 0;
        z-index: 11;
        padding-top: 10em;
        width: 90%;
        height: 100%;
        text-align: center;
    }

    .textbackground {
        background-color: #ffd600;
        height: 3em;
        box-shadow: 1px 1px 1px grey;
        opacity: 0.8;
    }

    .textinline {
        /* height: 3em;*/
        position: relative;
        z-index: 5;
        opacity: 1;
        margin-top: -0.5em;
        line-height: 2;
    }

    .searchSRS {
        margin-top: 8em;
    }

    .furnitureAd {
        margin-top: 8em;
    }

    .furnitureAd>img {
        max-height: 100%;
        max-width: 100%;
    }

    .news {
        margin-top: 8em;
    }

    .redNote {
        position: relative;
        z-index: 3;
        height: 2em;
        width: 6em;
        margin-left: 9.7em;
        margin-right: 10em;
        background-color: red;
    }

    .newss {
        position: relative;
        z-index: 1;
        margin-top: -2.5em;
        background-color: #fff9c4;
    }

    .newss h3 {
        padding-top: 1.5em;
    }

    ul {
        list-style-type: none;
    }

    .textfix {
        margin-left: -15px;
    }
    .textfix ul{
        padding-bottom: 2em;
    }
    .mobileBar {
        margin-top: 8em;
    }
    .mobileBar>img {
        max-height: 100%;
        max-width: 100%;
    }
    .headerBar{
    	width: 95em;
    }
    
    #loginArea{
    	width: 130px;
    }

    footer {
        background-color: white;
        padding-top: 4em;
    }

    .copyri {
        margin-top: 3em;
    }
    
    .box-width{
        width:520px;
        margin-left: 50px;
    }
    .margin-top{
        margin-top: 20px;
    }
    .searchstate{
        margin-left: 50px;
    }
    #searchrequire{
        font-size: 18px;
    }
    #aggrement{
        color: #999999;
    }

    #fancy-checkbox-info {
    display: none;
    }
    
    #loginDiv{
    	margin-top: 200px;
    }
    
    #loginform{
        
    }

    .searchstate{
        margin-left: 50px;
    }
    #searchrequire{
        font-size: 18px;
    }

    #fancy-checkbox-info {
        display: none;
    }

    #searchSwitch{
        margin-left: 110px;
        margin-top: 8px;
    }
    
    #aggrement{
        color: #999999;
        margin-left: 30px;
        margin-top: 20px;
    }
    
     #loginBtn{
        margin-left: 50px;
        width: 510px;
    }
    
    .inForm{
    	margin-left: 50px;
    	width: 560px;
    }
    
    .magicdiv{
        margin-top: 8px;
    }
    
    <!-- 燈箱內容 開始- ->
    .modal{
        text-align: center;
        padding: 0!important;
    }
    .modal:before{
        content: '';
        display: inline-block;
        height: 100%;
        vertical-align: middle;
        margin-right: 42em;
    }
    .modal-dialog{
        display: inline-block;
        text-align: left;
        vertical-align: middle;
    }
    .joinbtn{
    	margin: 30px;
    	width: 90%;
    }
    <!-- 燈箱內容 結束 -->
   
    </style>

</head>
<body>
    <div class="container-fluid backgroundpng">
        <img class="row" src="<%=request.getContextPath()%>/images/fixed_bg.png">
    </div>
    <nav class="navbar navbar-fixed-top">
        <jsp:include page="navbar.jsp" />
    </nav>
    
    <!-- 燈箱內容 -->
    <div class="modal fade" id="joinUs">
		<div class="modal-dialog">
			<div class="modal-content">
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title text-center">好房事歡迎您的加入</h4>
            </div>
				<div>
					<div class="form-group"> </div>
				    <div class="joinbtn">
				        <a class="btn btn-info btn-lg btn-block" href="" role="button"><h3>我  是  房  仲</h3></a>
				    </div>
				    <div class="form-group"> </div>
				    <div class="joinbtn">
				        <a class="btn btn-info btn-lg btn-block" href="<%=request.getContextPath()%>/front/seller/register.jsp" role="button"><h3>我  是  廠  商</h3></a>
				    </div>
				</div>
			</div>
		</div>
	</div>
	<!-- 燈箱內容  結束-->
    
    
    <div class="container" id="loginDiv">
    	<div class="row">
    		<div class="col-xs-10 col-sm-7 col-sm-offset-3">
                 <div class="panel panel-default">
					<div class="panel-heading">
			            <h4 class="text-center"><label class="control-label">登入</label></h4>
			        </div>
			        
				    <%-- 錯誤表列 --%>
				    <div class="panel-body text-center">
				    <c:if test="${not empty errorMsgs}">
						<font style="color:red">${errorMsgs}</font>
					</c:if>
						
                  	<form class="form-horizontal" action="<%=request.getContextPath()%>/front/checkIn.do" method="post">
                  		<input type="hidden" name="action" value="login">
                        <div class="form-group margin-top">
                        	<div class="col-sm-10 inForm">
                            <input type="email" class="form-control loginform" id="loginid" name="mem_id" placeholder="帳號 (請輸入e-mail)">
                            </div>
                        </div>
                        <div class="form-group margin-top">
                        	<div class="col-sm-10 inForm">
                            <input type="password" class="form-control loginform" id="password" name="mem_psw" placeholder="密碼">
                            </div>
                        </div>
                        <div class="form-group margin-top" id="loginBtn">
                            <input type="submit" value="登入" class="btn btn-primary btn-lg btn-block">
                            <div class="col-sm-offset-8 col-sm-5 magicdiv">
                                <button type="button" class="btn btn-default btn-xs" id="magicBtn1">賈</button>
                                <button type="button" class="btn btn-default btn-xs" id="magicBtn2">喬</button>
                                <button type="button" class="btn btn-default btn-xs" id="magicBtn3">永</button>
                                <button type="button" class="btn btn-default btn-xs" id="magicBtn4">比</button>
                                <button type="button" class="btn btn-default btn-xs" id="magicBtn5">衣</button>
                            </div>
                        </div>
                  </form>
              </div>
          </div>
        </div>
      </div>
    </div>
    

    <footer class="container-fluid">
        <div class="container text-center">
        <div class="col-xs-12 col-sm-2">
            <a href="#">HOME</a>
        </div>
        <div class="col-xs-12 col-sm-2">
            <a href="#">OUR TEAM</a>
        </div>
        <div class="col-xs-12 col-sm-2">
            <a href="#">COMMUNITIES</a>
        </div>
        <div class="col-xs-12 col-sm-2">
            <a href="#">COMMUNITIES</a>
        </div>
        <div class="col-xs-12 col-sm-2">
            <a href="#">COMMUNITIES</a>
        </div>
        <div class="col-xs-12 col-sm-2">
            <a href="#">CONTACT US</a>
        </div></div>
        <div class="copyri text-center">
            <p><small>Copyright © 2017 For House</small></p>
        </div>
    </footer>
        

</body>
</html>