<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ann.model.*"%>

<%
	AnnVO annVO = (AnnVO) request.getAttribute("annVO");
	AnnService annSvc = new AnnService();
	List<AnnVO> annlist = annSvc.getAll();
	pageContext.setAttribute("annlist", annlist);
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
<link rel="shortcut icon" href="http://localhost:8081/BA104G5/images/Houselogo1.png" />
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
<link href="assets/css/magnific-popup.css" type="text/css"
	rel="stylesheet" />
<!-- Main CSS -->
<link href="assets/css/style.css" type="text/css" rel="stylesheet" />
<link href="assets/css/responsive.css" type="text/css" rel="stylesheet" />
<!-- end套用別人的cdn -->
<!--navbar阿逵做的css -->
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

	<!-- 我的新聞首頁 ===========================================================================-->
	<!-- MAIN CONTAINER -->
	<div class="main-content">
		<!-- HEADER TREE -->
		<section class="header-tree" data-speed="8" data-type="background">
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
							<li><a class="link text-success" href="">首頁</a></li>
							<li><a class="link" href="#">最新消息</a></li>
						</ul>
					</div>
					<!-- /.col-md-12 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container -->
		</section>
		<!-- HEADER TREE END -->
	</div>

	<!-- 寫死的介紹文======================================================================================= -->
	<!-- BLOG INTRODUCTION -->
	<section>
		<!-- .container -->
		<div class="container">
			<!-- .row -->
			<div class="row">
				<!-- .col-md-12 -->
				<div class="col-md-12">

					<!-- All Blog posts -->
					<h1 class="text-primary wow fadeInDown text-uppercase text-center"
						data-wow-duration="1s" data-wow-delay=".3s">好房事，讓你不僅房內事沒問題，房外事也處理好好</h1>
					<div class="text-center">
						<i class="text-muted align-center mar-bottom-sm"> 成家霸業
							ForHouse</i>
					</div>
					<div class="text-center">
						<span class="separator"> <i
							class="icon icon-small iline2-ruler9 text-primary no-margin"></i>
						</span>
					</div>
					<!-- .row -->
					<div class="row">
						<div class="col-md-4 col-xs-12 mar-bottom-md">
							<div class="wow fadeInUp text-center" data-wow-duration="1s"
								data-wow-delay=".5s" id="cooper_run">
								<div>
									<br> <br>讓你知道現在房市的最新消息!<br>想要買房的各路王公子爵們，覺得現在市場上，消息太多，又不知道如何分辨消息來源!?<br>沒有關係，因為真的假不了，假的不能真，江湖你在走，消息我們一定有!<br>讓你隨時掌握各路消息，霸業可成<br>
								</div>
								<div>
									<br> <br>俗語說:「化妝界牛耳，做菜界有阿基師、天界有比克大魔王」 <br>那你不可不知道，我們就是房屋界裡的牛耳、阿基師、比克大魔王!<br>我們隨時提供時下最新室內設計，木工裝修、還有如何逃漏稅，阿，不是，是對抗一大堆專業學問，讓你成就霸業，不需要費一兵一卒!
								</div>
							</div>
						</div>
						<div class="col-md-4 hidden-xs hidden-sm">
							<div class="thumbnail mar-auto wow fadeInUp"
								data-wow-duration="1s" data-wow-delay=".5s">
								<figure>
									<img src="images/news_special.png" class="img-responsive"
										alt="" />
									<img src="images/news_special2.png" class="img-responsive"
										alt="" />
									<div class="image-overlay">
										<p>
											<a href="images/news_special3.png" class="show-image"> <i
												class="iline2-arrows55"></i>
											</a>
										</p>
									</div>
									<!--image overlay-->
								</figure>
								<!--/figure-->
							</div>
						</div>
						<div class="col-md-4">
							<div class="wow fadeInUp text-center" data-wow-duration="1s"
								data-wow-delay=".5s" id="cooper_run2">
								<div>
									<br> <br>讓你知道現在房市的最新消息!<br>想要買房的各路王公子爵們，覺得現在市場上，消息太多，又不知道如何分辨消息來源!?<br>沒有關係，因為真的假不了，假的不能真，江湖你在走，消息我們一定有!<br>讓你隨時掌握各路消息，霸業可成<br>
								</div>
								<div>
									<br> <br>俗語說:「化妝界牛耳，做菜界有阿基師、天界有比克大魔王」 <br>那你不可不知道，我們就是房屋界裡的牛耳、阿基師、比克大魔王!<br>我們隨時提供時下最新室內設計，木工裝修、還有如何逃漏稅，阿，不是，是對抗一大堆專業學問，讓你成就霸業，不需要費一兵一卒!
								</div>
							</div>
						</div>
					</div>
					<!-- /.row -->
					<!--/ Blog introduction text -->

				</div>
				<!-- /.col-md-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</section>
	<!-- BLOG INTRODUCTION END -->
	<!-- 結束寫死的介紹文======================================================================================= -->


	<!-- BLOG BODY SECTION -->
	<section class="pattern-3 no-padding-right">
		<!-- .container -->
		<div class="container">
			<!-- .row -->
			<div class="row">
				<!-- .col-md-12 -->
				<div class="col-md-12">
					<!-- All blog posts -->
					<div class="mar-top-lg">
						<ul class="isotope-blog">


							<!-- 房市新聞 =============================================================================================-->
							<!-- .blog-post -->
							<div>
								<li class="blog-post wow fadeInUp" data-wow-duration="1s"
									data-wow-delay=".7s">
									<p class="cat-links">
										<a class="text-capitalize"><i
											class="iline2-tag27 no-margin"></i>房市最新消息</a>
									</p> <span class="blog-date"> 21 <small class="blog-month">jul</small>
								</span>
									<div class="blog-body">
										<img src="assets/images/blog/post-1.jpg" alt="post 1"
											class="img-responsive img-full" />
										<h4 class="text-primary text-center text-uppercase">Work</h4>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing
											elit, sed do [...]</p>
										<a class="btn btn-primary" href="blog-post.html" role="button"
											title="continue reading"> Read more</a>
									</div>
								</li>
								<!-- /.blog-post -->
								<!-- .blog-post -->
								<li class="blog-post wow fadeInUp" data-wow-duration="1s"
									data-wow-delay=".7s">
									<p class="cat-links">
										<a class="text-capitalize"><i
											class="iline2-tag27 no-margin"></i>房市最新消息</a>
									</p> <span class="blog-date"> 21 <small class="blog-month">jul</small>
								</span>
									<div class="blog-body">
										<!-- embed youtube 4:3 aspect ratio -->
										<div class="embed-responsive embed-responsive-4by3">
											<iframe src="https://www.youtube.com/embed/4m1EFMoRFvY"
												allowfullscreen></iframe>
										</div>
										<h4 class="text-primary text-center text-uppercase">Youtube
											embed</h4>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing
											elit, sed do [...]</p>
										<a class="btn btn-primary" href="blog-post.html" role="button"
											title="continue reading"> Read more</a>
									</div>
								</li>
								<!-- /.blog-post -->
								<!-- .blog-post -->
								<li class="blog-post wow fadeInUp" data-wow-duration="1s"
									data-wow-delay=".7s">
									<p class="cat-links">
										<a class="text-capitalize"><i
											class="iline2-tag27 no-margin"></i>房市最新消息</a>
									</p> <span class="blog-date"> 20 <small class="blog-month">jul</small>
								</span>
									<div class="blog-body">
										<img src="assets/images/blog/post-2.jpg" alt="post 2"
											class="img-responsive img-full" />
										<h4 class="text-success text-center text-uppercase">Marketing
											#1</h4>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing
											elit, sed do [...]</p>
										<a class="btn btn-primary" href="blog-post.html" role="button">
											Read more</a>
									</div>
								</li>
							</div>
							<!-- 房市新聞 ============================================================================================-->

							<!-- 結束促銷資訊 =============================================================================================-->
							<li class="blog-post wow fadeInUp" data-wow-duration="1s"
								data-wow-delay=".7s">
								<p class="cat-links">
									<a class="text-capitalize"><i
										class="iline2-tag27 no-margin"></i>促銷資訊</a>
								</p> <span class="blog-date"> 19 <small class="blog-month">jul</small>
							</span>
								<div class="blog-body">
									<img src="assets/images/blog/post-3.jpg" alt="post 3"
										class="img-responsive img-full" />
									<h4 class="text-primary text-center text-uppercase">Logo</h4>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing
										elit, sed do [...]</p>
									<a class="btn btn-primary" href="blog-post.html" role="button"
										title="continue reading"> Read more</a>
								</div>
							</li>
							<!-- /.blog-post -->
							<!-- .blog-post -->
							<li class="blog-post wow fadeInUp" data-wow-duration="1s"
								data-wow-delay=".7s">
								<p class="cat-links">
									<a class="text-capitalize"><i
										class="iline2-tag27 no-margin"></i>促銷資訊</a>
								</p> <span class="blog-date"> 18 <small class="blog-month">jul</small>
							</span>
								<div class="blog-body">
									<img src="assets/images/blog/post-4.jpg" alt="post 4"
										class="img-responsive img-full" />
									<h4 class="text-primary text-center text-uppercase">Marketing
										#2</h4>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing
										elit, sed do [...]</p>
									<a class="btn btn-primary" href="blog-post.html" role="button"
										title="continue reading"> Read more</a>
								</div>
							</li>
							<!-- /.blog-post -->
							<!-- .blog-post -->
							<li class="blog-post wow fadeInUp" data-wow-duration="1s"
								data-wow-delay=".7s">
								<p class="cat-links">
									<a class="text-capitalize"><i
										class="iline2-tag27 no-margin"></i>促銷資訊</a>
								</p> <span class="blog-date"> 18 <small class="blog-month">jul</small>
							</span>
								<div class="blog-body">
									<blockquote>
										<p>
											<i class="icon-small iline1-light34 mar-top-sm"> Integer
												in mauris eu nibh euismod gravida. Duis ac tellus et risus
												vulputate vehicula. Donec lobortis risus a elit. Etiam
												tempor. Ut ullamcorper, ligula eu tempor congue. </i>
										</p>
										<cite>- John Smith</cite>
									</blockquote>
								</div>
							</li>
							<!-- 結束促銷資訊 =============================================================================================-->

                <li class="blog-post wow fadeInUp" data-wow-duration="1s">
