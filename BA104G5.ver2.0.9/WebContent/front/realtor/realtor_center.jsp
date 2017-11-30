<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.realestate.model.*"%>

<jsp:useBean id="realestateSvc" scope="page"
	class="com.realestate.model.RealEstateService" />

<%
	RealtorVO realtorVO = (RealtorVO) session.getAttribute("realtorVO");
	session.setAttribute("realtorVO",realtorVO);
	RealtorVO realtorVO2 = (RealtorVO) request.getAttribute("realtorVO");
	// 如為 null, 代表此user未登入過 , 才做以下工作.
	//*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁
	//*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
	if (realtorVO == null) {
		response.sendRedirect("realtor_register.jsp"); //*工作2 : 請該user去房仲註冊網頁
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

<!-- 右邊房仲相關資料區include資料用 =====================================-->
<div class="col-sm-12 col-sm-8 form_realtor">

	<!-- 房仲資料已下 ====================================================-->
	<!-- 房仲顯示基本資料用 =================================================-->
	<div class="modal-header">
		<h4 class="modal-title">個人資料</h4>
	</div>
	<div class="form-group div0">
		<label for="rtr_photo"></label> <img
			src="<%=request.getContextPath()%>/tool/showimage.do?action=rtr_photo&rtr_no=${realtorVO.rtr_no}"
			class="w3ls-mobile rtr_photo" alt="<%=realtorVO.getRtr_name()%>個人照片"
			data-pin-nopin="true">
	</div>
	<div class="form-group">
		<label for="rtr_id">e-mail帳號</label>
		<div>
			<h5><%=realtorVO.getRtr_id()%></h5>
		</div>
	</div>
	<div class="form-group">
		<label for="rtr_name">姓名</label>
		<div>
			<h5 class=""><%=realtorVO.getRtr_name()%></h5>
		</div>
	</div>
	<div class="form-group">
		<label for="rtr_idno">身分證</label>
		<div>
			<h5 class=""><%=realtorVO.getRtr_idno()%></h5>
		</div>
	</div>
	<div class="form-group">
		<label for="re_no">房仲公司</label>
		<div>
			<h5 class="">${realestateSvc.getOne(realtorVO.re_no).getRe_name()}</h5>
		</div>
	</div>
	<div class="form-group">
		<label for="rtr_area">服務地區</label>
		<div>
			<h5 class="">${realtorVO.rtr_area}</h5>
		</div>
	</div>

	<div class="form-group">
		<label for="ann_content">簡介</label>
		<textarea rows="10" class="form-control intro_cooper" name="rtr_intro"
			placeholder="簡介" readonly style="cursor:no-drop;"><%=realtorVO.getRtr_intro()%></textarea>
	</div>
	<div class="modal-footer">
		<form METHOD="post" ACTION="realtorchange.do">
			<!-- <a href='#realtor_jump' data-toggle="modal" class="btn">修改資料</a> -->
			<input type="hidden" name="action" value="getOne_For_Update">
			<input type="hidden" name="rtr_no" value="${realtorVO.rtr_no}">
			<input type="submit" class="btn" title="選擇修改資料" value="選擇修改資料">
		</form>
	</div>
</div>
<!-- 右邊房仲相關資料區include資料用結束 =====================================-->

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