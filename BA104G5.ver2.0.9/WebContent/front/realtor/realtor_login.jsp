<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>歡迎登入</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<!-- 神奇小按鈕的script 開始-->
<script>
	$(document).ready(function() {
		$("#magicBtn1").click(function() {
			$("#loginid").val("realtor002@gmail.com");
			$("#password").val("123456");
		});
	});
</script>
<!-- 神奇小按鈕的script 結束-->

<style type="text/css">
body {
	font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體,
		sans-serif;
}

#outter {
	margin-top: 4em;
}

#loginDiv {
	width: 40em;
}

.loginform {
	margin-top: 10px;
}

#loginBtn {
	margin-top: 20px;
}
</style>

<!-- cooperkai.css -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front/realtor/css/realtor_cooper.css">
<!-- cooperkai.css -->


<script>
	function statusChangeCallback(response) {
		alert('感謝你使用FaceBook登入系統，但還是請您等候房仲會員確認信!');
// 		alert(response);
		if (response.status === 'connected') {
			fblogin();
		} else if (response.status === 'not_authorized') {
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into this app.';
		} else {
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into Facebook.';
		}
	}
	function checkLoginState() {
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	}


	window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '193615331198785',//有期限限制，請再自行去FB登記使用
	      xfbml      : true,
	      version    : 'v2.11'//版本
	    });
	    FB.AppEvents.logPageView();
	  };

	  (function(d, s, id){
	     var js, fjs = d.getElementsByTagName(s)[0];
	     if (d.getElementById(id)) {return;}
	     js = d.createElement(s); js.id = id;
	     js.src = "https://connect.facebook.net/en_US/sdk.js";
	     fjs.parentNode.insertBefore(js, fjs);
	   }(document, 'script', 'facebook-jssdk'));

	function testAPI() {
		console.log('Welcome!  Fetching your information.... ');
		FB.api('/me',function(response) {
			console.log('Successful login for: '+ response.name);
			document.getElementById('status').innerHTML = 'Thanks for logging in, '	+ response.name+ response.email  +response.id+'!';});
		//讀照片必備	  
		FB.api('/me/picture?type=large', function(response) {
			document.getElementById('status1').innerHTML = response.data.url;
		});
	}

		function fblogin(){
			  //===實作(填入程式碼)
			  var xhr=new XMLHttpRequest();
			  xhr.onreadystatechange=function(){
				  if(xhr.readyState==4){
					  if(xhr.status==200){
						  document.getElementById("IdShowPanel").innerHTML=xhr.responseText;
					  } 
					  else{
						  alert(xhr.status);
						  }  
					  }
				  }
			  //取得使用者資訊，還可以取很多個，但要該名使用者同意
			  FB.api('/me', 'GET', {"fields":"id,name,email"},function(response) {
				  name = response.name;
				  email = response.email;
				  fbid = response.id;
			
			      //建立好Get連接與送出請求
				  var url="<%=request.getContextPath()%>/front/realtor/realtor.do?action=FBLogin&name=" + name + "&email=" + email + "&id=" + fbid;
							xhr.open("Get", url, true);
							xhr.send(null);
			 });
	}
</script>


</head> 
<body>
	<div class="container text-center" id="outter">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<a href="<%=request.getContextPath()%>/front/index.jsp">
				<img src="<%=request.getContextPath()%>/images/For House logo.png" alt="回ForHouse首頁" title="回ForHouse首頁" width="120px"></a>
			</div>
		</div>
	</div>
	<div class="container text-center" id="loginDiv">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading rtr_loghead_cooper">
					<h4 class="text-center">
						<label class="control-label rtr_label_cooper">房仲登入</label>
					</h4>
				</div>
				<%-- 錯誤表列 --%>
				<div class="panel-body">
					<c:if test="${not empty loginError}">
						<font style="color: red">${loginError}</font>
					</c:if>
					<form class="form-horizontal loginform" action="realtor.do"
						method="post">
						<div class="form-group">
							<div class="col-sm-12">

								<input type="email" class="form-control" id="loginid"
									name="rtr_id" placeholder="帳號 (請輸入e-mail)">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<input type="password" class="form-control" id="password"
									name="rtr_psw" placeholder="密碼">
							</div>
						</div>
						<!-- FB缺一不可 -->
						<div class="form-group">
							<div id="fb-root">
								<div id="IdShowPanel">
								<div class="col-sm-3"></div>
								<div class="col-sm-3"></div>
								<div class="col-sm-3"></div>
								<div class="col-sm-1"></div>
									<div id="status" class="2" style="margin: 0;">
										<fb:login-button scope="public_profile,email"
											onlogin="checkLoginState();" data-size="large">
										</fb:login-button>
									</div>

								</div>
							</div>
						</div>

						<div class="form-group margin-top" id="loginBtn">
							<div class="col-sm-12 col-sm-3">
								<a class="btn btn-lg btn-danger"
									href="<%=request.getContextPath()%>/front/index.jsp"
									title="回ForHouse首頁" role="button">取消</a>
							</div>
							<div class="col-sm-12 col-sm-3">
								<a class="btn btn-lg btn-info"
									href="<%=request.getContextPath()%>/front/realtor/realtor_forgot.jsp"
									title="忘記密碼" role="button">忘記密碼</a>
							</div>
							<div class="col-sm-12 col-sm-3">
								<a class="btn btn-lg btn-primary"
									href="<%=request.getContextPath()%>/front/realtor/realtor_register.jsp"
									title="註冊房仲" role="button">註冊房仲</a>
							</div>
							<div class="col-sm-12 col-sm-3">
								<input type="submit" value="登入"
									class="btn btn-primary btn-lg btn-block btn_rtr_cooper"
									title="登入房仲"> <input type="hidden" name="action"
									value="realtorLogin">
							</div>
						</div>
						<div class="form-group margin-top" id="loginBtn">
							<div class="row">
								<div class="col-sm-10"></div>
								<div class="col-sm-2" style="margin: 0;">
									<input type="button" class="btn btn-default btn-xs" value="快貼"
										id="magicBtn1">
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>