<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.news.model.*"%>
<%@ page import="com.newstype.model.*"%>
<%@ page import="com.ann.model.*"%>

<jsp:useBean id="newsSvc" scope="page" class="com.news.model.NewsService" />
<jsp:useBean id="annSvc" scope="page" class="com.ann.model.AnnService" />

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");

	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
	List<NewsVO> newsListTime = newsSvc.getAllByTime();
	pageContext.setAttribute("newsListTime", newsListTime);
	
	AnnVO annVO = (AnnVO) request.getAttribute("annVO");
	List<AnnVO> annListTime = annSvc.getAllByTime();
	pageContext.setAttribute("annListTime", annListTime);
	
	
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/Houselogo1.png" />
    <title>For House</title>
</head>

<body>
    <nav class="navbar navbar-fixed-top">
        <jsp:include page="/front/navbar.jsp" />
    </nav>
    
	<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.css">
	<script src='<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.min.js '></script>
	<script src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.js"></script>
    
    <script>
		$(document).ready(function() {
			if(<%=request.getAttribute("logoutSuccess")!=null%>){swal('<h3><%=request.getAttribute("logoutSuccess")%></h3>')}
		});
	</script>
    
    <!-- 神奇小按鈕的script 開始-->
    <script>
        $(document).ready(function() {
            $("#magicBtn1").click(function() {
                $("#loginid").val("abc@gmail.com");
                $("#password").val("123456");
            });
            $("#magicBtn2").click(function() {
                $("#mem_id").val("abc@gmail.com");
                $("#password1").val("123456");
                $("#password2").val("123456");
                $("#mem_name").val("大大");
                $("#mem_addr").val("中壢市中大路300號");
                $("#fancy-checkbox-info").attr("checked","enable");
                $("#aggrement2").attr("checked","enable");
            });
        });
    </script>
    <!-- 神奇小按鈕的script 結束-->

    <style type="text/css">
   
    .mobileBar {
        margin-top: 8em;
    }
    .mobileBar>img {
        max-height: 100%;
        max-width: 100%;
    }
    .headerBar{
    	width: 95em;
    }
    
    #loginArea{
    	width: 130px;
    }

    .box-width{
        width:520px;
        margin-left: 50px;
    }
    .margin-top{
        margin-top: 20px;
    }
    .searchstate{
        margin-left: 50px;
    }
    #searchrequire{
        font-size: 18px;
    }
    #aggrement{
        color: #999999;
    }

    #fancy-checkbox-info {
    display: none;
    }
    
    <!-- 燈箱內容 開始 -->
    .modal{
        text-align: center;
        padding: 0!important;
    }
    .modal:before{
        content: '';
        display: inline-block;
        height: 100%;
        vertical-align: middle;
        margin-right: 42em;
    }
    .modal-dialog{
        display: inline-block;
        text-align: left;
        vertical-align: middle;
    }
    .joinbtn{
    	margin: 30px;
    	width: 90%;
    }
    <!-- 燈箱內容 結束 -->
    
    </style>

    <div class="container-fluid backgroundpng">
        <img class="row" src="<%=request.getContextPath()%>/images/fixed_bg.png">
    </div>
    
    
    <!-- 燈箱內容 -->
    <div class="modal fade" id="joinUs">
		<div class="modal-dialog">
			<div class="modal-content">
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title text-center">好房事歡迎您的加入</h4>
            </div>
				<div>
					<div class="form-group"> </div>
				    <div class="joinbtn">
				        <a class="btn btn-info btn-lg btn-block" href="<%=request.getContextPath()%>/front/realtor/realtor_register.jsp" role="button"><h3>我  是  房  仲</h3></a>
				    </div>
				    <div class="form-group"> </div>
				    <div class="joinbtn">
				        <a class="btn btn-info btn-lg btn-block" href="<%=request.getContextPath()%>/front/seller/register.jsp" role="button"><h3>我  是  廠  商</h3></a>
				    </div>
				</div>
			</div>
		</div>
	</div>
	<!-- 燈箱內容  結束-->
    
   <div id="myCarousel" class="carousel slide " data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
            <li data-target="#myCarousel" data-slide-to="4"></li>
        </ol>
        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="<%=request.getContextPath()%>/images/furniture_ad1.jpeg" alt="Image">
                <div class="carousel-caption">
                    <h3>Image1</h3>
                    <p>something</p>
                </div>
            </div>
            <div class="item">
                <img src="<%=request.getContextPath()%>/images/furniture_ad2.jpeg" alt="Image">
                <div class="carousel-caption">
                    <h3>Image2</h3>
                    <p>more</p>
                </div>
            </div>
            <div class="item">
                <img src="<%=request.getContextPath()%>/images/furniture_ad7.jpg" alt="Image">
                <div class="carousel-caption">
                    <h3>Image3</h3>
                    <p>more.</p>
                </div>
            </div>
            <div class="item">
                <img src="<%=request.getContextPath()%>/images/furniture_ad3.jpeg" alt="Image">
                <div class="carousel-caption">
                    <h3>Image4</h3>
                    <p>more..</p>
                </div>
            </div>
            <div class="item">
                <img src="<%=request.getContextPath()%>/images/furniture_ad4.jpeg" alt="Image">
                <div class="carousel-caption">
                    <h3>Image5</h3>
                    <p>more...</p>
                </div>
            </div>
             <div class="item">
                <a href="<%=request.getContextPath()%>/front/games/SlotMachine.jsp"><img style="width:2100px;height:700px" src="<%=request.getContextPath()%>/images/furniture_ad5.jpg" alt="Image"></a>
                <div class="carousel-caption">
                    <h3>Image5</h3>
                    <p>more...</p>
                </div>
            </div>
            <div class="searchbar col-sm-5 col-sm-offset-3">
                <form class="search"
							action="<%=request.getContextPath()%>/house/houseServlet.do"
							method="post">
							<div class="input-group">
								<span class="input-group-btn"> <!-- <div class="dropdown"> -->
									<a href="#" class="btn btn-info dropdown-toggle search-filter"
									data-toggle="dropdown">台北市 <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="#">松山區</a></li>
										<li><a href="#">大安區</a></li>
										<li><a href="#">中正區</a></li>
										<li><a href="#">大同區</a></li>
										<li><a href="#">中山區</a></li>
										<li><a href="#">萬華區</a></li>
										<li><a href="#">士林區</a></li>
										<li><a href="#">北投區</a></li>
										<li><a href="#">內湖區</a></li>
										<li><a href="#">南港區</a></li>
										<li><a href="#">文山區</a></li>
									</ul> <!-- </div> -->
								</span> <input type="text" class="form-control" name="para"
									placeholder="關鍵字、學校、街道"> <input type="hidden"
									name="sortedCondition" value="${sortedCondition}"> <input
									type="hidden" name="action" value="findByKeyword">
								<div class="input-group-btn">
									<button class="btn btn-default search-form-btn" type="submit">
										<!-- <span class="button-text ">找房子 </span> -->
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</div>
						</form>
            </div>
        </div>
        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
    </div>


    <div class="searchSRS">
        <div class="container">
            <div class="col-xs-12 col-sm-4 ina">
                <a href="#">
                    <div class="imgbox">
                        <img src="<%=request.getContextPath()%>/images/SearchHouse.jpg">
                        <div class="text-center textbox">
                            <div class="textbackground ">
                                <h3 class="textinline"><b>找 房 子<b></h3></div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-xs-12 col-sm-4 ina">
                <a href="#">
                    <div class="imgbox">
                        <img src="<%=request.getContextPath()%>/images/realtor.jpeg">
                        <div class="text-center textbox">
                            <div class="textbackground ">
                                <h3 class="textinline"><b>找 房 仲<b></h3></div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-xs-12 col-sm-4 ina">
                <a href="#">
                    <div class="imgbox">
                        <img src="<%=request.getContextPath()%>/images/Home-Sofa-2.jpg">
                        <div class="text-center textbox">
                            <div class="textbackground ">
                                <h3 class="textinline"><b>找 傢 俱<b></h3></div>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
    <div class="jumbotron furnitureAd">
    	<img src="<%=request.getContextPath()%>/images/furniture_ad6.jpg">
    </div>
    
    <div class="container">
            <div class="row">
                <div class=col-sm-6>
					<div class="panel panel-primary">
						<div class="panel-heading">最新房市新聞</div>
							<c:forEach var="newsVO2" items="${newsListTime}" varStatus="s">
							<div class="panel-body">
								<a href='#news_jump${s.index}' data-toggle="modal">${newsVO2.news_title} - ${newsVO2.news_date}</a>
							</div>
							<div class="modal fade" id="news_jump${s.index}">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">系統公告</h4>
										</div>
										<div class="modal-body">
											<img src="<%=request.getContextPath()%>/front/tools/showimage.do?action=news_photo&news_no=${newsVO2.news_no}" class="img-thumbnail" style="width: 100%; height:400px;">
										</div>
										<div class="modal-body">
											<label for="ann_title">新聞標題</label>
											<h4>${newsVO2.news_title}</h4>
										</div>
										<div class="modal-body">
											<label for="ann_content">新聞內容</label>
											<h4>${newsVO2.news_content}</h4>
										</div>
										<div class="modal-body panel-footer">
											<label for="post_date">新聞發布時間 - ${newsVO2.news_date}</label>
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
                <div class="col-sm-2"></div>
                <div class=col-sm-6>
					<div class="panel panel-primary">
						<div class="panel-heading">最新系統公告</div>
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
            </div>
        </div>
        
        
        <script type="text/javascript">
			function setBstModalMaxHeight(element) {
			  this.$element          = $(element);
			  this.$modalContent     = this.$element.find('.modal-content');
			  var $window            = $(window);
			  var $modalContentOH    = this.$modalContent.outerHeight();
			  var $modalContentIH    = this.$modalContent.innerHeight();
			  var _modalBorderWidth   = $modalContentOH - $modalContentIH;
			  var _modalDialogMargin  = $window.width() < 768 ? 20 : 60;
			  var _modalContentHeight = $window.height() - (_modalDialogMargin + _modalBorderWidth);
			  var _modalHeaderHeight  = this.$element.find('.modal-header').outerHeight() || 0;
			  var _modalFooterHeight  = this.$element.find('.modal-footer').outerHeight() || 0;
			  var _modalMaxHeight     = _modalContentHeight - (_modalHeaderHeight + _modalFooterHeight);
			
			  this.$modalContent.css({
			      'overflow': 'hidden'
			  });
			  
			  this.$element
			    .find('.modal-body').css({
			      'max-height': _modalMaxHeight,
			      'overflow-y': 'auto'
			  });
			}
			
			$('.modal').on('show.bs.modal', function() {
			  $(this).show();
			  setBstModalMaxHeight(this);
			});
			
			$(window).resize(function() {
			  if ($('.modal.in').length != 0) {
			    setBstModalMaxHeight($('.modal.in'));
			  }
			});
		</script>
        
        
        
    
    <div class="mobileBar jumbotron">
    	<img src="<%=request.getContextPath()%>/images/mobileBar.jpg">
    </div>

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
            <a href="<%=request.getContextPath()%>/front/qa/qa.jsp">常見問題</a>
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