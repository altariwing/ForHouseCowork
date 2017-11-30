<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>For House! |</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
<!--  Bootstrap CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">

<!-- Custom Theme Style -->
<link href="<%=request.getContextPath()%>/back/css/bklogin.css" rel="stylesheet">
<style id="style-1-cropbar-clipper">/* Copyright 2014 Evernote Corporation. All rights reserved. */
.login {
	background: #EEEEEE
}

.login .fa-paw {
	font-size: 26px
}

.login_wrapper {
	right: 0;
	margin: 5% auto 0;
	max-width: 350px;
	position: relative
}

.form-control {
	margin-right: 5px;
	margin-bottom: 10px
}


</style>
</head>

<body style="background: #F7F7F7">
	<div>

		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content">
				<img class="navshadow" src="<%=request.getContextPath()%>/images/For House logo.png" width="120px">
					<form method="post" action="<%=request.getContextPath()%>/back/login.do">
						<h1>網站管理者登入</h1>
						<div>
							<input class="form-control" placeholder="帳號" required=""
								type="text" name="emp_id">
						</div>
						<div>
							<input class="form-control" placeholder="密碼" required=""
								type="password" name="emp_psw">
						</div>
						<div>
							<button class="btn" type="submit">登入</button>
							
							<a class="btn btn-default submit" href="#">忘記密碼</a>
						</div>
						<%-- 錯誤表列 --%>
				    <div class="panel-body text-center">
				    <c:if test="${not empty errorMsgs}">
						<font style="color:red">${errorMsgs}</font>
					</c:if>
					</form>




				</section>
			</div>


		</div>
	</div>


</body>
</html>