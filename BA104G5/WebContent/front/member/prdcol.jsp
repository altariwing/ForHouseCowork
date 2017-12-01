<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
<title>For House</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<style type="text/css">
	body {
		font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體,	sans-serif;
	}
	#headerBar{
    	width: 95em;
    }
	/* 會員中心  開始  */
	.memtitle {
		margin-top: 70px;
	}
    .member_center{
      color: #00ADEE;
    }
    .areaOutter{
        margin-top:40px;
    }
    .areaInner{
        margin-top:20px;
    }
    /* 會員中心  結束  */
	
	
	/* 收藏商品的form */
    h3.panel-title {
        font-size: 24px;
    }

    .new-prd{
        margin: 20px;
    }
    .box-width{
      font-size: 20px;
      width:670px;
    }
	
</style>


<body>

<!-- 背景圖 -->
<div class="container-fluid backgroundpng">
	<img class="row" src="images/fixed_bg.png">
</div>

<!-- 上方的 header (navbar) -->
<nav class="navbar navbar-fixed-top">
	<jsp:include page="/front/navbar.jsp" />
</nav>
    
    
<div class="col-xs-12 memtitle"><h2 class="text-center member_center">會員中心</h2></div>

<!-- 下方大區塊 start -->
<div class="container-fluid areaOutter">
	<div class="row areaOutter">
	
	<!-- include 左側選單區塊  開始-->
	<jsp:include page="leftpanel.jsp" />
	<!-- include 左側選單區塊  結束-->

	<!-- 右側頁面區塊  開始 -->
	<div class="col-sm-offset-2 col-sm-5 areaInner">
		
				<div class="tab-content">
					
						<div class="panel panel-primary">
                                   <div class="panel-heading">
                                     <h3 class="panel-title text-center">收藏的商品</h3>
                                   </div>
                                   <form>
                                       <div class="form-group new-prd">
                                         <label for="exampleInputEmail1">商品類別</label>
                                         <select class="form-control opts">
                                           <option value="table">桌子</option>
                                           <option value="chair">椅子</option>
                                           <option value="cabinet">櫃子</option>
                                         </select>
                                       </div>
                                       <div class="form-group new-prd">
                                         <label for="exampleInputEmail1">商品名稱</label>
                                         <input type="text" class="form-control" id="prd_name" placeholder="商品名稱">
                                       </div>
                                       <div class="form-group new-prd">
                                         <label for="exampleInputPassword1">商品詳情</label>
                                         <input type="text" class="form-control" id="prd-detail" placeholder="商品詳情">
                                       </div>
                                       <div class="form-group new-prd">
                                       <label for="exampleInputPassword1">商品單價</label>
                                         <input type="text" class="form-control" id="prd_price" placeholder="商品單價">
                                       </div>
                                       <div class="form-group new-prd">
                                       <label for="exampleInputPassword1">商品數量</label>
                                         <input type="text" class="form-control" id="prd_stock" placeholder="商品數量">
                                       </div>
                                       <div class="checkbox new-prd">
                                         <button type="submit" class="btn btn-warning box-width">上架</button>
                                       </div>
                                     </form>
                                 </div>
					
				</div>

	</div>
	<!-- 右側頁面區塊結束 -->
	
	
	</div>
</div>
<!-- 下方大區塊 end -->

</body>

</html>