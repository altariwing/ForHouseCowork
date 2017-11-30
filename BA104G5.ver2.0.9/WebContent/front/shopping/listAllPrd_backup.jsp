<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.prdimg.model.*"%>
<jsp:useBean id="pcService" scope="session"
	class="com.prdcategory.model.PcService" />
<jsp:useBean id="slrService" scope="session"
	class="com.slr.model.SlrService" />

<%
	List<PrdVO> list = (List<PrdVO>) request.getAttribute("listEmps_ByCompositeQuery");
	if (list == null) {
		PrdService prdService = new PrdService();
		list = prdService.getAll();
		pageContext.setAttribute("list", list);

	} else {
		pageContext.setAttribute("list", list);
	}

	List<PrdVO> cartlist = (List<PrdVO>) session.getAttribute("cartlist");
%>

<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/images/Houselogo1.png" />
<title>For House</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script src="https://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.all.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.min.js"></script>

<!-- Include SmartCart CSS -->
<link href="<%=request.getContextPath()%>/front/shopping/css/smart_cart.min.css" rel="stylesheet" type="text/css" />

<script>
	$(document).ready(function() {
		if(<%=request.getAttribute("cartEmpty") != null%>){swal('${cartEmpty}','','error')}
		if(<%=request.getAttribute("errorQuantity") != null%>){swal('${errorQuantity}','','error')}
	});
</script>

<style>
body {
	font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體,
		sans-serif;
}

h4 {
	position: relative;
	line-height: 1.4em;
	/* 1 times the line-height to show 1 lines */
	height: 1.4em;
	overflow: hidden;
}

#marketDiv {
	margin-top: 130px;
}

#searchOption {
	position: fixed;
	margin-left: 70px;
}

.col-md-7 {
	margin-left: 385px;
}

#priceRange {
	padding: 4px;
}

#eachProduct img {
	max-height: 300px;
}

.buyQuantity {
	width: 40px;
}

.sc-cart-item-summary {
	font-size: 18px;
}

.prdQuantity {
	color: red;
	font-size: 22px;
}

#checkOut {
	width: 100%;
	height: 46px;
	font-size: 26px;
}

#cartDiv {
	width: 105%;
	position: fixed;
	right: -113em;
}
</style>
</head>



