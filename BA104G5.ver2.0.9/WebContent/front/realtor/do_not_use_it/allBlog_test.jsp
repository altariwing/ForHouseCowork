<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.article.model.*"%>

<%
	RealtorVO realtorVO = new RealtorVO();
	RealtorService realtorSvc = new RealtorService();
	List<RealtorVO> list = realtorSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list2 = articleSvc.getAll();
	List<ArticleVO> artTime = articleSvc.getAllByTime();
	pageContext.setAttribute("list2", list2);
	pageContext.setAttribute("artTime", artTime);
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
		<div class="col-sm-6 col-sm-offset-3">
			<%@ include file="pageart.file"%>
		</div>
	</div>
</div>
<c:forEach var="articleVO" items="${artTime}">
	<div class="container">
		<div class="row">
			<div class="panel panel-default col-sm-6 col-sm-offset-3">
				<div class="panel-heading form-group">
					<h4>文章</h4>
					<h4 style="float: right;">
						<a
							href="<%=request.getContextPath()%>/front/realtor/do_not_use_it/listAllBlog.jsp">回房仲文章列表</a>
					</h4>
				</div>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/front/article/article.do"
					name="form1">
					<c:forEach var="realtorVO" items="${list}">
					<div class="form-group">
						<label for="rtr_name">房仲名稱</label>
						<div>
							<h5>${realtorVO.getRtr_name()}</h5>
						<img
							src="<%=request.getContextPath()%>/tool/showimage.do?action=rtr_photo&rtr_no=${realtorVO.rtr_no}"
							style="width: 80px; height: 80px;"> 
						</div>
					</div>
					</c:forEach>
					<div class="form-group">
						<label for="article_no">文章編號</label>
						<div>
							<h5 name="article_no" class="">${articleVO.article_no}</h5>
						</div>
					</div>
					<div class="form-group">
						<label for="article_body">文章內容</label>
						<textarea rows="8" class="form-control" name="article_body">${articleVO.article_body}</textarea>
					</div>
					<div class="form-group">
						<label for="post_date">發佈日期</label>
						<div>
							<h5 class="">${articleVO.post_date}</h5>
						</div>
					</div>
					
					<div class = "input-group">
						<textarea class="form-control" name=""></textarea>
               			<span class = "input-group-btn">
                  			<button class = "btn btn-primary" type = "submit" style="padding:16px;">留言</button>
                  			<input type="hidden" name="action" value="insert"> 
							<input type="hidden" name="post_date" value="">
               			</span>
            		</div>
				</FORM>
			</div>
		</div>
	</div>
</c:forEach>
</body>



<script src="https://code.jquery.com/jquery.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 為了下拉式選單的js -->
<script	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>


</html>