<div class="container">
   
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active" style="background-color: red;"></li>
                <li data-target="#myCarousel" data-slide-to="1" style="background-color: red;"></li>
                <li data-target="#myCarousel" data-slide-to="2" style="background-color: red;"></li>
            </ol>
            
            <!-- Wrapper for slides -->
       <div class="carousel-inner"> 
            <%for(int i=0;i<3;i++){%>
                <%if(i==0){%>
                    <div class="item active">
                <%}else{%>
                    
                    <div class="item ">
                    <%}%>
                
                <%for(int j=0;j<3;j++){%>
                <div class="col-sm-4">
                        <div class="card">
                            <img class="img-fluid" src="http://fakeimg.pl/350x200/" alt="Card image cap">
                            <div class="card-body">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                <div style="text-align:center;">
                                    <button class="btn btn-info">按我</button>
                                </div>
                            </div>
                        </div>
                
                </div>
                 <%  } %>
                </div>
          <%  } %>
            </div>
         </div> 
         
            
            
            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev" style="color: red;">
      <span class="glyphicon glyphicon-chevron-left" style="color: red;"></span>
      <span class="sr-only">Previous</span>
    </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next" style="color: red;">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
    
    
    </div>
</div>

</li> 

















							<!-- 系統公告================================================================================================= -->
