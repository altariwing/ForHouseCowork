<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promo.model.*"%>

<%
	PromoVO promoVO = (PromoVO) request.getAttribute("promoVO");
	PromoService promoSvc = new PromoService();
	List<PromoVO> list = promoSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>

<!-- 後端include -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- 後端include -->

<!-- 一定要留住的<div> ================================================================================== -->
<div class="col-xs-12 col-sm-10 maincontext">
	<!-- 一定要留住的<div> ================================================================================== -->

	<!-- 以下是你可以放的內容 ================================================================================== -->
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
				<form role="form" METHOD="post" ACTION="promo.do"
					enctype="multipart/form-data" name="news">
					<div class="modal-header">
						<h4 class="modal-title">修改促銷資訊</h4>
					</div>
					<div class="form-group">
						<label for="promo_no">促銷編號</label>
						<div>
							<h5 class="update_color"><%=promoVO.getPromo_no()%></h5>
						</div>
					</div>
					<label for="promo_period">促銷時間</label>
					<div class="col-sm-12">
						<div class="form-group">
							<div class="row date_size">
								<div class="col-xs-12 col-sm-6">
									From <input type="text" class="form-control"
										id="promo_period_from" name="promo_from"
										value="<%=(promoVO == null) ? promoVO.getPromo_from() : promoVO.getPromo_from()%>">
								</div>
								<div class="col-xs-12 col-sm-6">
									to <input type="text" class="form-control" id="promo_period_to"
										name="promo_to"
										value="<%=(promoVO == null) ? promoVO.getPromo_to() : promoVO.getPromo_to()%>">
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="promo_name">促銷標題</label> <input type="text"
							class="form-control" name="promo_name"
							value="<%=(promoVO == null) ? "修改促銷標題" : promoVO.getPromo_name()%>">
					</div>
					<div class="form-group">
						<label for="promo_content">促銷內容</label>
						<textarea rows="10" class="form-control" name="promo_content"><%=(promoVO == null) ? "修改促銷內容" : promoVO.getPromo_content()%></textarea>
					</div>
					<div class="form-group dropdown">
						<label for="promo_content">修改促銷狀態</label> <select
							name="promo_state" class="form-control btn_cooper" id="sel1">
							<option name="default_item2" value="">請選擇</option>
							<option class="onitem" value="已上架"
								${(promoVO.promo_state=='已上架')? 'selected': ''}>已上架</option>
							<option class="offitem" value="已撤銷"
								${(promoVO.promo_state=='已下架')? 'selected': ''}>已下架</option>
							<option class="offitem" value="未上架"
								${(promoVO.promo_state=='未上架')? 'selected': ''}>未上架</option>
						</select>
					</div>
					<div class="form-group">
						<label for="promo_photo">先前照片</label>
						<div class="" style="height: 145.8px; width: 194px;">
							<img style="height: 145.8px; width: 194px;"
								src="<%=request.getContextPath()%>/tool/showimage.do?action=promo_photo&promo_no=${promoVO.promo_no}">
						</div>
					</div>
					<div class="form-group div0">
						<label for="promo_photo">促銷照片</label> <input type="file"
							name="promo_photo" onchange="readURL(this);"> <img
							name="promo_photo" id="imgpreview" alt="preview image">
					</div>
					<div class="form-group">
						<label for="empno_no">員工編號</label><input type="text"
							class="form-control" name="emp_no"
							value="<%=(promoVO == null) ? "EM00000002" : promoVO.getEmp_no()%>" />
					</div>
					<div class="modal-footer">
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="promo_no" value="${promoVO.promo_no}">
						<input type="hidden" name="requestURL"
							value="<%=request.getParameter("requestURL")%>">
						<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
						<input type="hidden" name="whichPage"
							value="<%=request.getParameter("whichPage")%>">
						<button type="submit" class="btn btn_cooper" value="送出修改">送出修改</button>
					</div>
				</form>
			</div>
		</div>

		<br>送出修改的來源網頁路徑:<br> <b> <font color=blue>request.getParameter("requestURL"):</font>
			<%=request.getParameter("requestURL")%><br> <font color=blue>request.getParameter("whichPage"):
		</font> <%=request.getParameter("whichPage")%>
		</b>

	</div>

	<!-- 以上是你可以放的內容 ================================================================================== -->

	<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->