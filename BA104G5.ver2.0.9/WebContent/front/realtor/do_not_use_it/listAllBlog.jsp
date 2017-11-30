<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.article.model.*"%>


<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll();
	List<ArticleVO> artTime = articleSvc.getAllByTime();
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("artTime", artTime);
%>

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<jsp:useBean id="reSvc" scope="page"
	class="com.realestate.model.RealEstateService" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

<%--
	<table>
		<tr>
			<th>房仲文章編號</th>
			<th>房仲編號</th>
			<th>房仲文章內容</th>
			<th>文章發布日期</th>
			<th>文章狀態</th>
			<th colspan="2">修改文章</th>

		</tr>
		<%@ include file="page1.file"
		<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'>
				<td>${articleVO.article_no}</td>
				<td>${articleVO.rtr_no}</td>
				<td>${articleVO.article_body}</td>
				<td>${articleVO.post_date}</td>
				<td>${articleVO.article_state}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="新增"> 
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--送出當前是第幾頁給Controller-->
						<input type="hidden" name="action" value="insert">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="article_no" value="${articleVO.article_no}"> 
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--送出當前是第幾頁給Controller-->
						<input type="hidden" name="action" value="update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@include file="page2.file"%>
--%>
	<hr><br><br>
	
	
	
	<table>
		<tr>
			<th>房仲文章編號</th>
			<th>房仲編號</th>
			<th>房仲文章內容</th>
			<th>文章發布日期</th>
			<th>文章狀態</th>
			<th colspan="2">修改文章</th>

		</tr>
		<%@ include file="pageart.file"%>
		<c:forEach var="articleVO" items="${artTime}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'>
				<td>${articleVO.article_no}</td>
				<td>${articleVO.rtr_no}</td>
				<td>${articleVO.article_body}</td>
				<td>${articleVO.post_date}</td>
				<td>${articleVO.article_state}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="新增"> 
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--送出當前是第幾頁給Controller-->
						<input type="hidden" name="action" value="insert">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="article_no" value="${articleVO.article_no}"> 
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--送出當前是第幾頁給Controller-->
						<input type="hidden" name="action" value="update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@include file="pageart2.file"%>

	
	
	