<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.realtor.model.*"%>

<%
	RealtorVO realtorVO = (RealtorVO) session.getAttribute("realtorVO");
	RealtorVO realtorVOtmp = (RealtorVO) request.getAttribute("realtorVOtmp");
	if (realtorVO == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
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


	<!-- 房仲修改個人資料用 =================================================-->


	<form role="form" METHOD="post"
		ACTION="<%=request.getContextPath()%>/front/realtor/realtorchange.do"
		enctype="multipart/form-data" name="realtor">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">修改個人資料</h4>
		</div>
		<div class="modal-body">
			<div class="form-group">
				<label for="rtr_id">e-mail帳號</label>
				<div>
					<h5 id="update_color"><%=realtorVO.getRtr_id()%></h5>
				</div>
			</div>

			<div class="form-group div0">
				<label for="rtr_photo">修改個人照片</label> <input type="file"
					name="rtr_photo" onchange="readURL(this);"> <img
					name="rtr_photo" id="imgpreview" alt="preview image" />
			</div>

			<div class="form-group">
				<label for="rtr_name">姓名</label>
				<div>
					<input type="text" class="form-control" name="rtr_name"
						value="<%=(realtorVOtmp == null) ? realtorVO.getRtr_name()
					: ((realtorVOtmp.getRtr_name().length() > 0) ? realtorVOtmp.getRtr_name()
							: realtorVO.getRtr_name())%>">
				</div>
			</div>
			<div class="form-group">
				<label for="ann_content">簡介</label>
				<textarea rows="10" class="form-control" name="rtr_intro"><%=(realtorVOtmp == null) ? realtorVO.getRtr_intro()
					: ((realtorVOtmp.getRtr_intro().length() > 0) ? realtorVOtmp.getRtr_intro()
							: realtorVO.getRtr_intro())%></textarea>
			</div>
			<div class="modal-footer">
				<input type="hidden" name="action" value="realtor_Change_data">
				<input type="hidden" name="rtr_no" value="${realtorVO.rtr_no}">
				<button type="submit" class="btn" title="送出修改" value="送出修改">確認修改資料</button>
			</div>

		</div>
	</form>


	<!-- 結束房仲彈窗修改個人資料用 =================================================-->

</div>
<!-- 右邊房仲相關資料區include資料用結束 =====================================-->

<!-- 充版面用 -->
<div class="col-sm-1"></div>
<!-- 充版面用 -->
</div>
</div>
<!-- 房仲左邊功能列結束 ================================================================-->


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
</body>
</html>

