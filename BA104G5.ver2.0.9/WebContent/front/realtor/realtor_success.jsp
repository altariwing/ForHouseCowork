<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.realtor.model.*"%>

<%
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>

<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>註冊成功</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
<title>For House</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/main.js"></script>

<style type="text/css">
body {
	font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體,
		sans-serif;
}

#outter {
	margin-top: 1em;
}

#success {
	margin-top: 20px;
	text-align: center;
}

.realtor_success {
	border: 1px solid #f26649;
}
</style>

</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-sm-6 col-sm-offset-3  realtor_success">
				<div id="success" class="col-sm-6 col-sm-offset-3">
					<a href="<%=request.getContextPath()%>/front/realtor/realtor_center.jsp">
						<img src="<%=request.getContextPath()%>/images/For House logo.png" width="120px">
					</a>
					<h3>房仲註冊系統</h3>
				</div>
				<div id="success" class="col-sm-6 col-sm-offset-3">
					<button type="button" class="btn btn-success btn-lg">
						<h1>加入成功</h1>
					</button>
					<h3>
						<p>請等待審核以啟用帳號</p>
						<p>審核通過會以您註冊的e-mail通知您</p>
					</h3>
					<h4>
<!-- 						<p> -->
<%-- 							<a href="<%=request.getContextPath()%>/front/realtor/realtor_center.jsp"><button>進入房仲會員中心</button></a> --%>
<!-- 						</p> -->
						<p>
							<a href="<%=request.getContextPath()%>/front/index.jsp"><button>進入ForHouse首頁</button></a>
						</p>
					</h4>
				</div>
			</div>
		</div>
	</div>
</body>
</html>