<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
  MemVO memVOtmp = (MemVO) request.getAttribute("memVOtmp");
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/Houselogo1.png" />
    <title>For House</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    
    <!-- 神奇小按鈕的script 開始-->
    <script>
        $(document).ready(function() {
            $("#magicBtn2").click(function() {
                $("#mem_id").val("gakki@gmail.com");
                $("#password1").val("123456");
                $("#password2").val("123456");
                $("#mem_name").val("新垣結衣");
                $("#twcitytext").val("嬌妻路611號");
                $("#fancy-checkbox-info").attr("checked","enable");
                $("#aggrement2").attr("checked","enable");
            });
        });
    </script>
    <!-- 神奇小按鈕的script 結束-->

    <style type="text/css">
    body {
        font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
    }

    .searchSRS {
        margin-top: 8em;
    }

    footer {
        background-color: white;
        padding-top: 4em;
        margin-top: 450px;
    }

    .copyri {
        margin-top: 3em;
    }
    
    .box-width{
        width:440px;
        /*margin-left: 10px;*/
    }
    .margin-top{
        margin-top: 20px;
    }
    #formNote{
    	color: red;
    }

    #registerForm{
    	margin-top: 100px;
    }

    #aggrement{
        color: #999999;
        margin-left: 30px;
        margin-top: 5px;
    }
    
     #regbtn{
        margin-left: 25px;
        width: 660px;
    }
    
    #mem_addr{
    	width: 560px;
    }
    #twcity{
    	font-size: 14px;
    	width: 80px;
    	padding: 6px;
    	padding-bottom: 12px;
        vertical-align: middle;
    }
    #twdist{
    	font-size: 14px;
    	width: 80px;
    	padding: 6px;
    	padding-bottom: 12px;
        vertical-align: middle;
    }
    #twcitytext{
    	width: 280px;
    }
    
    #captchaDiv{
    	margin-left: 30px;
    }
    #captcha{
    	width: 130px;
    	height: 40px;
    	font-size: 20px;
    	text-align: center;
    }

    .magicdiv{
        margin-top: 8px;
    }
    
    #uploadDiv {
    	margin-left: 36px;
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

/*-- 找房狀態需要的css */
    .form-group input[type="checkbox"] + .btn-group > label span {
        width: 20px;
    }

    .form-group input[type="checkbox"] + .btn-group > label span:first-child {
        display: none;
    }
    .form-group input[type="checkbox"] + .btn-group > label span:last-child {
        display: inline-block;   
    }

    .form-group input[type="checkbox"]:checked + .btn-group > label span:first-child {
        display: inline-block;
    }
    .form-group input[type="checkbox"]:checked + .btn-group > label span:last-child {
        display: none;   
    }
        #fancy-checkbox-info {
        display: none;
    }
    #searchSwitch{
        margin-left: 30px;
        margin-top: 8px;
    }
    #searchicon1{
    	height: 20px;
    }
    #searchicon2{
    	height: 20px;
    }

    </style>
    <script>
        $(function(){
            $('[data-toggle="tooltipNews"]').tooltip(
                {title: "<div><a href='#'>房市新聞</a></div><div><a href='#'>促銷資訊</a></div><div><a href='#'>系統公告</a></div>",
                 html: true,
                 delay:{"show":100,"hide":1000
             }});
        });
    </script>
