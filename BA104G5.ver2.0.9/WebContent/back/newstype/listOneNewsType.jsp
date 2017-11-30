<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%@ page import="com.newstype.model.*"%>

<!-- 取出controller NewsTypeServlet.java已存入request的NewsTypeVO物件 -->
<%
	NewsTypeVO newstypeVO = (NewsTypeVO) request.getAttribute("newstypeVO");
%>

<!-- 取出對應的NewsVO物件 -->
<%
	NewsService newsSvc = new NewsService();
	NewsVO newsVO = newsSvc.getOne(newstypeVO.getNtype_no());
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
	<!-- 新聞種類 ========================================================================================= -->
	<div class="container cooper_house">
		<div class="row">
			<div class="col-xs-12 col-sm-12 table_bgcolor">
				<table class="table table-hover table_main">
					<caption class="title_pr">新聞種類</caption>
					<thead>
						<tr class="col_title">
							<th>新聞種類編號</th>
							<th>新聞種類</th>
						</tr>
					</thead>
					<tbody>
						<tr class="col_name">
							<td>${newstypeVO.ntype_no}</td>
							<td>${newstypeVO.news_type}</td>
						</tr>
					</tbody>
				</table>
				<table>
					<tr>
						<td>
							<h4>
								<a
									href="<%=request.getContextPath()%>/back/newstype/listAllNewsType.jsp"><img
									src="<%=request.getContextPath()%>/images/back1.gif"
									width="100" height="32" border="0">回新聞種類</a>
							</h4>
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
