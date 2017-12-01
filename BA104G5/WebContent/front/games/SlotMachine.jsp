<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >


<jsp:useBean id="promoSvc" scope="request" class="com.promo.model.PromoService" />

<html>
<head>
<title>DrawCircle</title>
<!-- <style type="text/css">
	canvas{
		border: 1px solid black;
	}
</style> -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.css">
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src='<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.min.js '></script>

<script type="text/javascript">
	var j = 1, radius = 50, r = 30;
	var timere;
	function $id(id) {
		return document.getElementById(id);
	}
	var max;
	var min;
	function rand(max, min) {
		var result = Math.floor(Math.random() * (max - min + 1)) + min;
		return result;
	}
	var k = rand(180, 80);
	var i = rand(11, 0);

	var destinyNum = Math.floor(Math.random() * 101);

	var discount_type;
	var cp_discountCondition;
	var cp_discountPrice;

	if (destinyNum < 3) {
		discount_type = 'priceDiscount';
		cp_discountCondition = 0;
		cp_discountPrice = 30000;
	} else if (destinyNum < 6) {
		discount_type = 'priceDiscount';
		cp_discountCondition = 0;
		cp_discountPrice = 10000;
	} else if (destinyNum < 16) {
		discount_type = 'percentDiscount';
		cp_discountCondition = 0;
		cp_discountPrice = 75;
	} else if (destinyNum < 31) {
		discount_type = 'priceDiscount';
		cp_discountCondition = 1000;
		cp_discountPrice = 100;
	} else if (destinyNum < 61) {
		discount_type = 'priceDiscount';
		cp_discountCondition = 10000;
		cp_discountPrice = 100;
	} else {
		discount_type = 'none';
		cp_discountCondition = 0;
		cp_discountPrice = 0;
	}

	function show() {

		var steps = 14;
		var centerX = 500, centerY = 500;
		var xValues = [ centerX ];
		var yValues = [ centerY ];

		xValues[i] = Math.floor(centerX + radius
				* Math.cos(2 * Math.PI * i / steps));
		yValues[i] = Math.floor(centerY + radius
				* Math.sin(2 * Math.PI * i / steps));

		//console.log("xValues["+i+"] : "+xValues[i]+", yValues["+i+"] : "+yValues[i]);
		var myCanvas = $id("myCanvas");
		var cxt = myCanvas.getContext("2d");
		var grd = cxt.createRadialGradient(500, 500, 1, 500, 500, 500);
		grd.addColorStop(1, "#FFF800");
		grd.addColorStop(0.67, "#FF1361");
		grd.addColorStop(0.3, "#44107A");
		grd.addColorStop(0, "#21d4fd");
		cxt.strokeStyle = "white";
		cxt.lineWidth = 30;
		cxt.beginPath();
		cxt.moveTo(500, 500);
		cxt.lineTo(xValues[i], yValues[i]);
		cxt.closePath();
		cxt.stroke();

		cxt.fillStyle = "white";
		cxt.beginPath();
		cxt.arc(xValues[i], yValues[i], 180, 0, Math.PI / 180 * 360, true);
		cxt.closePath();
		cxt.fill();
		i++;
		xValues[i] = Math.floor(centerX + radius
				* Math.cos(2 * Math.PI * i / steps));
		yValues[i] = Math.floor(centerY + radius
				* Math.sin(2 * Math.PI * i / steps));
		cxt.strokeStyle = grd;
		cxt.fillStyle = grd;
		cxt.lineWidth = 15;
		cxt.beginPath();
		cxt.moveTo(500, 500);
		cxt.lineTo(xValues[i], yValues[i]);
		cxt.closePath();
		cxt.stroke();

		cxt.beginPath();
		cxt.arc(500, 500, 20, 0, Math.PI / 180 * 360, true);
		cxt.closePath();
		cxt.fill();

		cxt.beginPath();
		cxt.arc(xValues[i], yValues[i], r, 0, Math.PI / 180 * 360, true);
		cxt.closePath();
		cxt.fill();

		radius += 3;
		r++;
		if (radius >= 300) {
			radius = 300;
		}

		if (r >= 150) {
			r = 150;
		}

		// console.log("radius : "+radius);
		// console.log("r : "+r);
		clearInterval(timeid);
		timere = setInterval(gameStart, 1);
		if (i == k) {
			var gr = cxt.createLinearGradient((xValues[i] - 150), yValues[i],
					(xValues[i] + 150), yValues[i]);
			gr.addColorStop(0, "#00c9ff");
			//#0fd850→#f9f047 #fa709a→#fee140
			//gr.addColorStop(0.4,"green");
			//gr.addColorStop(0.5,"#EAC100");
			gr.addColorStop(1, "#92fe9d");

			var str;
			if (discount_type == 'none') {
				str = '再接再厲!';
			} else if (discount_type == 'priceDiscount') {
				str = '恭喜獲得：單筆購物滿 ' + cp_discountCondition + ' 折 '
						+ cp_discountPrice + " 元!";
			} else if (discount_type == 'percentDiscount') {
				str = '恭喜獲得：單筆購物滿 ' + cp_discountCondition + ' 打 '
						+ cp_discountPrice + " 折!";
			}

			cxt.font = "30px Comic Sans MS";
			cxt.fillStyle = gr;
			cxt.textAlign = "center";
			cxt.fillText(str, xValues[i], yValues[i]);
			clearInterval(timeid);
			clearInterval(timere);
			sendToContoller(discount_type,cp_discountCondition,cp_discountPrice)
		}
		
		
		
	}
	
	function sendToContoller(discount_type,cp_discountCondition,cp_discountPrice){
		
		$.ajax({
			type:'post',
			url:'<%=request.getContextPath()%>/back/coupon/coupon.do',
			data : {
				discount_type : discount_type,
				cp_discountCondition : cp_discountCondition,
				cp_discountPrice : cp_discountPrice,
				action:'slotMachine',
				promo_no:'PRO0005005',
				mem_game:'${memVO.mem_game}'
			},
			

			success : function(response) {
				swal({
					type : 'success',
					title : response,
					showConfirmButton : true					
				}).then((result) => {
					  if (result){
						    window.location.href="<%=request.getContextPath()%>/front/index.jsp";
						  }
				});
			},

			error : function() {
				swal({
					type : 'error',
					title : '已經在你的最愛了',
					showConfirmButton : true
				}).then((result) => {
					  if (result){
						    window.location.href="<%=request.getContextPath()%>/front/index.jsp";
						  }
				});
			},
		})
	}

	function gameStart() {
		$('#start-btn').attr("disabled","disabled");
		timeid = setInterval(show, j);
		j += 1;
		clearInterval(timere);		
	}
	$(document).ready(function(){
		ChanceSet();
		$('#start-btn').click(gameStart);
	});
	
	
	function ChanceSet(){
		var chance = '${memVO.mem_game}';
		var couponQuanity =${promoSvc.getOne("PRO0005005").coupon_quanity};
		
		if(chance==0){
			$('#start-btn').attr("disabled","disabled");
			swal({
				type : 'error',
				title : '今日抽獎次數已完畢囉',
				showConfirmButton : true					
			}).then((result) => {
				  if (result){
					    window.location.href="<%=request.getContextPath()%>/front/index.jsp";
					  }
			});
			
			
		}
		
		
		if(couponQuanity==0){
			$('#start-btn').attr("disabled","disabled");
			swal({
				type : 'error',
				title : '優惠券已發放完畢，下次請早！',
				showConfirmButton : true					
			}).then((result) => {
				  if (result){
					    window.location.href="<%=request.getContextPath()%>/front/index.jsp";
					  }
			});			
		}		
	}
	
	
	
