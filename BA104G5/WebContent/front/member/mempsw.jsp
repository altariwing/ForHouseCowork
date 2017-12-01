<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
// 	if (memVO == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
// 		session.setAttribute("location", request.getRequestURI()); //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
// 		response.sendRedirect(request.getContextPath() + "/front/login.jsp"); //*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
// 		return;
// 	}
%>

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

<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.all.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.min.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.min.css"></script> -->

<script>
	$(document).ready(function() {
		if(<%=request.getAttribute("url")!=null%>){swal('密碼修改成功','','success')}
	});
</script>

<style type="text/css">
	body {
		font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體,	sans-serif;
	}
	
	/* 會員中心  開始  */
	.memtitle {
		margin-top: 70px;
	}
    .member_center{
      color: #00ADEE;
    }
    .areaOutter{
        margin-top:40px;
    }
    .areaInner{
        margin-top:20px;
    }
    /* 會員中心  結束  */
	
	/* 更改密碼的form */
    .psw_form{
        margin: 20px;
    }
    h3.panel-title {
        font-size: 24px;
    }
    .savebutton{
      width: 615px;
      margin-left: 13px;
      font-size: 18px;
    }
	
</style>


<body>

<!-- 背景圖 -->
<div class="container-fluid backgroundpng">
	<img class="row" src="<%= request.getContextPath()%>/images/fixed_bg.png">
</div>

<!-- 上方的 header (navbar) -->
<nav class="navbar navbar-fixed-top">
	<jsp:include page="/front/navbar.jsp" />
</nav>
    
    
<div class="col-xs-12 memtitle"><h2 class="text-center member_center">會員中心</h2></div>

<!-- 下方大區塊 start -->
<div class="container-fluid areaOutter">
	<div class="row areaOutter">
	
	<!-- include 左側選單區塊  開始-->
	<jsp:include page="leftpanel.jsp" />
	<!-- include 左側選單區塊  結束-->

	<!-- 右側頁面區塊  開始 -->
	<div class="col-sm-offset-2 col-sm-5 areaInner">
		
				<div class="tab-content">
					
					
					
					<!-- 更換的程式碼放下面 -->
					<c:if test="${not empty pswErrors}">
						<font style="color:red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${pswErrors}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title text-center">更改密碼</h3>
						</div>
						<form class="form-horizontal" action="memMgmt.do" method="post">
							<input type="hidden" name="action" value="change_psw">
							<div class="form-group psw_form">
								<label for="psw_ori" class="col-sm-2 control-label">現在的密碼</label>
								<div class="col-sm-9">
									<input type="password" class="form-control" id="psw_ori" name="psw_ori" placeholder="現在的密碼">
								</div>
							</div>
							<div class="form-group psw_form">
							<label for="psw_new1" class="col-sm-2 control-label">新的密碼</label>
							<div class="col-sm-9">
							<input type="password" class="form-control" id="psw_new1" name="psw_new1" placeholder="新的密碼">
							</div>
							</div>
							<div class="form-group psw_form">
							<label for="psw_new2" class="col-sm-2 control-label">確認密碼</label>
							<div class="col-sm-9">
							<input type="password" class="form-control" id="psw_new2" name="psw_new2" placeholder="確認密碼">
							</div>
							</div>
							<div class="checkbox psw_form">
							<button type="submit" class="btn btn-warning savebutton">確認</button>
							</div>
							<div class="checkbox psw_form"> </div>
						</form>
					</div>
					<!-- 更換的程式碼放上面 -->
					
					
					
				</div>

	</div>
	<!-- 右側頁面區塊結束 -->
	
	
	</div>
</div>
<!-- 下方大區塊 end -->

</body>

</html>