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


Map<String,String> cartMap = (Map<String,String>)session.getAttribute("cartMap");

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
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.css">
<script src='<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.min.js '></script>
<script src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.js"></script>

<style>
body {
        font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
    }
    
/* h4 { */
/* 	position: relative; */
/* 	line-height: 1.4em; */
/* 	height: 1.4em; */
/* 	overflow: hidden; */
/* } */

#marketDiv {
	margin-top: 130px;
}

#searchOption {
	position: fixed;
	margin-left: 70px;
}

#priceRange {
	padding: 4px;
}

#rightDiv {
	margin-left: 385px;
}

#eachProduct img{ 
 	max-height: 320px; 
	
}
#searchOption{
	border:1px solid lightgray;
	background-color: #fff;
}
.modal-content{
   width:950px;
}
.modal-dialoga{
   width: 600px;
   margin: 20px 700px;
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
				<input class="btn btn-primary" type="submit" value="查詢" >
				
					<!--reset -->
					<input class="btn btn-warning" type="reset" value="重設條件">
			</FORM>
		</div>
     
     
     
<div class="col-md-7" id="rightDiv">
         
	<div class="row">
	    <!-- BEGIN PRODUCTS -->
		<c:forEach var="prdVO" items="${list}">
		
		<div class="col-md-4 col-sm-6" id="eachProduct">
			<span class="sc-product-item thumbnail">
			
			<form action="shop.do" method="POST">
				<input type="hidden" name="action" value="getOne_Product">
				<input name="prd_no" value="${prdVO.prd_no}" type="hidden" /> <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				<!-- 進入查看商品詳情 -->
				<a href='#modal-id' data-toggle="modal">
					<img class="prdimg" name="product_image" src="<%=request.getContextPath()%>/front/tools/showimage.do?getImg=by_prd_no&prd_no=${prdVO.prd_no}">
				</a>
			</form>
			
			<div class="caption">
					<h4 name="product_name" class="text-center">${prdVO.prd_name}</h4>
				<hr class="line">
				<div>
					<strong class="price pull-left text-danger">$${prdVO.prd_price}</strong>
					<input name="product_price" value="${prdVO.prd_price}" type="hidden" />
					<input class="prd_no" name="product_id" value="${prdVO.prd_no}" type="hidden" />
					<button class="sc-add-to-cart btn btn-primary btn-sm pull-right">加入購物車</button>
				</div>

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
        <form action="<%=request.getContextPath()%>/front/shopping/cart.do" method="POST"> 
        	<input type="hidden" name="action" value="CHECKOUT">
        
            <!-- SmartCart element -->
            <div id="smartcart"></div>
        </form>
        
    </aside>
</div>

	<div class="modal fade" id="modal-id">
			<div class="modal-dialog modal-dialoga" style="width: 600px;margin: 100px 360px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						
					</div>
					<div class="modal-body">
						
					</div>
				</div>
			</div>
		</div>




  
    <!-- Include SmartCart -->
    <script src="<%=request.getContextPath()%>/front/shopping/js/jquery.smartCart.js" type="text/javascript"></script>
    <!-- Initialize -->
    <script type="text/javascript">
        $(document).ready(function(){
        	$("#smartcart").on("itemAdded",function(e,element){
        		
        		//console.log(element.product_id,element.product_quantity);
        		
        		$.ajax({
                    url: "<%=request.getContextPath()%>/front/shopping/cart.do",
                    data: { action:"addToCart", prd_no: element.product_id, quantity: element.product_quantity},
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
        		
        		//console.log(element.product_id,element.product_quantity);
        		$.ajax({
                    url: "<%=request.getContextPath()%>/front/shopping/cart.do",
                    data: { action:"addToCart", prd_no: element.product_id, quantity: element.product_quantity},
                    type: "POST",

                    success: function(msg) {
                        
                    },

                    error: function(xhr, ajaxOption, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });
        	});
        	
        	
			$("#smartcart").on("quantityUpdated",function(e,element){
        		
        		//console.log(element.product_id,element.product_quantity);
        		$.ajax({
                    url: "<%=request.getContextPath()%>/front/shopping/cart.do",
                    data: { action:"addToCart", prd_no: element.product_id, quantity: element.product_quantity},
                    type: "POST",

                    success: function(msg) {
                        
                    },

                    error: function(xhr, ajaxOption, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });
        	});
        	
			$("#smartcart").on("itemRemoved",function(e,element){
        		
        		//console.log(element.product_id,element.product_quantity);
        		
        		$.ajax({
                    url: "<%=request.getContextPath()%>/front/shopping/cart.do",
                    data: { action:"itemRemoved", prd_no: element.product_id, quantity: element.product_quantity},
                    type: "POST",

                    success: function(msg) {
                        
                    },

                    error: function(xhr, ajaxOption, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });
        	});
        	
			
			$("#smartcart").on("cartCleared",function(e){
				$.ajax({
                    url: "<%=request.getContextPath()%>/front/shopping/cart.do",
                    data: { action:"cartCleared"},
                    type: "POST",

                    success: function(msg) {
                        
                    },

                    error: function(xhr, ajaxOption, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });
        		
        	});
			
			
			$("#smartcart").on("cartSubmitted",function(e){
				$.ajax({
                    url: "<%=request.getContextPath()%>/front/shopping/cart.do",
                    data: { action:"cartSubmitted"},
                    type: "POST",

                    success: function(msg) {
                        
                    },

                    error: function(xhr, ajaxOption, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });
        		
        	});
        	
        	
            // Initialize Smart Cart    	
            $('#smartcart').smartCart();
            
            $(".prdimg").click(function(){
            	
            	//console.log($(this).closest("#eachProduct").find(".prd_no").val());
            	
            	
            	$.ajax({
                    url: "<%=request.getContextPath()%>/front/shopping/shop.do",
                    data: { action:"getOne_Product",requestURL:"<%=request.getServletPath()%>",prd_no:$(this).closest("#eachProduct").find(".prd_no").val()},
                    type: "POST",
					
                    success: function(data) {
                    	$(".modal-body").empty();
                    	$(".modal-body").append(data);
                    	
//                         for(var i = 0 ; i < data.list.length ; i ++){
//                         			console.log(data.list[i]);
                        		
//                         }
                        
                        
                    },

                    error: function(xhr, ajaxOption, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });
            });
		});
    </script>
</body>

</html>