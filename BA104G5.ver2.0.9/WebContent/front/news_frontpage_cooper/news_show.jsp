<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>
<%@ page import="com.newstype.model.*"%>

<%-- 採用 EL 的寫法取值 --%>

<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
	NewsService newsSvc = new NewsService();
	List<NewsVO> list = newsSvc.getAll();
	pageContext.setAttribute("list", list);
	
	List<NewsVO> listTime = newsSvc.getAllByTime();
	pageContext.setAttribute("listTime", listTime);
%>
<%
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>For House</title>
<link rel="shortcut icon" href="images/houselogo1.png" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/main.js"></script>

<!-- 套用別人的cdn -->
<!-- Bootstrap Core CSS -->
<link href="bootstrap/css/bootstrap.min.css" type="text/css"
	rel="stylesheet" />
<!-- Plugins CSS -->
<link href="assets/css/normalize.css" type="text/css" rel="stylesheet" />
<link href="assets/css/iline-icons.css" type="text/css" rel="stylesheet" />
<link href="assets/css/animate.css" type="text/css" rel="stylesheet" />
<link href="assets/css/owl.carousel.css" type="text/css"
	rel="stylesheet" />
<link href="assets/css/owl.theme.css" type="text/css" rel="stylesheet" />
<link href="assets/css/owl.transitions.css" type="text/css"
	rel="stylesheet" />
<!-- Main CSS -->
<link href="assets/css/style.css" type="text/css" rel="stylesheet" />
<link href="assets/css/responsive.css" type="text/css" rel="stylesheet" />
<!-- Shortcut icon -->
<link rel="shortcut icon" href="assets/images/favicon.ico"
	type="image/x-icon" />
<!-- end套用別人的cdn -->
<link rel="stylesheet" href="css/main.css">
<!-- cooperkai.css -->
<link rel="stylesheet" href="css/news_front_cooper.css">


</head>

