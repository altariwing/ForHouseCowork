<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.house.model.*"%>
<%@ page import="com.houseImage.model.*"%>
<%@ page import="com.mem.model.*"%>
<%
  MemVO memVO = (MemVO) session.getAttribute("memVO");
%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>forHouse</title>

<style type="text/css">
.check_bar {
	margin-bottom: 30px;
}

.check_bar a {
	background-color: #eeeeee;
	height: 2.3em;
	color: black;
	font-size: 1.5em;
	font-weight: bold;
	font-family: Microsoft JhengHei;
	margin-top: 50px;
	margin-bottom: 30px
}

.check_bar li.active>a {
	background-color: #0064b1
}

/*hr美化*/
hr {
	height: 2px;
	background-color: #555;
	margin-top: 20px;
	margin-bottom: 20px;
	width: 100%;
}
/* 各標題 */
.sub_title {
	font-weight: bold;
	font-size: 2em;
	color: #f37748;
}

/* 預約房屋按鈕 */
.check_btn {
	background-color: #0678c2;
	color: white;
	box-shadow: 2px 2px 2px gray;
}
/* 取消房屋按鈕 */
.cancel_btn {
	background-color: #F6BF02;
	color: white;
	box-shadow: 2px 2px 2px gray;
}
/* 加入收藏按鈕 */
.follow_btn {
	background-color: #f6bf02;
	color: white;
	box-shadow: 2px 2px 2px gray;
}
/* 篩選條件的按鈕 */
.btn-block button {
	width: 80px;
	zoom: 1.3;
	box-shadow: 2px 2px 2px gray;
}
/* 下拉式選單按鈕的容器 */
.btn-group {
	margin-left: 5px;
}
/* 下拉式按鈕跳出的清單 */

