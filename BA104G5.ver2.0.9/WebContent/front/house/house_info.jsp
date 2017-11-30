<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.house.model.*"%>
<%@ page import="com.houseImage.model.*"%>

<%
	HouseVO houseVO = (HouseVO) request.getAttribute("houseVO");
	HouseImageService svc = new HouseImageService();
	List<HouseImageVO> list = svc.findByHouseNo(houseVO.getHouse_no());
	pageContext.setAttribute("ImageList", list);
%>

<html lang="">
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 此行為顯示IE相容性版本 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<link rel="shortcut icon" href="image/Houselogo1.png" />
<title>好事多_房屋瀏覽</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">

/*強迫限制大小*/
.limited {
	width: 64.3em;
}

/*水平置中*/
.vertical-horizontal::before {
	content: '';
	width: 0;
	height: 100%;
	display: inline-block;
	position: relative;
	vertical-align: middle;
	background: #f00;
}

/*hr美化*/
hr {
	height: 2px;
	background-color: #555;
	margin-top: 20px;
	margin-bottom: 20px;
	width: 100%;
}
/*強迫不換行*/
.sameRow {
	display: inline
}
/*預約看屋按鈕*/
.check_btn {
	width: 100%;
	background-color: #0678c2;
	color: white;
	box-shadow: 2px 2px 2px gray;
}
/*收藏房屋按鈕*/
.follow_btn {
	width: 100%;
	background-color: #F6BF02;
	color: white;
	box-shadow: 2px 2px 2px gray;
}
/*主區塊*/
.main_block {
	background: #eeeeee;
	border: solid 1px #dddddd;
}
/*主區塊圖片*/
.main_img {
	min-width: 600px;
	max-width: 600px;
	min-height: 400px;
	max-height: 400px;
}

.main_img img {
	min-width: 600px;
	max-width: 600px;
	min-height: 400px;
	max-height: 400px;
}

/*主區塊標題*/
.info_title {
	margin-top: 0;
	font-size: 2em;
	font-weight: bold;
	font-family: Microsoft JhengHei;
}
/*主區塊價錢*/
.price_highlight {
	color: red;
	font-size: 2em;
	font-weight: bold
}
/*主區塊小字區塊*/
.main_block_detail {
	margin-top: 1em
}
/*主區塊的小字區塊的小字*/
.main_block_detail div {
	font-family: Microsoft JhengHei;
	font-size: 1.2em
}
/*細節表單*/
.detail-total-form {
	/*border: solid 1px #dddddd;*/
	background: #ffffff;
}
/*細節表單每個td標籤*/
.detail-total-form td {
	height: 3.5em
}

.detail-total-form td li {
	line-height: 1.5em
}
/*細節表單每個td標籤都垂直置中*/
.detail-total-form td::before {
	content: '';
	width: 0;
	height: 100%;
	display: inline-block;
	position: relative;
	vertical-align: middle;
	background: #f00;
}
/*主區塊外的標題*/
.form_title {
	font-weight: bold;
	font-size: 1.5em;
	color: #f37748;
}
/*廣告CSS*/
#abgne_float_ad {
	display: none;
	position: absolute;
}

#abgne_float_ad img {
	border: none;
}

.ad_btn {
	width: 100px;
	height: 60px;
}
/*廣告CSS結束*/
#map {
	height: 400px;
	width: 100%;
}
/*一欄推薦物件*/
.rec_item {
	background-color: white
}

.rec_item * {
	color: black
}
/*推薦物件的圖片*/
.rechouse_Img {
	width: 100%;
	height: 210px
}
/*推薦物件的標題*/
.rec_price h4 {
	color: red;
}

.mb10 {
	margin-bottom: 20px
}

.w80 {
	width: 80%;
}

.pd20 {
	padding: 20px;
}

div.backgroundpng {
	position: fixed;
	top: 0;
	z-index: -15;
}