<body>

	<div class="container-fluid backgroundpng">
		<img class="row"
			src="<%=request.getContextPath()%>/images/fixed_bg.png">
	</div>

	<nav class="navbar navbar-fixed-top">
		<jsp:include page="/front/navbar.jsp" />
	</nav>


	<div class="row" id="marketDiv">


		<div class="col-md-2" id="searchOption">
			<h3 class="">
				<b>搜尋商品</b>
			</h3>
			<!-- ul開始 -->
			<FORM METHOD="post" ACTION="shop.do">
				<input type="hidden" name="action" value="listPrd_ByCompositeQuery">


				<b>輸入查詢字串:</b> <input type="text" name="prd_name">
				<p>
					<!-- 選擇商品分類 -->
				<p>
					<b>商品分類:</b>
					<c:forEach var="PcVO" items="${pcService.all}">
						<input type="radio" id="${PcVO.cate_name}" name="cate_no"
							value="${PcVO.cate_no}">
						<label for="${PcVO.cate_name}">${PcVO.cate_name}</label>
					</c:forEach>
				<p>
					<b>選擇品牌:</b> <br>
					<!-- 選擇品牌 -->
					<c:forEach var="SlrVO" items="${slrService.allSlr}">
						<input type="checkbox" id="${SlrVO.slr_name}" name="slr_no"
							value="${SlrVO.slr_no}">
						<label for="${SlrVO.slr_name}">${SlrVO.slr_name}</label>
						<br>
					</c:forEach>
					<br> <b>價格區間:</b> <select name="price_range" id="priceRange">
						<option value="0">請選擇價格區間</option>
						<option value="1 1000">NT$1-NT$1,000</option>
						<option value="1001 5000">NT$1,001-NT$5,000</option>
						<option value="5001 10000">NT$5,001-NT$10,000</option>
						<option value="10001 15000">NT$10,001-NT$15,000</option>
						<option value="15001 20000">NT$15,001-NT$20,000</option>
						<option value="20000">NT$20,000以上</option>
					</select>
				<p></p>
				<br>
				<!-- ul結束-->
				<input class="btn btn-primary" type="submit" value="查詢"
					style="width: 100%">
				<p>
					<br>
					<!--reset -->
					<input class="btn btn-warning" type="reset" value="重設條件">
			</FORM>
		</div>



		<div class="col-md-7">

			<div class="row">
				<!-- BEGIN PRODUCTS -->
				<c:forEach var="prdVO" items="${list}">

					<div class="col-md-4 col-sm-6" id="eachProduct">
						<span class="sc-product-item thumbnail">

							<form action="shop.do" method="POST">
								<input type="hidden" name="action" value="getOne_Product">
								<input name="prd_no" value="${prdVO.prd_no}" type="hidden" /> <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<!-- 進入查看商品詳情 -->
								<a href="javascript:;" onclick="parentNode.submit();">
									<img data-name="product_image"
									src="<%=request.getContextPath()%>/front/tools/showimage.do?getImg=by_prd_no&prd_no=${prdVO.prd_no}">
								</a>
							</form>
							<div class="caption">
								<form action="<%=request.getContextPath()%>/front/shopping/cart.do" method="POST">
									<h4 data-name="prd_name" class="text-center">${prdVO.prd_name}</h4>

									<%--  <p data-name="product_desc">${prdVO.prd_desc}</p> --%>
									<div>
										<strong class="price pull-left text-danger">$${prdVO.prd_price}</strong>
										<input type="hidden" name="action" value="addToCart">
										<input type="hidden" name="slr_no" value="${SlrVO.slr_no}">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="prd_no" value="${prdVO.prd_no}">
										<input type="hidden" name="prd_price" value="${prdVO.prd_price}">
										<input name="prd_name" value="${prdVO.prd_name}" type="hidden" />
										<button id="addcart" class="sc-add-to-cart btn btn-primary btn-sm pull-right">加入購物車</button>
										<%-- <input name="product_id" value="${prdVO.prd_no}" type="hidden" /> --%>
									</div>
								</form>
								<div class="clearfix"></div>
							</div>

						</span>
					</div>

				</c:forEach>
				<!-- END PRODUCTS -->
			</div>



		</div>


		<%-- 購物車內容 start --%>
		<div id="cartDiv">
			<aside class="col-md-2">
				<div class="panel panel-default sc-cart sc-theme-default">
					<div class="panel-heading sc-cart-heading">
						<img src='images/cart.png' height='30px' />
						<span class="sc-cart-count badge"><%=(cartlist != null) ? cartlist.size() : 0%></span>
					</div>

					<div class="list-group sc-cart-item-list">

						<c:set var="index" value="0" />
						<c:set var="subtotal" value="0" />
						<c:if test="${not empty cartlist}">
							<c:forEach var="aProduct" items="${cartlist}">

								<div class="sc-cart-item list-group-item">

									<form action="<%=request.getContextPath()%>/front/shopping/cart.do" method="POST">
										<%-- for刪除按鈕 --%>
										<input type="hidden" name="prd_no" value="${aProduct.prd_no}">
										<input type="hidden" name="del" value="${index}">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="action" value="DELETE">
										<button type="submit" class="sc-cart-remove">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
									</form>

									<img class="img-responsive pull-left"
										src="<%=request.getContextPath()%>/front/tools/showimage.do?getImg=by_prd_no&prd_no=${aProduct.prd_no}">${aProduct.prd_name}
									<p class="list-group-item-text"></p>
									<div class="sc-cart-item-summary">
										<span class="sc-cart-item-price">${aProduct.prd_price}</span>
										x
										<%-- <span class="sc-cart-item-amount prdQuantity"> ${aProduct.quantity} </span> --%>
										
										<input id="${aProduct.quantity}" class="finalQuantity" type="number" name="quantity" min="1" max="${aProduct.prd_stock}" value="${aProduct.quantity}" style="width: 40px"> 
											<input type="hidden" name="OrignQuantity" value="${aProduct.quantity}"> 
											<input type="hidden" name="prd_no" value="${aProduct.prd_no}"> 
											<input type="hidden" name="prd_name" value="${aProduct.prd_name}">
											<input type="hidden" name="prd_price" value="${aProduct.prd_price}"> 
											<input type="hidden" name="eitherAjax" value="yes">
										=
											<span class="sc-cart-item-amount prdSummary">${aProduct.prd_price*aProduct.quantity}</span>
										<p>
									</div>

								</div>
								<c:set var="subtotal" value="${subtotal+(aProduct.prd_price*aProduct.quantity)}" />
								<c:set var="index" value="${index+1}" />
							</c:forEach>
						</c:if>
					</div>

					<div class="panel-footer sc-toolbar">
						<form action="<%=request.getContextPath()%>/front/shopping/cart.do" method="POST">
							<div class="sc-cart-summary">
								<div class="sc-cart-summary-subtotal">
									小計 : &nbsp <span id="totalPrice" class="sc-cart-subtotal">${subtotal}</span>
									<p>
										<span class="sc-cart-subtotal"></span>
								</div>
							</div>
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
							<input type="hidden" id="subtotal" name="subtotal" value="${subtotal}">
							<input type="hidden" name="action" value="CHECKOUT">
							<input class="btn btn-primary" id="checkOut" type="submit" value="結帳">
						</form>
						<form action="<%=request.getContextPath()%>/front/shopping/cart.do" method="POST">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="deleteALL">
							<button class="btn btn-danger sc-cart-clear" type="submit" id="clearCart">清空</button>
						</form>
					</div>
				</div>
			</aside>
		</div>
		<%-- 購物車內容 end --%>

	</div>