</head>
<body>
    <div class="container-fluid backgroundpng">
        <img class="row" src="<%=request.getContextPath()%>/images/fixed_bg.png">
    </div>
    <nav class="navbar navbar-fixed-top">
        <jsp:include page="navbar.jsp" />
    </nav>
    
    
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
				        <a class="btn btn-info btn-lg btn-block" href="" role="button"><h3>我  是  房  仲</h3></a>
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
    
    
    <div class="container" id="registerForm">
    <div class="row">
    <div class="col-xs-12 col-sm-8 col-sm-offset-3">
		<div class="panel panel-default">
		  <div class="panel-heading">
            <h4 class="text-center"><label class="control-label">註冊會員</label></h4>
          </div>
          <div class="panel-body">
	        <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
        <form class="form-horizontal" action="<%=request.getContextPath()%>/front/checkIn.do" method="post"  enctype="multipart/form-data">
        	<input type="hidden" name="action" value="memRegister">
        	<div class="text-center" id="formNote">
            	<p>注意:所有欄位都必須輸入</p>
            </div>
            <div class="form-group margin-top">
                <label for="mem_id" class="col-sm-2 control-label">帳號</label>
                <div class="col-sm-9">
                <input type="email" class="form-control box-width" id="mem_id" name="mem_id" placeholder="請輸入e-mail" value="<%= (memVOtmp==null)? "" : memVOtmp.getMem_id()%>">
                </div>
            </div>
            <div class="form-group margin-top">
                <label for="password1" class="col-sm-2 control-label">密碼</label>
                <div class="col-sm-9">
                <input type="password" class="form-control box-width" id="password1" name="mem_psw" placeholder="密碼" value="<%= (memVOtmp==null)? "" : memVOtmp.getMem_psw()%>">
                </div>
            </div>
            <div class="form-group margin-top">
                <label for="password2" class="col-sm-2 control-label">確認密碼</label>
                <div class="col-sm-9">
                <input type="password" class="form-control box-width" id="password2" name="mem_psw2" placeholder="確認密碼" value="<%= (memVOtmp==null)? "" : memVOtmp.getMem_psw()%>">
                </div>
            </div>
            <div class="form-group margin-top">
                <label for="mem_name" class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-9">
                <input type="text" class="form-control box-width" id="mem_name" name="mem_name" placeholder="姓名" value="<%= (memVOtmp==null)? "" : memVOtmp.getMem_name()%>">
                </div>
            </div>
            <div class="form-group margin-top">
                <label for="mem_addr" class="col-sm-2 control-label">地址</label>
                <div class="col-sm-10">
                <input id="mem_addr" class="form-control box-width twaddress" name="mem_addr" value="<%= (memVOtmp==null)? "" : memVOtmp.getTwcitytext()%>"/>
                </div>
            </div>
            
            <div class="row">
            
            <div class="col-md-8">
            <div class="form-group" id="uploadDiv">
                <label for="mem_img" class="col-sm-2 control-label">照片</label>
                <div class="col-sm-7">
                <label class="btn btn-info">
                	<input type="file" id="mem_img" name="mem_img" style="display:none;" accept="image/*">選擇照片
                </label>
                </div>
            </div>
            <div class="[ form-group ]" id="searchSwitch">
                <input type="checkbox" name="search_state" id="fancy-checkbox-info" autocomplete="off" <%= (memVOtmp!=null && memVOtmp.getSearch_state().equals("ON"))? "checked" : "" %>/>
                <div class="[ btn-group btn-lg]">
                    <label for="fancy-checkbox-info" class="[ btn btn-info ]">
                        <span class="[ glyphicon glyphicon-ok ]" id="searchicon1"></span>
                        <span id="searchicon2"> </span>
                    </label>
                    <label for="fancy-checkbox-info" class="[ btn btn-default active ]">
                       	我願意公開我的找房狀態，讓房仲能主動找到我 
                    </label>
                </div>
            </div>
            <div class="form-group margin-top" id="captchaDiv">
            	<input type="text" class="form_control" id="captcha" name="captcha" placeholder="請輸入右側驗證碼"> <img src="IdentityServlet" id="captchaimg" height="50px">
            	<input type="button" value="重新產生" onclick="captchaimg.src='IdentityServlet?r=' + (new Date()).getTime()" />
            </div>
            </div>
            
            <div class="col-md-3">
            	<div id="imgDiv">
					<img class="img-responsive preview">
				</div>
            
            </div>
            
            </div>
            
            <div class="form-group">
              <div class="col-sm-offset-1 col-sm-11"  id="aggrement">
                  <div class="checkbox">
                    <label>
                      <input type="checkbox" name="aggrement" id="aggrement2" <%= (memVOtmp!=null && memVOtmp.getAggrement().equals("on"))? "checked" : "" %>/> 
                    	 若要繼續註冊，請先閱讀並同意好房事的<a href="">服務條款</a> & <a href="">隱私權政策</a>
                    </label>
                  </div>
              </div>
            </div>
              <div class="form-group" id="regbtn">
                    <input type="submit" value="註冊" class="btn btn-primary btn-lg btn-block">
                  <div class="col-sm-offset-11 col-sm-2 magicdiv">
                      <button type="button" class="btn btn-default btn-xs" id="magicBtn2">貼</button>
                  </div>
              </div>
        </form>
        </div>
        </div>
    </div>
    </div>
    </div>
    
    <footer class="container-fluid">
        <div class="container text-center">
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
        </div></div>
        <div class="copyri text-center">
            <p><small>Copyright © 2017 For House</small></p>
        </div>
    </footer>


