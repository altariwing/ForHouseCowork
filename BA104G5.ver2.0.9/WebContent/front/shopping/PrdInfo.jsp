<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.prdimg.model.*"%>
<jsp:useBean id="slrService" scope="session" class="com.slr.model.SlrService" />
<jsp:useBean id="pdoService" scope="page" class="com.product_order.model.Product_orderService" />

<%
	PrdVO prdVO = (PrdVO) session.getAttribute("prdVO");
	List<PrdImgVO> list = (List<PrdImgVO>) session.getAttribute("list");
	
	List<PrdVO> cartlist = (List<PrdVO>) session.getAttribute("cartlist");  //購物車清單
	
	session.setAttribute("prdVO",prdVO);
	
%>

<html>

<head>

<!-- Include SmartCart CSS -->
<link href="<%=request.getContextPath()%>/front/shopping/css/smart_cart.css" rel="stylesheet" type="text/css" />


<style>
h4 {
	position: relative;
	line-height: 1.4em;
	/* 1 times the line-height to show 1 lines */
	height: 1.4em;
	overflow: hidden;
}

.mySlides {display:none}
.demo {cursor:pointer} 

.responsive-flamingo {
	width: 16%;
	height: auto;
}

section {
    display: table;
}

#mainImg {
	height: 500px;
	width: 500px;
	display: table-cell;
    vertical-align: middle;
    padding-left:15px;
}

#mainImg img{
	max-height: 500px;
	width: 500px;
}

#prdInfoDiv {
	
	left: 25%;
}

#productText {
	margin-left: 30px;
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
/* 	width: 105%; */
/*     position: fixed; */
/*     right: -110em; */
}

</style>
</head>



<body>

<!-- one----------------------------------------------------------------------------------------------------------- -->
<!-- one----------------------------------------------------------------------------------------------------------- -->
<!-- one----------------------------------------------------------------------------------------------------------- -->
<div id="prdInfoDiv">

	<!-- 主區塊左側 -->
	<div class="col-md-6 col-sm-6 row">
		<div class="row">
			<!-- 商品圖大區塊  -->
			<section>
			<div id="mainImg">
				<c:forEach var="PrdImgVO" items="${list}">

					 <img class="card-img-top mySlides img-responsive" data-name="product_image"
						src="<%=request.getContextPath()%>/front/tools/showimage.do?getImg=by_img_no&img_no=${PrdImgVO.img_no}"
						alt="Responsive image" id="">
					
				</c:forEach>
			</div>
			</section>
			<!-- 商品圖大區塊結束  -->
		</div>
		<div class="row">
			<!-- 商品圖小區塊  -->
			<div class="w3-row-padding w3-section">
				<div class="item w3-col s4">

					<%int currentimg = 0; %>
					<c:forEach var="PrdImgVO" items="${list}">

						&nbsp
						<img data-name="product_image" class="responsive-flamingo demo w3-opacity w3-hover-opacity-off"
							src="<%=request.getContextPath()%>/front/tools/showimage.do?getImg=by_img_no&img_no=${PrdImgVO.img_no}"
							alt="Product image" onmouseover="currentDiv(<%=++currentimg %>)">
						

					</c:forEach>
				</div>
			</div>
			<!-- 商品圖小區塊 結束  -->
		</div>
	</div>
	<!-- 左側區塊結束 -->
	
	
	<!-- 主區塊右側 -->
<!-- 這堆還要上CSS 這堆還要上CSS 這堆還要上CSS -->				
<div class="col-md-6 col-sm-6" id="productText">
		<div class="">
			
