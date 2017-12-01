<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>
<%@ page import="com.promo.model.*"%>






<jsp:useBean id="couponSvc" scope="page" class="com.coupon.model.CouponService" />


<!-- 後端include -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- 後端include -->

<!-- 一定要留住的<div> ================================================================================== -->
<div class="col-xs-12 col-sm-10 maincontext">
	<!-- 一定要留住的<div> ================================================================================== -->

	<!-- 以下是你可以放的內容 ================================================================================== -->
	<!-- 新增房市最新消息 ========================================================================================= -->
	<div class="container-fluid cooper_house">
		<div class="row">
			<div class="col-xs-12 col-sm-12 table_bgcolor">
				<table class="table table-hover table_main">
					<caption class="title_pr">
						優惠卷<a href='<%=request.getContextPath() %>/back/coupon/addCoupon.jsp' data-toggle="modal"
							class="btn btn_cooper modal_jump cooper_ann_href">新增優惠卷 </a>
					</caption>
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
							<th>修改優惠卷內容</th>
						</tr>
					</thead>

					<tbody>

						<c:forEach var="couponVO" items="${couponSvc.getAll()}">
						
							<tr class="col_name"
								${(couponVO.cp_no==param.cp_no) ? 'bgcolor=#54FF9F' : ''}>
								<!--將修改的那一筆加入對比色而已-->
								<td>${couponVO.cp_no}</td>
								<td>
								${couponVO.cp_from}
								<br>~<br>
								${couponVO.cp_to}
								<td>${couponVO.cp_content}</td>
								<td>${couponVO.cp_discount}</td>
								<td>${couponVO.pdo_no!=null?couponVO.pdo_no:"未使用"}</td>
								<td><div class="dropdown">${couponVO.cp_state}</div></td>
								<td>${couponVO.cp_date}</td>
								<td>${couponVO.mem_no!=null?couponVO.mem_no:"未發放"}</td>
								<td>${couponVO.promo_no}</td>
								<td><a class="btn btn_cooper modal_jump cooper_ann_href"
									href="<%=request.getContextPath()%>/back/coupon/coupon.do?action=getOne_For_Update&cp_no=${couponVO.cp_no}">修改優惠券</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>



				</table>
				<table class="table_main">
					<tr>
						<td>
							<h4>
								<a
									href="<%=request.getContextPath()%>/back/backend/select_page_home.jsp"><img
									src="<%=request.getContextPath()%>/images/back1.gif"
									width="100" height="32" border="0">回後端首頁</a>
							</h4>
						</td>
					</tr>
					<tr>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back/coupon/coupon.do">
								<b>選擇優惠卷編號:</b> <select size="1" name="cp_no">
									<c:forEach var="couponVO" items="${couponSvc.getAll()}">
										<option value="${couponVO.cp_no}">${couponVO.cp_no}
									</c:forEach>
								</select> <input type="hidden" name="action" value="getOne_For_Display">
								<input type="submit" value="送出">
							</FORM>
						</td>
					</tr>
					<div class="container">
						<div class="row">
							<div class="col-sm-12"></div>
						</div>
					</div>
					<tr>
						<td>
							<%-- 錯誤表列 --%> 
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
						</td>
					</tr>
				</table>

			</div>
		</div>
	</div>
	<!-- 彈出新增=====================================================================================-->

	<!-- 結束房市最新消息 ================================================================================== -->
	<!-- 以上是你可以放的內容 ================================================================================== -->

	<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->