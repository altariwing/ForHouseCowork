<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.house.model.*"%>

<jsp:useBean id="list" scope="request" class="java.util.ArrayList" />

<!DOCTYPE html>
<html lang="">
<head>
<title>好事多_房屋搜尋</title>
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/images/houselogo1.png" />
<style type="text/css">

/* 搜尋輸入列 */
.search-text {
	width: 610px
}
/* 預約房屋按鈕 */
.check_btn {
	background-color: #0678c2;
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
.dropdown-item {
	font-weight: bolder;
	font-family: Microsoft JhengHei;
	font-size: 20px;
	align-content: center;
}
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
div.backgroundpng {
	position: fixed;
	top: 0;
	z-index: -15;
}

.dropdown {
	float: right;
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
	<div class="container limited mt9">
		<div class="col-xs-12 col-sm-6">
			<ol class="breadcrumb">
				<li><a href="#">首頁</a></li>
				<li><a href="#">房屋搜尋</a></li>
				<li class="active">條件搜尋</li>
			</ol>
		</div>
		<div class="col-xs-12 col-sm-6"></div>
	</div>
	<!-- 麵包屑結束 -->

	<!--Search Bar-->
	<div class="container limited search_bar">
		<div class="row">
			<div class="col-xs-12 col-sm-10 col-sm-offset-1"
				style="margin-bottom: 20px">

				<div class="row">
					<a
						href="<%=request.getContextPath()%>/house/houseServlet.do?action=getAll"
						class="btn btn-default">條件搜尋</a> <a
						href="<%=request.getContextPath()%>/front/house/house_search_map.jsp"
						class="btn btn-default">地圖搜尋</a>
				</div>
				<div class="row">

					<div>
						<form class="search"
							action="<%=request.getContextPath()%>/house/houseServlet.do"
							method="post">
							<div class="input-group">
								<span class="input-group-btn"> <!-- <div class="dropdown"> -->
									<a href="#" class="btn btn-info dropdown-toggle search-filter"
									data-toggle="dropdown">台北市 <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="#">松山區</a></li>
										<li><a href="#">大安區</a></li>
										<li><a href="#">中正區</a></li>
										<li><a href="#">大同區</a></li>
										<li><a href="#">中山區</a></li>
										<li><a href="#">萬華區</a></li>
										<li><a href="#">士林區</a></li>
										<li><a href="#">北投區</a></li>
										<li><a href="#">內湖區</a></li>
										<li><a href="#">南港區</a></li>
										<li><a href="#">文山區</a></li>
										<li><a href="#">台灣大學</a></li>

									</ul> <!-- </div> -->
								</span> <input type="text" class="form-control" name="para"
									placeholder="關鍵字、學校、街道"> <input type="hidden"
									name="sortedCondition" value="${sortedCondition}"> <input
									type="hidden" name="action" value="findByKeyword">
								<div class="input-group-btn">
									<button class="btn btn-default search-form-btn" type="submit">
										<!-- <span class="button-text ">找房子 </span> -->
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</div>
						</form>
					</div>

				</div>


				<!--搜尋按鈕btn block	-->
				<div class="row" style="margin-top: 10px">
					<div class="container limited btn-block">
						<div class="col-xs-12 col-sm-12">


							<div class="btn-group">
								<button type="button" class="btn btn-primary dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">區域</button>
								<div class="dropdown-menu">
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=北投區">北投區</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=士林區">士林區</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=內湖區">內湖區</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=中山區">中山區</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=大同區">大同區</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=松山區">松山區</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=中正區">中正區</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=大安區">大安區</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=信義區">信義區</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=南港區">南港區</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=萬華區">萬華區</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_a&para=文山區">文山區</a><br>


								</div>
							</div>


							<div class="btn-group">
								<button type="button" class="btn btn-primary dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">價位</button>
								<div class="dropdown-menu">
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_b&para=0_1000">1000萬以下</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_b&para=1001_2000">1000-2000萬</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_b&para=2001_3000">2000-3000萬</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_b&para=3001_4000">3000-4000萬</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_b&para=4001_5000">4000-5000萬</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_b&para=5000_9999">5000萬以上</a><br>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-primary dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">類型</button>
								<div class="dropdown-menu">
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_d&para=電梯">電梯</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_d&para=公寓">公寓</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_d&para=辦公">辦公</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_d&para=透天">透天</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_d&para=商辦">商辦</a>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-primary dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">房數</button>
								<div class="dropdown-menu">
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_c&para=1">套房</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_c&para=2">兩房</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_c&para=3">三房</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_c&para=4">四房</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_c&para=99">四房以上</a><br>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-primary dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">坪數</button>
								<div class="dropdown-menu">
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_e&para=0_20">20坪以下</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_e&para=21_30">20-30坪</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_e&para=31_40">30-40坪</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_e&para=41_50">40-50坪</a><br>
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/house/houseServlet.do?action=findByCondition_e&para=51_99">50坪以上</a><br>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-warning dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">更多篩選</button>
								<div class="dropdown-menu dropdown-menu-right"
									style="width: 680px">

									<FORM
										action="<%=request.getContextPath()%>/house/houseServlet.do"
										method="post">
										<input type="hidden" name="action" value="findBySeveralConds">
										<div class="col-xs-12 col-sm-2 ">
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="北投區">北投區</label>
											</div>
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="士林區">士林區</label>
											</div>
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="內湖區">內湖區</label>
											</div>
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="中山區">中山區</label>
											</div>
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="大同區">大同區</label>
											</div>
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="松山區">松山區</label>
											</div>
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="中正區">中正區</label>
											</div>
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="大安區">大安區</label>
											</div>
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="信義區">信義區</label>
											</div>
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="南港區">南港區</label>
											</div>
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="萬華區">萬華區</label>
											</div>
											<div class="checkbox">
												<label><input name="location" type="checkbox"
													value="文山區">文山區</label>
											</div>

										</div>
										<div class="col-xs-12 col-sm-3">
											<div class="checkbox">
												<label><input name="price" type="checkbox"
													value="0_1000">1000萬以下</label>
											</div>
											<div class="checkbox">
												<label><input name="price" type="checkbox"
													value="1001_2000">1000-2000萬</label>
											</div>
											<div class="checkbox">
												<label><input name="price" type="checkbox"
													value="2001_3000">2000-3000萬</label>
											</div>
											<div class="checkbox">
												<label><input name="price" type="checkbox"
													value="3001_4000">3000-4000萬</label>
											</div>
											<div class="checkbox">
												<label><input name="price" type="checkbox"
													value="4001_5000">4000-5000萬</label>
											</div>
											<div class="checkbox">
												<label><input name="price" type="checkbox"
													value="5001_9999">5000萬以上</label>
											</div>

										</div>
										<div class="col-xs-12 col-sm-2">
											<div class="checkbox" style="margin-bottom: 10px">
												<label><input name="house_Type" type="checkbox"
													value="電梯">電梯</label>
											</div>
											<div class="checkbox">
												<label><input name="house_Type" type="checkbox"
													value="公寓">公寓</label>
											</div>
											<div class="checkbox">
												<label><input name="house_Type" type="checkbox"
													value="辦公">辦公</label>
											</div>
											<div class="checkbox">
												<label><input name="house_Type" type="checkbox"
													value="透天">透天</label>
											</div>
											<div class="checkbox">
												<label><input name="house_Type" type="checkbox"
													value="商辦">商辦</label>
											</div>

										</div>
										<div class="col-xs-12 col-sm-2">
											<div class="checkbox">
												<label><input name="pattern" type="checkbox"
													value="1">套房</label>
											</div>
											<div class="checkbox">
												<label><input name="pattern" type="checkbox"
													value="2">兩房</label>
											</div>
											<div class="checkbox">
												<label><input name="pattern" type="checkbox"
													value="3">三房</label>
											</div>
											<div class="checkbox">
												<label><input name="pattern" type="checkbox"
													value="4">四房</label>
											</div>
											<div class="checkbox">
												<label><input name="pattern" type="checkbox"
													value="99">五房以上</label>
											</div>
										</div>
										<div class="col-xs-12 col-sm-2">
											<div class="checkbox">
												<label><input name="total_Pings" type="checkbox"
													value="0_20">20坪以下</label>
											</div>
											<div class="checkbox">
												<label><input name="total_Pings" type="checkbox"
													value="21_30">20-30坪</label>
											</div>
											<div class="checkbox">
												<label><input name="total_Pings" type="checkbox"
													value="31_40">30-40坪</label>
											</div>
											<div class="checkbox">
												<label><input name="total_Pings" type="checkbox"
													value="41_50">40-50坪</label>
											</div>
											<div class="checkbox">
												<label><input name="total_Pings" type="checkbox"
													value="50_9999">50坪以上</label>
											</div>
										</div>
										<div class="col-xs-12 col-sm-1">
											<br> <br> <br> <br> <br> <br> <br>
											<br> <br> <br> <br> <br> <br> <br>
											<input type="submit" value="送出">

										</div>
									</FORM>

								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>




	<center style="color: red">${warning}</center>

	<!--共有房屋數&排序條件挑選-->
	<div class="container limited vertical-horizontal">
		<div class="row">
			<div class="col-xs-12 col-sm-2">
				<div class="vertical-horizontal">
					共有<b style="color: red">${list.size()}</b>間房屋
				</div>
			</div>
			<div class="col-xs-12 col-sm-10">

				<div class="dropdown">
					<button style="float: right"
						class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenuButton" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">排序方式</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a
							href="<%=request.getContextPath()%>/house/houseServlet.do?para=${para}&action=${action}&sortedCondition=PRICE">價位低到高</a><br>
						<a
							href="<%=request.getContextPath()%>/house/houseServlet.do?para=${para}&action=${action}&sortedCondition=AGE">屋齡低到高</a><br>
						<a
							href="<%=request.getContextPath()%>/house/houseServlet.do?para=${para}&action=${action}&sortedCondition=LAND_PINGS">地坪低到高</a><br>
						<a
							href="<%=request.getContextPath()%>/house/houseServlet.do?para=${para}&action=${action}&sortedCondition=TOTAL_PINGS">總坪數低到高</a><br>
						<a
							href="<%=request.getContextPath()%>/house/houseServlet.do?para=${para}&action=${action}&sortedCondition=MAIN_PINGS">主坪數低到高</a><br>
					</div>
				</div>


			</div>
		</div>
	</div>


	<!--這是物件列表總表-->
	<div class="container limited item-list">
		<%@ include file="/front/house/page1.file"%>
		<c:forEach var="houseVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<!--這是物件列表裡其中一欄物件-->
			<div class="row list-item">
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
								<h3 class="item-title">${houseVO.title}</h3>

							</a>
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
							<form action="<%=request.getContextPath()%>/appointment/AppointmentServlet.do">
								<input type="hidden" name="house_nos" value="${houseVO.house_no}">
								<input type="hidden" name="action" value="makeAppointmentByHouseNo">
								<button
									type="submit"
									class="bigger check_btn btn dropdown-toggle"> 預約看屋 </button>
							</form>
						</div>

						<div class="col-xs-12 col-sm-12">
							<a id="${houseVO.house_no}"
								class="addFavor follow_btn btn dropdown-toggle"> 收藏房屋</a>
						</div>
					</div>

				</div>
			</div>
			<!-- 一欄物件結束 -->
		</c:forEach>

		<%@ include file="/front/house/page2.file"%>

	</div>
	<!-- 物件列總表結束 -->






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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.5/sweetalert2.all.js"></script>
	<script>
	
	$('.addFavor').click(function(event){
		var id = event.target.id;
		console.log(id);
		
		$.ajax({
			type:'post',
			url:'<%=request.getContextPath()%>/hesColl/HseCollServlet.do',
										data : {
											action : 'insert',
											house_no : id,
											mem_no : '${memVO.mem_no}',
										},

										success : function(response) {
											swal({
												type : 'success',
												title : '新增成功',
												showConfirmButton : true,
												timer : 1200
											})
										},

										error : function() {
											swal({
												type : 'error',
												title : '已經在你的最愛了',
												showConfirmButton : true,
												timer : 1200
											})
										},
									})
						})
	</script>



</body>
</html>