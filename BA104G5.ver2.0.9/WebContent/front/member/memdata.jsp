<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
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
		if(<%=request.getAttribute("url")!=null%>){swal('資料修改成功','','success')}
	});
</script>

<style type="text/css">
	body {
		font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體,	sans-serif;
	}
	
	/* 	找房狀態的 checkbox 開始 */
	.form-group input[type="checkbox"]+.btn-group>label span {
		width: 20px;
	}
	.form-group input[type="checkbox"]+.btn-group>label span:first-child {
		display: none;
	}
	.form-group input[type="checkbox"]+.btn-group>label span:last-child {
		display: inline-block;
	}
	.form-group input[type="checkbox"]:checked+.btn-group>label span:first-child {
		display: inline-block;
	}
	.form-group input[type="checkbox"]:checked+.btn-group>label span:last-child {
		display: none;
	}
	#fancy-checkbox-info {
		display: none;
	}
	#searchSwitch {
		margin-left: 92px;
		margin-top: 35px;
	}
	#searchicon1 {
		height: 20px;
	}
	#searchicon2 {
		height: 20px;
	}
	/* 	找房狀態的 checkbox 結束 */
	
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
	
	/*會員資料表單的*/
    .memform{
        margin-top: 25px;
    }
    .box-width{
      font-size: 24px;
      width:690px;
    }

    h3.panel-title {
        font-size: 24px;
    }
    .savebutton{
    	margin-top: 20px;
		width: 450px;
		margin-left: 30px;
		font-size: 24px;
    }
    #imgDiv img{
    	max-width: 220px;
    	max-height: 250px;
    }
    
	
</style>


<body>

<!-- 背景圖 -->
<div class="container-fluid backgroundpng">
	<img class="row" src="<%=request.getContextPath()%>/images/fixed_bg.png">
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
	<jsp:include page="/front/member/leftpanel.jsp" />
	<!-- include 左側選單區塊  結束-->

	<!-- 右側頁面區塊  開始 -->
	<div class="col-sm-offset-2 col-sm-5 areaInner">
		<div class="tab-content">
					
					
	<!-- 更換的程式碼放下面 -->
	<c:if test="${not empty dataErrors}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${dataErrors}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title text-center">基本資料</h3>
		</div>
		<form class="form-horizontal" action="memMgmt.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="action" value="memModify">
			<div class="row">
				<div class="col-md-8">
					<div class="form-group memform">
						<label class="col-sm-3 control-label">帳號(e-mail)</label>
						<div class="col-sm-4">
							<p class="form-control-static"><%=memVO.getMem_id()%></p>
						</div>
					</div>
					<div class="form-group memform">
						<label for="mem_name" class="col-sm-3 control-label">姓名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="mem_name" name="mem_name" value="<%=memVO.getMem_name()%>">
						</div>
					</div>
					<div class="form-group memform">
						<label for="mem_addr" class="col-sm-3 control-label">地址</label>
						<div class="col-sm-8">
						<input type="text" class="form-control" id="mem_addr" name="mem_addr" value="<%=memVO.getMem_addr()%>">
						</div>
					</div>
					<div class="[ form-group ] memform" id="searchSwitch">
						<input type="checkbox" name="search_state" id="fancy-checkbox-info" autocomplete="off"
								<%=(memVO != null && memVO.getSearch_state().equals("ON")) ? "checked" : ""%> />
						<div class="[ btn-group btn-lg]">
							<label for="fancy-checkbox-info" class="[ btn btn-info ]">
							<span class="[ glyphicon glyphicon-ok ]" id="searchicon1"></span>
							<span id="searchicon2"> </span>
							</label> 
							<label for="fancy-checkbox-info" class="[ btn btn-default active ]">我願意公開我的找房狀態，讓房仲能主動找到我 </label>
						</div>
					</div>
				</div>
				<div class="col-md-4" style="margin-top:20px;">
					<div id="imgDiv">
						<img class="img-responsive preview" src="<%=request.getContextPath()%>/front/MemImg?mem_no=<%=memVO.getMem_no()%>">
					</div>
					<div style="margin-left:145px;margin-top:10px;">
						<label class="btn btn-info">
							<input id="upload_img" name="mem_img" style="display:none;" type="file" accept="image/*">換照片
						</label>
					</div>
				</div>
				
			</div>
			
			<div class="checkbox memform">
			<button type="submit" class="btn btn-warning btn-lg savebutton">儲存</button>
			</div>
			<div class="checkbox memform"></div>
		</form>
	</div>
	<!-- 更換的程式碼放上面 -->
					
					
		</div>
	</div>
	<!-- 右側頁面區塊結束 -->
	
	
	</div>
</div>
<!-- 下方大區塊 end -->


<!-- 換照片的預覽script -->
<script>
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
 
    $("body").on("change", "#upload_img", function (){
        preview(this);
    })
    
})
</script>

</body>

</html>