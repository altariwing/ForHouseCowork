<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>
<%@ page import="com.promo.model.*"%>

<jsp:useBean id="couponSvc" scope="page" class="com.coupon.model.CouponService" />
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/Houselogo1.png" />
<title>For House</title>
<style type="text/css">
	body {
		font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體,	sans-serif;
	}
	
	/* 	找房狀態的 checkbox 開始 */
	.form-group input[type="checkbox"]+.btn-group>label span {
		width: 20px;
	}
	.form-group input[type="checkbox"]+.btn-group>label span:first-child {
		display: none;
	}
	.form-group input[type="checkbox"]+.btn-group>label span:last-child {
		display: inline-block;
	}
	.form-group input[type="checkbox"]:checked+.btn-group>label span:first-child {
		display: inline-block;
	}
	.form-group input[type="checkbox"]:checked+.btn-group>label span:last-child {
		display: none;
	}
	#fancy-checkbox-info {
		display: none;
	}
	#searchSwitch {
		margin-left: 92px;
		margin-top: 35px;
	}
	#searchicon1 {
		height: 20px;
	}
	#searchicon2 {
		height: 20px;
	}
	/* 	找房狀態的 checkbox 結束 */
	
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
	
	/*會員資料表單的*/
    .memform{
        margin-top: 25px;
    }
    .box-width{
      font-size: 24px;
      width:690px;
    }

    h3.panel-title {
        font-size: 24px;
    }
    .savebutton{
    	margin-top: 20px;
		width: 450px;
		margin-left: 30px;
		font-size: 24px;
    }
    #imgDiv img{
    	max-width: 220px;
    	max-height: 250px;
    }
    
	
</style>
<body>
	<!-- nav bar -->
	<nav class="navbar navbar-fixed-top">
		<jsp:include page="/front/navbar.jsp" />
	</nav>
	<!-- nav bar結束 -->
	<div class="backgroundpng">
		<img class="backgroundpng"
			src="<%=request.getContextPath()%>/images/sinyi_bg.png">
	</div>
	
<div class="col-xs-12 memtitle"><h2 class="text-center member_center">會員中心</h2></div>

<!-- 下方大區塊 start -->
<div class="container-fluid areaOutter">
	<div class="row areaOutter">
	
	<!-- include 左側選單區塊  開始-->
	<jsp:include page="/front/member/leftpanel.jsp" />
	<!-- include 左側選單區塊  結束-->

	<!-- 右側頁面區塊  開始 -->
	<div class="col-sm-offset-1 col-sm-8 areaInner">
		
			<div class="table_bgcolor">
				<table class="table table-hover table_main">
				
					<thead>
						<tr class="col_title">
							<th>優惠卷編號</th>
							<th>優惠卷期限</th>
							<th>優惠卷內容</th>
							<th>優惠卷折扣</th>
							<th>訂單編號</th>
							<th>優惠卷狀態</th>
							<th>新增時間</th>
							<th>會員編號</th>
							<th>促銷編號</th>
						</tr>
					</thead>

					<tbody>

						<c:forEach var="couponVO"
							items="${couponSvc.getByMemNO(memVO.mem_no)}">

							<tr class="col_name"
								${(couponVO.cp_no==param.cp_no) ? 'bgcolor=#54FF9F' : ''}>
								<!--將修改的那一筆加入對比色而已-->
								<td>${couponVO.cp_no}</td>
								<td>${couponVO.cp_from} <br>~<br>
									${couponVO.cp_to}
								<td>${couponVO.cp_content}</td>
								<td>${couponVO.transDiscountToHumanLanguage()}</td>
								<td>${couponVO.pdo_no!=null?couponVO.pdo_no:"未使用"}</td>
								<td><div class="dropdown">${couponVO.cp_state}</div></td>
								<td>${couponVO.cp_date}</td>
								<td>${couponVO.mem_no!=null?couponVO.mem_no:"未發放"}</td>
								<td>${couponVO.promo_no}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	
	<!-- 彈出新增=====================================================================================-->

	<!-- 結束房市最新消息 ================================================================================== -->
	<!-- 以上是你可以放的內容 ================================================================================== -->
	<!-- 右側頁面區塊結束 -->
	
	
	</div>
</div>
</body>

</html>
