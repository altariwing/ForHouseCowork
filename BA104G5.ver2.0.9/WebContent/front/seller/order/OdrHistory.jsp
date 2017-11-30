<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.slr.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.prdcategory.model.*"%>
<%@ page import="com.prdimg.model.*"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.odrdetail.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
  SlrVO slrVO = (SlrVO) session.getAttribute("slrVO");

  PrdOdrService poSvc = new PrdOdrService();						//訂單總表
  List<PrdOdrVO> shippedOdrList = poSvc.slrGetByDeliver(slrVO.getSlr_no());  //已出貨訂單
  pageContext.setAttribute("shippedOdrList",shippedOdrList);

  List<PrdOdrVO> cancelledOdrList = poSvc.slrGetByCancel(slrVO.getSlr_no()); //已取消的訂單
  pageContext.setAttribute("cancelledOdrList",cancelledOdrList);
  
  OdrDetailService odSvc = new OdrDetailService();                  //訂單明細
  pageContext.setAttribute("odSvc",odSvc);
  
  SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
  pageContext.setAttribute("sdf",sdf);
  
%>
<jsp:useBean id="pcSvc" scope="page" class="com.prdcategory.model.PcService" />
<jsp:useBean id="prdImgSvc" scope="page" class="com.prdimg.model.PrdImgService" />
<jsp:useBean id="prdSvc" scope="page" class="com.product.model.PrdService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>廠商會員中心</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/seller/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    
    <style type="text/css">
    body {
		font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
	}
    .panel-title {
        font-size: 24px;
    }
    .panelboxtitle{
        margin-top:-1.2em;
    }
    .areaInner{
        margin-top: 140px;
    }
    h2.panel-title {
        font-size: 24px;
    }
    #OdrList {
    	width: 1480px;
    	pading: 1em;
    }
    
    
    </style>
</head>

<body>

<nav class="navbar navbar-fixed-top main">
	<jsp:include page="/front/seller/navbar.jsp" />
</nav>



<div class="container-fluid">
    <div class="row">
            
    <!-- include 左側選單區塊  開始-->
	<jsp:include page="/front/seller/leftpanel.jsp" />
	<!-- include 左側選單區塊  結束-->
            

<!-- 右側內容區塊 開始-->
<!-- 更換的程式碼放下面 -->
<div class="col-xs-12 col-sm-7 maincontext" id="dashboard">
		
	<h3 class="text-center">已出貨訂單列表</h3>
	
	<c:if test="${empty shippedOdrList}">
		<h3 class="text-center">&lt;查無資料&gt;</h3>
	</c:if>
	
	<c:if test="${not empty shippedOdrList}">
	<table class="table-hover" border="1" id="OdrList" style="border:3px #cccccc solid;" cellpadding="5">
		<tr height="35px">
			<th rowSpan="2" width="170px" class="text-center">訂單編號</th>
			<th colSpan="5" class="text-center">訂 單 明 細</th>
			<th rowSpan="2" class="text-center">訂單總金額</th>
			<th rowSpan="2" class="text-center">訂購者</th>
			<th rowSpan="2" class="text-center">訂購時間</th>
			<th rowSpan="2" class="text-center">評分</th>
		</tr>
		<tr height="35px">
	  	   <th width="200px" class="text-center">圖片</th>
	  	   <th width="350px" class="text-center">商品名稱</th>
	  	   <th width="90px" class="text-center">單價</th>
	  	   <th width="70px" class="text-center">數量</th>
	  	   <th width="110px" class="text-center">小計</th>
		</tr>
		
		<%@ include file="pages/historyP1.file" %> <p>
		<c:forEach var="shippedOdrVO" items="${shippedOdrList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<!-- 迴圈1開始 -->
		<tr>
			<th class="text-center">${shippedOdrVO.pdo_no}</th>  <!-- 訂單編號 -->
			
			<td colspan="5">
			
			<c:set var="amount" value="0" />
			
			<table class="table-hover" cellpadding="5">
			<c:forEach var="odrDtlVO" items="${odSvc.getListByPdoNo(shippedOdrVO.pdo_no)}"> <!-- 迴圈2開始 -->
			<tr>
				<td width="200px" class="text-center">
				<c:if test="${not empty prdImgSvc.findFirstByPrdNo(odrDtlVO.prd_no)}">
					<img src="<%=request.getContextPath()%>/DBImgReader?img_no=${prdImgSvc.findFirstByPrdNo(odrDtlVO.prd_no).img_no}" height="120px">
				</c:if>
				<c:if test="${empty prdImgSvc.findFirstByPrdNo(odrDtlVO.prd_no)}">
					<img src="<%=request.getContextPath()%>/front/seller/images/nopic.jpg" height="120px">
				</c:if>
				</td> <!--圖片-->
				<td width="350px">${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_name}</td> <!--商品名稱-->
				<td width="90px" class="text-center">${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_price}</td> <!--單價-->
				<td width="70px" class="text-center">${odrDtlVO.quantity}</td> <!--數量-->
				<td width="110px" class="text-center">${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_price * odrDtlVO.quantity}</td> <!--總金額-->
			</tr>
			<c:set var="amount2" value="${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_price * odrDtlVO.quantity}" />
			<c:set var="amount" value="${amount2 + amount}" />
			</c:forEach> <!-- 迴圈2結束 -->
			</table>
			
		    </td>

