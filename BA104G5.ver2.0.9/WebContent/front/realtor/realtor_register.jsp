<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.realtor.model.*"%>

<%
	RealtorVO realtorVOreg = (RealtorVO) request.getAttribute("realtorVOreg");
%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>房仲註冊系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">


<!-- cooperkai.css -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front/realtor/css/realtor_cooper.css">
<!-- cooperkai.css -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>


<!-- realtor_cooperkai.js -->
<script src="<%=request.getContextPath()%>/front/realtor/js/realtor_cooper.js"></script>
<!-- realtor_cooperkai.js -->

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>

</head>
<body>

	<div class="container text-center" id="outter">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<a href="<%=request.getContextPath()%>/front/index.jsp"><img src="<%=request.getContextPath()%>/images/For House logo.png"
					width="120px" alt="回到ForHouse首頁" title="回到ForHouse首頁"></a>
				<h3>房仲註冊系統</h3>
			</div>
		</div>
	</div>

	<div class="container ctext-center" id="outter2">
		<div class="row">
			<div class="panel panel-primary" style="border-color:#f26649;">
				<div class="panel-heading" style="background-color:#f26649; border-color:#f26649;">
					<h2 class="panel-title text-center rtr_label_cooper">註冊 基本資料</h2>
				</div>
				<div class="panel-body">

					<%-- 錯誤表列 --%>
					<c:if test="${not empty regErrors}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${regErrors}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<form class="form-horizontal"
						action="<%=request.getContextPath()%>/front/realtor/realtorchange.do"
						enctype="multipart/form-data" method="post" id="regform">
						<input type="hidden" name="action" value="realtorRegister">

						<div class="form-group">
							<label class="col-sm-2 control-label" for="rtr_photo">個人照片</label>
							<div class="col-sm-3">
								<input type="file" name="rtr_photo" onchange="readURL(this);">
							</div>
							<div class="col-sm-6">
								<img name="rtr_photo" id="imgpreview" alt="照片預覽">
							</div>
						</div>

						<div class="form-group">
							<label for="rtr_name" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="rtr_name"
									name="rtr_name" placeholder="姓名"
									value="<%=(realtorVOreg == null) ? "" : realtorVOreg.getRtr_name()%>">
							</div>
						</div>
						<div class="form-group">
							<label for="rtr_id" class="col-sm-2 control-label">登入帳號</label>
							<div class="col-sm-9">
								<input type="email" class="form-control" id="rtr_id"
									name="rtr_id" placeholder="請輸入e-mail"
									value="<%=(realtorVOreg == null) ? "" : realtorVOreg.getRtr_id()%>">
							</div>
						</div>
						<div class="form-group">
							<label for="rtr_psw" class="col-sm-2 control-label">密碼</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="rtr_psw"
									name="rtr_psw" placeholder="密碼"
									value="<%=(realtorVOreg == null) ? "" : realtorVOreg.getRtr_psw()%>">
							</div>
						</div>
						<div class="form-group">
							<label for="rtr_psw2" class="col-sm-2 control-label">確認密碼</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="rtr_psw2"
									name="rtr_psw2" placeholder="確認密碼"
									value="<%=(realtorVOreg == null) ? "" : realtorVOreg.getRtr_psw()%>">
							</div>
						</div>
						<div class="form-group">
							<label for="re_no" class="col-sm-2 control-label">服務公司</label>
							<div class="col-sm-9">
								<jsp:useBean id="realestateSvc" scope="page"
									class="com.realestate.model.RealEstateService" />
								<select class="selectpicker show-tick form-control" size="1"
									name="re_no">
									<option name="default_item" value="">選擇服務公司</option>
									<c:forEach var="realestateVO" items="${realestateSvc.all}">
										<option value="${realestateVO.re_no}"
											${(realtorVO.re_no==realestateVO.re_no)? '':'${realestateVO.re_name}'}>${realestateVO.re_name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="rtr_area" class="col-sm-2 control-label">服務地區</label>
							<div class="col-sm-9">

								<select class="selectpicker show-tick form-control"
									name="rtr_area">
									<option name="default_item" value="">選擇服務地區</option>
									<option
										value="<%=(realtorVOreg == null) ? "": "realtorVOreg.getRtr_area()"%>"></option>
									<option>北投區</option>
									<option>中正區</option>
									<option>士林區</option>
									<option>內湖區</option>
									<option>大安區</option>
									<option>信義區</option>
									<option>萬華區</option>
									<option>中山區</option>
									<option>大同區</option>
									<option>大安區</option>
									<option>信義區</option>
									<option>中正區</option>
								</select>

								<!-- <input type="text" class="form-control" id="rtr_area" -->
								<!-- name="rtr_area" placeholder="服務地區" -->
								<%-- value="<%=(realtorVOreg == null) ? "" : realtorVOreg.getRtr_area()%>"> --%>
								<!-- </div> -->

							</div>
						</div>
						<div class="form-group">
							<label for="rtr_idno" class="col-sm-2 control-label">身分證</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="rtr_idno"
									name="rtr_idno" placeholder="身分證"
									value="<%=(realtorVOreg == null) ? "" : realtorVOreg.getRtr_idno()%>">
							</div>
						</div>
						<div class="form-group">
							<label for="rtr_intro" class="col-sm-2 control-label">簡介</label>
							<div class="col-sm-9">
								<textarea class="form-control" rows="5" id="rtr_intro"
									name="rtr_intro"><%=(realtorVOreg == null) ? "" : realtorVOreg.getRtr_intro()%></textarea>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10" id="aggrement">
								<div class="checkbox">
									<label> <input type="checkbox" name="aggrement"
										id="aggrement2"
										<%=(realtorVOreg != null && realtorVOreg.getAggrement().equals("on")) ? "checked" : ""%> />
										若要繼續註冊，請先閱讀並同意好房事的<a href="">服務條款</a> & <a href="">隱私權政策</a>
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-sm-2"></div>
							<div class="col-sm-12 col-sm-2"></div>
							<div class="col-sm-4">
								<button type="submit" class="btn btn-primary btn-lg btn_rtr_cooper"
									style="width: 250px;" title="房仲註冊">加入註冊</button>
							</div>
							<div class="col-sm-4">
								<a
									href="<%=request.getContextPath()%>/front/realtor/realtor_login.jsp"
									title="登入房仲"><button type="button"
										class="btn btn-success btn-lg">已有帳號? 登入</button></a>
							</div>
						</div>
						<div class="magicdiv">
							<div class="row">
								<div class="col-sm-10"></div>
								<div class="col-sm-2" style="margin: 0;">
									<button type="button" class="btn btn-default btn-xs"
										id="magicBtn1">快貼</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>