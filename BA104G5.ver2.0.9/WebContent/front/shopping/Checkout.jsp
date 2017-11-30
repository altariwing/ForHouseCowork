<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.prdimg.model.*"%>


<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/Houselogo1.png" />
<title>For House</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script src="https://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">

<style>

.table {
	font-size: 22px;
}

.money {
	padding-left: 20px;
	padding-right: 10px;
	align: right;
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

<div style="margin: auto;width:900px;margin-top:130px;">
	<h2 class="text-center">購物車明細</h2>
	<form action="<%=request.getContextPath()%>/front/order/PrdOrd.do" method="post">
		<table class="table table-hover">
			<tr>
				<th class="text-center">圖片</th><th class="text-center">名稱</th><th class="text-center">單價</th><th class="text-center">數量</th><th class="text-center">總價</th>
			</tr>
			
			<c:set var="subtotal" value="0" />
			<c:forEach var="prdVO" items="${cartlist}">
				<tr class="text-center">
					<td><img data-name="product_image" src="<%=request.getContextPath()%>/front/tools/showimage.do?getImg=by_prd_no&prd_no=${prdVO.prd_no}" height='120px'></td>
					<td valign='middle'>${prdVO.prd_name}</td>
					<td class="text-center">${prdVO.prd_price}</td>
					<td class="text-center">${prdVO.quantity}</td>
					<td class="text-center">${ prdVO.prd_price * prdVO.quantity }</td>
				</tr>
				<c:set var="subtotal" value="${subtotal+(prdVO.prd_price*prdVO.quantity)}" />
			</c:forEach>
			<tr class="text-center">
				<td colspan="3"></td>
				<td>
					<font size="4px"> 訂單金額 </font> 
				</td>
				<td>
					<font size="5px" color="red"> $ ${subtotal} </font> 
				</td>
			</tr>
		</table>
		
		<table>
			<tr align="right">
				<td width="400px">&nbsp</td>
				<td width="300px">
					<input type="checkbox" name="coupon"> 使用優惠券 
				</td>
				<td width="200px" style="padding-right:28px">
					-$ <span id="coupon"> 0  
				</td>
			</tr>
			
			<tr align="right" height="100px">
				<td width="500px">&nbsp</td>
				<td>
					<font size="+2"> 總付款金額 </font> 
				</td>
				<td style="padding-right:26px">
					<font color="red" size="+3"> $ ${subtotal} </font>
				</td>
			</tr>
		
		</table>
		
		<div class="row">
		<hr></hr>
			<div class="col-sm-8"></div>
			<div class="col-sm-4">
				<input type="submit" class="btn btn-warning btn-lg" value="下訂單" style="width:100%">
			</div>
		</div>
		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<input type="hidden" name="action" value="insert">
		
	</form>
		
</div>

</body>

</html>