</script>
</head>
<body>

	
		<a id="start-btn" style="margin-top:auto;margin-left:auto" class="btn btn-primary">試試你的手氣！<span>剩餘次數 ${memVO.mem_game} 次</span></a>


	<div class="row">
		<center>
			<canvas id="myCanvas" width="1000" height="1000"></canvas>
		</center>
	</div>
	<!-- 	<div class="row"> -->
	<%-- 		<form method="post" action="<%=request.getContextPath()%>/back/coupon/coupon.do"> --%>
	<!-- 			<input name="action" type="hidden" value="insert_form_client"> -->
	<!-- 			<input name="promo_no" type="hidden" value="PRO0005010">  -->
	<!-- 			<input name="discount_type" type="hidden"> -->
	<!-- 			<input name="cp_discountCondition" type="hidden">  -->
	<!-- 			<input name="cp_discountPrice" type="hidden"> -->
	<%-- 			<input name="mem_no" type="hidden" value="${memVO.mem_no}">  --%>
	<!-- 			<input name="cp_state" type="hidden" value="notUse"> -->
	<!-- 			<input name="cp_content" type="hidden">			 -->
	<!-- 		</form> -->
	<!-- 	</div> -->

</body>
</html>
<!-- DrawCircle.html -->
<!-- 選擇開啟工具 -->
<!-- 目前顯示的是「DrawCircle.html」。 -->