.backgroundpng img {
	width: 115%;
	opacity: 0.9;
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

footer {
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

.unlimited {
	width: 69%;
}

.mt9 {
	margin-top: 9em;
}
</style>
</head>
<body>

	<div class="backgroundpng">
		<img class="backgroundpng"
			src="<%=request.getContextPath()%>/images/sinyi_bg.png">
	</div>

	<!-- nav bar -->
	<nav class="navbar navbar-fixed-top">
		<jsp:include page="/front/navbar.jsp" />
	</nav>
	<!-- nav bar結束 -->
	<style type="text/css">

/* 強迫把網頁內容縮到這個大小 */
.limited {
	width: 64.3em;
}
</style>
	<!-- 麵包屑 -->
	<div class="limited container mt9">
		<div class="col-xs-12 col-sm-3"></div>
		<ol class="breadcrumb">
			<li><a href="#">首頁</a></li>
			<li><a href="#">看房去</a></li>
			<li class="active">${houseVO.title}</li>
		</ol>
		<div class="col-xs-12 col-sm-9"></div>
	</div>

	<!-- 主區塊 -->
	<div class="limited container main_block">
		<div class="row">
			<!-- 主區塊左側 -->
			<div class="col-xs-12 col-sm-8">
				<div class="row">
					<!-- 跑馬燈區塊開始 -->
					<div id="myCarousel" class="carousel slide" data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
							<c:forEach varStatus="s" items="${ImageList}">
								<li data-target="#myCarousel" data-slide-to="${s.count}"></li>
							</c:forEach>
						</ol>
						<!-- Wrapper for slides -->
						<div class="carousel-inner">
							<div class="item main_img active">
								<img class="img_of_td"
									src="<%=request.getContextPath()%>/house/ImageReader/${houseVO.house_no}">
							</div>


							<!-- 跑馬燈照片開始 -->
							<c:forEach var="HouseImageVO" items="${ImageList}">
								<div class="item main_img">
									<img class="img_of_td"
										src="<%=request.getContextPath()%>/houseImage/ImageReader/${HouseImageVO.getImg_no()}">
								</div>
							</c:forEach>
							<!-- 跑馬燈照片結束 -->



						</div>

						<!-- Left and right controls -->
						<a class="left carousel-control" href="#myCarousel"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#myCarousel"
							data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
					<!-- 跑馬燈區塊結束 -->
				</div>
			</div>
			<!-- 左側區塊結束 -->
			<!-- 主區塊右側 -->
			<div class="col-xs-12 col-sm-4">
				<div class="col-sx-12 col-sm-12">
					<!-- 標題 -->
					<div class="row info_title">
						<p>${houseVO.title}</p>
					</div>
					<!-- 地區+編號+價錢 -->
					<div class="row">
						<div class="col-xs-12 col-sm-8">
							<div class="row">
								${houseVO.location}<br>
								${houseVO.re_no}:${houseVO.house_serial_number}
							</div>
						</div>
						<div class="col-sx-12 col-sm-4">
							<div class="row">
								<p class="sameRow price_highlight">${houseVO.price}</p>
								<p class="sameRow">萬</p>
							</div>
						</div>
					</div>
					<!-- 主要區塊的細節們 -->
					<div class="row main_block_detail"></div>
					<div class="row main_block_detail">
						<div>${houseVO.pattern}</div>
					</div>
					<div class="row main_block_detail">
						<div>屋齡:${houseVO.age}年</div>
					</div>
					<div class="row main_block_detail">
						<div class="sameRow">類型:${houseVO.house_type}</div>
					</div>
					<div class="row main_block_detail">
						<div class="sameRow">樓層:${houseVO.floor}</div>
					</div>

					<div class="row">
						<form
							action="<%=request.getContextPath()%>/appointment/AppointmentServlet.do">
							<input type="hidden" name="house_nos" value="${houseVO.house_no}">
							<input type="hidden" name="action"
								value="makeAppointmentByHouseNo">
							<button type="submit"
								class="check_btn main_block_detail btn  dropdown-toggle">
								預約看屋</button>
						</form>
					</div>
					<div class="row">
						<a id="${houseVO.house_no}"
							class="addFavor follow_btn main_block_detail btn  dropdown-toggle">
							收藏房屋 </a>
					</div>
				</div>
			</div>
			<!-- 主區塊右側結束 -->
		</div>
		<!-- 主區塊第一列結束 -->
	</div>
	<!-- 主區塊結束 -->

	<!-- 分隔線 -->
	<div class="limited container">
		<div class="row">
			<hr>
		</div>
	</div>



	<!-- 詳細資訊表單開始 -->
	<div class="limited container detail-total-form">
		<!-- 詳細資訊表格左邊 -->
		<!-- 其中一欄資訊表格開始 -->
		<div class="row">
			<h3 class="form_title">房屋詳細資料</h3>
			<table class="table table-hover">

				<thead>
				</thead>
				<tbody>
					<tr>
						<td colspan="2">地址：${houseVO.location}</td>
					</tr>
					<tr>
						<td>所屬房屋公司:${houseVO.re_no}</td>
						<td>物件序號:${houseVO.house_serial_number}</td>
					</tr>

					<tr>
						<td>總價：${houseVO.price}萬</td>
						<td>格局：${houseVO.pattern}</td>

					</tr>
					<tr>
						<td>屋齡：${houseVO.age}年</td>
						<td>類型：${houseVO.house_type}</td>
					</tr>
					<tr>
						<td>朝向：${houseVO.orientation}</td>
						<td>建材：${houseVO.building_materials}</td>

					</tr>
					<tr>
						<td colspan="2">停車位：${houseVO.parking_space}</td>


					</tr>
					<tr>
						<td>土地坪數：${houseVO.land_pings}</td>
						<td>${houseVO.classification_of_land}</td>
					</tr>


				</tbody>
			</table>

		</div>
		<!-- 其中一欄資訊表格結束 -->

		<!-- 其中一欄資訊表格開始 -->
		<div class="row">
			<h3 class="form_title">建物登記坪數</h3>
			<table class="table table-hover">

				<thead>
				</thead>
				<tbody>
					<tr>
						<td colspan="3">建物總坪數：${houseVO.total_pings} 坪</td>
					</tr>
					<tr>
						<td>主建物坪數：${houseVO.main_pings}坪</td>
						<td>附屬建物坪數：${houseVO.accessory_pings}坪</td>
						<td>共有建物坪數：${houseVO.amenity_pings}坪</td>
					</tr>

				</tbody>
			</table>

		</div>
		<!-- 其中一欄資訊表格結束 -->
	</div>
	<!-- 詳細資訊表單結束 -->

	<!-- GOOGLE MAP開始 -->
	<div class="limited container mb10">
		<div class="row">
			<h3 class="form_title">周邊機能地圖</h3>
			<div id="map"></div>
		</div>
	</div>
	<!-- GOOGLE MAP結束 -->

	<!-- 推薦物件區塊開始 -->
	<div class="limited container">
		<div class="row">
			<h3 class="form_title">熱門物件推薦</h3>

			<!-- 一格介紹區塊開始 -->
			<div class="col-xs-12 col-sm-4 rec_item pd20 ad_describe_block">
				<a
					href="<%=request.getContextPath()%>/house/houseServlet.do?action=getOneHouseInfo_b&house_no=${houseVO1.house_no}">
					<div class="row">
						<img class="rechouse_Img"
							src="<%=request.getContextPath()%>/house/ImageReader/${houseVO1.house_no}">
					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-8">
							<h4>${houseVO1.title}</h4>
						</div>
						<div class="col-xs-12 col-sm-4 rec_price">
							<h4>${houseVO1.price}萬</h4>
						</div>
					</div>

					<div class="rec_content">${houseVO1.location}</div>
					<div class="rec_type sameRow">${houseVO1.house_type}</div>
					<div class="rec_pattern sameRow">${houseVO1.pattern}</div>
					<div class="rec_age sameRow">${houseVO1.age}年</div>
				</a>
			</div>
			<!-- 一格介紹區塊結束 -->



			<!-- 一格介紹區塊開始 -->
			<div class="col-xs-12 col-sm-4 rec_item pd20 ad_describe_block">
				<a
					href="<%=request.getContextPath()%>/house/houseServlet.do?action=getOneHouseInfo_b&house_no=${houseVO2.house_no}">
					<div class="row">
						<img class="rechouse_Img"
							src="<%=request.getContextPath()%>/house/ImageReader/${houseVO2.house_no}">
					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-8">
							<h4>${houseVO2.title}</h4>
						</div>
						<div class="col-xs-12 col-sm-4 rec_price">
							<h4>${houseVO2.price}萬</h4>
						</div>
					</div>

					<div class="rec_content">${houseVO2.location}</div>
					<div class="rec_type sameRow">${houseVO2.house_type}</div>
					<div class="rec_pattern sameRow">${houseVO2.pattern}</div>
					<div class="rec_age sameRow">${houseVO2.age}年</div>
				</a>
			</div>
			<!-- 一格介紹區塊結束 -->


			<!-- 一格介紹區塊開始 -->
			<div class="col-xs-12 col-sm-4 rec_item pd20 ad_describe_block">
				<a
					href="<%=request.getContextPath()%>/house/houseServlet.do?action=getOneHouseInfo_b&house_no=${houseVO3.house_no}">
					<div class="row">
						<img class="rechouse_Img"
							src="<%=request.getContextPath()%>/house/ImageReader/${houseVO3.house_no}">
					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-8">
							<h4>${houseVO3.title}</h4>
						</div>
						<div class="col-xs-12 col-sm-4 rec_price">
							<h4>${houseVO3.price}萬</h4>
						</div>
					</div>

					<div class="rec_content">${houseVO3.location}</div>
					<div class="rec_type sameRow">${houseVO3.house_type}</div>
					<div class="rec_pattern sameRow">${houseVO3.pattern}</div>
					<div class="rec_age sameRow">${houseVO3.age}年</div>
				</a>
			</div>
			<!-- 一格介紹區塊結束 -->


		</div>
	</div>



	<!-- 推薦物件區塊結束 -->





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
			</div>
		</div>
		<div class="copyri text-center">
			<p>
				<small>Copyright © 2017 For House</small>
			</p>
		</div>
	</footer>


	<!-- 以下是所有的JAVASCRIPT -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.5/sweetalert2.all.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- 		GOOGLEMAP JAVASCRIPT -->
	<script>
		function initMap() {
			var uluru = {
				lat : ${houseVO.lat},
				lng : ${houseVO.lng}
			};
			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 4,
				center : uluru,
				zoom: 19
				
			});
			var marker = new google.maps.Marker({
				position : uluru,
				map : map
			});
		};
	</script>
	<script>
	
	$('.addFavor').click(function(event){
		var id = event.target.id;
		console.log(id);
		
		$.ajax({
			type:'post',
			url:'<%=request.getContextPath()%>/hesColl/HseCollServlet.do',
			data:{
				action:'insert',				
				house_no:id,
				mem_no:'${memVO.mem_no}',							
				},
			success:function(response){
				
				swal({
					type:'success',
					title:'新增成功',
					showConfirmButton:true,
					timer:1200					
					})	
								
				},
				
				error:function(){
					swal({
					type:'error',
					title:'已經在你的最愛了',
					showConfirmButton:true,
					timer:1200		
					})
				},			
		})		
	});
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATnJXHEYAz4_W9Aoy_T2y6wdRNdKy3r6g&callback=initMap">
		
	</script>


</body>
</html>