<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>常見問題</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/qa/css/qa_front.css">

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
	
<div class="container" style="margin-top:15em; margin-bottom: 10em;">
<div class="row">
<div class="col-sm-2"></div>
<div class="col-sm-10">
<h2 style="text-decoration: underline; color: #0080FF;">常見問題，好房事關心您</h2>
	<div class="tab">
        <button class="tablinks" onclick="openCity(event, '註冊問題')">註冊問題</button>
        <button class="tablinks" onclick="openCity(event, '如何預約房仲')">如何預約房仲</button>
        <button class="tablinks" onclick="openCity(event, '如何購買商品')">如何購買商品</button>
        <button class="tablinks" onclick="openCity(event, '其他問題')">其他問題</button>
    </div>
        <div id="註冊問題" class="tabcontent">
            <div class="jumbotron">
                <div class="container text-center">
                    <h2>熱門問題</h2>
                </div>
                <div class="container">
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion"
                                            href="#collapse1">加入會員</a>
                                    </h4>
                            </div>
                            <div id="collapse1" class="panel-collapse collapse">
                                <div class="panel-body">非常歡迎您的加入，如果你本身工作是房仲或是傢俱廠商，請點選加入我們，在按照步驟一步步註冊登入，如果已上皆不是的話請點選"註冊"成為我們會員。「好房事」關心你的大小事!</div>
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion"
                                            href="#collapse2">我有多重身份怎麼辦?</a>
                                    </h4>
                            </div>
                            <div id="collapse2" class="panel-collapse collapse">
                                <div class="panel-body">非常謝謝您的加入，這方面的話就需要你同時註冊登入不同會員，因為每個會員的功能都不一樣，為了你的權益以及使用上的方面集便利，本網站建議你申請不同帳號，來享有更多的服務</div>
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">找不到註冊的按鈕</a>
                                </h4>
                            </div>
                            <div id="collapse3" class="panel-collapse collapse">
                                <div class="panel-body">我只能說施主對不起，這是本網站的疏失，他們分別在網站最上面，會員的請尋找右上方註冊，而房仲以及廠商身分請點選加入我們，即可註冊登入成為我們的會員!</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="如何預約房仲" class="tabcontent">
            <div class="jumbotron">
                <div class="container text-center">
                    <h2>如何預約一位好房仲</h2>
                </div>
                <div class="container">
                    <div class="panel-group" id="accordiona">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordiona"
                                            href="#a1">搜尋房仲</a>
                                    </h4>
                            </div>
                            <div id="a1" class="panel-collapse collapse">
                                <div class="panel-body">點選收尋房仲，將會列出本網站所有優質房仲，您可依照條件收尋: 有地區、服務公司和關鍵字搜尋，預祝找到您喜歡的房仲</div>
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordiona"
                                            href="#a2">我不認識房仲，該怎麼了解本人?</a>
                                    </h4>
                            </div>
                            <div id="a2" class="panel-collapse collapse">
                                <div class="panel-body">每位房仲都有自己的簡介，以及心情文章分享，可以從他們每天的心情分享中，發現到每位房仲的個性，以及處事態度</div>
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordiona"
                                            href="#a3">確認並選擇房仲</a>
                                    </h4>
                            </div>
                            <div id="a3" class="panel-collapse collapse">
                                <div class="panel-body">當您真的對某位房仲很感興趣，您可以將該為房仲加入收藏，或是即時跟他通訊連絡，深入了解你喜歡的房屋物件。</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="如何購買商品" class="tabcontent">
            <div class="jumbotron">
                <div class="container text-center">
                    <h2>熱門問題</h2>
                </div>
                <div class="container">
                    <div class="panel-group" id="accordionb">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordionb"
                                            href="#b1">傢俱商品</a>
                                    </h4>
                            </div>
                            <div id="b1" class="panel-collapse collapse">
                                <div class="panel-body">有上百種商品等著你的選購，品質絕對是上乘的，看到喜歡的就隨即加入購物車裡吧</div>
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordionb"
                                            href="#b2">檢舉商品</a>
                                    </h4>
                            </div>
                            <div id="b2" class="panel-collapse collapse">
                                <div class="panel-body">本網站絕對不認同有商品不實的廠商，如有發現請各位一定要即時反應，我們絕對嚴懲</div>
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordionb"
                                            href="#b3">附款方式</a>
                                    </h4>
                            </div>
                            <div id="b3" class="panel-collapse collapse">
                                <div class="panel-body">請用信用卡消費結帳</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <div id="其他問題" class="tabcontent">
        <div class="jumbotron">
            <div class="container text-center">
                <h2>交屋購屋簽約6步驟</h2>
            </div>
            <div class="container">
                <div class="panel-group" id="accordion4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion4"
                                            href="#d1">1 價格協商</a>
                                    </h4>
                        </div>
                        <div id="d1" class="panel-collapse collapse">
                            <div class="panel-body">
                                為了讓買賣雙方在房屋價格上達成共識，買方可透過要約書或斡旋金來傳達購買的意願。 斡旋金： 買賣雙方在價金或付款方式有認知差距下，由買方交付相當之金額（房屋總 價2~5%），委託仲介代理人在一定期間內，向賣方展現誠意，並爭取達成買方要求，否則斡旋金必須無息全額退還。 如果賣方承諾買方的承買條件並簽收時，斡旋金即轉成為定金，成為買賣價金的一部分。 要約書： 買方亦可以書立要約書方式，向賣方表達承買意願，如果賣方同意出售並簽認，買賣即因要約經過承諾而成立；相反的，賣方不同意出售，要約即失其效力。
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion4"
                                            href="#d2">2 付定</a>
                                    </h4>
                        </div>
                        <div id="d2" class="panel-collapse collapse">
                            <div class="panel-body">
                                付定為買方交付賣方一定比例的金錢，以確保契約履行。 定金大約為總價2~5%不等(依成交總價金額多寡)，在簽約前支付。
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion4"
                                            href="#d3">3 簽約</a>
                                    </h4>
                        </div>
                        <div id="d3" class="panel-collapse collapse">
                            <div class="panel-body">
                                買賣雙方在成交時將簽立「不動產買賣合約書」，包括： 土地及建物標示、增建及車位記載、付款約定、稅費約定、貸款約定、違約處罰、房地點交及其他約定事項。 買方簽立與尾款同面額之本票交由地政士(代書)保管，等尾款交付後，本票再歸還買方。 若買方須辦理銀行貸款，代書亦於此時提供銀行利率資訊，並於約定時間內決定貸款銀行及準備貸款資料。
                                <br>簽約時，買賣雙方應備齊以下資料與證件：
                                <br> 買方：
                                <br> 1.身分證正本、2.普通印章（蓋完後即可歸還）。 賣方：
                                <br> 1.身分證正本、2.印鑑證明二份（申請日期為最近一年內）、3.戶籍資料三份（戶籍謄本正本或戶口名簿影本皆可）、4.最近一期房屋稅單、5.最近一期地價稅稅單、6.建物所有權狀、7.土地所有權狀、8.印鑑章（蓋完後即可歸還）。
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion4"
                                            href="#d4">4 用印</a>
                                    </h4>
                        </div>
                        <div id="d4" class="panel-collapse collapse">
                            <div class="panel-body">
                                地政士(代書)會確實檢視印鑑證明與印鑑章是否符合、證件是否齊全，然後將申報契稅、增值稅、貸款資料等整理完成後，買賣雙方將印鑑章、印鑑證明、戶口名簿或戶籍謄本交付承辦地政士(代書)，由地政士(代書)於增值稅申報書、契稅申報書、所有權移轉契約書（公契）、登記申請書上蓋好買賣雙方印章。</div>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion4"
                                            href="#d5">5 完稅</a>
                                    </h4>
                        </div>
                        <div id="d5" class="panel-collapse collapse">
                            <div class="panel-body">
                                稅捐單位核發稅單後，承辦代書即通知買賣雙方於一定時間內繳交契稅及增值稅，完稅後並簽收完稅款。完稅之後即進行過戶、設定手續。</div>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion4"
                                            href="#d6">6 交屋</a>
                                    </h4>
                        </div>
                        <div id="d6" class="panel-collapse collapse">
                            <div class="panel-body">
                                承辦地政士(代書)至地政機關領取過戶完成的新權狀，並聯繫雙方交屋及交尾款時間，並通知銀行撥款時間，點交確認無誤後，賣方取得尾款，買方取得新權狀，地政士(代書)並交還買賣雙方：擔保本票、所有權移轉契約書(公契)正影本、土地增值稅單正影本、契稅單正影本、房屋鑰匙、遙控器等相關文件。</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</div>
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
				<a
					href="<%=request.getContextPath()%>/front/news_frontpage_cooper/newsfront.jsp">房市新聞</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a
					href="<%=request.getContextPath()%>/front/news_frontpage_cooper/annfront.jsp">系統公告</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">COMMUNITIES</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">CONTACT US</a>
			</div>
		</div>
		<div class="copyri text-center">
			<p>
				<small>Copyright © 2017 For House</small>
			</p>
		</div>
	</footer>
	
	<script>
    function openCity(evt, cityName) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(cityName).style.display = "block";
        evt.currentTarget.className += " active";
    }
    </script>
	
</body>
</html>