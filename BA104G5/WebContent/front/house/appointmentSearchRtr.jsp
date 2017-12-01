<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.resrec.model.*"%>
<%@ page import="com.house.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.realestate.model.*"%>

<jsp:useBean id="realestateSvc" scope="page" class="com.realestate.model.RealEstateService" />
<jsp:useBean id="list" scope="request" type="java.util.List<RealtorVO>" />
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	if (memVO == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
		session.setAttribute("location", request.getRequestURI()); //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
		response.sendRedirect(request.getContextPath() + "/login.jsp"); //*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
		return;
	}

// 	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
// 	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
// 	response.setDateHeader("Expires", 0);

	List<ResRecVO> resRecVO = new ResRecService().getMem_kuei(memVO.getMem_no());
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

.fuevent .fc-event {
	background-color: white;
	color: black;
	cursor: pointer;
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
			<li><a href="#">1.確認房屋</a></li>
			<li class="active"><a href="#">2.預約房仲</a></li>
			<li><a href="#">3.預約時間</a></li>
			<li><a href="#">4.確認預約</a></li>
			<li><a href="#">5.預約成功</a></li>
		</ul>
	</div>


	<div class="container-fluid">

		<div class="row">
			<h3 class="sub_title">預約清單(VO版，裡面為HouseVO)</h3>
		</div>
		<div class="checkedList">
			<div class="col-xs-12 col-sm-5 fuevent">
				<c:forEach var="houseVO" items="${checkedHouseList}">
					<div id="all${houseVO.house_no}" class="list-item fc-event">
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
								<div class="col-xs-12 col-sm-12 row"></div>
							</div>
							<!-- 一欄物件結束 -->
						</div>
					</div>

				</c:forEach>
			</div>

			<div class="col-xs-12 col-sm-7">
<!-- 			<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/front/realtor/realtor.do" --%>
<!-- 						id="form1"> -->
<!-- 						<div class="btn-group"> -->
<!-- 							<b>選擇地區:</b> <select size="1" name="RTR_AREA" id="rtr_area"> -->
<!-- 								<option value="">搜尋服務地區</option> -->
<%-- 								<c:forEach var="realtorVO" items="${list2}"> --%>
<%-- 									<option value="${realtorVO.rtr_area }">${realtorVO.rtr_area}</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 						<div class="btn-group"> -->
<!-- 							<b>選擇服務公司:</b> <select name="RE_NO" id="re_no"> -->
<!-- 								<option value="">搜尋服務公司</option> -->
<%-- 								<c:forEach var="realestateVO" items="${estatelist}"> --%>
<%-- 									<option value="${realestateVO.re_no}">${realestateVO.re_name}</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 						<input type="hidden" name="action" value="listQueryB"> <input -->
<!-- 							type="submit" value="送出"> -->
<!-- 					</form> -->
				

	<c:forEach var="realtorVO" items="${list}">
		<div class="container container_size">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-sm-offset-1 div_sm_12 rtrbar">
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
						<p class="intro_content text_overflow">${realtorVO.rtr_intro}</p>
					</div>

					<div class="col-sm-3">
						<div class="wrapper">
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<br>
	</c:forEach>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				
			</div>
		</div>
	</div>
					</div>
		</div>
	</div>

</body>
</html>
