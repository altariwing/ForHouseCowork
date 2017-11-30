<%-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ page import="java.util.*"%> --%>
<%-- <%@ page import="com.ann.model.*"%> --%>

<%-- 
// 	AnnVO annVO = (AnnVO) request.getAttribute("annVO");

// 	AnnService annSvc = new AnnService();
// 	List<AnnVO> list = annSvc.getAll();
// 	pageContext.setAttribute("list", list);
--%>


<!-- 去掉上面taskboard的框架 -->
<%-- <jsp:include page="back_cooper.jsp" flush="true" /> --%>


<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color: red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color: red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<!-- 彈出新增公告=============================================================-->
<!-- <div class="modal fade" id="ann_jump"> -->
<!-- 	<div class="modal-dialog"> -->
<!-- 		<div class="modal-content"> -->
<!-- 			<div class="modal-header"> -->
<!-- 				<button type="button" class="close" data-dismiss="modal" -->
<!-- 					aria-hidden="true">&times;</button> -->
<!-- 				<h4 class="modal-title">系統公告</h4> -->
<!-- 			</div> -->
<!-- 			<div class="modal-body"> -->

<!-- 				<form role="form" METHOD="post" ACTION="ann.do" name="ann"> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label for="ann_title">公告標題</label> <input type="text" -->
<!-- 							class="form-control" name="ann_title" placeholder="新增公告名稱" -->
<%-- 							value="<%=(annVO == null) ? "測試名稱" : annVO.getAnn_title()%>" /> --%>
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label for="ann_content">公告內容</label> -->
<!-- 						<textarea rows="10" class="form-control" name="ann_content" -->
<!-- 							placeholder="新增公告內容" -->
<%-- 							value="<%=(annVO == null) ? "測試內容" : annVO.getAnn_content()%>"></textarea> --%>
<!-- 					</div> -->
<!-- 					<div class="form-group dropdown"> -->
<!-- 						<label for="ann_state">修改狀態</label> <select name="ann_state" -->
<!-- 							class="form-control btn_cooper" id="sel1"> -->
<!-- 							<option class="default_item" name="default_item" value="">請選擇</option> -->
<!-- 							<option class="onitem" value="公告中" -->
<%-- 								${(annVO.ann_state=='公告中')? 'selected': ''}>公告中</option> --%>
<!-- 							<option class="onitem" value="已撤銷" -->
<%-- 								${(annVO.ann_state=='已撤銷')? 'selected': ''}>已撤銷</option> --%>
<!-- 						</select> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label for="emp_no">員工編號</label> <input type="text" -->
<!-- 							class="form-control" name="emp_no" placeholder="輸入員工編號" -->
<%-- 							value="<%=(annVO == null) ? "EM00000002" : annVO.getEmp_no()%>" /> --%>
<!-- 					</div> -->

<!-- 					<div class="modal-footer"> -->
<!-- 						<button type="button" class="btn btn-default" data-dismiss="modal">離開</button> -->
<!-- 						<input type="hidden" name="action" value="insert"> -->
<!-- 						<button type="submit" class="btn btn_cooper" value="送出新增">送出新增</button> -->
<!-- 					</div> -->
<!-- 				</form> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<!-- 結束公告 =========================================================================-->
<!-- </div> -->
