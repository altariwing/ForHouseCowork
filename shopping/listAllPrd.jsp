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
List<PrdVO> list = (List<PrdVO>)request.getAttribute("listEmps_ByCompositeQuery");
if(list==null){
	PrdService prdService = new PrdService();
	list = prdService.getAll();
	pageContext.setAttribute("list", list);
	
}else {
	pageContext.setAttribute("list", list);
}

%>

<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon" href="images/houselogo1.png" />
<title>For House</title>

<!-- Include SmartCart CSS -->
<link href="<%=request.getContextPath()%>/front/shopping/css/smart_cart.min.css" rel="stylesheet" type="text/css" />


<style>
body {
        font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
    }
    
h4 {
	position: relative;
	line-height: 1.4em;
	/* 1 times the line-height to show 1 lines */
	height: 1.4em;
	overflow: hidden;
}

/* #prd_desc { */
/* 	position: relative; */
/* 	line-height: 1.4em; */
/* 	height: 4.2em; */
/* 	overflow: hidden; */
/* } */

/* #prd_desc::after { */
/* 	content: "..."; */
/* 	font-weight: bold; */
/* 	position: absolute; */
/* 	bottom: 0; */
/* 	right: 0; */
/* 	padding: 0 20px 1px 45px; */
/* 	background: */
/* 		url(http://css88.b0.upaiyun.com/css88/2014/09/ellipsis_bg.png) */
/* 		repeat-y; */
/* } */

.responsive-flamingo {  width: 80%;  height: auto; }

#marketDiv {
	margin-top: 130px;
}

#searchOption {
	margin-left: 70px;
}

 #eachProduct img{ 
 	max-height: 300px; 
	
}

</style>
</head>



<body>

<div class="container-fluid backgroundpng">
    <img class="row" src="<%=request.getContextPath()%>/images/fixed_bg.png">
</div>

<nav class="navbar navbar-fixed-top">
	<jsp:include page="/front/navbar.jsp" />
</nav>


<div class="row" id="marketDiv">
    
    
	<div class="col-md-2" id="searchOption">
		<h4 class="card-title">搜尋商品</h4>
		<!-- ul開始 -->
		<FORM METHOD="post"	ACTION="shop.do">
			<input type="hidden" name="action" value="listPrd_ByCompositeQuery">
			
		
			<b>輸入查詢字串:</b> <input type="text" name="prd_name"><br> 
			<!-- 選擇商品分類 -->	
			<b>商品分類:</b>
			<c:forEach var="PcVO" items="${pcService.all}">
				<input type="radio" id="${PcVO.cate_name}" name="cate_no" value="${PcVO.cate_no}">
				<label for="${PcVO.cate_name}">${PcVO.cate_name}</label>
			</c:forEach>
			
			<br> <b>選擇品牌:</b> 
			<!-- 選擇品牌 -->
			<c:forEach var="SlrVO" items="${slrService.allSlr}">
				<input type="checkbox" id ="${SlrVO.slr_name}" name="slr_no" value="${SlrVO.slr_no}"> 
				<label for="${SlrVO.slr_name}">${SlrVO.slr_name}</label> <br>
			</c:forEach>
			
			<br> <b>價格區間:</b> <select name=price_range>
				<option value="0">請選擇價格區間</option>
				<option value="1 1000">NT$1-NT$1,000</option>
				<option value="1001 5000">NT$1,001-NT$5,000</option>
				<option value="5001 10000">NT$5,001-NT$10,000</option>
				<option value="10001 15000">NT$10,001-NT$15,000</option>
				<option value="15001 20000">NT$15,001-NT$20,000</option>
				<option value="20000">NT$20,000以上</option>
			</select><br>
			<!-- ul結束-->
			
			<!--reset -->
	        <input class="btn btn-primary" type ="reset" value="重設條件"><br>
			<input class="btn btn-primary float-right" type="submit" value="查詢">
		</FORM>
	</div>
     
     
     
<div class="col-md-7">
         
	<div class="row">
	    <!-- BEGIN PRODUCTS -->
		<c:forEach var="prdVO" items="${list}">
		
		<div class="col-md-4 col-sm-6" id="eachProduct">
			<span class="sc-product-item thumbnail">
			
			<img data-name="product_image" src="<%=request.getContextPath()%>/front/tools/showimage.do?getImg=by_prd_no&prd_no=${prdVO.prd_no}">
			
			
			<div class="caption">
			<form action="shop.do" method="POST">
				<a href="javascript:;" onclick="parentNode.submit();">
					<h4 data-name="product_name" class="text-center">${prdVO.prd_name}</h4>
				</a>
				<%--  <p data-name="product_desc">${prdVO.prd_desc}</p> --%>
			
				<hr class="line">
				<div>
					<strong class="price pull-left text-danger">$${prdVO.prd_price}</strong>
					<input type="hidden" name="action" value="getOne_Product">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input name="product_price" value="${prdVO.prd_price}" type="hidden" />
					<input name="product_id" value="${prdVO.prd_no}" type="hidden" />
					<button class="sc-add-to-cart btn btn-primary btn-sm pull-right">加入購物車</button>
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
    
    <aside class="col-md-2">
        
        <!-- Cart submit form -->
        <form action="results.php" method="POST"> 
            <!-- SmartCart element -->
            <div id="smartcart"></div>
        </form>
        
    </aside>
</div>
  
    <!-- Include SmartCart -->
    <script src="<%=request.getContextPath()%>/front/shopping/js/jquery.smartCart.js" type="text/javascript"></script>
    <!-- Initialize -->
    <script type="text/javascript">
        $(document).ready(function(){
        	$("#smartcart").on("itemAdded",function(e,element){
        		
        		console.log(element.product_id,element.product_quantity);
        		
        		$.ajax({
                    url: "<%=request.getContextPath()%>/",
                    data: { action:"prdAdd", prd_no: element.product_id, quantity: element.product_quantity},
                    type: "POST",

                    success: function(msg) {
                        
                    },

                    error: function(xhr, ajaxOption, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });
        	});
        	
        	
			$("#smartcart").on("itemUpdated",function(e,element){
        		
        		console.log(element.product_id,element.product_quantity);
        	});
        	
        	
			$("#smartcart").on("quantityUpdated",function(e,element){
        		
        		console.log(element.product_id,element.product_quantity);
        	});
        	
        	
            // Initialize Smart Cart    	
            $('#smartcart').smartCart();
		});
    </script>
</body>

</html>