/* 會讓物件置中 */
.vertical-horizontal::before {
	content: '';
	width: 0;
	height: 100%;
	display: inline-block;
	position: relative;
	vertical-align: middle;
	background: #f00;
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
	min-height: 200px;
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

body {
	font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體,
		sans-serif;
}

.navbar {
	background-color: #ffffff;
	margin-bottom: 0;
	border-radius: 0;
	height: 80px;
	box-shadow: 0px 2px 1px #bdbdbd;
}

.navshadow {
	box-shadow: 0px 2px 1px #bdbdbd;
}

.activebar>li>a {
	background-color: #ffffff;
	font-size: 22px;
	padding-top: 30px;
	padding-bottom: 0px;
	height: 80px;
}

.logina>li>a {
	background-color: #ffffff;
	padding-top: 2em;
	padding-bottom: 0px;
	height: 80px;
}

.backgroundpng {
	position: fixed;
	top: 0;
	z-index: -15;
}

.backgroundpng img {
	width: 115%;
	opacity: 0.9;
}

.form-control {
	height: 38.4px;
	border-radius: 0px;
}

.searchbar {
	position: absolute;
	top: 21em;
}

.search-filter {
	height: 38.4px;
	text-align: center;
	border: none;
	font-size: 20px;
}

.search-form-btn {
	color: #fff;
	background: #231f20;
	border: none;
	height: 38.4px;
	border-radius: 0 3px 3px 0;
	width: calc(60px);
}

.navbar-brand {
	z-index: 20;
}

.navbar-brand img {
	background-color: #FFF;
	border-radius: 15px;
}

.tooltip-inner {
	font-size: 22px;
	background-color: #fff;
	border-radius: 0px;
}

.input-group-btn:last-child>.btn, .input-group-btn:last-child>.btn-group
	{
	padding-top: 0.7em;
}

a {
	color: #00ADEE;
}

a:hover {
	text-decoration-line: none;
}

a:active {
	　text-decoration-line: none;
}

a:visited {
	text-decoration-line: none;
}

.main {
	padding-bottom: 100px;
}

footer {
	margin-top: 40em;
	font-weight: 700;
	background-color: white;
	position: absolute;
	width: 100%;
	padding: 25px;
	padding-top: 4em;
}

.copyri {
	margin-top: 3em;
}

nav * {
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="backgroundpng">
		<img class="backgroundpng"
			src="<%=request.getContextPath()%>/images/sinyi_bg.png">
	</div>

	<div class="main">
		<nav class="navbar navbar-fixed-top">
			<jsp:include page="/front/navbar.jsp" />
		</nav>
	</div>

	<style type="text/css">

/* 強迫把網頁內容縮到這個大小 */
.limited {
	width: 64.3em;
}
</style>


	<div class="row check_bar">
		<ul class="nav nav-pills nav-justified ">
			<li class="active"><a href="#">1.確認房屋</a></li>
			<li><a href="#">2.預約房仲&&時間</a></li>
			<li><a href="#">3.確認預約</a></li>
			<li><a href="#">4.預約成功</a></li>
		</ul>
	</div>


	<div class="container limited">

		<div class="row">
			<h3 class="sub_title">預約清單</h3>
		</div>
		<div class="row checkedList">
		
		<form action="<%=request.getContextPath()%>/appointment/AppointmentServlet.do" method="post">
		
		<div class="testArea">
			<c:forEach var="houseVO" items="${checkedHouseList}">
				<div id="all${houseVO.house_no}" class="row list-item">
					<!--這是物件列表裡其中一欄物件-->
					<!-- 物件左邊區塊 -->
					<div class="col-xs-12 col-sm-3">
						<div class="row">
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
								</a>
								<h3 class="item-title">${houseVO.title}</h3>

								<h5 class="item-subtitle">${houseVO.location}</h5>
							</div>
						</div>
						<ul class="item-detail">
							<li>${houseVO.house_type }</li>
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
							<div class="col-xs-12 col-sm-12 row">
								<!-- <FORM -->
								<%-- action="<%=request.getContextPath()%>/appointment/AppointmentServlet.do?action=removeFromAppointment&house_no=${houseVO.house_no}" --%>
								<!-- method="post"> -->
								<button type="button" id="${houseVO.house_no}"
									class="bigger cancel_btn btn dropdown-toggle">取消預約</button>
								<input name="house_no" type="hidden" value="${houseVO.house_no}">
								<%-- <input type="hidden" name="house_no" value="${houseVO.house_no}"> --%>
								<!-- <input type="hidden" name="action" value="removeFromAppointment"> -->
								<!-- </FORM> -->
							</div>
						</div>
						<!-- 一欄物件結束 -->
					</div>
				</div>
			</c:forEach>
			</div>
				<input type="hidden" name="action" value="checkHouseList">
				<input type="hidden" name="mem_no" value="<%=memVO.getMem_no()%>">
				<button type="submit" class="btn btn-warning bigger" style="float: right">預約看屋去</button>
			</form>
		</div>
		
		
		<hr>
		<h3 class="sub_title">我的收藏</h3>

		<div class="row favoriteList">
			<c:forEach var="houseVO" items="${HseCollList}">
				<!--這是物件列表裡其中一欄物件-->
				<div id="all${houseVO.house_no}" class="row list-item">
					<!-- 物件左邊區塊 -->
					<div class="col-xs-12 col-sm-3">
						<div class="row">
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
								</a>
								<h3 class="item-title">${houseVO.title}</h3>

								<h5 class="item-subtitle">${houseVO.location}</h5>
							</div>
						</div>
						<ul class="item-detail">
							<li>${houseVO.house_type }</li>
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
							<div class="col-xs-12 col-sm-12 row">
								<!-- 								<FORM -->
								<%-- 									action="<%=request.getContextPath()%>/appointment/AppointmentServlet.do?action=addToAppointment&house_no=${houseVO.house_no}" --%>
								<!-- 									method="post"> -->
								<button id="${houseVO.house_no }" type="button"
									class="bigger check_btn btn dropdown-toggle">加入預約</button>
								<input name="house_no" type="hidden" value="${houseVO.house_no}">
								<%-- <input type="hidden" name="house_no" value="${houseVO.house_no}"> --%>
								<!-- <input type="hidden" name="action" value="addToAppointment"> -->
								<!-- 								</FORM> -->
							</div>


						</div>

					</div>
				</div>
				<!-- 一欄物件結束 -->
			</c:forEach>
		</div>

	</div>


	<!-- 燈箱內容開始 -->
	<!-- 註冊燈箱 -->
	<div class="modal fade" id="registerForm">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a href='#loginForm' data-toggle="modal"><span
						class="glyphicon glyphicon-log-in"></span> 登入</a>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="text-center">
						<label class="control-label">註冊會員</label>
					</h4>
				</div>
				<form class="form-horizontal" action="" method="post">
					<div class="input-group margin-top">
						<input type="text" class="form-control" id="mem_mobile"
							name="mem_mobile" placeholder="手機號碼"> <span
							class="input-group-btn">
							<button type="button" class="btn btn-default" id="send_code">發送驗證碼</button>
						</span>
					</div>
					<div class="form-group margin-top">
						<input type="text" class="form-control box-width" id="get_code"
							name="get_code" placeholder="輸入驗證碼">
					</div>
					<div class="form-group margin-top">
						<input type="email" class="form-control box-width" id="mem_id"
							name="mem_id" placeholder="帳號(請輸入email)">
					</div>
					<div class="form-group margin-top">
						<input type="password" class="form-control box-width"
							id="password1" name="Password" placeholder="密碼">
					</div>
					<div class="form-group margin-top">
						<input type="password" class="form-control box-width"
							id="password2" placeholder="確認密碼">
					</div>
					<div class="form-group margin-top">
						<input type="text" class="form-control box-width" id="mem_name"
							name="memName" placeholder="姓名">
					</div>
					<div class="form-group margin-top">
						<input type="email" class="form-control box-width"
							id="mem_address" name="Address" placeholder="地址">
					</div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-11">
							<div class="checkbox">
								<label> <input type="checkbox" name="aggrement">
									若要繼續註冊，請先閱讀並同意好房事的服務條款 & 隱私權政策
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<button type="button"
							class="btn btn-primary btn-lg btn-block box-width">註冊</button>
						<div class="col-sm-offset-10 col-sm-2">
							<button type="button" class="btn btn-default btn-xs"
								id="magicBtn2">貼</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


	<!-- 登入燈箱 -->
	<div class="modal fade" id="loginForm">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">登入</h4>
				</div>
				<form class="form-horizontal" action="loginCheck.do" method="post">
					<div class="form-group margin-top">
						<input type="email" class="form-control box-width" id="loginid"
							name="ID" placeholder="帳號(請輸入email)">
					</div>
					<div class="form-group margin-top">
						<input type="password" class="form-control box-width"
							id="password" name="Password" placeholder="密碼">
					</div>
					<div class="form-group">
						<button type="button"
							class="btn btn-primary btn-lg btn-block box-width">登入</button>
						<div class="col-sm-offset-10 col-sm-2">
							<button type="button" class="btn btn-default btn-xs"
								id="magicBtn1">貼</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 燈箱內容結束 -->




	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.5/sweetalert2.all.js"></script>
	<script>
	function removeFromCheckedList(event){
		var id = event.target.id;
		console.log(id)
		$("#"+id).text("加入預約");
		$("#"+id).removeClass("cancel_btn");
		$("#"+id).addClass("check_btn");
		$("#"+id).unbind();
		$("#"+id).click(addToCheckedList);
		$('.favoriteList').prepend($('#all'+id));
		
		$.ajax({
			type:'post',
			url:'<%=request.getContextPath()%>/appointment/AppointmentServlet.do',
										data : {
											action : 'removeFromAppointment',
											house_no : id,
										},

										success : function(response) {
// 											swal({
// 												type : 'success',
// 												title : '新增成功',
// 												showConfirmButton : true,
// 												timer : 1200
// 											})
										},

										error : function() {
// 											swal({
// 												type : 'error',
// 												title : '已經在你的最愛了',
// 												showConfirmButton : true,
// 												timer : 1200
// 											})
										},
									});
						}
	
	$('.cancel_btn').click(removeFromCheckedList);
						
						
						
	function addToCheckedList(event){
		var id = event.target.id;
		console.log(id)
		$("#"+id).text("取消預約");
		$("#"+id).removeClass("check_btn");
		$("#"+id).addClass("cancel_btn");
		$("#"+id).unbind();
		$("#"+id).click(removeFromCheckedList);
		$('.testArea').append($('#all'+id));		
		$.ajax({
			type:'post',
			url:'<%=request.getContextPath()%>/appointment/AppointmentServlet.do',
										data : {
											action : 'addToAppointment',
											house_no : id
										},

										success : function(response) {
// 											swal({
// 												type : 'success',
// 												title : '新增成功',
// 												showConfirmButton : true,
// 												timer : 1200
// 											})
										},

										error : function() {
// 											swal({
// 												type : 'error',
// 												title : '已經在你的最愛了',
// 												showConfirmButton : true,
// 												timer : 1200
// 											})
										},
									});
						}
	$('.check_btn').click(addToCheckedList);
	</script>

</body>
</html>
