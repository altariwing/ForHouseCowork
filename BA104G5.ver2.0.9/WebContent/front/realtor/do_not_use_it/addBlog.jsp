<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.article.model.*"%>

<%
	RealtorService realtorSvc = new RealtorService();
	List<RealtorVO> list = realtorSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> artlist = articleSvc.getAll();
	pageContext.setAttribute("artlist", artlist);

	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
	pageContext.setAttribute("articleVO", articleVO);
%>

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<jsp:useBean id="reSvc" scope="page"
	class="com.realestate.model.RealEstateService" />

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 為了下拉式選單的css -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
<title>員工資料新增 - addEmp.jsp</title>

</head>

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
		<div class="panel panel-default col-sm-6 col-sm-offset-3 top-margin-sm dia-msg">
			<div class="panel-heading form-group">
				<h4>文章新增</h4>
				<h4 style="float:right;">
					<a href="<%=request.getContextPath()%>/front/realtor/do_not_use_it/listAllBlog.jsp">回房仲文章列表</a>
				</h4>
			</div>
		
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/front/article/article.do"
				name="form1">
				<div class="form-group">
				<img src="https://api.fnkr.net/testimg/80x80" style="float:left" class="form-group"><label  for="rtr_no">房仲名稱</label>
					<input type="TEXT" name="rtr_no" size="45" class="form-control"
						value="<%=(articleVO == null) ? "編號" : articleVO.getRtr_no()%>" />
				</div>
				<div class="form-group">
					文章內容
					<textarea rows="10" class="form-control" name="article_body"><%=(articleVO == null) ? "文章內容" : articleVO.getArticle_body()%></textarea>
				</div>
				<div class="form-group">
					<label for="article_state">是否開放</label> <select
						class="selectpicker show-tick form-control" name="article_state">
						<option name="default_item" value="">選擇是否開放</option>
						<option value="ON"
							${(articleVO.ARTICLE_STATE == 'ON') ? 'selected' : ''}>ON</option>
						<option value="OFF"
							${(articleVO.ARTICLE_STATE == 'OFF') ? 'selected' : ''}>OFF</option>
					</select>
				</div>

				<div class="panel-footer form-group">
					<input type="hidden" name="action" value="insert"> <input
						type="hidden" name="post_date" value=""> <input
						type="submit" value="送出新增">
				</div>
			</FORM>
		</div>
	</div>
</div>
</body>

<%
	java.sql.Timestamp post_date = null;
	try {
		post_date = articleVO.getPost_date();
	} catch (Exception e) {
		post_date = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>


<script src="https://code.jquery.com/jquery.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 為了下拉式選單的js -->
<script	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>


</html>