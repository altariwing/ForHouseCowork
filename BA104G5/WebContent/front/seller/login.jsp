<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.slr.model.*"%>
<%
  SlrVO slrVO = (SlrVO) session.getAttribute("slrVO");
%>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>歡迎登入</title>
    <link rel="shortcut icon" href="images/houselogo1.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <!-- 神奇小按鈕的script 開始-->
    <script>
        $(document).ready(function() {
            $("#magicBtn1").click(function() {
                $("#loginid").val("sales@ikea.com");
                $("#password").val("123456");
            });
            $("#magicBtn2").click(function() {
                $("#loginid").val("sales@order.com");
                $("#password").val("123456");
            });
            $("#magicBtn3").click(function() {
                $("#loginid").val("sales@hola.com.tw");
                $("#password").val("123456");
            });
            $("#magicBtn4").click(function() {
                $("#loginid").val("sales@scanteak.com");
                $("#password").val("123456");
            });
        });
    </script>
    <!-- 神奇小按鈕的script 結束-->
    
    <style type="text/css">
    body {
        font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
    }
    p {
    	font-family: 微軟正黑體;
    	font-size: 200%;
    	color: #00ADEE;
    }
    #outter{
    	margin-top: 10em;
    }
    #loginDiv{
    	width: 40em;
    	margin-top: 150px;
    }
    .loginform{
    	margin-top: 10px;
    }
    #loginBtn{
    	margin-top: 20px;
    }
    
    </style>
    
</head>

<body>

<nav class="navbar navbar-fixed-top main">
	<jsp:include page="navbar.jsp" />
</nav>
	

	<div class="container text-center" id="loginDiv">
    	<div class="row">
    		
                 <div class="panel panel-default">
					<div class="panel-heading">
			            <p class="text-center"><label class="control-label">廠商登入系統</label></p>
			        </div>
			        
				    <%-- 錯誤表列 --%>
				    <div class="panel-body">
				    <c:if test="${not empty loginError}">
						<font style="color:red">${loginError}</font>
					</c:if>
						
                  	<form class="form-horizontal loginform" action="slr.do" method="post">
                  	<input type="hidden" name="action" value="slrLogin">
                        <div class="form-group">
                        	<div class="col-sm-12">
                            <input type="email" class="form-control" id="loginid" name="slr_id" placeholder="帳號 (請輸入e-mail)">
                            </div>
                        </div>
                        <div class="form-group">
                        	<div class="col-sm-12">
                            <input type="password" class="form-control" id="password" name="slr_psw" placeholder="密碼">
                            </div>
                        </div>
                        <div class="form-group margin-top" id="loginBtn">
                          <div class="col-sm-12">
                            <input type="submit" value="登入" class="btn btn-primary btn-lg btn-block">
                          </div>
                            <div class="col-sm-offset-8 col-sm-4 loginform">
                                <button type="button" class="btn btn-default btn-xs" id="magicBtn1">宜</button>
                                <button type="button" class="btn btn-default btn-xs" id="magicBtn2">歐</button>
                                <button type="button" class="btn btn-default btn-xs" id="magicBtn3">特</button>
                                <button type="button" class="btn btn-default btn-xs" id="magicBtn4">詩</button>
                            </div>
                        </div>
                  </form>
              </div>
          </div>
        
      </div>
    </div>


</body>
</html>