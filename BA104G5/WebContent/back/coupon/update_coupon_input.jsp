<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>
<%@ page import="com.promo.model.*"%>

<jsp:useBean id="promoSvc" scope="page"
	class="com.promo.model.PromoService" />




<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!-- 後端include -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- 後端include -->

<style>
table, th, td {
	border: 0px solid #CCCCFF;
}
</style>


<!-- 一定要留住的<div> ================================================================================== -->
<div class="col-xs-12 col-sm-10 maincontext">
	<!-- 一定要留住的<div> ================================================================================== -->

	<!-- 以下是你可以放的內容 ================================================================================ -->
	<!--修改優惠卷========================================================================================= -->
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-sm-offset-3 form_ann">
				<form role="form" METHOD="post"
					ACTION="<%=request.getContextPath()%>/back/coupon/coupon.do"
					name="news">
					<div class="modal-header">
						<h4 class="modal-title">修改房市最新消息</h4>
					</div>
					<div class="form-group">
						<label for="news_no">優惠卷編號</label>
						<div>							
							<input type="text"class="form-control" name=cp_no value="${couponVO.cp_no}" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="promo_name">促銷活動編號</label> 
						<input type="text"class="form-control" name="promo_no" value="${couponVO.promo_no}" readonly>
					</div>
					
					
					<div class="col-sm-12">
						<div class="form-group">
							<div class="row date_size">
								<div class="col-xs-12 col-sm-6">
									From <input type="text" class="form-control"
										id="promo_period_from" name="cp_from"
										value="${couponVO.cp_from}">
								</div>
								<div class="col-xs-12 col-sm-6">
									to <input type="text" class="form-control" id="promo_period_to"
										name="cp_to" value="${couponVO.cp_to}">
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="cp_content">優惠卷內容</label>
						<textarea rows="10" class="form-control" name="cp_content">${couponVO.cp_content}</textarea>
					</div>
					<div class="form-group">
						<label for="promo_content">折價類型</label> 
						<select	name="discount_type" class="form-control btn_cooper" id="sel1">							
							<option value="priceDiscount" selected>滿額折金額</option>
							<option value="percentDiscount">滿額折%數</option>
<!-- 							<option value="pricediscount2">滿額折扣</option>	 -->
<!-- 							<option value="percentDiscount2">滿額件折%數</option>						 -->
						</select>
					</div>
						<div class="form-group">
						<label for="cp_discountCondition">需滿金額</label>
						<input type="number"class="form-control" name="cp_discountCondition" value="1000">
					</div>
					<div class="form-group">
						<label for="cp_discountPrice">折扣金額或%數</label>
						<input type="number"class="form-control" name="cp_discountPrice" value="100">
					</div>
					<div class="form-group">
						<label for="promo_name">訂單編號</label> 
						<input type="text"class="form-control" name="pdo_no" placeholder="無指定可免填">
					</div>
					<div class="form-group">
							<label for="promo_content">修改優惠券狀態</label> <select
							name="cp_state" class="form-control btn_cooper" id="sel1">
<!-- 							<option selected>請選擇</option> -->
							<option class="offitem" value="notUse" selected>未使用</option>							
							<option class="onitem" value="onUse" >已使用</option>
						</select>
					</div>
					<div class="form-group">
						<label for="mem_no">會員編號</label><input type="text"
							class="form-control" name="mem_no"
							value="${couponVO.mem_no}" />
					</div>
					
					<div class="form-group">
						<label for="empno_no">員工編號</label><input type="text"
							class="form-control" name="emp_no" value="${empVO.emp_no}" readonly />
					</div>
					<div class="modal-footer">
						<input type="hidden" name="action" value="update"> 
<!-- 						<input type="hidden" name="requestURL" -->
<%-- 							value="<%=request.getParameter("requestURL")%>"> --%>
<!-- 						接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用 -->
<!-- 						<input type="hidden" name="whichPage" -->
<%-- 							value="<%=request.getParameter("whichPage")%>"> --%>
						<!--只用於:istAllEmp.jsp-->
						<button type="submit" class="btn btn_cooper" value="送出修改">送出修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 結束修改新聞公告 ===================================================================================== -->
	<!-- 以上是你可以放的內容 ================================================================================= -->

	<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->
