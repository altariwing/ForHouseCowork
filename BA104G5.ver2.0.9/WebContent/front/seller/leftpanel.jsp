<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.slr.model.*"%>
<%
  SlrVO slrVO = (SlrVO) session.getAttribute("slrVO");
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/seller/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    
    
    <style type="text/css">
    .headpic {
        margin-top: 4em;
        border-radius: 99em;
        width: 7em;
        height: 7em;
        overflow: hidden;
        (讓多出來的不顯示。)
    }

    .headpic img {
        height: 7em;
        width: 7em;
    }

    .headbrand {
    	color: white;
        margin-top: 5em;
        padding-bottom: 3em;
    }

    .sidebar {
        margin-top: 80px;
        height: 50em;
        background-color: #337ab7;
    }

    .maincontext {
        margin-top: 100px;
    }

    .paneltitle {
        border-top-left-radius: 0px;
        border-top-right-radius: 0px;
        padding: 15px 15px;
    }

    .panel-title {
        font-size: 18px;
    }

    .panelbody {
        color: #fff;
        background-color: #337ab7;
    }

    .panelbody:hover {
        opacity: 0.8;
    }

    .titletext {
        width: 3em;
        margin-right: 3em;
    }

    .titlebrand {
        color: #fff;
    }

    .titlebrand:hover {
        color: #fff;
        text-decoration-line: none;
        /*opacity: 0.8;*/
        /*font-size: 20px;*/
    }

    .titlebrand:active {
        color: #fff;
        text-decoration-line: none;
    }

    .titlebrand:visited {
        color: #fff;
        text-decoration-line: none;
    }

    .navbrand {
        color: #F48500;
    }

    .paneltext {
        font-size: 6em;
    }
    .linka{
        position:absolute; 
        left: 15em;
    }
    .panelboxtitle{
        margin-top:-1.2em;
    }
    .areaInner{
        margin-top: 140px;
    }

    .basedata{
        margin: 20px;
    }

    .new-prd{
        margin: 20px;
    }
    .box-width{
      font-size: 18px;
      width:690px;
    }
    h2.panel-title {
        font-size: 24px;
    }
    </style>
</head>

<body>

        
        
<div class="col-xs-12 col-sm-2 sidebar">
    <div class="col-xs-12 col-sm-4">
        <div class="headpic row">
            <img src="<%=request.getContextPath()%>/front/seller/images/Home-Sofa-2.jpg">
        </div>
    </div>
    <div class="col-xs-12 col-sm-8 text-center headbrand">
        <h4>Welcome ~</h4>
        <c:if test="${slrVO!=null}">
			<h2><b>
			<%= slrVO.getSlr_name()%>
			</b></h2>
		</c:if>
		<c:if test="${slrVO==null}">
			<h4><b>
			　
			</b></h4>
		</c:if>
	</div>

	<!-- 左側選單 -->
	<div class="panel-group" id="accordion2" role="tablist">
		<div class="panel panel-primary siderbarbox">
		
			<div class="panel-heading paneltitle row" role="tab" id="panel1">
				<h4 class="panel-title">
				    <span class="titletext"><b>基本資料</b></span>
					<a class="titlebrand collapsed linka" href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button">
					    <span class="box-left glyphicon glyphicon-chevron-down"></span>
					</a>
				</h4>
			</div>
			<div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
				<a href="<%=request.getContextPath()%>/front/seller/profile/slrdata.jsp" role="button">
				<div class="panel-body panelbody">資料管理</div>
				</a>
				<a href="<%=request.getContextPath()%>/front/seller/profile/slrpsw.jsp" role="button">
				<div class="panel-body panelbody">更改密碼</div>
				</a>
			</div>
			
			
			<!-- 商品管理  -->
			<div class="panel-heading paneltitle row" role="tab" id="panel2">
				<h4 class="panel-title"><span class="titletext"><b>商品管理</b></span>
					<a class="titlebrand collapsed linka" href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button">
				<span class="box-left glyphicon glyphicon-chevron-down"></span></a></h4>
			</div>
			<div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
				<a href="<%=request.getContextPath()%>/front/seller/product/addprd.jsp" role="button">
					<div class="panel-body panelbody" id="newprdt">上架新商品</div>
				</a>
				<a href="<%=request.getContextPath()%>/front/seller/product/PrdList.jsp" role="button">
					<div class="panel-body panelbody" id="modifyprdt">上架商品維護</div>
				</a>
				<a href="<%=request.getContextPath()%>/front/seller/product/stopSellList.jsp" role="button">
					<div class="panel-body panelbody" id="modifyprdt">已下架商品</div>
				</a>
			</div>
			
			
			<div class="panel-heading paneltitle row" role="tab" id="panel3">
			    <h4 class="panel-title"><span class="titletext"><b>訂單管理</b></span>
				<a class="titlebrand collapsed linka" href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button">
				<span class="box-left glyphicon glyphicon-chevron-down"></span></a>
				</h4>
			</div>
			<div id="ccc" class="panel-collapse collapse" role="tabpanel">
				<a href="<%=request.getContextPath()%>/front/seller/order/OdrList.jsp" role="button">
					<div class="panel-body panelbody">最新訂單</div>
				</a>
				<a href="<%=request.getContextPath()%>/front/seller/order/OdrHistory.jsp" role="button">
					<div class="panel-body panelbody">歷史訂單</div>
				</a>
			</div>
			
			
						
			<div class="panel-heading paneltitle row" role="tab" id="panel2">
				<h4 class="panel-title"><span class="titletext"><b>廣告管理</b></span>
				<a class="titlebrand collapsed linka" href="#eee" data-parent="#accordion2" data-toggle="collapse" role="button">
				<span class="box-left glyphicon glyphicon-chevron-down"></span></a>
				</h4>
			</div>
			<div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
			<a href="<%=request.getContextPath()%>/seller/你的jsp" role="button">
				<div class="panel-body panelbody">申請廣告</div>
			</a>
			<a href="<%=request.getContextPath()%>/seller/你的jsp" role="button">
				<div class="panel-body panelbody">申請進度查詢</div>
			</a>
			</div>
		</div>
	</div>
	<!-- 左側選單 結束-->
</div>


</body>
</html>