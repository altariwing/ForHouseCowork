<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<html>

<head>
	<meta charset="utf-8">
    <meta http-equiv="refresh" content="8; url=index.jsp">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
    <title>For House</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <style type="text/css">
    body {
        font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
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

    footer {
        background-color: white;
        padding-top: 4em;
        margin-top: 800px;
    }

    .copyri {
        margin-top: 3em;
    }

	#success {
		margin-top: 200px;
		text-align: center;
	}
   
    </style>
    <script>
        $(function(){
            $('[data-toggle="tooltipNews"]').tooltip(
                {title: "<div><a href='#'>房市新聞</a></div><div><a href='#'>促銷資訊</a></div><div><a href='#'>系統公告</a></div>",
                 html: true,
                 delay:{"show":100,"hide":1000
             }});
        });
    </script>
</head>

<body>

    <div class="container-fluid backgroundpng">
        <img class="row" src="<%=request.getContextPath()%>/images/fixed_bg.png">
    </div>
    <!-- include navbar -->
    <nav class="navbar navbar-fixed-top">
        <jsp:include page="/front/navbar.jsp" />
    </nav>

	
	<div id="success">
		<button type="button" class="btn btn-success btn-lg"><h1>註冊成功</h1></button>
		<h2>
			<p>請收取e-mail完成最後驗證以啟用帳號</p>
		</h2>
		<h3>
			<p>8秒後自動回到首頁</p>
			<p><a href="<%=request.getContextPath()%>/front/index.jsp"><button>立即回到首頁</button></a></p>
		</h3>
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