<body>
	<!-- navbar -->
	<div class="main" id="main_cooper">
		<nav class="navbar navbar-fixed-top">
			<div class="container">
				<div class="navbar-header col-xs-12 col-sm-2">
					<a class="navbar-brand " href="#"><img class="navshadow"
						src="images/For House logo.png" width="120px"></a>
				</div>
				<div class="col-xs-12 col-sm-8"
					style="color: white; background-color: white;">
					<ul class="nav navbar-nav activebar"
						style="color: white; background-color: white;">
						<li><a href="#" data-toggle="tooltipNews"
							data-placement="bottom">最新消息</a></li>
						<li><a href="#">常見問題</a></li>
						<li><a href="#">看房去</a></li>
						<li><a href="#">找房仲</a></li>
						<li><a href="#">安家商城</a></li>
						<li><a href="#">加入我們</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-2">
					<ul class="nav navbar-nav logina">
						<li><a href="#"><span class="glyphicon glyphicon-user"></span>
								註冊</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
								登入</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<!-- endnavbar -->

	<!-- 我的新聞內頁 ===========================================================================-->
	<!-- MAIN CONTAINER -->
	<div class="main-content">
		<!-- HEADER TREE -->
		<section class="header-tree" data-speed="8" data-type="background">
			<h2 class="hidden">Header tree</h2>
			<!-- parallax background -->
			<div class="cover-1" data-type="sprite" data-offsetY="-700"
				data-Xposition="50%" data-speed="-2"></div>
			<!-- .container -->
			<div class="container">
				<!-- .row -->
				<div class="row">
					<!-- .col-md-12 -->
					<div class="col-md-12">
						<ul class="breadcrumb clearfix mar-top-3x">
							<li><a class="link text-success" href="/">首頁</a></li>
							<li><a class="link" href="<%=request.getContextPath()%>/front/news_frontpage_cooper/news_front.jsp">最新消息</a></li>
							<li><a class="link" href="#">房市最新消息</a></li>
						</ul>
					</div>
					<!-- /.col-md-12 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container -->
		</section>
		<!-- HEADER TREE END -->
		<!-- BLOG POST BODY SECTION -->
		<section class="pattern-3 no-padding-right">
			<!-- .container -->
			<div class="container">
				<!-- .row -->
				<div class="row">
					<!-- .col-md-12 -->
					<div class="col-md-12">
						<!-- All blog posts -->
						<div class="mar-top-lg row">
							<!-- Right part -->
							<div class="col-md-4 blog-sidebar">
								<!-- Categories List -->
								<div class="post-detail">
									<div class="post-detail-body sep-xs">
										<h3 class="text-primary text-uppercase text-center">房市最新消息</h3>
										<ul class="text-capitalize sidebar">
											<jsp:useBean id="newstypeSvc" scope="page"
												class="com.newstype.model.NewsTypeService" />
											<c:forEach var="newstypeVO" items="${newstypeSvc.all}">
												<li><a href="#" class="iline1-spiral4">${newstypeVO.news_type}</a></li>
											</c:forEach>
										</ul>
									</div>
								</div>

								<!-- Archive List End -->
							</div>
							<!-- /Right part -->
							<!-- Left part -->
							<div class="col-md-8 blog-main">
								<%@include file="page_front_news/pageNewsFront.file"%>
								<c:forEach var="newsVO" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
									<div class="post-detail sep-bottom-lg">
										<figure class="no-margin">
											<img
												src="<%=request.getContextPath()%>/tool/showimage.do?action=news_photo&news_no=${newsVO.news_no}"
												class="img-responsive">
										</figure>
										<div class="post-detail-body sep-xs">
											<p>(圖:房市景氣谷底已過，近來不少建商出手獵地，帶動標售市場轉趨熱絡，民眾購屋信心也明顯揚升。)</p>
											<h3 class="text-uppercase">${newsVO.news_title}</h3>
											<span class="line-sep-gray"></span>
											<p>${newsVO.news_content}</p>
											<p><div class="text-right">
												<cite class="from_internet">取自udn聯合新聞網</cite>
											</div></p>
											
											<ul class="post-info pull-left">
												<li><i class="iline2-round27 text-primary"></i><a>${newsVO.news_date}</a>
												</li>
											</ul>
											<div class="text-center share-menu">
												<ul class="social">
													<li><a target="_blank" title="" data-toggle="tooltip"
														data-placement="top" href="#"
														class="icon iline-iline-3google27"
														data-original-title="Share on Google Plus"></a></li>
													<li><a target="_blank" title="" data-toggle="tooltip"
														data-placement="top" href="#"
														class="icon iline-3facebook27"
														data-original-title="Share on Facebook"></a></li>
													<li><a target="_blank" title="" data-toggle="tooltip"
														data-placement="top" href="#"
														class="icon iline-3pinterest14"
														data-original-title="Share on Pinterest"></a></li>
													<li><a target="_blank" title="" data-toggle="tooltip"
														data-placement="top" href="#"
														class="icon iline-3twitter19"
														data-original-title="Share on Twitter"></a></li>
													<li><a target="_blank" title="" data-toggle="tooltip"
														data-placement="top" href="#" class="icon iline-3linked3"
														data-original-title="Share on Linkedin"></a></li>
												</ul>
											</div>
										</div>
									</div>
								</c:forEach>


								<div class="nav-links mar-top-md">
									<%@include file="page_front_news/pageNewsFront2.file"%>

								</div>
								<!-- Related Posts -->
								<div class="post-detail mar-top-md mar-bottom-md">
									<div class="post-detail-body sep-xs">
										<h3 class="text-primary text-uppercase text-center">最新發佈</h3>
										<div class="owl-carousel" id="related-posts">
										<c:forEach var="newsVO2" items="${listTime}"><form method="post" action="news.do">
											<a href="<%=request.getContextPath()%>/front/news/news.do?action=getOne_For_Display&news_no=${newsVO2.news_no}" rel=""
												title="${newsVO2.news_title}">
												<img src="<%=request.getContextPath()%>/tool/showimage.do?action=news_photo&news_no=${newsVO2.news_no}" alt="新聞照片"
												class="img-responsive" />
												<h5 class="text-center text-success text-uppercase">${newsVO2.news_content}</h5> <i
												class="text-center text-muted">${newsVO2.news_date}</i>
											</a> </form>
										</c:forEach>
										</div>
									</div>
								</div>
								<!-- Related Posts End -->
							</div>
							<!-- /Left part -->
						</div>
					</div>
					<!-- /.col-md-12 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container -->
		</section>
		<!-- BLOG POST BODY SECTION END -->
	</div>
	<!-- MAIN CONTAINER END -->
	<!-- Back to top -->
	<div id="back-to-top" class="back-to-top">
		<a href="#" class="icon iline2-thin16 no-margin" style="color: white;"></a>
	</div>
	<!-- Back to top end -->
	<!-- end我的新聞內頁 ===========================================================================-->
	<footer class="container-fluid" id="cooper_footer">
		<div class="text-center">
			<div class="col-xs-12 col-sm-2">
				<a href="#">HOME</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">OUR TEAM</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">COMMUNITIES</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">COMMUNITIES</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">COMMUNITIES</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">CONTACT US</a>
			</div>
		</div>
		<div class="copyri text-center">
			<p style="text-align: center;">
				<small>Copyright © 2017 For House</small>
			</p>
		</div>
	</footer>
	<!-- Include js plugin -->
	<script src="assets/js/libs/jquery-1.11.2.min.js"></script>
	<script src="assets/js/libs/jqBootstrapValidation.js"></script>
	<script src="assets/js/libs/imagesloaded.pkgd.min.js"></script>
	<script src="assets/js/libs/imagesloaded.js"></script>
	<script src="assets/js/libs/jquery.magnific-popup.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/libs/isotope.pkgd.min.js"></script>
	<script src="assets/js/libs/ParallaxScrolling.js"></script>
	<script src="assets/js/libs/jquery.mailchimp.js"></script>
	<script src="assets/js/libs/wow.min.js"></script>
	<script src="assets/js/libs/owl.carousel.js"></script>
	<script src="assets/js/libs/jquery.fittext.js"></script>
	<script src="assets/js/libs/jquery.lettering.js"></script>
	<script src="assets/js/libs/jquery.textillate.js"></script>
	<!-- Main JS -->
	<script src="assets/js/main.js"></script>
</body>

</html>