<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.prdcategory.model.*"%>
<%@ page import="com.prdimg.model.*"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.odrdetail.model.*"%>

<%
  MemVO memVO = (MemVO) session.getAttribute("memVO");
//   if (memVO == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
// 	  session.setAttribute("location", request.getRequestURI()); //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
// 	  response.sendRedirect(request.getContextPath() + "/front/login.jsp"); //*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
// 	  return;
//   }
 
  PrdOdrService poSvc = new PrdOdrService();						//訂單總表
  Map<String, List<PrdOdrVO>> odrHistoryMap = poSvc.memGetByDeliver(memVO.getMem_no());
  pageContext.setAttribute("odrHistoryMap",odrHistoryMap);
  
  OdrDetailService odSvc = new OdrDetailService();                  //訂單明細
  pageContext.setAttribute("odSvc",odSvc);
  
%>
<jsp:useBean id="pcSvc" scope="page" class="com.prdcategory.model.PcService" />
<jsp:useBean id="prdImgSvc" scope="page" class="com.prdimg.model.PrdImgService" />
<jsp:useBean id="prdSvc" scope="page" class="com.product.model.PrdService" />
<jsp:useBean id="slrSvc" scope="page" class="com.slr.model.SlrService" />
<%
	
%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/Houselogo1.png" />
<title>For House</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>


<style type="text/css">
	body {
		font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體,	sans-serif;
	}
	
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

    h3.panel-title {
        font-size: 24px;
    }

	
</style>


<body>

<!-- 背景圖 -->
<div class="container-fluid backgroundpng">
	<img class="row" src="<%=request.getContextPath()%>/images/fixed_bg.png">
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
	<jsp:include page="/front/member/leftpanel.jsp" />
	<!-- include 左側選單區塊  結束-->

	<!-- 右側頁面區塊  開始 -->
	<div class="col-sm-9 areaInner">
		<div class="tab-content">
					
					
			<!-- 更換的程式碼放下面 -->
			
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title text-center">購買紀錄</h3>
				</div>
				<div>
				<c:if test="${empty odrHistoryMap}">
					<h3 class="text-center">&lt;查無資料&gt;</h3>
				</c:if>
			
				<c:if test="${not empty odrHistoryMap}">
				<table class="table-hover" border="1" id="OdrList" style="border:3px #cccccc solid;" cellpadding="10">
				<tr height="35px">
					<th rowSpan="2" width="170px" class="text-center">訂購時間</th>
					<th rowSpan="2" width="170px" class="text-center">訂單編號</th>
					<th rowSpan="2" width="140px" class="text-center">賣方</th>
					<th colSpan="5" class="text-center">訂 單 明 細</th>
					<th rowSpan="2" width="100px" class="text-center">小計</th>
				</tr>
				<tr height="35px">
			  	   <th width="200px" class="text-center">圖片</th>
			  	   <th width="410px" class="text-center">商品名稱</th>
			  	   <th width="90px" class="text-center">單價</th>
			  	   <th width="70px" class="text-center">數量</th>
			  	   <th width="100px" class="text-center">金額</th>
				</tr>
		
				<!-- 迴圈1開始 -->
				<c:forEach var="odrDate" items="${odrHistoryMap.keySet()}">
				
				<tr>
					<th class="text-center" rowSpan="${odrHistoryMap.get(odrDate).size()+1}"> ${odrDate} </th>
				</tr>
				
				<!-- 迴圈2開始 -->
				<c:set var="subtotal" value="0" />
				<c:forEach var="dlvrOdrVO" items="${odrHistoryMap.get(odrDate) }">
				<tr>
	   
					<th class="text-center">${dlvrOdrVO.pdo_no}</th>  <!-- 訂單編號 -->
		<!--賣家-->  <td align='center'>
						<span class="str">${slrSvc.findByNo(dlvrOdrVO.slr_no).slr_name}</span>
					</td>
					
					<td colspan="5">
						<c:set var="amount" value="0" />
						
						<table class="table-hover" cellpadding="10">
						<!-- 迴圈3_ 訂單明細   開始-->
						<c:forEach var="odrDtlVO" items="${odSvc.getListByPdoNo(dlvrOdrVO.pdo_no)}">
						<tr class="text-center">
							<td width="220px" class="text-center"> <!--圖片-->
								<img src="<%=request.getContextPath()%>/DBImgReader?img_no=${prdImgSvc.findFirstByPrdNo(odrDtlVO.prd_no).img_no}" height="120px">
							</td> 
							<td width="470px">${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_name}</td> <!--商品名稱-->
							<td width="110px">&nbsp&nbsp ${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_price}</td> <!--單價-->
							<td width="70px">&nbsp&nbsp&nbsp &nbsp${odrDtlVO.quantity}</td> <!--數量-->
							<td width="130px">${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_price * odrDtlVO.quantity}</td> <!--金額-->
						</tr>
						<c:set var="amount2" value="${prdSvc.getOnByPrdNo(odrDtlVO.prd_no).prd_price * odrDtlVO.quantity}" />
						<c:set var="amount" value="${amount2 + amount}" />
						</c:forEach>
						<!-- 迴圈3_ 訂單明細    結束 -->
						</table>
						<c:set var="subtotal" value="${subtotal + amount}" />
				    </td>
					<td align='center' valign='middle'>${amount}</td><!--小計-->
				</tr>
				</c:forEach>
				
				
				<!-- 迴圈2結束 -->
					
				</c:forEach>
				<!-- 迴圈1結束 -->
				</c:if>
			</div>
			</table>
	
				
			</div>
			<!-- 更換的程式碼放上面 -->
					
					
		</div>
	</div>
	<!-- 右側頁面區塊結束 -->
	
	
	</div>
</div>
<!-- 下方大區塊 end -->

</body>

</html>