<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.resrec.model.*"%>
<%@ page import="com.hsecoll.model.*"%>
<%@ page import="com.house.model.*"%>
<%@ page import="java.util.List"%>



<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/images/houselogo1.png" />
<title>房屋收藏</title>
<body>
	<!-- 背景圖 -->
	<div class="container-fluid backgroundpng">
		<img class="row"
			src="<%=request.getContextPath()%>/images/fixed_bg.png">
	</div>
	<!-- 上方的 header (navbar) -->
	<nav class="navbar navbar-fixed-top">
		<jsp:include page="/front/navbar.jsp" />
	</nav>
	<div class="col-xs-12 memtitle">
		<h2 class="text-center member_center">房屋收藏</h2>
	</div>
	<!-- 下方大區塊 start -->
	<div class="container-fluid areaOutter">
		<div class="row areaOutter">
			<!-- include 左側選單區塊  開始-->
			<jsp:include page="/front/member/leftpanel.jsp" />
			<!-- include 左側選單區塊  結束-->
			<!-- 右側頁面區塊  開始 -->
			<div class="col-xs-12 col-sm-10 areaInner">
				<div class="tab-content">

					<style>
#top {
	background: #eee;
	border-bottom: 1px solid #ddd;
	padding: 0 10px;
	line-height: 40px;
	font-size: 12px;
	color: #000;
}

#top .selector {
	display: inline-block;
	margin-right: 10px;
}

#top select {
	font: inherit; /* mock what Boostrap does, don't compete  */
}

.memtitle {
	margin-top: 70px;
}

.member_center {
	color: #00ADEE;
}

.areaOutter {
	margin-top: 40px;
}

.areaInner {
	margin-top: 20px;
}

h3.panel-title {
	font-size: 24px;
}

.left {
	float: left
}

.right {
	float: right
}

.clear {
	clear: both
}

#eventUrl {
	color: #fff;
}

.trashicon {
	color: #5C9CCC;
	font-size: 2em;
}

.resr_no {
	display: none;
}

.tablebackground {
	background-color: #fff;
}

td:nth-child(1)>div {
	width: 100px;
}

td:nth-child(2)>div {
	width: 150px;
}

th {
	text-align: center;
}

textarea {
	overflow-y: scroll;
	resize: none;
}

.ratetip {
	/* 	background-color:#000; */
	margin-top: 1.2px;
	font-weight: 900;
	color: darkseagreen;
	width: 100px;
	display: none;
	font-size: 20px;
}

/* 每一個物件*/
.list-item {
	box-shadow: 10px 10px 5px gray;
	border: solid #dddddd 1px;
	background-color: #ffffff;
	margin-top: 10px;
	margin-bottom: 30px;
	height: 170px;
}
/* 每一個物件的標題 */
.item-title {
	margin-top: 4px;
	color: blue;
	font-weight: bolder;
	font-family: Microsoft JhengHei;
}
/* 每一個物件的圖片 */
.list-item img {
	min-width: 200px;
	max-width: 200px;
	min-height: 170px;
	max-height: 170px
}
/* 每一個物件的價錢 */
.item-price {
	margin-top: 10px;
	margin-bottom: 5px;
	color: red;
	font-size: 2.3em;
	white-space: nowrap
}
/* 放大按鈕1.5倍 */
.bigger {
	zoom: 1.5
}

.item-subtitle {
	color: #a1a1a1
}

.item-detail li {
	width: 33.333333%;
	float: left
}

.limited {
	width: 64.3em;
}

.ml5 {
	margin-left: 5em;
}

