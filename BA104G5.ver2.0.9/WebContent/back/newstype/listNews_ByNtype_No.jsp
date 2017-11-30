<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>


<jsp:useBean id="listNews_ByNtype_No" scope="request"
	type="java.util.Set<NewsVO>" />

<jsp:useBean id="newstypeSvc" scope="page"
	class="com.newstype.model.NewsTypeService" />
<%
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>

<!-- 以下是你可以放的內容 ================================================================================== -->

<style type="text/css">
.newstype_title, .newstype_col {
	color: #4682B4;
}

#btn_newstype {
	background-color: #4682B4;
}
</style>

<div class="container cooper_house">
	<hr style="border: 1px solid red;">
	<div class="row">
		<div class="col-xs-12 col-sm-12 table_bgcolor">
			<table class="table table-hover table_main">
				<caption class="title_pr newstype_title">房市新聞種類列表</caption>
				<thead>
					<tr class="col_title newstype_col">
						<th>新聞編號</th>
						<th>新聞種類</th>
						<th>新聞標題</th>
						<th>新聞內容</th>
						<th>新聞狀態</th>
						<th>新聞照片</th>
						<th>新增時間</th>
						<th>員工編號</th>
						<th>修改新聞狀態</th>
					</tr>
				</thead>
				<tbody>
					<%@ include file="page_newstype/pagenewstype.file"%>
					<c:forEach var="newsVO" items="${listNews_ByNtype_No}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr class="col_name">
							<td>${newsVO.news_no}</td>
							<td><c:forEach var="newstypeVO" items="${newstypeSvc.all}">
									<c:if test="${newsVO.ntype_no==newstypeVO.ntype_no}">
									${newstypeVO.ntype_no}<br>
										<font color=orange>[${newstypeVO.news_type}]</font>
									</c:if>
								</c:forEach></td>
							<td class="text_overflow">${newsVO.news_title}</td>
							<td class="text_overflow">${newsVO.news_content}</td>
							<td>
								<div class="dropdown">${newsVO.news_state}</div>
							</td>
							<td><div class="" style="height: 145.8px; width: 194px;">
									<img style="height: 145.8px; width: 194px;"
										src="<%=request.getContextPath()%>/tool/showimage.do?action=news_photo&news_no=${newsVO.news_no}">
								</div></td>
							<td>${newsVO.news_date}</td>
							<td>${newsVO.emp_no}</td>
							<td>
								<!-- 跳轉到房市新聞修改新聞內容 -->
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/back/news/news.do">
									<input class="btn btn_cooper modal_jump cooper_ann_href"
										id="btn_newstype" type="submit" value="修改新聞公告"> <input
										type="hidden" name="news_no" value="${newsVO.news_no}">
									<input type="hidden" name="requestURL"
										value="<%=request.getServletPath()%>"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</form> <!-- end跳轉到房市新聞修改新聞內容 -->
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<%@ include file="page_newstype/pagenewstype2.file"%>
			</table>
			<table>
				<tr>
					<!-- <td> -->
					<!-- <h4> -->
					<!-- <a -->
					<%-- href="<%=request.getContextPath()%>/back/newstype/listAllNewsType.jsp"><img --%>
					<%-- src="<%=request.getContextPath()%>/images/back1.gif" width="100" --%>
					<!-- height="32" border="0">回新聞種類</a> -->
					<!-- </h4> -->
					<!-- </td> -->
					<!-- </tr> -->
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
<!-- 以上是你可以放的內容 =========================================================================== -->
