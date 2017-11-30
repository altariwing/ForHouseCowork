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

	<div class="container cooper_promo">
		<div class="row">
			<div class="col-xs-12 col-sm-12 table_bgcolor">
				<table class="table table-hover table_main">
					<caption class="title_pr">
						促銷活動 <a href='#promo_jump' data-toggle="modal"
							class="btn btn_cooper modal_jump"> 新增促銷活動 </a>
					</caption>
					<thead>
						<tr class="col_title">
							<th>促銷編號</th>
							<th>促銷時間</th>
							<th>促銷標題</th>
							<th>促銷內容</th>
							<th>促銷照片</th>
							<th>修改狀態</th>
							<th>新增時間</th>
							<th>員工編號</th>
							<th>修改促銷內容</th>
						</tr>
					</thead>
					<tbody>
						<tr class="col_name"
							${(promoVO.promo_no==param.promo_no) ? 'bgcolor=#54FF9F' : ''}>
							<td>${promoVO.promo_no}</td>
							<td><fmt:formatDate value="${promoVO.promo_from}"
									pattern="MM-dd" />~ <br> <fmt:formatDate
									value="${promoVO.promo_to}" pattern="MM-dd" /></td>
							<td class="text_overflow">${promoVO.promo_name}</td>
							<td class="text_overflow">${promoVO.promo_content}</td>
							<td>
								<div class="" style="height: 145.8px; width: 194px;">
									<img style="height: 145.8px; width: 194px;"
										src="<%=request.getContextPath()%>/tool/showimage.do?action=promo_photo&promo_no=${promoVO.promo_no}" />
								</div>
							</td>
							<td>
								<div class="dropdown">${promoVO.promo_state}</div>
							</td>
							<td><fmt:formatDate value="${promoVO.promo_date}"
									pattern="MM-dd" /></td>
							<td>${promoVO.emp_no}</td>
							<td>
								<form METHOD="post" ACTION="promo.do">
									<input class="btn btn_cooper modal_jump cooper_ann_href"
										type="submit" value="修改促銷資訊"> <input type="hidden"
										name="promo_no" value="${promoVO.promo_no}"> <input
										type="hidden" name="requestURL"
										value="<%=request.getServletPath()%>">
									<!--送出當前是第幾頁給Controller-->
									<input type="hidden" name="action" value="getOne_For_Update">
								</form>
							</td>
						</tr>
					</tbody>
				</table>

				<table class="table_main">
					<tr>
						<td>
							<h4>
								<a
									href="<%=request.getContextPath()%>/back/promo/listAllPromo.jsp"><img
									src="<%=request.getContextPath()%>/images/back1.gif"
									width="100" height="32" border="0">回促銷資訊</a>
							</h4>
						</td>
					</tr>
					<tr>
						<td>
							<FORM METHOD="post" ACTION="promo.do">
								<b>選擇促銷編號:</b> <select size="1" name="promo_no">
									<c:forEach var="promoVO" items="${list}">
										<option value="${promoVO.promo_no}">${promoVO.promo_no}
									</c:forEach>
								</select> <input type="hidden" name="action" value="getOne_For_Display">
								<input type="submit" value="送出">
							</FORM>
						</td>
					</tr>
					<tr>
						<td>
							<%-- 錯誤表列 --%> <c:if test="${not empty errorMsgs}">
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


	<!-- 以上是你可以放的內容 ================================================================================== -->

	<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->