<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.newstype.model.*"%>
 
<jsp:useBean id="newstypeSvc" scope="page"
	class="com.newstype.model.NewsTypeService" />

<%
	NewsTypeVO newstypeVO = (NewsTypeVO) request.getAttribute("newstypeVO");
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
	<!-- 新聞種類 ========================================================================================= -->
	<div class="container cooper_house">
		<div class="row">
			<div class="col-xs-12 col-sm-12 table_bgcolor">
				<table class="table table-hover table_main">
					<caption class="title_pr">
						新聞種類 <a href='#newstype_jump' data-toggle="modal"
							class="btn btn_cooper modal_jump cooper_ann_href"> 新增新聞種類</a>
					</caption>
					<thead>
						<tr class="col_title">
							<th>新聞種類編號</th>
							<th>新聞種類</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="newstypeVO" items="${newstypeSvc.all}">
							<tr class="col_name">
								<td>${newstypeVO.ntype_no}</td>
								<td>${newstypeVO.news_type}</td>
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
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/newstype/newstype.do">
								<b>選擇新聞種類編號:</b> <select size="1" name="ntype_no">
									<c:forEach var="newstypeVO" items="${newstypeSvc.getAll()}">
										<option value="${newstypeVO.ntype_no}">${newstypeVO.ntype_no}-&nbsp;[${newstypeVO.news_type}]
									</c:forEach>
								</select> <input type="hidden" name="action" value="getOne_For_Display">
								<input type="submit" value="送出">
							</FORM>
						</td>
					</tr>
					<tr>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/newstype/newstype.do">
								<b>選擇新聞種類:</b> <select size="1" name="ntype_no">
									<c:forEach var="newstypeVO" items="${newstypeSvc.getAll()}">
										<option value="${newstypeVO.ntype_no}">${newstypeVO.news_type}
									</c:forEach>
								</select> <input type="hidden" name="action" value="listNews_ByNtype_No_B">
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

	<!-- 彈出新增新聞種類=====================================================================================-->
	<div class="modal fade" id="newstype_jump">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">新增新聞種類</h4>
				</div>
				<div class="modal-body">
					<form role="form" METHOD="post" ACTION="<%=request.getContextPath()%>/back/newstype/newstype.do"
						enctype="multipart/form-data" name="house">

						<div class="form-group">
							<label for="ntype_no">新聞種類</label> <select
								class="form-control btn_cooper" id="sel1" name="ntype_no">
								<option name="default_item1" value="">請選擇</option>
								<c:forEach var="newstypeVO" items="${newstypeSvc.all}">
									<option class="onitem" value="${newstypeVO.ntype_no}"
										${(newsVO.ntype_no==newstypeVO.ntype_no)? 'selected': ''}>[${newstypeVO.ntype_no}
										- ${newstypeVO.news_type}]</option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group">
							<label for="news_title">新聞類型</label> <input type="text"
								class="form-control" name="ntype_no"
								value="<%=(newstypeVO == null) ? "新增新聞類型" : newstypeVO.getNtype_no()%>" />
						</div>
						<div class="modal-footer">
							<input type="hidden" name="action" value="insert">
							<button type="submit" class="btn btn_cooper" value="送出新增">送出新增</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 結束新聞種類 ================================================================================== -->
	
	<%if (request.getAttribute("listNews_ByNtype_No") != null){%>
		<jsp:include page="listNews_ByNtype_No.jsp" />
	<%} %>
	

	<!-- 以上是你可以放的內容 =========================================================================== -->

	<!-- 一定要留住的</div> ============================================================================ -->
</div>
<!-- 一定要留住的</div> ================================================================================ -->