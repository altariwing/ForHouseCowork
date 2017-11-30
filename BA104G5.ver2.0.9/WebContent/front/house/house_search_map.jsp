<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.house.model.*"%>


<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/Houselogo1.png" />
<title>好事多_地圖搜尋</title>

<style type="text/css">

/*強迫不換行*/
.sameRow {
	display: inline
}

/* 強迫把網頁內容縮到這個大小 */
.limited {
	width: 64.3em;
}
.mt9{
	margin-top:9em;
}
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

.backgroundpng img {
	width: 115%;
	opacity: 0.9;
}

/*小逵的CSS*/

.backgroundpng {
	position: fixed;
	top: 0;
	z-index: -15;
}

.backgroundpng img {
	width: 115%;
	opacity: 0.9;
}

#map {
	height: 720px;
	width: 100%;
}

.scroll-bar>div {
	width: 100%;
	overflow: hidden;
}
.scroll-bar{
height:720px;
overflow-y: scroll;
}

.scroll-bar-img {
	height: 140px;
	width: 100%;
}

.scroll-bar-price {
	color: red;
	float: right;
	font-size: 1.5em
}

.scroll-bar-price-count {
	float: right
}

.scroll-bar-title {
	color: #067bc1;
	font-size: 1.2em
}

.scroll-item {
	border: solid 1px #dddddd;
	background-color: white;
}

