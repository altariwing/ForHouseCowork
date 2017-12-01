<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.article.model.*"%>

<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
<jsp:useBean id="realtorSvc" scope="page" class="com.realtor.model.RealtorService" />

<%
	RealtorVO realtorVO = (RealtorVO) session.getAttribute("realtorVO");
	List<ArticleVO> list = articleSvc.getAllByTime();
	pageContext.setAttribute("list", list);

	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>For House</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/realtor/css/main.css">

<!-- 阿蓋的css -->
<!-- 多加的 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front/realtor/css/realtor_cooper.css">
<!-- end阿蓋的css -->

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/front/realtor/js/main.js"></script>


<!-- realtor_cooperkai.js -->
<script
	src="<%=request.getContextPath()%>/front/realtor/js/realtor_cooper.js"></script>
<!-- realtor_cooperkai.js -->

</head>
<body>



	<!-- 背景圖================================================================================= -->
	<div class="container-fluid backgroundpng">
		<img class="row"
			src="<%=request.getContextPath()%>/images/fixed_bg.png">
	</div>

	<nav class="navbar navbar-fixed-top">
		<jsp:include page="/front/navbar.jsp" />
	</nav>

	<div class="container" style="margin-top: 10em;">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div role="tabpanel">
					<!-- 標籤面板：標籤區 -->
					<ul class="nav nav-tabs" role="tablist" id="showActive">
						<li role="presentation" id="showActive2">
							<a href="<%=request.getContextPath()%>/front/realtor/allBlog.jsp" role="tab" id="top">最新文章</a>
						</li>
						<c:if test="${realtorVO != null}">
							<li role="presentation" id="showActive3">
								<a href="<%=request.getContextPath()%>/front/realtor/myBlog.jsp" role="tab">房仲怨天尤人區</a>
							</li>
						</c:if>
					</ul>