<script>

    $('.finalQuantity').change(function(event){	
    	var id =event.target.id;
    	
    	var quantityOrg =  $('#'+id).next().val();
    	var quantity = parseInt($('#'+id).val()) - parseInt(quantityOrg);
    	var prd_no = $('#'+id).next().next().val();
    	var prd_name = $('#'+id).next().next().next().val();
    	var prd_price = $('#'+id).next().next().next().next().val();
    	var isAjax = $('#'+id).next().next().next().next().next().val();

    	    	
    	$.ajax({ 	    	
 	    	type:'post',
 	    	url:'<%=request.getContextPath()%>/front/shopping/cart.do',
 	    	data:{
 	    		action:'addToCart', 	    		
 	    		prd_no:prd_no,
 	    		prd_name:prd_name, 	    		
 	    		prd_price:prd_price,   	
 	    		quantity:quantity,
 	    		isAjax:isAjax,
 	    	},
 	    	
 	    	success:function(response){
 	    		$('#'+id).next().next().next().next().next().next().text(response);
 	    		var newQuantity = parseInt(quantityOrg)+parseInt(quantity);
 	    		$('#'+id).next().val(newQuantity); 	    		
 	    		var length = $('.prdSummary').length
 	    		var sum = 0; 	    			
 	    		for(var i =0 ; i<length ; i++){
 	    			sum = parseInt(sum) + parseInt($('.prdSummary').eq(i).text()); 	    	 	
 	    		}
 	    		$('#totalPrice').text(sum);
 	    		$('#subtotal').val(sum);
 	    	}, 	    	
 	    	
    	 	error:function(){
	    		alert("ERROR!");
	    	}	    	
 	    	
 	    })    	
    })
</script>


 
</body>

</html>