<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.resrec.model.*"%>
<%@ page import="com.followRTR.model.*"%>
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
<title>追蹤房仲</title>
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
		<h2 class="text-center member_center">追蹤清單</h2>
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

.div_sm_11 {
	margin-left: 0;
	border: 1px solid #000000;
	box-shadow: 2px 2px 2px #9D9D9D;
	background-color: #e6ffff;
}

.cancelBtn {
	position: absolute;
	top: 0;
	left: 0;
	z-index: 10;
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
	left: 30;
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

/* .RATERTR{ */
/* 	MARGIN: 0PX 30PX; */
/* } */
</style>
					<link rel='stylesheet prefetch'
						href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>
					<link rel="stylesheet" type="text/css"
						href="<%=request.getContextPath()%>/front/realtor/css/realtor_cooper.css">
					<link rel="stylesheet"
						href="<%=request.getContextPath()%>/tools/ratestar/jquery.rateyo.min.css" />
					<link rel="stylesheet"
						href="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.css">
					<link rel='stylesheet prefetch'
						href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>
					<link rel="stylesheet" type="text/css"
						href="<%=request.getContextPath()%>/front/realtor/css/realtor_cooper.css">
					<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/tools/ratestar/jquery.min.js"></script> --%>
					<script type="text/javascript"
						src="<%=request.getContextPath()%>/tools/ratestar/jquery.rateyo.js"></script>
					<script
						src='<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.min.js '></script>
					<script
						src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.js"></script>


					<div class="limited ml5">
						<form class="search"
							action="<%=request.getContextPath()%>/hesColl/HseCollServlet.do"
							method="post">


							<c:forEach var="realtorVO" items="${list}">
								<div class="container container_size">
									<div class="row">

										<div
											class="col-xs-12 col-sm-12 col-sm-offset-1 div_sm_11 rtrbar tempfixed row">
											<div class="cancelBtn">
												<a
													href="<%=request.getContextPath()%>/followRTR/FRServlet.do?action=delete&rtr_no=${realtorVO.rtr_no}&mem_no=${memVO.mem_no}"
													class="cancelBtn btn btn-danger">刪除收藏</a>
											</div>
											<div class="col-xs-12 col-sm-3">
												<img
													src="<%=request.getContextPath()%>/tool/showimage.do?action=rtr_photo&rtr_no=${realtorVO.rtr_no}"
													alt="${realtorVO.rtr_name}個人照片"
													style="width: 180px; height: 200px;">
											</div>

											<div class="col-xs-12 col-sm-3">
												<ul class="list-unstyled info_estate">
													<li>房仲姓名 ${realtorVO.rtr_name}</li>
													<li>房仲公司
														${realestateSvc.getOne(realtorVO.re_no).getRe_name()}</li>
													<li>服務地區 ${realtorVO.rtr_area}</li>
												</ul>
											</div>

											<div class="col-xs-12 col-sm-3">
												<h2 class="intro_estate">簡介</h2>
												<a href="#"><p class="intro_content text_overflow">${realtorVO.rtr_intro.length()>75?realtorVO.rtr_intro.substring(0,75):realtorVO.rtr_intro.substring(0,realtorVO.rtr_intro.length()-1)}...(see
														more)</p></a>
											</div>

											<div class="col-sm-3">
												<div class="wrapper">
													<div class="container-fluid">




														<label class="cutebox"> <input name="house_nos"
															value="${houseVO.house_no}" type="radio"> <span
															class="checkmark"></span>
														</label>


													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<br>
							</c:forEach>


							<input type="hidden" name="action" value="makeaAppointment">

							<c:if test="${list.size()!=0}">
								<button class="btn btn-warning bigger" style="float: right"
									type="submit">跟他看房去</button>
							</c:if>
							<c:if test="${list.size()==0}">
								<center>
									<img style="width: 80em"
										src="http://animalprotection.hchg.gov.tw/SCA/C/images/nodata.jpg">
								</center>
							</c:if>

						</form>
					</div>

					<!-- 右側頁面區塊結束 -->
					<!-- 下方大區塊 end -->
</body>

</html>