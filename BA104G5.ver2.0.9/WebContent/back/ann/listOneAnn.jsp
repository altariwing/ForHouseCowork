<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ann.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	AnnVO annVO = (AnnVO) request.getAttribute("annVO"); //AnnServlet.java(Concroller), 存入req的annVO物件
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
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 table_bgcolor">
				<table class="table table-hover table_main">
					<caption class="title_pr">查詢系統公告</caption>
					<thead>
						<tr class="col_title">
							<th>公告編號</th>
							<th>公告標題</th>
							<th>公告內容</th>
							<th>公告狀態</th>
							<th>公告時間</th>
							<th>員工編號</th>
						</tr>
					</thead>
					<tbody>
						<tr class="col_name">
							<td><%=annVO.getAnn_no()%></td>
							<td class="text_overflow"><%=annVO.getAnn_title()%></td>
							<td class="text_overflow"><%=annVO.getAnn_content()%></td>
							<td>
								<div class="dropdown">${annVO.ann_state}</div>
							</td>
							<td><%=annVO.getAnn_date()%></td>
							<td><%=annVO.getEmp_no()%></td>
						</tr>

					</tbody>
				</table>
				<table id="table-1">
					<tr>
						<td>
							<h4>
								<a href="<%=request.getContextPath()%>/back/ann/listAllAnn.jsp"><img
									src="<%=request.getContextPath()%>/images/back1.gif"
									width="100" height="32" border="0">回系統公告</a>
							</h4>
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