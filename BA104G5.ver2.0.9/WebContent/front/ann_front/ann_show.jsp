<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>For House</title>
    <link rel="shortcut icon" href="images/houselogo1.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
  
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>

    <!-- 套用別人的cdn -->
    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <!-- Plugins CSS -->
    <link href="assets/css/normalize.css" type="text/css" rel="stylesheet" />
    <link href="assets/css/iline-icons.css" type="text/css" rel="stylesheet" />
    <link href="assets/css/animate.css" type="text/css" rel="stylesheet" />
    <link href="assets/css/owl.carousel.css" type="text/css" rel="stylesheet" />
    <link href="assets/css/owl.theme.css" type="text/css" rel="stylesheet" />
    <link href="assets/css/owl.transitions.css" type="text/css" rel="stylesheet" />
    <!-- Main CSS -->
    <link href="assets/css/style.css" type="text/css" rel="stylesheet" />
    <link href="assets/css/responsive.css" type="text/css" rel="stylesheet" />
    <!-- Shortcut icon -->
    <link rel="shortcut icon" href="assets/images/favicon.ico" type="image/x-icon" />
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
                    <a class="navbar-brand " href="#"><img class="navshadow" src="http://localhost:8081/BA104G5/images/For House logo.png" width="120px"></a>
                </div>
                <div class="col-xs-12 col-sm-8" style="color: white; background-color: white;">
                    <ul class="nav navbar-nav activebar" style="color: white; background-color: white;">
                        <li><a href="#" data-toggle="tooltipNews" data-placement="bottom">最新消息</a></li>
                        <li><a href="#">常見問題</a></li>
                        <li><a href="#">看房去</a></li>
                        <li><a href="#">找房仲</a></li>
                        <li><a href="#">安家商城</a></li>
                        <li><a href="#">加入我們</a></li>
                    </ul>
                </div>
                <div class="col-xs-12 col-sm-2">
                    <ul class="nav navbar-nav logina">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> 註冊</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> 登入</a></li>
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
            <div class="cover-1" data-type="sprite" data-offsetY="-700" data-Xposition="50%" data-speed="-2"></div>
            <!-- .container -->
            <div class="container">
                <!-- .row -->
                <div class="row">
                    <!-- .col-md-12 -->
                    <div class="col-md-12">
                        <ul class="breadcrumb clearfix mar-top-3x">
                            <li><a class="link text-success" href="/">首頁</a></li>
                            <li><a class="link" href="news_front.html">最新消息</a></li>
                            <li><a class="link" href="#">系統公告</a></li>
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
                                            <li><a href="#" class="iline1-spiral4">房市新聞</a></li>
                                            <li><a href="#" class="iline1-spiral4">買屋百科</a></li>
                                            <li><a href="#" class="iline1-spiral4">安家百科</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <!-- Categories List End -->
                                <!-- Archive List -->
                                <!-- <div class="post-detail mar-top-md">
                    <div class="post-detail-body sep-xs">
                      <h3 class="text-primary text-uppercase text-center">ARCHIVES</h3>
                      <ul class="text-capitalize sidebar">
                        <li><a href="#" class="iline1-spiral4">June 2015 (150)</a></li>
                        <li><a href="#" class="iline1-spiral4">May 2015 (134)</a></li>
                        <li><a href="#" class="iline1-spiral4">April 2015 (232)</a></li>
                        <li><a href="#" class="iline1-spiral4">March 2015 (274)</a></li>
                        <li><a href="#" class="iline1-spiral4">February 2015 (434)</a></li>
                        <li><a href="#" class="iline1-spiral4">January 2015 (934)</a></li>
                        <li><a href="#" class="iline1-spiral4">December 2014 (224)</a></li>
                        <li><a href="#" class="iline1-spiral4">November 2014 (439)</a></li>
                      </ul>
                    </div>
                  </div> -->
                                <!-- Archive List End -->
                            </div>
                            <!-- /Right part -->
                            <!-- Left part -->
                            <div class="col-md-8 blog-main">
                                <div class="post-detail sep-bottom-lg">
                                    <figure class="no-margin">
                                        <img src="http://localhost:8081/BA104G5/images/nopic.jpg" class="img-responsive">
                                    </figure>
                                    <div class="post-detail-body sep-xs">
                                        <p>(圖:房市景氣谷底已過，近來不少建商出手獵地，帶動標售市場轉趨熱絡，民眾購屋信心也明顯揚升。)</p>
                                        <h3 class="text-uppercase">土地標售市場開紅盤，房市現曙光</h3>
                                        <span class="line-sep-gray"></span>
                                        <p>
                                            <span class="letter-dropcap">台</span> 灣房地產市場已陷入不景氣多年，近日終於出現一絲曙光。新北市日前標售位於新店中央新村北側的重劃區土地，標脫率逾八成，包括大陸建設、茂德機構旗下和碩開發等均出手；桃園市府近日也釋出土地標售，其中，中路特區土地標脫率達六成，表現也不錯。
                                            <br> 房地產專家分析，台灣的不動產制度是永久所有權制，土地是「賣一塊、少一塊」，換句話說，土地是房市最重要的「原料」，如今指標建商勇於出手獵地，顯見房市景氣已大致落底，後續有開始反彈走揚的機會。
                                            <br>
                                            <br> 央北重劃區 指標建商插旗
                                        </p>
                                        <p>
                                              	新店的央北重劃區是大台北最受矚目的新興重劃區，屬於「閃耀五星計畫」之一，鄰近捷運新店線小碧潭站、及興建中的環狀線十四張站，未來可與安坑線轉乘，又有環河快速道路、中安大橋等，交通頗為便捷，新店高中、陽光運動公園等公共設施，也為生活機能加分不少。
                                            <br> 此次標售，新北市共釋出七筆土地，有六筆成功標脫，總金額約57.6億元，平均溢價11%。最熱門的一筆，是斯馨段129地號因鄰近捷運小碧潭站、又是角地，吸引六方人馬競逐，最後由黃姓自然人以6.9億元、溢價約27%標下，每坪標脫單價約167.9萬元。
                                            <br> 上市公司欣陸投控旗下大陸建設，也以7.15億元取得520.8坪土地，老牌建商茂德機構旗下和碩開發，則以6.96億元標得555.4坪土地。專家分析，指標建商都接連出手，顯然是嗅到市場即將回溫。
                                        </p>
                                        <p>
                                            <br>桃園中路熱門 標脫率六成
                                            <br> 桃園市政府近日也標售經國重劃區及中路地區土地，共分25個標號，吸引22封標單，其中，中路特區順利脫標6件，脫標率高達六成。競爭激烈的是中路三192地號，面積近千坪，達934.2坪，有七人競標，最後以總價 5.85億元，每坪單價約62.7萬元標出；中路二136地號標脫單價則最高，達每坪204.9 萬元。
                                            <br> 觀察得標者，包括寶佳、興富發等大型建商。資深房仲分析，中路地區緊鄰桃園中正藝文特區，規劃完善，特區規劃有綠廊道與綠園道，對於都市住宅稠密的區域來說，公園綠地最能吸引購屋者青睞，加上兩區周邊的交通便利程度相當高，自然吸引建商看中此區。
                                        </p>
                                        <p>
                                            <br>標案連登場 總額逾270億元
                                            <br> 房產專家分析，如今房市的谷底已過，市場信心逐漸恢復，加上建商928檔推案造勢、建案讓利，以及銀行友善房貸條件等等，各種關鍵原因，讓近來的市場銷售狀況轉優，建商自然願意出手獵地，帶動標售狀況熱絡。
                                            <br> 觀察得標者，包括寶佳、興富發等大型建商。資深房仲分析，中路地區緊鄰桃園中正藝文特區，規劃完善，特區規劃有綠廊道與綠園道，對於都市住宅稠密的區域來說，公園綠地最能吸引購屋者青睞，加上兩區周邊的交通便利程度相當高，自然吸引建商看中此區。
                                            <br> 由於標況轉好，官方與民間單位紛紛釋出手上存貨，據統計，未來一個半月，北中南都有大型不動產標售，包括高鐵土地、新莊副都心土地，桃園、台南重劃區土地等，總金額逾270億元，標售結果值得期待。
                                            <div class="text-right">
                                                <cite class="from_internet">取自udn聯合新聞網</cite>
                                            </div>
                                        </p>
                                        <ul class="post-info pull-left">
                                            <li>
                                                <i class="iline2-round27 text-primary"></i><a>2017-10-17</a>
                                            </li>
                                        </ul>
                                        <div class="text-center share-menu">
                                            <ul class="social">
                                                <li><a target="_blank" title="" data-toggle="tooltip" data-placement="top" href="#" class="icon iline-iline-3google27" data-original-title="Share on Google Plus"></a></li>
                                                <li><a target="_blank" title="" data-toggle="tooltip" data-placement="top" href="#" class="icon iline-3facebook27" data-original-title="Share on Facebook"></a></li>
                                                <li><a target="_blank" title="" data-toggle="tooltip" data-placement="top" href="#" class="icon iline-3pinterest14" data-original-title="Share on Pinterest"></a></li>
                                                <li><a target="_blank" title="" data-toggle="tooltip" data-placement="top" href="#" class="icon iline-3twitter19" data-original-title="Share on Twitter"></a></li>
                                                <li><a target="_blank" title="" data-toggle="tooltip" data-placement="top" href="#" class="icon iline-3linked3" data-original-title="Share on Linkedin"></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="nav-links mar-top-md">
                                    <a href="blog-post-gallery.html" class="text-uppercase pull-left"><i class="iline1-arrowhead2"></i>上一則</a>
                                    <a href="blog-post-carousel.html" class="text-uppercase pull-right">下一則<i class="iline1-chevron15"></i></a>
                                </div>
                                <!-- Related Posts -->
                                <div class="post-detail mar-top-md mar-bottom-md">
                                    <div class="post-detail-body sep-xs">
                                        <h3 class="text-primary text-uppercase text-center">RELATED POSTS</h3>
                                        <div class="owl-carousel" id="related-posts">
                                            <a href="#" rel="" title="Lorem ipsum dolor sit amet, consectetur adipisicing elit.">
                          <img src="assets/images/works/work-1.jpg" alt="" class="img-responsive" />
                          <h5 class="text-center text-success text-uppercase">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</h5>
                          <i class="text-center text-muted">April 17, 2015</i>
                        </a>
                                            <a href="#" rel="" title="Lorem ipsum dolor sit amet, consectetur adipisicing elit.">
                          <img src="assets/images/works/work-2.jpg" alt="" class="img-responsive" />
                          <h5 class="text-center text-primary text-uppercase">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</h5>
                          <i class="text-center text-muted">April 17, 2015</i>
                        </a>
                                            <a href="#" rel="" title="Lorem ipsum dolor sit amet, consectetur adipisicing elit.">
                          <img src="assets/images/works/work-3.jpg" alt="" class="img-responsive" />
                          <h5 class="text-center text-primary text-uppercase">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</h5>
                          <i class="text-center text-muted">April 17, 2015</i>
                        </a>
                                            <a href="#" rel="" title="Lorem ipsum dolor sit amet, consectetur adipisicing elit.">
                          <img src="assets/images/works/work-4.png" alt="" class="img-responsive" />
                          <h5 class="text-center text-primary text-uppercase">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</h5>
                          <i class="text-center text-muted">April 17, 2015</i>
                        </a>
                                            <a href="#" rel="" title="Lorem ipsum dolor sit amet, consectetur adipisicing elit.">
                          <img src="assets/images/works/work-5.jpg" alt="" class="img-responsive" />
                          <h5 class="text-center text-primary text-uppercase">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</h5>
                          <i class="text-center text-muted">April 17, 2015</i>
                        </a>
                                            <a href="#" rel="" title="Lorem ipsum dolor sit amet, consectetur adipisicing elit.">
                          <img src="assets/images/works/work-6.jpg" alt="" class="img-responsive" />
                          <h5 class="text-center text-success">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</h5>
                          <i class="text-center text-muted">April 17, 2015</i>
                        </a>
                                            <a href="#" rel="" title="Lorem ipsum dolor sit amet, consectetur adipisicing elit.">
                          <img src="assets/images/works/work-7.jpg" alt="" class="img-responsive" />
                          <h5 class="text-center text-primary text-uppercase">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</h5>
                          <i class="text-center text-muted">April 17, 2015</i>
                        </a>
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
            <p style="text-align: center;"><small>Copyright © 2017 For House</small></p>
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