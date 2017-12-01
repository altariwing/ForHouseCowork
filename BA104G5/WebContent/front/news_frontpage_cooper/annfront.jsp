<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ann.model.*"%>

<%
	AnnVO annVO = (AnnVO) request.getAttribute("annVO");
	AnnService annSvc = new AnnService();

	List<AnnVO> list = annSvc.getAll();
	pageContext.setAttribute("list", list);

	List<AnnVO> annListTime = annSvc.getAllByTime();
	pageContext.setAttribute("annListTime", annListTime);

	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/Houselogo1.png">
<title>系統公告</title>

<!-- 阿蓋的css -->
<!-- 多加的 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front/news_frontpage_cooper/css/news_front_cooper.css">
<!-- end阿蓋的css -->

<!-- realtor_cooperkai.js -->
<script src="<%=request.getContextPath()%>/front/news_frontpage_cooper/js/news_front_cooper.js"></script>
<script src="https://use.fontawesome.com/add3377d0a.js"></script>
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

	<!-- 顯示的畫面================================================================================ -->
	<div class="container" style="margin-top: 12em;">
		<div class="row">
			<div class="col-sm-1"></div>
			<%@include file="page_front_news/pageNewsFront.file"%>
			<div class="col-sm-1"></div>
		</div>
	</div>
	<br>

	<c:forEach var="annVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<div class="container">
			<div class="row">
				<div class=col-sm-3>
					<div class="panel panel-primary">
						<div class="panel-heading">最新發佈</div>
						<c:forEach var="annVO2" items="${annListTime}" varStatus="s">
							<div class="panel-body">
								<a href='#ann_jump${s.index}' data-toggle="modal">${annVO2.ann_title} - ${annVO2.ann_date}</a>
							</div>
							<div class="modal fade" id="ann_jump${s.index}">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">系統公告</h4>
										</div>
										<div class="modal-body">
											<label for="ann_title">公告標題</label>
											<h4>${annVO2.ann_title}</h4>
										</div>
										<div class="modal-body">
											<label for="ann_content">公告內容</label>
											<h4>${annVO2.ann_content}</h4>
										</div>
										<div class="modal-body panel-footer">
											<label for="post_date">公告時間 - ${annVO2.ann_date}</label>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">確認</button>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="panel panel-default col-sm-8">
					<div class="panel-heading form-group">
						<h4>系統公告</h4>
					</div>

					<div class="form-group">
						<label for="rtr_name">系統公告標題</label>
						<div>
							<h5>${annVO.ann_title}</h5>
						</div>
					</div>
					<div class="form-group">
						<label for="post_date">系統公告內容</label>
						<div>
							<h5 class="">${annVO.ann_content}</h5>
						</div>
					</div>
					<div class="form-group panel-footer">
						<label for="post_date">公告時間 - ${annVO.ann_date}</label>
					</div>
					</div>
				</div>
			</div>
	</c:forEach>

	<div class="nav-links mar-top-md">
		<%@include file="page_front_news/pageNewsFront2.file"%>
	</div>

	<!-- 回到最上面    -->
	<div id="gotop">˄</div>
	<!-- end阿蓋的搜尋房仲 =========================================================================================-->

	<!-- footer======================================================================================= -->
	<footer class="container-fluid">
        <div class="container text-center">
        <div class="col-xs-12 col-sm-2">
            <a href="#">For House首頁</a>
        </div>
        <div class="col-xs-12 col-sm-2">
            <a href="#">OUR TEAM</a>
        </div>
        <div class="col-xs-12 col-sm-2">
            <a href="<%=request.getContextPath()%>/front/news_frontpage_cooper/newsfront.jsp">房市新聞</a>
        </div>
        <div class="col-xs-12 col-sm-2">
            <a href="<%=request.getContextPath()%>/front/news_frontpage_cooper/annfront.jsp">系統公告</a>
        </div>
        <div class="col-xs-12 col-sm-2">
            <a href="#">COMMUNITIES</a>
        </div>
        <div class="col-xs-12 col-sm-2">
            <a href="#">CONTACT US</a>
        </div></div>
        <div class="copyri text-center">
            <p><small>Copyright © 2017 For House</small></p>
        </div>
    </footer>

</body>
</html>