<!-- 這堆還要上CSS 這堆還要上CSS 這堆還要上CSS -->			

			<!-- 標題 -->
			<div class="">
				<h2 data-name="product_name">${PrdVO.prd_name}</h2>
			</div>
			<br>
			<!-- 價錢 -->
			<div class="">
				<h3 class="text-danger">$${PrdVO.prd_price}</h3>
			</div>
			<!-- 主要區塊的細節們 -->

			<div class="">
				<div class="sameRow">${PrdVO.prd_desc}</div>
			</div>

			<div>
				<div class="">
					<h4 class="text-info">庫存: ${PrdVO.prd_stock}</h4>
				</div>
				
				<div class="row">
					<div class="col-md-6" style="width:250px">
					<form action="<%=request.getContextPath()%>/front/shopping/cart.do" method="POST">
						數量: <select class="selectpicker" name="quantity">
							<c:forEach var="item" begin="1" end="${PrdVO.prd_stock}">
								<option value='${item}'>${item}</option>
							</c:forEach>
						</select>
	
						<strong class="price pull-left text-danger">${prdVO.prd_price}</strong>
						
						<input type="hidden" name="action" value="addToCart">
						<input type="hidden" name="action" value="getOne_Product">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<input name="product_price" value="${prdVO.prd_price}" type="hidden" />
						<input name="product_id" value="${prdVO.prd_no}" type="hidden" />
						<button class="sc-add-to-cart btn btn-primary btn-sm pull-right">加入購物車</button>
						</form>
					</div>
					
					<div class="col-md-1"></div>
					
					<!-- 檢舉按鈕 有登入才看得到-->
					<c:if test="${not empty memVO}">
					<div class="col-md-5">
						<button type="button" class="btn btn-warning" data-toggle="popover" data-placement="bottom" title="檢舉理由"
						data-content='
							<div class="panel panel-primary">
								<form action="<%=request.getContextPath()%>/front/shopping/RptPrd" method="POST">
									<input type="hidden" name="action" value="addToCart">
									<input type="hidden" name="prd_no" value="${PrdVO.prd_no}">
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<textarea name="reportReason" rows="3" style="width:220px" id="reportText"></textarea>
									<p></p>
									<button class="btn btn-default" type="submit" style="width:100%">送出</button>
								</form>
							</div>
						'>檢舉</button>
					</div>
					</c:if>
					
				<div class="">
				<!-- 商家 -->
				<div class="col-md-12"><h4>商家資訊: ${slrService.findByNo(PrdVO.slr_no).slr_name}</h4></div>
				<div class="col-md-12">${PrdVO.slr_no}</div>
				
				
				
				<div class="col-md-12">
				<FORM METHOD="post"	ACTION="shop.do">
					<input type="hidden" name="action" value="listPrd_ByCompositeQuery">
					<input type="hidden" name="slr_no" value="${PrdVO.slr_no}">
					<a href="javascript:;" onclick="parentNode.submit();"><h4>查看賣家商品</h4></a>
				</FORM>	
				</div>
				<div class="row">
				
				<c:if test= "${'0.0' != pdoService.getSlrAvgRate(PrdVO.slr_no)}" >
				<div class="star-ratings">
				<div class="star-ratings-top"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
				<div class="star-ratings-bottom"><span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span></div>
				</div>
								
				
				評價分數：${pdoService.getSlrAvgRate(PrdVO.slr_no)}
				
				</c:if>
				</div>
			</div>
				</div>
			</div>
		</div>
	</div>
	


  		
	<!-- 主區塊右側結束 -->
</div>
<!-- 主區塊第一列結束 -->
<div></div>
<!-- two----------------------------------------------------------------------------------------------------------- -->
<!-- two----------------------------------------------------------------------------------------------------------- -->
<!-- two----------------------------------------------------------------------------------------------------------- -->

<div class="row border border-primary"></div>


		<script>
			var slideIndex = 1;
			showDivs(slideIndex);

			function plusDivs(n) {
				showDivs(slideIndex += n);
			}

			function currentDiv(n) {
				showDivs(slideIndex = n);
			}

			function showDivs(n) {
				var i;
				var x = document.getElementsByClassName("mySlides");
				var dots = document.getElementsByClassName("demo");
				if (n > x.length) {
					slideIndex = 1
				}
				if (n < 1) {
					slideIndex = x.length
				}
				for (i = 0; i < x.length; i++) {
					x[i].style.display = "none";
				}
				for (i = 0; i < dots.length; i++) {
					dots[i].className = dots[i].className.replace(
							" w3-opacity-off", "");
				}
				x[slideIndex - 1].style.display = "block";
				dots[slideIndex - 1].className += " w3-opacity-off";
			}
			
			$(document).ready(function(){
				$(".w3-hover-opacity-off").mouseenter(function(){
					$(this).css("border","1px solid #efefef");
					$(this).css("box-shadow","2px 2px 2px gray");
				    
				});
				$(".w3-hover-opacity-off").mouseleave(function(){
					$(this).css("border","none");
					$(this).css("box-shadow","none");
				});
			});
		</script>


<script>
    $(function(){
        $('[data-toggle="popover"]').popover({
        	html:true
        });
    })
    
    $(document).ready(function() {
 		if(<%=request.getAttribute("reportSuccess")!=null%>){swal('<h3>檢舉成功</h3>','<h4>我們會盡速處理，謝謝。</h4>','success')}
 	});
    
    
    $(document).ready(function(){
    	$("#smartcart").on("itemAdded",function(e,element){
    		
    		console.log(element.product_id,element.product_quantity);
    		
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
    		
    		console.log(element.product_id,element.product_quantity);
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
    		
    		console.log(element.product_id,element.product_quantity);
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
    		
    		console.log(element.product_id,element.product_quantity);
    		
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
        $('#smartcart').smartCart({
        	cart:{
        	}
        });
    });
    
</script>

</body>

</html>