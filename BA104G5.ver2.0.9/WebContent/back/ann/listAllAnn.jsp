<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ann.model.*"%>
<%--採用 EL 的寫法取值 --%>

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


	<!-- 以下是你可以放的內容 ================================================================================== -->
	<!--新增系統公告================================================================================ -->
	<div class="container cooper_ann">
		<div class="row">
			<div class="col-xs-12 col-sm-12 table_bgcolor">
				<table class="table table-hover table_main">
					<caption class="title_pr">
						系統公告<a href='#ann_jump' data-toggle="modal"
							class="btn btn_cooper modal_jump cooper_ann_href"> 新增系統公告 </a>
					</caption>
					<thead>
						<tr class="col_title">
							<th>公告編號</th>
							<th>公告標題</th>
							<th>公告內容</th>
							<th>公告狀態</th>
							<th>公告時間</th>
							<th>員工編號</th>
							<th>修改公告狀態</th>
						</tr>
					</thead>
					<tbody>
						<%@include file="page_ann/pageann.file"%>
						<c:forEach var="annVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr class="col_name">
								<td>${annVO.ann_no}</td>
								<td class="text_overflow">${annVO.ann_title}</td>
								<td class="text_overflow">${annVO.ann_content}</td>
								<td>
									<div class="dropdown">${annVO.ann_state}
										<!-- <select class="form-control btn_cooper" id="sel1"> -->
										<!-- <option class="default_item">請選擇</option> -->
										<%-- <option class="onitem btn_cooper">${annVO.ann_state}</option> --%>
										<!-- </select> -->
									</div>
								</td>
								<td>${annVO.ann_date}</td>
								<td>${annVO.emp_no}</td>
								<td>
									<form METHOD="post" ACTION="ann.do">
										<input class="btn btn_cooper modal_jump cooper_ann_href"
											type="submit" value="修改系統公告"> <input type="hidden"
											name="ann_no" value="${annVO.ann_no}"> <input
											type="hidden" name="action" value="getOne_For_Update">
									</form>
								</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table>
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
							<FORM METHOD="post" ACTION="ann.do">
								<b>選擇公告編號:</b> <select size="1" name="ann_no">
									<c:forEach var="annVO" items="${list}">
										<option value="${annVO.ann_no}">${annVO.ann_no}
									</c:forEach>
								</select> <input type="hidden" name="action" value="getOne_For_Display">
								<input type="submit" value="送出">
							</FORM>
						</td>
					</tr>
					<tr>
						<td>
							<FORM METHOD="post" ACTION="ann.do">
								<b>選擇刪除公告編號:</b> <select size="1" name="ann_no">
									<c:forEach var="annVO" items="${list}">
										<option value="${annVO.ann_no}">${annVO.ann_no}
									</c:forEach>
								</select> <input type="hidden" name="action" value="delete"> <input
									class="delete_confirm btn btn-danger" type="submit" value="送出">
							</FORM>
						</td>
					</tr>
					<div class="container">
						<div class="row">
							<div class="col-sm-12">
								<%@include file="page_ann/pageann2.file"%>
							</div>
						</div>
					</div>
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




	<!-- 彈出新增系統公告============================================================= -->
	<div class="modal fade" id="ann_jump">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">新增系統公告</h4>
				</div>
				<div class="modal-body">
					<form role="form" METHOD="post" ACTION="ann.do" name="ann">
						<div class="form-group">
							<label for="ann_title">公告標題</label> <input type="text"
								class="form-control" name="ann_title"
								value="<%=(annVO == null) ? "新增公告名稱" : annVO.getAnn_title()%>" />
						</div>
						<div class="form-group">
							<label for="ann_content">公告內容</label>
							<textarea rows="10" class="form-control" name="ann_content"
								value="<%=(annVO == null) ? "新增公告內容" : annVO.getAnn_content()%>"></textarea>
						</div>
						<div class="form-group dropdown">
							<label for="ann_state">公告狀態</label> <select name="ann_state"
								class="form-control btn_cooper" id="sel1">
								<option name="default_item" value="">請選擇</option>
								<option class="onitem" value="公告中"
									${(annVO.ann_state=='公告中')? 'selected': ''}>公告中</option>
								<option class="onitem" value="已撤銷"
									${(annVO.ann_state=='已撤銷')? 'selected': ''}>已撤銷</option>
							</select>
						</div>
						<div class="form-group">
							<label for="emp_no">員工編號</label> <input type="text"
								class="form-control" name="emp_no"
								value="<%=(annVO == null) ? "EM00000002" : annVO.getEmp_no()%>" />
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">離開</button>
							<input type="hidden" name="action" value="insert">
							<button type="submit" class="btn btn_cooper" value="送出新增">送出新增</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 結束新增系統公告 ============================================================================ -->




	<!-- 彈出修改系統公告 ============================================================================= -->
	<!-- 	<div class="modal2 fade" id="ann_jump_update"> -->
	<!-- 		<div class="modal-dialog"> -->
	<!-- 			<div class="modal-content2"> -->
	<!-- 				<div class="modal-header2"> -->
	<!-- 					<button type="button" class="close" data-dismiss="modal" -->
	<!-- 						aria-hidden="true">&times;</button> -->
	<!-- 					<h4 class="modal-title">修改系統公告</h4> -->
	<!-- 				</div> -->
	<!-- 				<div class="modal-body2"> -->
	<!-- 					<form role="form" METHOD="post" ACTION="ann.do" name="ann"> -->
	<!-- 						<div class="form-group"> -->
	<!-- 							<label for="ann_title">公告編號</label> -->
	<!-- 							<div> -->
	<%-- 								<h5 class="update_color"><%=annVO.getAnn_no()%></h5> --%>
	<!-- 							</div> -->
	<!-- 						</div> -->
	<!-- 						<div class="form-group"> -->
	<!-- 							<label for="ann_title">公告標題</label> <input type="text" -->
	<!-- 								class="form-control" name="ann_title" -->
	<%-- 								value="<%=(annVO == null) ? "修改公告名稱" : annVO.getAnn_title()%>" /> --%>
	<!-- 						</div> -->
	<!-- 						<div class="form-group"> -->
	<!-- 							<label for="ann_content">公告內容</label> -->
	<!-- 							<textarea rows="10" class="form-control" name="ann_content" -->
	<%-- 								value="<%=(annVO == null) ? "修改公告內容" : annVO.getAnn_content()%>"></textarea> --%>
	<!-- 						</div> -->
	<!-- 						<div class="form-group dropdown"> -->
	<!-- 							<label for="ann_state">修改公告狀態</label> <select name="ann_state" -->
	<!-- 								class="form-control btn_cooper" id="sel1"> -->
	<!-- 								<option class="default_item">請選擇</option> -->
	<!-- 								<option class="onitem" value="公告中" -->
	<%-- 									${(annVO.ann_state=='公告中')? 'selected': ''}>公告中</option> --%>
	<!-- 								<option class="onitem" value="已撤銷" -->
	<%-- 									${(annVO.ann_state=='已撤銷')? 'selected': ''}>已撤銷</option> --%>
	<!-- 							</select> -->
	<!-- 						</div> -->
	<!-- 						<div class="form-group"> -->
	<!-- 							<label for="emp_no">員工編號</label> <input type="text" -->
	<!-- 								class="form-control" name="emp_no" -->
	<%-- 								value="<%=(annVO == null) ? "EM00000002" : annVO.getEmp_no()%>" /> --%>
	<!-- 						</div> -->

	<!-- 						<div class="modal-footer2"> -->
	<!-- 							<input type="hidden" name="action" value="leave"> -->
	<!-- 							<button type="submit" class="btn btn-default" -->
	<!-- 								data-dismiss="modal" value="離開">離開</button> -->
	<%-- 							<input type="hidden" name="ann_no" value="${annVO.ann_no}"> --%>
	<!-- 							<input type="hidden" name="action" value="update"> -->
	<!-- 							<button type="submit" class="btn btn_cooper" value="送出修改">送出修改</button> -->
	<!-- 						</div> -->
	<!-- 					</form> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 結束修改系統公告 ================================================================================== -->


	<!-- 以上是你可以放的內容 ================================================================================== -->

	<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->