<%-- 							<%@include file="page_front_news/pageann.file"%> --%>
<%-- 							<c:forEach var="annVO" items="${annlist}" begin="<%=pageIndex%>" --%>
<%-- 								end="<%=pageIndex+rowsPerPage-1%>"> --%>
<!-- 								<li class="blog-post wow fadeInUp" data-wow-duration="1s" -->
<!-- 									data-wow-delay=".7s"> -->
<!-- 									<p class="cat-links"> -->
<!-- 										<a class="text-capitalize"><i -->
<!-- 											class="iline2-tag27 no-margin"></i>系統公告</a> -->
<%-- 									</p> <span class="blog-date"> <fmt:formatDate --%>
<%-- 											value="${annVO.ann_date}" pattern="MM" />月 <small --%>
<%-- 										class="blog-month"><fmt:formatDate --%>
<%-- 												value="${annVO.ann_date}" pattern="dd" /></small> --%>
<!-- 								</span> -->
<!-- 									<div class="blog-body"> -->
<!-- 										<img src="assets/images/blog/post-5.jpg" alt="post 5" -->
<!-- 											class="img-responsive img-full" /> -->
<%-- 										<h4 class="text-primary text-center text-uppercase">${annVO.ann_title}</h4> --%>
<%-- 										<p>${annVO.ann_content}</p> --%>
<!-- 										<div style="text-align: center;"> -->
<!-- 											<form method="post" -->
<%-- 												action="<%=request.getContextPath()%>/back/ann/ann.do"> --%>
<!-- 												<input type="hidden" name="requestURL" -->
<%-- 													value="<%=request.getServletPath()%>"> <input --%>
<%-- 													type="hidden" name="ann_no" value="${annVO.ann_no}"> --%>
<!-- 												<input type="hidden" name="action" -->
<!-- 													value="getOne_For_Display"> -->
<!-- 												<button class="ann_btn">按我</button> -->
<!-- 											</form> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</li> -->
<%-- 							</c:forEach> --%>

							<!-- 彈出系統公告內容 -->
							<div class="" style="display: none; margin-top: 50em;"
								id="ann_jump">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title">系統公告</h4>
										</div>
										<div class="modal-body">
											<form role="form" name="ann">
												<div class="form-group">
													<h4 class="text-primary text-center text-uppercase">${annVO.ann_title}</h4>
												</div>
												<div class="form-group">
													<label for="ann_content">公告內容</label>
													<textarea rows="10" class="form-control" name="ann_content">${annVO.ann_content}</textarea>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-info ann_leave">確認</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
							<!-- 結束彈出系統公告內容 -->

							<!-- 結束系統公告================================================================================================= -->
						</ul>
