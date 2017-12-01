<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.slr.model.*"%>


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/seller/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/seller/js/main.js"></script>    
    
    <style type="text/css">
    body {
        font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
    }
    #outter{
    	margin-top: 4em;
    }    
    #success {
		margin-top: 20px;
		text-align: center;
	}
    </style>
    
</head>

<body>

	<div class="container text-center" id="outter">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<a href="<%=request.getContextPath()%>/seller/slrindex.jsp"><img src="images/For House logo.png" width="120px"></a>
				<h3>廠商註冊系統</h3>
			</div>		
		</div>
	</div>

	
	<div id="success">
		<button type="button" class="btn btn-success btn-lg"><h1>加入成功</h1></button>
		<h2>
			<p>請等待審核以啟用帳號</p>
			<p>審核通過會以您註冊的e-mail通知您</p>
		</h2>
		<h4>
			<p><a href="slrindex.jsp"><button>進入廠商會員中心首頁</button></a></p>
		</h4>
	</div>
	


</body>
</html>