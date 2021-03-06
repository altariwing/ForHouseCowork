<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.realestate.model.*"%>

<jsp:useBean id="realestateSvc" scope="page"
	class="com.realestate.model.RealEstateService" />


<jsp:useBean id="list" scope="request" type="java.util.List<RealtorVO>" />

<%
	List<RealEstateVO> estatelist = (List<RealEstateVO>) request.getAttribute("estatelist");
	pageContext.setAttribute("estatelist", estatelist);
	List<RealtorVO> list2 = (List<RealtorVO>) request.getAttribute("list2");
	pageContext.setAttribute("list2", list2);
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
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>For House</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
    <title>For House</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/main.js"></script>

<!-- 阿蓋的css -->
<!-- 多加的 -->
<link rel='stylesheet prefetch'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front/realtor/css/realtor_cooper.css">
<!-- end阿蓋的css -->


<!-- realtor_cooperkai.js -->
<script src="<%=request.getContextPath()%>/front/realtor/js/realtor_cooper.js"></script>
<script src="https://use.fontawesome.com/add3377d0a.js"></script>
<!-- realtor_cooperkai.js -->


</head>
<body>

	<!-- 背景圖================================================================================= -->
	<div class="container-fluid backgroundpng">
		<img class="row" src="images/fixed_bg.png">
	</div>



	<!-- navnar======================================================================================= -->
	<div class="main">
		<nav class="navbar navbar-fixed-top">
			<div class="container">
				<div class="navbar-header col-xs-12 col-sm-2">
					<a class="navbar-brand " href="#"><img class="navshadow"
						src="images/For House logo.png" width="120px"></a>
				</div>
				<div class="col-xs-12 col-sm-8 ">
					<ul class="nav navbar-nav activebar">
						<li><a href="#" data-toggle="tooltipNews"
							data-placement="bottom">最新消息</a></li>
						<li><a href="#">常見問題</a></li>
						<li><a href="#">看房去</a></li>
						<li><a
							href="<%=request.getContextPath()%>/front/realtor/realtor.do?action=listQueryB">找房仲</a></li>
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



	<!-- 阿蓋的搜尋房仲 ============================================================================================-->



	<!-- 搜尋按鈕===============================================================================================-->
	<!-- 之後可改成用ul-li沒有點點的方法創造清單鈕 -->
	<div class="container btn-block container_size">
		<div class="row">
			<div class="col-xs-12 col-sm-12 btn_padding">
				<div class="row">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front/realtor/realtor.do"
						id="form1">
						<div class="btn-group">
							<b>選擇地區:</b> <select size="1" name="RTR_AREA" id="rtr_area">
								<option value="">搜尋服務地區</option>
								<c:forEach var="realtorVO" items="${list2}">
									<option value="${realtorVO.rtr_area }">${realtorVO.rtr_area}</option>
								</c:forEach>
							</select>
						</div>
						<div class="btn-group">
							<b>選擇服務公司:</b> <select name="RE_NO" id="re_no">
								<option value="">搜尋服務公司</option>
								<c:forEach var="realestateVO" items="${estatelist}">
									<option value="${realestateVO.re_no}">${realestateVO.re_name}</option>
								</c:forEach>
							</select>
						</div>
						<input type="hidden" name="action" value="listQueryB"> <input
							type="submit" value="送出">
					</form>
				</div>
			</div>
		</div>
	</div>
	<br>


	<!-- 搜尋房仲顯示的畫面================================================================================ -->
	<%@ include file="page_front_realtor/pagesearch.file"%>
	<!-- 共有幾位房仲 -->
	<div class="container container_size totatl_estate">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="vertical-horizontal">
					<p>
						共有<font color=red><%=rowNumber%></font>位房仲
					</p>
				</div>
			</div>
		</div>
	</div>

	<c:forEach var="realtorVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<div class="container container_size">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-sm-offset-1 div_sm_12">
					<div class="col-xs-12 col-sm-3">
						<img
							src="<%=request.getContextPath()%>/tool/showimage.do?action=rtr_photo&rtr_no=${realtorVO.rtr_no}"
							alt="${realtorVO.rtr_name}個人照片"
							style="width: 180px; height: 200px;">
					</div>

					<div class="col-xs-12 col-sm-3">
						<ul class="list-unstyled info_estate">
							<li>房仲姓名 ${realtorVO.rtr_name}</li>
							<li>房仲公司
								${realestateSvc.getOne(realtorVO.re_no).getRe_name()}</li>
							<li>服務地區 ${realtorVO.rtr_area}</li>
						</ul>
					</div>

					<div class="col-xs-12 col-sm-3">
						<h2 class="intro_estate">簡介</h2>
						<p class="intro_content text_overflow">${realtorVO.rtr_intro}</p>
					</div>

					<div class="col-sm-3">
						<div class="wrapper">
							<div class="container-fluid">
								<div class="row">
									<div class="heart_size">
										<a class="heart_main" href="#"> <i class="fa fa-heart"
											aria-hidden="true">加入最愛</i>
										</a>
									</div>
								</div>
								<div class="row forward_estate">
									<button class="button" style="vertical-align: middle">
										<span class="forward_word">查看詳請</span>
									</button>
								</div>
								<div class="row forward_estate">
									<button class="button"
										style="vertical-align: middle; background-color: red;">
										<span class="forward_word">聊天or伴遊</span>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br>
	</c:forEach>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<%@include file="page_front_realtor/pageByCompositeQuery2.file"%>
			</div>
		</div>
	</div>
	<!-- end阿蓋的搜尋房仲 =========================================================================================-->





	<!-- footer======================================================================================= -->
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
			</div>
		</div>
		<div class="copyri text-center">
			<p>
				<small>Copyright © 2017 For House</small>
			</p>
		</div>
	</footer>


	<script type="text/javascript">
		// 		$(document).ready(function() {
		// 			$('#rtr_area option').click(function() {
		// 				$('#form1').submit();
		// 			})
		// 			$('#re_no option').click(function() {
		// 				$('#form1').submit();
		// 			})
		// 		})
	</script>


</body>
</html>