<%-- 						<%@include file="page_front_news/pageann2.file"%> --%>
						<!-- .navigation -->



						<!-- 				換頁 ==================================================================== -->
						<!--                 <nav class="navigation paging-navigation"> -->
						<!--                   <ul class="page-numbers pagination"> -->
						<!--                     <li><span class="page-numbers pagination current">1</span></li> -->
						<!--                     <li><a class="page-numbers pagination" href="#">2</a></li> -->
						<!--                     <li><a class="page-numbers pagination" href="#">3</a></li> -->
						<!--                     <li><a class="next page-numbers pagination" href="#">Next →</a></li> -->
						<!--                   </ul> -->
						<!--                 </nav>/.navigation -->
					</div>
					<!--               /All blog posts -->
				</div>
			</div>
		</div>
	</section>
	<!--       換頁 ==================================================================== -->




	<!-- Back to top -->
	<div id="back-to-top" class="back-to-top">
		<a href="#" class="icon iline2-thin16 no-margin" style="color: white;"></a>
	</div>
	<!-- Back to top end -->

	<!-- end我的新聞內頁 ===========================================================================-->


	<!-- footer -->
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

	<!-- 做文字跑馬燈必備的cdn -->
	<script type="text/javascript"
		src="http://malsup.github.com/chili-1.7.pack.js"></script>
	<script type="text/javascript"
		src="http://malsup.github.com/jquery.cycle.all.js"></script>
	<script type="text/javascript"
		src="http://malsup.github.com/jquery.cycle.all.js"></script>
	<script type="text/javascript" src="js/news_front_cooper.js"></script>
	<!-- 做文字跑馬燈必備的cdn -->


	<!-- 出現彈窗的系統公告資訊 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$(".ann_btn").click(function() {
				$("#ann_jump").toggle();
			});
		});
	</script>
	<!-- 關閉彈窗的系統公告資訊 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$(".ann_leave").click(function() {
				$("#ann_jump").hide();
			});
		});
	</script>
</body>

</html>