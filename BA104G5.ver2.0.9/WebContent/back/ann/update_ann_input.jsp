<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ann.model.*"%>

<%
	AnnVO annVO = (AnnVO) request.getAttribute("annVO");
	AnnService annSvc = new AnnService();
	List<AnnVO> list = annSvc.getAll();
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


<!-- 以下是你可以放的內容 ================================================================================ -->
<!--修改系統公告========================================================================================= -->
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
				<form role="form" METHOD="post" ACTION="ann.do" name="ann">
					<div class="modal-header">
						<h4 class="modal-title">修改系統公告</h4>
					</div>
					<div class="form-group">
						<label for="ann_no">公告編號</label>
						<div>
							<h5 class="update_color"><%=annVO.getAnn_no()%></h5>
						</div>
					</div>
					<div class="form-group">
						<label for="ann_title">公告標題</label> <input type="text"
							class="form-control" name="ann_title"
							value="<%=(annVO == null) ? " 修改公告標題 " : annVO.getAnn_title()%>" />
					</div>
					<div class="form-group">
						<label for="ann_content">公告內容</label>
						<textarea rows="10" class="form-control" name="ann_content"
							value="<%=(annVO == null) ? "修改公告內容 " : annVO.getAnn_content()%>"></textarea>
					</div>
					<div class="form-group dropdown">
						<label for="ann_state">修改公告狀態</label> <select name="ann_state"
							class="form-control btn_cooper" id="sel1">
							<option class="default_item">請選擇</option>
							<option class="onitem" value="公告中"
								${(annVO.ann_state=='公告中' )? 'selected': ''}>公告中</option>
							<option class="onitem" value="已撤銷"
								${(annVO.ann_state=='已撤銷' )? 'selected': ''}>已撤銷</option>
						</select>
					</div>
					<div class="form-group">
						<label for="emp_no">員工編號</label> <input type="text"
							class="form-control" name="emp_no"
							value="<%=(annVO == null) ? " EM00000002 " : annVO.getEmp_no()%>" />
					</div>
					<div class="modal-footer">						
						<input type="hidden" name="ann_no" value="${annVO.ann_no}">
						<input type="hidden" name="action" value="update">
						<button type="submit" class="btn btn_cooper" value="送出修改">送出修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>
<!-- 結束修改系統公告 ===================================================================================== -->
<!-- 以上是你可以放的內容 ================================================================================= -->

<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->