<!--地址選擇欄位-->
<script>
(function ($) {
    var city_list = [
        { pinyin: 'taipeicity', name: '台北市' }, { pinyin: 'xinbeicity', name: '新北市' }, { pinyin: 'keelungcity', name: '基隆市' },
        { pinyin: 'taoyuancounty', name: '桃園市' }, { pinyin: 'hsinchucounty', name: '新竹縣' }, { pinyin: 'hsinchucity', name: '新竹市' },
        { pinyin: 'miaolicounty', name: '苗栗縣' }, { pinyin: 'yilancounty', name: '宜蘭縣' }, { pinyin: 'taichungcity', name: '台中市' },
        { pinyin: 'changhuacounty', name: '彰化縣' }, { pinyin: 'nantoucounty', name: '南投縣' }, { pinyin: 'yunlincounty', name: '雲林縣' },
        { pinyin: 'chiayicity', name: '嘉義市' }, { pinyin: 'chiayicounty', name: '嘉義縣' }, { pinyin: 'tainancity', name: '台南市' },
        { pinyin: 'kaohsiungcity', name: '高雄市' }, { pinyin: 'pingtungcounty', name: '屏東縣' }, { pinyin: 'hualiencounty', name: '花蓮縣' },
        { pinyin: 'taitungcounty', name: '台東縣' }, { pinyin: 'penghucounty', name: '澎湖縣' }, { pinyin: 'kinmencounty', name: '金門縣' },
        { pinyin: 'lienchiangcounty', name: '連江縣' }
        ]
    var dist_list = [
        { city: 'keelungcity', pinyin: 'qidu', name: '七堵區' },
        { city: 'penghucounty', pinyin: 'qimei', name: '七美鄉' },
        { city: 'tainancity', pinyin: 'qigu', name: '七股區' },
        { city: 'pingtungcounty', pinyin: 'sandimen', name: '三地門鄉' },
        { city: 'xinbeicity', pinyin: 'sanxia', name: '三峽區' },
        { city: 'yilancounty', pinyin: 'sanxing', name: '三星鄉' },
        { city: 'kaohsiungcity', pinyin: 'sanmin', name: '三民區' },
        { city: 'miaolicounty', pinyin: 'sanwan', name: '三灣鄉' },
        { city: 'miaolicounty', pinyin: 'sanyi', name: '三義鄉' },
        { city: 'xinbeicity', pinyin: 'sanzhi', name: '三芝區' },
        { city: 'xinbeicity', pinyin: 'sanchong', name: '三重區' },
        { city: 'tainancity', pinyin: 'xiaying', name: '下營區' },
        { city: 'taichungcity', pinyin: 'central', name: '中區' },
        { city: 'xinbeicity', pinyin: 'zhonghe', name: '中和區' },
        { city: 'chiayicounty', pinyin: 'zhongpu', name: '中埔鄉' },
        { city: 'taoyuancounty', pinyin: 'zhongli', name: '中壢區' },
        { city: 'nantoucounty', pinyin: 'zhongliao', name: '中寮鄉' },
        { city: 'keelungcity', pinyin: 'zhongshan', name: '中山區' },
        { city: 'taipeicity', pinyin: 'zhongshan', name: '中山區' },
        { city: 'keelungcity', pinyin: 'zhongzheng', name: '中正區' },
        { city: 'taipeicity', pinyin: 'zhongzheng', name: '中正區' },
        { city: 'tainancity', pinyin: 'westcentral', name: '中西區' },
        { city: 'pingtungcounty', pinyin: 'jiuru', name: '九如鄉' },
        { city: 'yunlincounty', pinyin: 'erlun', name: '二崙鄉' },
        { city: 'changhuacounty', pinyin: 'erlin', name: '二林鎮' },
        { city: 'changhuacounty', pinyin: 'ershui', name: '二水鄉' },
        { city: 'hsinchucounty', pinyin: 'wufeng', name: '五峰鄉' },
        { city: 'yilancounty', pinyin: 'wujie', name: '五結鄉' },
        { city: 'xinbeicity', pinyin: 'wugu', name: '五股區' },
        { city: 'tainancity', pinyin: 'rende', name: '仁德區' },
        { city: 'keelungcity', pinyin: 'renai', name: '仁愛區' },
        { city: 'nantoucounty', pinyin: 'renai', name: '仁愛鄉' },
        { city: 'kaohsiungcity', pinyin: 'renwu', name: '仁武區' },
        { city: 'changhuacounty', pinyin: 'shengang', name: '伸港鄉' },
        { city: 'pingtungcounty', pinyin: 'jiadong', name: '佳冬鄉' },
        { city: 'tainancity', pinyin: 'jiali', name: '佳里區' },
        { city: 'pingtungcounty', pinyin: 'laiyi', name: '來義鄉' },
        { city: 'keelungcity', pinyin: 'xinyi', name: '信義區' },
        { city: 'taipeicity', pinyin: 'xinyi', name: '信義區' },
        { city: 'nantoucounty', pinyin: 'xinyi', name: '信義鄉' },
        { city: 'yunlincounty', pinyin: 'yuanchang', name: '元長鄉' },
        { city: 'hualiencounty', pinyin: 'guangfu', name: '光復鄉' },
        { city: 'pingtungcounty', pinyin: 'neipu', name: '內埔鄉' },
        { city: 'taipeicity', pinyin: 'neihu', name: '內湖區' },
        { city: 'kaohsiungcity', pinyin: 'neimen', name: '內門區' },
        { city: 'taoyuancounty', pinyin: 'bade', name: '八德區' },
        { city: 'xinbeicity', pinyin: 'bali', name: '八里區' },
        { city: 'miaolicounty', pinyin: 'gongguan', name: '公館鄉' },
        { city: 'tainancity', pinyin: 'liujia', name: '六甲區' },
        { city: 'chiayicounty', pinyin: 'liujiao', name: '六腳鄉' },
        { city: 'kaohsiungcity', pinyin: 'liugui', name: '六龜區' },
        { city: 'yilancounty', pinyin: 'dongshan', name: '冬山鄉' },
        { city: 'kaohsiungcity', pinyin: 'qianjin', name: '前金區' },
        { city: 'kaohsiungcity', pinyin: 'qianzhen', name: '前鎮區' },
        { city: 'hsinchucity', pinyin: 'north', name: '北區' },
        { city: 'taichungcity', pinyin: 'north', name: '北區' },
        { city: 'tainancity', pinyin: 'north', name: '北區' },
        { city: 'hsinchucounty', pinyin: 'beipu', name: '北埔鄉' },
        { city: 'taichungcity', pinyin: 'beitun', name: '北屯區' },
        { city: 'taipeicity', pinyin: 'beitou', name: '北投區' },
        { city: 'changhuacounty', pinyin: 'beidou', name: '北斗鎮' },
        { city: 'yunlincounty', pinyin: 'beigang', name: '北港鎮' },
        { city: 'lienchiangcounty', pinyin: 'beigan', name: '北竿鄉' },
        { city: 'tainancity', pinyin: 'beimen', name: '北門區' },
        { city: 'taitungcounty', pinyin: 'beinan', name: '卑南鄉' },
        { city: 'hualiencounty', pinyin: 'zhuoxi', name: '卓溪鄉' },
        { city: 'miaolicounty', pinyin: 'zhuolan', name: '卓蘭鎮' },
        { city: 'tainancity', pinyin: 'nanhua', name: '南化區' },
        { city: 'taichungcity', pinyin: 'south', name: '南區' },
        { city: 'tainancity', pinyin: 'south', name: '南區' },
        { city: 'taichungcity', pinyin: 'nantun', name: '南屯區' },
        { city: 'pingtungcounty', pinyin: 'nanzhou', name: '南州鄉' },
        { city: 'miaolicounty', pinyin: 'nanzhuang', name: '南庄鄉' },
        { city: 'nantoucounty', pinyin: 'nantou', name: '南投市' },
        { city: 'nanhaiislands', pinyin: 'nansha', name: '南沙群島' },
        { city: 'taipeicity', pinyin: 'nangang', name: '南港區' },
        { city: 'yilancounty', pinyin: 'nanao', name: '南澳鄉' },
        { city: 'lienchiangcounty', pinyin: 'nangan', name: '南竿鄉' },
        { city: 'yunlincounty', pinyin: 'kouhu', name: '口湖鄉' },
        { city: 'yunlincounty', pinyin: 'gukeng', name: '古坑鄉' },
        { city: 'taitungcounty', pinyin: 'taitung', name: '台東市' },
        { city: 'yunlincounty', pinyin: 'taixi', name: '台西鄉' },
        { city: 'hualiencounty', pinyin: 'jian', name: '吉安鄉' },
        { city: 'nantoucounty', pinyin: 'mingjian', name: '名間鄉' },
        { city: 'taichungcity', pinyin: 'houli', name: '后里區' },
        { city: 'taichungcity', pinyin: 'heping', name: '和平區' },
        { city: 'changhuacounty', pinyin: 'hemei', name: '和美鎮' },
        { city: 'yilancounty', pinyin: 'yuanshan', name: '員山鄉' },
        { city: 'changhuacounty', pinyin: 'yuanlin', name: '員林鎮' },
        { city: 'tainancity', pinyin: 'shanhua', name: '善化區' },
        { city: 'yunlincounty', pinyin: 'sihu', name: '四湖鄉' },
        { city: 'nantoucounty', pinyin: 'guoxing', name: '國姓鄉' },
        { city: 'xinbeicity', pinyin: 'tucheng', name: '土城區' },
        { city: 'yunlincounty', pinyin: 'tuku', name: '土庫鎮' },
        { city: 'xinbeicity', pinyin: 'pinglin', name: '坪林區' },
        { city: 'changhuacounty', pinyin: 'puxin', name: '埔心鄉' },
        { city: 'nantoucounty', pinyin: 'puli', name: '埔里鎮' },
        { city: 'changhuacounty', pinyin: 'puyan', name: '埔鹽鄉' },
        { city: 'changhuacounty', pinyin: 'pitou', name: '埤頭鄉' },
        { city: 'taipeicity', pinyin: 'shilin', name: '士林區' },
        { city: 'yilancounty', pinyin: 'zhuangwei', name: '壯圍鄉' },
        { city: 'hualiencounty', pinyin: 'shoufeng', name: '壽豐鄉' },
        { city: 'taichungcity', pinyin: 'waipu', name: '外埔區' },
        { city: 'tainancity', pinyin: 'danei', name: '大內區' },
        { city: 'taipeicity', pinyin: 'datong', name: '大同區' },
        { city: 'yilancounty', pinyin: 'datong', name: '大同鄉' },
        { city: 'taoyuancounty', pinyin: 'dayuan', name: '大園區' },
        { city: 'changhuacounty', pinyin: 'dacheng', name: '大城鄉' },
        { city: 'chiayicounty', pinyin: 'dapu', name: '大埔鄉' },
        { city: 'yunlincounty', pinyin: 'dapi', name: '大埤鄉' },
        { city: 'taichungcity', pinyin: 'daan', name: '大安區' },
        { city: 'taipeicity', pinyin: 'daan', name: '大安區' },
        { city: 'kaohsiungcity', pinyin: 'daliao', name: '大寮區' },
        { city: 'changhuacounty', pinyin: 'dacun', name: '大村鄉' },
        { city: 'chiayicounty', pinyin: 'dalin', name: '大林鎮' },
        { city: 'kaohsiungcity', pinyin: 'dashu', name: '大樹區' },
        { city: 'taitungcounty', pinyin: 'dawu', name: '大武鄉' },
        { city: 'miaolicounty', pinyin: 'dahu', name: '大湖鄉' },
        { city: 'taoyuancounty', pinyin: 'daxi', name: '大溪區' },
        { city: 'taichungcity', pinyin: 'dajia', name: '大甲區' },
        { city: 'kaohsiungcity', pinyin: 'dashe', name: '大社區' },
        { city: 'taichungcity', pinyin: 'dadu', name: '大肚區' },
        { city: 'taichungcity', pinyin: 'dali', name: '大里區' },
        { city: 'taichungcity', pinyin: 'daya', name: '大雅區' },
        { city: 'chiayicounty', pinyin: 'taibao', name: '太保市' },
        { city: 'taichungcity', pinyin: 'taiping', name: '太平區' },
        { city: 'taitungcounty', pinyin: 'taimali', name: '太麻里鄉' },
        { city: 'tainancity', pinyin: 'xuejia', name: '學甲區' },
        { city: 'tainancity', pinyin: 'annan', name: '安南區' },
        { city: 'tainancity', pinyin: 'anding', name: '安定區' },
        { city: 'tainancity', pinyin: 'anping', name: '安平區' },
        { city: 'keelungcity', pinyin: 'anle', name: '安樂區' },
        { city: 'tainancity', pinyin: 'guantian', name: '官田區' },
        { city: 'yilancounty', pinyin: 'yilan', name: '宜蘭市' },
        { city: 'hualiencounty', pinyin: 'fuli', name: '富里鄉' },
        { city: 'hsinchucounty', pinyin: 'baoshan', name: '寶山鄉' },
        { city: 'tainancity', pinyin: 'jiangjun', name: '將軍區' },
        { city: 'kaohsiungcity', pinyin: 'xiaogang', name: '小港區' },
        { city: 'hsinchucounty', pinyin: 'jianshi', name: '尖石鄉' },
        { city: 'pingtungcounty', pinyin: 'pingtung', name: '屏東市' },
        { city: 'tainancity', pinyin: 'shanshang', name: '山上區' },
        { city: 'kaohsiungcity', pinyin: 'gangshan', name: '岡山區' },
        { city: 'hsinchucounty', pinyin: 'emei', name: '峨眉鄉' },
        { city: 'pingtungcounty', pinyin: 'kanding', name: '崁頂鄉' },
        { city: 'yunlincounty', pinyin: 'lunbei', name: '崙背鄉' },
        { city: 'kaohsiungcity', pinyin: 'zuoying', name: '左營區' },
        { city: 'tainancity', pinyin: 'zuozhen', name: '左鎮區' },
        { city: 'chiayicounty', pinyin: 'budai', name: '布袋鎮' },
        { city: 'xinbeicity', pinyin: 'pingxi', name: '平溪區' },
        { city: 'taoyuancounty', pinyin: 'pingzhen', name: '平鎮區' },
        { city: 'taitungcounty', pinyin: 'yanping', name: '延平鄉' },
        { city: 'kaohsiungcity', pinyin: 'mituo', name: '彌陀區' },
        { city: 'changhuacounty', pinyin: 'changhua', name: '彰化市' },
        { city: 'tainancity', pinyin: 'houbi', name: '後壁區' },
        { city: 'miaolicounty', pinyin: 'houlong', name: '後龍鎮' },
        { city: 'taoyuancounty', pinyin: 'fuxing', name: '復興區' },
        { city: 'pingtungcounty', pinyin: 'hengchun', name: '恆春鎮' },
        { city: 'taitungcounty', pinyin: 'chenggong', name: '成功鎮' },
        { city: 'taipeicity', pinyin: 'wenshan', name: '文山區' },
        { city: 'yunlincounty', pinyin: 'douliu', name: '斗六市' },
        { city: 'yunlincounty', pinyin: 'dounan', name: '斗南鎮' },
        { city: 'tainancity', pinyin: 'xinhua', name: '新化區' },
        { city: 'pingtungcounty', pinyin: 'xinyuan', name: '新園鄉' },
        { city: 'hualiencounty', pinyin: 'xincheng', name: '新城鄉' },
        { city: 'hsinchucounty', pinyin: 'xinpu', name: '新埔鎮' },
        { city: 'pingtungcounty', pinyin: 'xinpi', name: '新埤鄉' },
        { city: 'taoyuancounty', pinyin: 'xinwu', name: '新屋區' },
        { city: 'tainancity', pinyin: 'xinshi', name: '新市區' },
        { city: 'xinbeicity', pinyin: 'xindian', name: '新店區' },
        { city: 'chiayicounty', pinyin: 'xingang', name: '新港鄉' },
        { city: 'tainancity', pinyin: 'xinying', name: '新營區' },
        { city: 'taichungcity', pinyin: 'xinshe', name: '新社區' },
        { city: 'kaohsiungcity', pinyin: 'xinxing', name: '新興區' },
        { city: 'xinbeicity', pinyin: 'xinzhuang', name: '新莊區' },
        { city: 'hsinchucounty', pinyin: 'xinfeng', name: '新豐鄉' },
        { city: 'kaohsiungcity', pinyin: 'qishan', name: '旗山區' },
        { city: 'kaohsiungcity', pinyin: 'qijin', name: '旗津區' },
        { city: 'pingtungcounty', pinyin: 'chunri', name: '春日鄉' },
        { city: 'keelungcity', pinyin: 'nuannuan', name: '暖暖區' },
        { city: 'penghucounty', pinyin: 'wangan', name: '望安鄉' },
        { city: 'chiayicounty', pinyin: 'puzi', name: '朴子市' },
        { city: 'kaohsiungcity', pinyin: 'shanlin', name: '杉林區' },
        { city: 'taichungcity', pinyin: 'dongshi', name: '東勢區' },
        { city: 'yunlincounty', pinyin: 'dongshi', name: '東勢鄉' },
        { city: 'chiayicity', pinyin: 'east', name: '東區' },
        { city: 'hsinchucity', pinyin: 'east', name: '東區' },
        { city: 'taichungcity', pinyin: 'east', name: '東區' },
        { city: 'tainancity', pinyin: 'east', name: '東區' },
        { city: 'tainancity', pinyin: 'dongshan', name: '東山區' },
        { city: 'lienchiangcounty', pinyin: 'dongyin', name: '東引鄉' },
        { city: 'nanhaiislands', pinyin: 'dongsha', name: '東沙群島' },
        { city: 'taitungcounty', pinyin: 'donghe', name: '東河鄉' },
        { city: 'pingtungcounty', pinyin: 'donggang', name: '東港鎮' },
        { city: 'chiayicounty', pinyin: 'dongshi', name: '東石鄉' },
        { city: 'taipeicity', pinyin: 'songshan', name: '松山區' },
        { city: 'xinbeicity', pinyin: 'banqiao', name: '板橋區' },
        { city: 'pingtungcounty', pinyin: 'fangliao', name: '枋寮鄉' },
        { city: 'pingtungcounty', pinyin: 'fangshan', name: '枋山鄉' },
        { city: 'yunlincounty', pinyin: 'linnei', name: '林內鄉' },
        { city: 'xinbeicity', pinyin: 'linkou', name: '林口區' },
        { city: 'kaohsiungcity', pinyin: 'linyuan', name: '林園區' },
        { city: 'pingtungcounty', pinyin: 'linbian', name: '林邊鄉' },
        { city: 'tainancity', pinyin: 'liuying', name: '柳營區' },
        { city: 'taoyuancounty', pinyin: 'taoyuan', name: '桃園區' },
        { city: 'kaohsiungcity', pinyin: 'taoyuan', name: '桃源區' },
        { city: 'chiayicounty', pinyin: 'meishan', name: '梅山鄉' },
        { city: 'kaohsiungcity', pinyin: 'ziguan', name: '梓官區' },
        { city: 'taichungcity', pinyin: 'wuqi', name: '梧棲區' },
        { city: 'taoyuancounty', pinyin: 'yangmei', name: '楊梅區' },
        { city: 'kaohsiungcity', pinyin: 'nanzi', name: '楠梓區' },
        { city: 'tainancity', pinyin: 'nanxi', name: '楠西區' },
        { city: 'xinbeicity', pinyin: 'shulin', name: '樹林區' },
        { city: 'kaohsiungcity', pinyin: 'qiaotou', name: '橋頭區' },
        { city: 'hsinchucounty', pinyin: 'hengshan', name: '橫山鄉' },
        { city: 'tainancity', pinyin: 'guiren', name: '歸仁區' },
        { city: 'chiayicounty', pinyin: 'minxiong', name: '民雄鄉' },
        { city: 'chiayicounty', pinyin: 'shuishang', name: '水上鄉' },
        { city: 'yunlincounty', pinyin: 'shuilin', name: '水林鄉' },
        { city: 'nantoucounty', pinyin: 'shuili', name: '水里鄉' },
        { city: 'xinbeicity', pinyin: 'yonghe', name: '永和區' },
        { city: 'kaohsiungcity', pinyin: 'yongan', name: '永安區' },
        { city: 'tainancity', pinyin: 'yongkang', name: '永康區' },
        { city: 'changhuacounty', pinyin: 'yongjing', name: '永靖鄉' },
        { city: 'xinbeicity', pinyin: 'xizhi', name: '汐止區' },
        { city: 'taitungcounty', pinyin: 'chishang', name: '池上鄉' },
        { city: 'taichungcity', pinyin: 'shalu', name: '沙鹿區' },
        { city: 'miaolicounty', pinyin: 'taian', name: '泰安鄉' },
        { city: 'xinbeicity', pinyin: 'taishan', name: '泰山區' },
        { city: 'pingtungcounty', pinyin: 'taiwu', name: '泰武鄉' },
        { city: 'taitungcounty', pinyin: 'haiduan', name: '海端鄉' },
        { city: 'xinbeicity', pinyin: 'danshui', name: '淡水區' },
        { city: 'xinbeicity', pinyin: 'shenkeng', name: '深坑區' },
        { city: 'taichungcity', pinyin: 'qingshui', name: '清水區' },
        { city: 'kaohsiungcity', pinyin: 'hunei', name: '湖內區' },
        { city: 'hsinchucounty', pinyin: 'hukou', name: '湖口鄉' },
        { city: 'penghucounty', pinyin: 'huxi', name: '湖西鄉' },
        { city: 'chiayicounty', pinyin: 'xikou', name: '溪口鄉' },
        { city: 'changhuacounty', pinyin: 'xizhou', name: '溪州鄉' },
        { city: 'changhuacounty', pinyin: 'xihu', name: '溪湖鎮' },
        { city: 'pingtungcounty', pinyin: 'manzhou', name: '滿州鄉' },
        { city: 'taichungcity', pinyin: 'tanzi', name: '潭子區' },
        { city: 'pingtungcounty', pinyin: 'chaozhou', name: '潮州鎮' },
        { city: 'kinmencounty', pinyin: 'lieyu', name: '烈嶼鄉' },
        { city: 'xinbeicity', pinyin: 'wulai', name: '烏來區' },
        { city: 'kinmencounty', pinyin: 'wuqiu', name: '烏坵鄉' },
        { city: 'taichungcity', pinyin: 'wuri', name: '烏日區' },
        { city: 'kaohsiungcity', pinyin: 'yanchao', name: '燕巢區' },
        { city: 'pingtungcounty', pinyin: 'mudan', name: '牡丹鄉' },
        { city: 'pingtungcounty', pinyin: 'shizi', name: '獅子鄉' },
        { city: 'miaolicounty', pinyin: 'shitan', name: '獅潭鄉' },
        { city: 'tainancity', pinyin: 'yujing', name: '玉井區' },
        { city: 'hualiencounty', pinyin: 'yuli', name: '玉里鎮' },
        { city: 'pingtungcounty', pinyin: 'liuqiu', name: '琉球鄉' },
        { city: 'hualiencounty', pinyin: 'ruisui', name: '瑞穗鄉' },
        { city: 'xinbeicity', pinyin: 'ruifang', name: '瑞芳區' },
        { city: 'pingtungcounty', pinyin: 'majia', name: '瑪家鄉' },
        { city: 'changhuacounty', pinyin: 'tianzhong', name: '田中鎮' },
        { city: 'kaohsiungcity', pinyin: 'tianliao', name: '田寮區' },
        { city: 'changhuacounty', pinyin: 'tianwei', name: '田尾鄉' },
        { city: 'kaohsiungcity', pinyin: 'jiaxian', name: '甲仙區' },
        { city: 'chiayicounty', pinyin: 'fanlu', name: '番路鄉' },
        { city: 'penghucounty', pinyin: 'baisha', name: '白沙鄉' },
        { city: 'tainancity', pinyin: 'baihe', name: '白河區' },
        { city: 'taichungcity', pinyin: 'shigang', name: '石岡區' },
        { city: 'xinbeicity', pinyin: 'shiding', name: '石碇區' },
        { city: 'xinbeicity', pinyin: 'shimen', name: '石門區' },
        { city: 'yilancounty', pinyin: 'jiaoxi', name: '礁溪鄉' },
        { city: 'changhuacounty', pinyin: 'shetou', name: '社頭鄉' },
        { city: 'taichungcity', pinyin: 'shengang', name: '神岡區' },
        { city: 'changhuacounty', pinyin: 'fuxing', name: '福興鄉' },
        { city: 'hualiencounty', pinyin: 'xiulin', name: '秀林鄉' },
        { city: 'changhuacounty', pinyin: 'xiushui', name: '秀水鄉' },
        { city: 'hsinchucounty', pinyin: 'zhubei', name: '竹北市' },
        { city: 'miaolicounty', pinyin: 'zhunan', name: '竹南鎮' },
        { city: 'changhuacounty', pinyin: 'zhutang', name: '竹塘鄉' },
        { city: 'nantoucounty', pinyin: 'zhushan', name: '竹山鎮' },
        { city: 'chiayicounty', pinyin: 'zhuqi', name: '竹崎鄉' },
        { city: 'hsinchucounty', pinyin: 'zhudong', name: '竹東鎮' },
        { city: 'pingtungcounty', pinyin: 'zhutian', name: '竹田鄉' },
        { city: 'taitungcounty', pinyin: 'ludao', name: '綠島鄉' },
        { city: 'changhuacounty', pinyin: 'xianxi', name: '線西鄉' },
        { city: 'yilancounty', pinyin: 'luodong', name: '羅東鎮' },
        { city: 'kaohsiungcity', pinyin: 'meinong', name: '美濃區' },
        { city: 'chiayicounty', pinyin: 'yizhu', name: '義竹鄉' },
        { city: 'hsinchucounty', pinyin: 'qionglin', name: '芎林鄉' },
        { city: 'changhuacounty', pinyin: 'fenyuan', name: '芬園鄉' },
        { city: 'changhuacounty', pinyin: 'huatan', name: '花壇鄉' },
        { city: 'hualiencounty', pinyin: 'hualien', name: '花蓮市' },
        { city: 'changhuacounty', pinyin: 'fangyuan', name: '芳苑鄉' },
        { city: 'miaolicounty', pinyin: 'yuanli', name: '苑裡鎮' },
        { city: 'kaohsiungcity', pinyin: 'lingya', name: '苓雅區' },
        { city: 'miaolicounty', pinyin: 'miaoli', name: '苗栗市' },
        { city: 'kaohsiungcity', pinyin: 'maolin', name: '茂林區' },
        { city: 'kaohsiungcity', pinyin: 'qieding', name: '茄萣區' },
        { city: 'nantoucounty', pinyin: 'caotun', name: '草屯鎮' },
        { city: 'lienchiangcounty', pinyin: 'juguang', name: '莒光鄉' },
        { city: 'yunlincounty', pinyin: 'citong', name: '莿桐鄉' },
        { city: 'pingtungcounty', pinyin: 'wandan', name: '萬丹鄉' },
        { city: 'pingtungcounty', pinyin: 'wanluan', name: '萬巒鄉' },
        { city: 'hualiencounty', pinyin: 'wanrong', name: '萬榮鄉' },
        { city: 'taipeicity', pinyin: 'wanhua', name: '萬華區' },
        { city: 'xinbeicity', pinyin: 'wanli', name: '萬里區' },
        { city: 'xinbeicity', pinyin: 'luzhou', name: '蘆洲區' },
        { city: 'taoyuancounty', pinyin: 'luzhu', name: '蘆竹區' },
        { city: 'yilancounty', pinyin: 'suao', name: '蘇澳鎮' },
        { city: 'taitungcounty', pinyin: 'lanyu', name: '蘭嶼鄉' },
        { city: 'yunlincounty', pinyin: 'huwei', name: '虎尾鎮' },
        { city: 'yunlincounty', pinyin: 'baozhong', name: '褒忠鄉' },
        { city: 'chiayicity', pinyin: 'west', name: '西區' },
        { city: 'taichungcity', pinyin: 'west', name: '西區' },
        { city: 'taichungcity', pinyin: 'xitun', name: '西屯區' },
        { city: 'penghucounty', pinyin: 'xiyu', name: '西嶼鄉' },
        { city: 'tainancity', pinyin: 'xigang', name: '西港區' },
        { city: 'miaolicounty', pinyin: 'xihu', name: '西湖鄉' },
        { city: 'yunlincounty', pinyin: 'xiluo', name: '西螺鎮' },
        { city: 'taoyuancounty', pinyin: 'guanyin', name: '觀音區' },
        { city: 'taichungcity', pinyin: 'fengyuan', name: '豐原區' },
        { city: 'hualiencounty', pinyin: 'fengbin', name: '豐濱鄉' },
        { city: 'xinbeicity', pinyin: 'gongliao', name: '貢寮區' },
        { city: 'kaohsiungcity', pinyin: 'luzhu', name: '路竹區' },
        { city: 'pingtungcounty', pinyin: 'checheng', name: '車城鄉' },
        { city: 'miaolicounty', pinyin: 'tongxiao', name: '通霄鎮' },
        { city: 'miaolicounty', pinyin: 'zaoqiao', name: '造橋鄉' },
        { city: 'taitungcounty', pinyin: 'daren', name: '達仁鄉' },
        { city: 'kaohsiungcity', pinyin: 'namaxia', name: '那瑪夏區' },
        { city: 'pingtungcounty', pinyin: 'ligang', name: '里港鄉' },
        { city: 'kinmencounty', pinyin: 'jincheng', name: '金城鎮' },
        { city: 'kinmencounty', pinyin: 'jinning', name: '金寧鄉' },
        { city: 'xinbeicity', pinyin: 'jinshan', name: '金山區' },
        { city: 'taitungcounty', pinyin: 'jinfeng', name: '金峰鄉' },
        { city: 'kinmencounty', pinyin: 'jinsha', name: '金沙鎮' },
        { city: 'kinmencounty', pinyin: 'jinhu', name: '金湖鎮' },
        { city: 'miaolicounty', pinyin: 'tongluo', name: '銅鑼鄉' },
        { city: 'pingtungcounty', pinyin: 'changzhi', name: '長治鄉' },
        { city: 'taitungcounty', pinyin: 'changbin', name: '長濱鄉' },
        { city: 'taitungcounty', pinyin: 'guanshan', name: '關山鎮' },
        { city: 'tainancity', pinyin: 'guanmiao', name: '關廟區' },
        { city: 'hsinchucounty', pinyin: 'guanxi', name: '關西鎮' },
        { city: 'kaohsiungcity', pinyin: 'alian', name: '阿蓮區' },
        { city: 'chiayicounty', pinyin: 'alishan', name: '阿里山鄉' },
        { city: 'nantoucounty', pinyin: 'jiji', name: '集集鎮' },
        { city: 'xinbeicity', pinyin: 'shuangxi', name: '雙溪區' },
        { city: 'pingtungcounty', pinyin: 'wutai', name: '霧台鄉' },
        { city: 'taichungcity', pinyin: 'wufeng', name: '霧峰區' },
        { city: 'miaolicounty', pinyin: 'toufen', name: '頭份鎮' },
        { city: 'yilancounty', pinyin: 'toucheng', name: '頭城鎮' },
        { city: 'miaolicounty', pinyin: 'touwu', name: '頭屋鄉' },
        { city: 'hsinchucity', pinyin: 'xiangshan', name: '香山區' },
        { city: 'penghucounty', pinyin: 'magong', name: '馬公市' },
        { city: 'pingtungcounty', pinyin: 'gaoshu', name: '高樹鄉' },
        { city: 'nantoucounty', pinyin: 'yuchi', name: '魚池鄉' },
        { city: 'kaohsiungcity', pinyin: 'niaosong', name: '鳥松區' },
        { city: 'kaohsiungcity', pinyin: 'fengshan', name: '鳳山區' },
        { city: 'hualiencounty', pinyin: 'fenglin', name: '鳳林鎮' },
        { city: 'xinbeicity', pinyin: 'yingge', name: '鶯歌區' },
        { city: 'pingtungcounty', pinyin: 'yanpu', name: '鹽埔鄉' },
        { city: 'kaohsiungcity', pinyin: 'yancheng', name: '鹽埕區' },
        { city: 'tainancity', pinyin: 'yanshui', name: '鹽水區' },
        { city: 'changhuacounty', pinyin: 'lugang', name: '鹿港鎮' },
        { city: 'chiayicounty', pinyin: 'lucao', name: '鹿草鄉' },
        { city: 'nantoucounty', pinyin: 'lugu', name: '鹿谷鄉' },
        { city: 'taitungcounty', pinyin: 'luye', name: '鹿野鄉' },
        { city: 'pingtungcounty', pinyin: 'linluo', name: '麟洛鄉' },
        { city: 'yunlincounty', pinyin: 'mailiao', name: '麥寮鄉' },
        { city: 'tainancity', pinyin: 'madou', name: '麻豆區' },
        { city: 'kaohsiungcity', pinyin: 'gushan', name: '鼓山區' },
        { city: 'taichungcity', pinyin: 'longjing', name: '龍井區' },
        { city: 'tainancity', pinyin: 'longqi', name: '龍崎區' },
        { city: 'taoyuancounty', pinyin: 'longtan', name: '龍潭區' },
        { city: 'taoyuancounty', pinyin: 'guishan', name: '龜山區' }
        ];

    $.fn.twaddress = function () {
        this.each(function () {
            function info() {
                this.city = '';
                this.city_pinyin = '';
                this.dist = '';
                this.dist_pinyin = '';
                this.detail = '';
            }
            var o = new info();
            var old_dist = null;
            var opts = $.extend({}, $.fn.twaddress.defaults);
            var options = { city: "", dist: "", detail: "" };
            var fisttimeflag = true;
            var $inputbox = $(this);
            var $twaddrs = $('<div style="display: inline;" class="form-inline"></div>');
            var $twcity = $('<select id="twcity" name="twcity"></select>');
            var $twmore = $('<input type="text" class="form-control" name="twcitytext" id="twcitytext" placeholder="街道"></input>');
            for (var i = 0; i < city_list.length; i++) {
                if ($inputbox.val().indexOf(city_list[i].name) >= 0) {
                    options.city = city_list[i].name;
                    $inputbox.val($inputbox.val().replace(city_list[i].name, ""));
                }
            }
            var mx_len = 0;
            for (var i = 0; i < dist_list.length; i++) {
                if ($inputbox.val().indexOf(dist_list[i].name) >= 0) {
                    if (mx_len < dist_list[i].name.length) {
                        mx_len = dist_list[i].name.length;
                        options.dist = dist_list[i].name;
                    }
                }
            }
            $inputbox.val($inputbox.val().replace(options.dist, ""));
            options.detail = $inputbox.val();
            $twcity.keyup(function () {
                $twcity.change();
            });
            $twcity.change(function () {
                var $new_twdist = $('<select id="twdist" name="twdist"></select>');
                if (options != undefined && options.detail != undefined && fisttimeflag) {
                    if (options.detail != "hidden") {
                        $twmore.val(options.detail);
                    } else {
                        $twmore.hide();
                    }
                }
                for (var i = 0; i < dist_list.length; i++) {
                    if (dist_list[i].city == $twcity.val()) {
                        if (options != undefined && options.dist != undefined && fisttimeflag && dist_list[i].name == options.dist) {
                            $new_twdist.append('<option value="' + dist_list[i].pinyin + '" selected="selected">' + dist_list[i].name + '</option>');
                            fisttimeflag = false;
                        } else {
                            $new_twdist.append('<option value="' + dist_list[i].pinyin + '">' + dist_list[i].name + '</option>');
                        }
                    }
                }
                $twcity.after($new_twdist);
                $inputbox.val(getValue($twcity, $new_twdist, $twmore, o));
                $inputbox.change();

                $new_twdist.keyup(function () {
                    $new_twdist.change();
                });
                $new_twdist.change(function () {
                    $inputbox.val(getValue($twcity, $new_twdist, $twmore, o));
                    $inputbox.change();
                });
                $twmore.change(function () {
                    $inputbox.val(getValue($twcity, $new_twdist, $twmore, o));
                    $inputbox.change();
                });
                if (old_dist != null) {
                    old_dist.hide().remove();
                }
                old_dist = $new_twdist;
            });

            for (var i = 0; i < city_list.length; i++) {
                if (options != undefined && options.city != undefined && city_list[i].name == options.city) {
                    $twcity.append('<option value="' + city_list[i].pinyin + '" selected="selected">' + city_list[i].name + '</option>');
                } else {
                    $twcity.append('<option value="' + city_list[i].pinyin + '">' + city_list[i].name + '</option>');
                }
            }
            $twaddrs.append($twcity);
            $twaddrs.append($twmore);
            $(this).after($twaddrs);
            $(this).hide();
            $twcity.change();
            return o;
        });
        function getValue(city, dist, more, o) {
            var full_address = '';
            for (var i = 0; i < city_list.length; i++) {
                if (city_list[i].pinyin == city.val()) {
                    full_address = city_list[i].name;
                    o.city = city_list[i].name;
                }
            }
            for (var i = 0; i < dist_list.length; i++) {
                if (dist_list[i].city == city.val() && dist_list[i].pinyin == dist.val()) {
                    full_address += dist_list[i].name;
                    o.dist = dist_list[i].name;
                }
            }
            o.city_pinyin = city.val();
            o.dist_pinyin = dist.val();
            o.detail = more.val();
            return full_address + more.val();
        }
    };

})(jQuery);

$(function () {
    $(".twaddress").twaddress();
});


<!-- 預覽照片的script -->
$(function (){
 
    function preview(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('.preview').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
 
    $("body").on("change", "#mem_img", function (){
        preview(this);
    })
    
})

</script>


</body>
</html>