/*  超可愛CHECKBOX */
.cutebox {
	display: block;
	position: relative;
	padding-left: 35px;
	margin-bottom: 12px;
	cursor: pointer;
	font-size: 22px;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

/* Hide the browser's default checkbox */
.cutebox input {
	position: absolute;
	opacity: 0;
}

/* Create a custom checkbox */
.checkmark {
	position: absolute;
	top: 60;
	left: 0;
	height: 90px;
	width: 100px;
	background-color: #ddd;
}

/* On mouse-over, add a grey background color */
.cutebox:hover input ~ .checkmark {
	background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
.cutebox input:checked ~ .checkmark {
	background-color: #2196F3;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
	content: "";
	position: absolute;
	display: none;
}

/* Show the checkmark when checked */
.cutebox input:checked ~ .checkmark:after {
	display: block;
}

/* Style the checkmark/indicator */
.cutebox .checkmark:after {
	left: 30px;
	top: 5px;
	width: 40px;
	height: 70px;
	border: solid white;
	border-width: 0 10px 10px 0;
	-webkit-transform: rotate(45deg);
	-ms-transform: rotate(45deg);
	transform: rotate(45deg);
}

.cancelBtn {
	position: absolute;
	top: 0;
	z-index: 10;
}

/* .RATERTR{ */
/* 	MARGIN: 0PX 30PX; */
/* } */
</style>
					<link rel="stylesheet"
						href="<%=request.getContextPath()%>/tools/ratestar/jquery.rateyo.min.css" />
					<link rel="stylesheet"
						href="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.css">
					<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/tools/ratestar/jquery.min.js"></script> --%>
					<script type="text/javascript"
						src="<%=request.getContextPath()%>/tools/ratestar/jquery.rateyo.js"></script>
					<script
						src='<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.min.js '></script>
					<script
						src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.js"></script>



					<div class="limited ml5">
						<form class="search"
							action="<%=request.getContextPath()%>/appointment/AppointmentServlet.do"
							method="post">
							<c:forEach var="houseVO" items="${Collectionlist}">
								<!--這是物件列表裡其中一欄物件-->
								<div class="row list-item">
									<!-- 物件左邊區塊 -->

									<div class="col-xs-12 col-sm-3">
										<div class="row">
											<div class="cancelBtn">
												<a
													href="<%=request.getContextPath()%>/hesColl/HseCollServlet.do?action=delete&house_no=${houseVO.house_no}&mem_no=${memVO.mem_no}"
													class="cancelBtn btn btn-danger">刪除收藏</a>
											</div>
											<a
												href="<%=request.getContextPath()%>/house/houseServlet.do?action=getOneHouseInfo_b&house_no=${houseVO.house_no}">
												<img class=""
												src="<%=request.getContextPath()%>/house/ImageReader/${houseVO.house_no}">
											</a>
										</div>
									</div>
									<!-- 物件中間區塊 -->
									<div class="col-xs-12 col-sm-7">
										<div class="row">
											<div class="col-xs-12 col-sm-12">
												<a
													href="<%=request.getContextPath()%>/house/houseServlet.do?action=getOneHouseInfo_b&house_no=${houseVO.house_no}">
													<h3 class="item-title">${houseVO.title}</h3>

												</a>
												<h5 class="item-subtitle">${houseVO.location}</h5>
											</div>
										</div>
										<ul class="item-detail">
											<li>${houseVO.house_type}</li>
											<li>${houseVO.age}年</li>
											<li>${houseVO.floor}</li>
											<li>土地：${houseVO.land_pings}坪</li>
											<li>總坪數：${houseVO.total_pings}坪</li>
											<li>主建坪：${houseVO.main_pings}坪</li>
											<li>${houseVO.pattern}</li>
											<li>${houseVO.re_no}-${houseVO.house_serial_number}</li>
										</ul>
									</div>

									<!-- 物件右邊區塊 -->
									<div class="col-xs-12 col-sm-2 row">

										<div class="col-xs-12 col-sm-12">
											<div class="col-xs-12 col-sm-12 row">
												<div class="item-price">${houseVO.price}萬</div>
											</div>
											<div class="col-xs-12 col-sm-12 row"></div>
											<label class="cutebox"> <input name="house_nos"
												value="${houseVO.house_no}" type="checkbox"> <span
												class="checkmark"></span>
											</label>



										</div>
									</div>
								</div>
								<!-- 一欄物件結束 -->
							</c:forEach>
							<input type="hidden" name="action" value="makeAppointmentByHouseNo">

							<c:if test="${Collectionlist.size()!=0}">
								<button class="btn btn-warning bigger" style="float: right"
									type="submit">預約看屋去</button>
							</c:if>
							<c:if test="${Collectionlist.size()==0}">
								<center>
									<img style="width:80em" src="http://animalprotection.hchg.gov.tw/SCA/C/images/nodata.jpg">
								</center>
							</c:if>

						</form>
					</div>

					<!-- 右側頁面區塊結束 -->
					<!-- 下方大區塊 end -->
</body>

</html>