<!--訂單總金額--><td align='center' valign='middle'>${amount}</td>
<!--買方-->    <td align='center'>${memSvc.getOneByNo(shippedOdrVO.mem_no).mem_name}</td>
<!--訂購時間--> <td align='center'>${sdf.format(shippedOdrVO.pdo_resr_date)}</td>
<!--評分--> <td align='center'>5顆星</td>
		</tr>
			
		</c:forEach>
	</table>
	</c:if>

	
	<p> <h2>&nbsp</h2> </p>
	<p> <h4>&nbsp</h4> </p>
	
	<h3 class="text-center">已取消訂單列表</h3>
	<p> <h4>&nbsp</h4> </p>
	<c:if test="${empty cancelledOdrList}">
		<h3 class="text-center">&lt;查無資料&gt;</h3>
	</c:if>
	
	<c:if test="${not empty cancelledOdrList}">
	<table class="table-hover" border="1" id="OdrList" style="border:3px #cccccc solid;" cellpadding="5">
		<tr height="35px">
			<th rowSpan="2" width="170px" class="text-center">訂單編號</th>
			<th colSpan="5" class="text-center">訂 單 明 細</th>
			<th rowSpan="2" class="text-center">訂單總金額</th>
			<th rowSpan="2" class="text-center">訂購者</th>
			<th rowSpan="2" class="text-center">評分</th>
		</tr>
		<tr height="35px">
	  	   <th width="200px" class="text-center">圖片</th>
	  	   <th width="350px" class="text-center">商品名稱</th>
	  	   <th width="90px" class="text-center">單價</th>
	  	   <th width="70px" class="text-center">數量</th>
	  	   <th width="130px" class="text-center">小計</th>
		</tr>
		
		<c:forEach var="cancelledOdrVO" items="${cancelledOdrList}">
		<!-- 迴圈1開始 -->
		<tr>
			<th class="text-center">${cancelledOdrVO.pdo_no}</th>  <!-- 訂單編號 -->
			
			<td colspan="5">
			
			<c:set var="amount" value="0" />
			
			<table class="table-hover" cellpadding="5">
			<c:forEach var="odrDtlVO" items="${odSvc.getListByPdoNo(cancelledOdrVO.pdo_no)}"> <!-- 迴圈2開始 -->
			<tr>
				<td width="200px" class="text-center"><img src="<%=request.getContextPath()%>/DBImgReader?img_no=${prdImgSvc.findFirstByPrdNo(odrDtlVO.prd_no).img_no}" height="120px"></td> <!--圖片-->
				<td width="350px">${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_name}</td> <!--商品名稱-->
				<td width="90px" class="text-center">${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_price}</td> <!--單價-->
				<td width="70px" class="text-center">${odrDtlVO.quantity}</td> <!--數量-->
				<td width="120px" class="text-center">${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_price * odrDtlVO.quantity}</td> <!--總金額-->
			</tr>
			<c:set var="amount2" value="${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_price * odrDtlVO.quantity}" />
			<c:set var="amount" value="${amount2 + amount}" />
			</c:forEach> <!-- 迴圈2結束 -->
			</table>
			
		    </td>

<!--訂單總金額--><td align='center' valign='middle'>${amount}</td>
<!--買方-->    <td align='center'>${memSvc.getOneByNo(cancelledOdrVO.mem_no).mem_name}</td>
<!--修改按鈕--> <td align='center'>5顆星</td>
		</tr>
			
		</c:forEach>
	</table>
	</c:if>
                  
</div>
<!-- 更換的程式碼放上面 -->        
<!-- 右側區塊 結束-->


    </div>
</div>


<jsp:include page="/front/seller/footer.jsp" />

</body>
</html>