.scroll-item-datail {
	height: 140px;
	border: solid 1px #dddddd;
	background-color: white;
}
.houseCount{
	font-size:20px;
	color:red;
}
.rightpart{
    margin-top: -28px;
}
</style>
</head>
<body>
	<div class="backgroundpng">
		<img class="backgroundpng" src="<%=request.getContextPath() %>/images/sinyi_bg.png">
	</div>

	
	<nav class="navbar navbar-fixed-top">
			<jsp:include page="/front/navbar.jsp" />
	</nav>
	<!-- 麵包屑 -->
	<div class="container limited mt9">
		<div class="col-xs-12 col-sm-4">
			<ol class="breadcrumb">
				<li><a href="#">首頁</a></li>
				<li><a href="#">房屋搜尋</a></li>
				<li class="active">地圖搜尋</li>
			</ol>
		</div>

		<div class="col-xs-12 col-sm-4">
			<div class="row">
				<a
					href="<%=request.getContextPath()%>/house/houseServlet.do?action=getAll"
					class="btn btn-default">條件搜尋</a> <a
					href="<%=request.getContextPath()%>/front/house/house_search_map.jsp"
					class="btn btn-default">地圖搜尋</a>
			</div>
		</div>
		<div class="col-xs-12 col-sm-4"></div>
	</div>
	<!-- 麵包屑結束 -->
	<div class="row">



		<!-- 左邊地圖區塊開始 -->
		<div class="col-xs-12 col-sm-8">

			<!-- GOOGLE MAP開始 -->
			<div>
				<div class="row">
					<div id="map"></div>
				</div>
			</div>



			<!-- GOOGLE MAP結束 -->

		</div>
		<!-- 左邊地圖區塊結束 -->
		<!-- 右邊物件區塊開始 -->
		<div class="col-xs-12 col-sm-4 row rightpart">

			<!-- 區塊內標題列開始 -->
			<div class="col-xs-12 col-sm-12 row">
				<div class="col-xs-12 col-sm-6">區域範圍內共有<span class="houseCount"> </span>間物件</div>
				<div class="col-xs-12 col-sm-6">
					<form>
						<select name="排序方式">
							<option value="price">價位低到高</option>
							<option value="pings">總坪數低到高</option>
							<option value="main_pings">主建坪低到高</option>
							<option value="land_pings">地坪低到高</option>
							<option value="age">屋齡低到高</option>
						</select>
					</form>
				</div>
			</div>
			<!-- 區塊內標題列結束 -->
			<div class="col-xs-12 col-sm-12">
				<div class="row">
					<nav id="scroll-bar" class="scroll-bar">

					

					</nav>
				</div>
			</div>
		</div>
		<!-- 右邊物件區塊結束 -->
	</div>


	<script src="https://code.jquery.com/jquery.js">
	</script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js">
		
	</script>

	<script>
				var map;
				var markers = [];
				var searchResult;
				var iconBase;
				
			     function initMap() {
			         var myLatlng = {lat: 25.04584, lng: 121.54786};

			         map = new google.maps.Map(document.getElementById('map'), {
			           center: myLatlng,
			           zoom: 18
			         });
			         var marker = new google.maps.Marker({
			           position: myLatlng,
			           map: map,
			           title: 'Click to zoom'
			         });
					
			         
			         // 抓房屋資訊
			         getJSON('<%=request.getContextPath()%>/house/MapServlet.do?action=getHouseJson',callback);
			       
			         map.addListener('center_changed', function() {
			         	deleteMarkers();
			         	getJSON('<%=request.getContextPath()%>/house/MapServlet.do?action=getHouseJson',callback);
							});
			         setMarkers(map);
			         
		}
			
			     function setMarkers(map) {
		        	  
		         }
			     
		function getJSON(url, callback) {
			var xhr = new XMLHttpRequest();
			xhr.open('POST', url, true);
			xhr.responseType = 'json';
			xhr.onload = function() {
				var status = xhr.status;
				if (status === 200) {
					callback(null, xhr.response);
				} else {
					callback(status, xhr.response);
				}
			};
			xhr.send("");
		}

		function callback(err, data) {
			if (err !== null) {
				alert('Something went wrong: ' + err);
			} else {
				searchResult = data;
				console.log(data[0]);
				$("#place").empty();
				var index = 0;
				
				$("#scroll-bar").html("");
				
				for (var i = 0; i < data.length; i++) {
					
					var house_no = data[i].house_no;
					var re_no = data[i].re_no;
					var title = data[i].title;
					var location = data[i].location;
					var floor = data[i].floor;
					var price = data[i].price;
					var total_pings = data[i].total_pings;
					var main_pings = data[i].main_pings;
					var amenity_pings = data[i].amenity_pings;
					var accessory_pings = data[i].accessory_pings;
					var age = data[i].age;
					var pattern = data[i].pattern;
					var orientation = data[i].orientation;
					var building_materials = data[i].building_materials;
					var parking_space = data[i].parking_space;
					var classification_of_land = data[i].classification_of_land;
					var land_pings = data[i].land_pings;
					var data_info = data[i].data_info;
					var lat = data[i].lat;
					var lng = data[i].lng;
					var main_img_base64 =data[i].main_img_base64;
					var latLng = new google.maps.LatLng(lat, lng);
					
					if (map.getBounds().contains(latLng)) {
						
						addMarker(latLng, data[i]);
						$("#scroll-bar").append(			
								
								"<!-- 一欄物件開始 -->"+
								"<div id='"+house_no+"' >"+
									"<div class='col-xs-12 col-sm-4'>"+
										"<div class='row'>"+
											"<a href='"+
											"<%=request.getContextPath()%>/house/houseServlet.do?action=getOneHouseInfo_b&house_no="+
											house_no+
											"'>"+ 
											"<img src='"+
											"<%=request.getContextPath()%>/house/ImageReader/" + 
											house_no +
											"' class='scroll-bar-img'>"+
											"</a>"+
										"</div>"+
									"</div>" +
									"<div class='col-xs-12 col-sm-8 scroll-item-datail'>"+
										"<div style='height: 25px'>"+
											"<a href='house_info.html'>"+
												"<p class='sameRow scroll-bar-title'>"+
												title+
												"</p>"+
											"</a>"+
											"<p class='sameRow scroll-bar-price'>"+
											price+
											"</p>"+
										"</div>"+
										"<br>"+
										"<p>"+
										location+
										"</p>"+
										"<p>"+
											"<font>"+
											"建物" + total_pings +"坪"+
											"</font>"+
											"<font>"+
											age+
											"年"+
											"</font>"+
											"<font>"+
											floor+
											"</font>"+
										"</p>"+
										"<font>"+
										pattern+
										"</font>"+
										"<button class='follow_btn main_block_detail btn dropdown-toggle' style='float: right; margin-bottom: 1px'>收藏房屋</button></div></div>"+
								"<!-- 一欄物件結束 -->"
										
						
						
						);
						index++;
						
					}
				}
				$(".houseCount").text(index);
			}
		}

		function addMarker(latLng, result) {
			iconBase = {
	        	    url: '<%=request.getContextPath()%>/images/Houselogo_burned.png',
	        	    // This marker is 20 pixels wide by 32 pixels high.
 	        	    scaledSize: new google.maps.Size(70, 70)
	        	    // The origin for this image is (0, 0).
// 	        	    origin: new google.maps.Point(0, 0),
// //	        	    // The anchor for this image is the base of the flagpole at (0, 32).
// 	        	    anchor: new google.maps.Point(0, 0)
	        	  };
			
			var marker = new google.maps.Marker({
				position : latLng,
				map : map,
				icon : iconBase
			});
			markers.push(marker);
			
			var infowindow = new google.maps.InfoWindow({
				content:
					"<a href='#"+result.house_no+"'><div><table>"+
					"<tr><th>標題：</th><td>"+result.title+"</td></tr>"+
					"<tr><th>地點：</th><td>"+result.location+"</td></tr>"+
					"<tr><th>坪數：</th><td>"+result.total_pings+"</td></tr>"+
					"<tr><th>格局：</th><td>"+result.pattern+"</td></tr>"+
					"<tr><th>價格：</th><td>"+result.price+"</td></tr>"+
					"</table></div></a>"
							
			});
			
			
			marker.addListener('click', function() {
				infowindow.open(map, marker);
			});
		}

		function deleteMarkers() {
			setMapOnAll(null);
			markers = [];
		}

		// Sets the map on all markers in the array.
		function setMapOnAll(map) {
			for (var i = 0; i < markers.length; i++) {
				markers[i].setMap(map);
			}
		}
	</script>
	<script async defer	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATnJXHEYAz4_W9Aoy_T2y6wdRNdKy3r6g&callback=initMap">
		
	</script>




</body>
</html>