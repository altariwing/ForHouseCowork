<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.realtor.model.*"%>

<%
	RealtorVO realtorVO = (RealtorVO) session.getAttribute("realtorVO");

	//如為 null, 代表此user未登入過 , 才做以下工作.
	//*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁
	//*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
	if (realtorVO == null) {
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect("realtor_login.jsp");
		return;
	}
%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!-- 房仲前端 -->
<jsp:include page="/front/realtor/frontPage.jsp" flush="true" />
<!-- 房仲前端 -->

<div class="col-sm-12 col-sm-8 form_realtor">
	<!-- 房仲修改密碼用 =================================================-->
	<form role="form" method="post"
		ACTION="<%=request.getContextPath()%>/front/realtor/realtorchange.do"
		id="realtor_change_psw">
		<div class="modal-header">
			<h4 class="modal-title">修改密碼</h4>
		</div>
		<div class="form-group">
			<label for="rtr_name">舊密碼</label>
			<div>
				<input type="password" class="form-control" id="realtor_psw"
					placeholder="舊密碼" name="psw_ori">
			</div>
		</div>
		<div class="form-group">
			<label for="rtr_name">新密碼</label>
			<div>
				<input type="password" class="form-control" id="psw_new1"
					name="psw_new1" placeholder="新密碼">
			</div>
		</div>

		<div class="form-group">
			<label for="rtr_name">確認新密碼</label>
			<div>
				<input type="password" class="form-control" id="psw_new2"
					name="psw_new2" placeholder="確認新密碼">
			</div>
		</div>
		<div class="modal-footer">
			<input type="hidden" name="action" value="realtor_Change_Psw">
			<button type="submit" class="btn btn-danger" title="送出修改" value="送出修改">修改密碼</button>
			<div style="float: left;">
				<c:if test="${not empty pswErrors}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${pswErrors}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
		</div>
	</form>
</div>
<!-- 結束房仲修改密碼用 =================================================-->


<!-- 充版面用 -->
<div class="col-sm-1"></div>
<!-- 充版面用 -->

<!-- 不可刪掉的div -->
</div>
</div>
<!-- 不可刪掉的div -->



<!-- footer -->
<footer class="container-fluid" id="cooper_footer">
	<div class="container text-center">
		<div class="col-xs-12 col-sm-2">
			<a href="#">HOME</a>
		</div>
		<div class="col-xs-12 col-sm-2">
			<a href="#">OUR TEAM</a>
		</div>
		<div class="col-xs-12 col-sm-2">
			<a href="#">COMMUNITIES</a>
		</div>
		<div class="col-xs-12 col-sm-2">
			<a href="#">COMMUNITIES</a>
		</div>
		<div class="col-xs-12 col-sm-2">
			<a href="#">COMMUNITIES</a>
		</div>
		<div class="col-xs-12 col-sm-2">
			<a href="#">CONTACT US</a>
		</div>
	</div>
	<div class="copyri text-center">
		<p>
			<small>Copyright © 2017 For House</small>
		</p>
	</div>
</footer>
<!-- footer -->
