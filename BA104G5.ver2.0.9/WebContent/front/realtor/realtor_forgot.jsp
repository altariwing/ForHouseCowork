<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.realtor.model.*"%>


<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>房仲會員忘記密碼</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">

<!-- cooperkai.css -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front/realtor/css/realtor_cooper.css">
<!-- cooperkai.css -->

<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>


</head>
<body>
	<div class="container text-center" id="outter">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<a href="<%=request.getContextPath()%>/front/index.jsp"><img
					src="<%=request.getContextPath()%>/images/For House logo.png"
					alt="回ForHouse首頁" title="回ForHouse首頁" width="120px"></a>
			</div>
		</div>
	</div>
	<div class="container text-center" id="loginDiv">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading rtr_loghead_cooper">
					<h4 class="text-center">
						<label class="control-label rtr_label_cooper">忘記密碼</label>
					</h4>
				</div>

				<form class="form-horizontal loginform"
					action="<%=request.getContextPath()%>/front/realtor/realtor.do"
					method="post">
					<div class="form-group">
						<div class="container-fluid">
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<div class="row">
									<div class="col-sm-3">
										<font style="color: red">請修正以下錯誤</font>
									</div>
									<div class="col-sm-4">
										<ul style="padding: 0;">
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red" style="padding:0;">${message}</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="rtr_id" class="col-sm-2 control-label">輸入帳號</label>
						<div class="col-sm-9">
							<input type="email" class="form-control" id="rtr_id"
								name="rtr_id" placeholder="帳號(請輸入e-mail)">
						</div>
					</div>
					<div class="form-group">
						<label for="rtr_name" class="col-sm-2 control-label">輸入姓名</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="rtr_name"
								name="rtr_name" placeholder="輸入姓名">
						</div>
					</div>
					<div class="form-group margin-top" id="loginBtn">
						<div class="col-sm-12 col-sm-2"></div>
						<div class="col-sm-12 col-sm-3"></div>
						<div class="col-sm-12 col-sm-3">
							<a class="btn btn-lg btn-danger"
								href="<%=request.getContextPath()%>/front/frontPage/index.jsp"
								title="回ForHouse首頁" role="button">取消</a>
						</div>
						<div class="col-sm-12 col-sm-3">
							<input type="submit" value="救回密碼"
								class="btn btn-primary btn-lg btn-block btn_rtr_cooper"
								title="救回密碼"> <input type="hidden" name="action"
								value="rtrForgot">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</div>

</body>
</html>