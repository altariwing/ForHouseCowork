<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.slr.model.*"%>
<%
  SlrVO slrVO = (SlrVO) session.getAttribute("slrVO");
  SlrVO slrVOtmp = (SlrVO) request.getAttribute("slrVOtmp");
//   if (slrVO == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
//     session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
//     response.sendRedirect("/front/seller/login.jsp");   //*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
//     return;
//   }
  if (request.getAttribute("dataErrors")!=null) {
  	String phoneErrShow = (String)request.getAttribute("phoneErr");
  }
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>廠商會員中心</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/seller/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<%--     <script src="<%=request.getContextPath()%>/front/seller/js/main.js"></script> --%>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.all.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.min.css"></script>
    
    <script>
		$(document).ready(function() {
			if(<%=request.getAttribute("dataSuccess")!=null%>){swal("修改成功!",'','success')}
		});
	</script>
    
    <style type="text/css">
    body {
		font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
	}
    .panel-title {
        font-size: 18px;
    }anelbody {
        color: #fff;
        background-color: #337ab7;
    }
    .panelboxtitle{
        margin-top:-1.2em;
    }
    .areaInner{
        margin-top: 140px;
    }
    h2.panel-title {
        font-size: 24px;
    }
    
    .basedata{
        margin: 20px;
    }
    #dataSaveBtn{
		width: 620px;
		margin-left: 28px;
		font-size: 18px;
	}
    </style>
</head>

<body>

<nav class="navbar navbar-fixed-top main">
	<jsp:include page="/front/seller/navbar.jsp" />
</nav>



<div class="container-fluid">
    <div class="row">
            
    <!-- include 左側選單區塊  開始-->
	<jsp:include page="/front/seller/leftpanel.jsp" />
	<!-- include 左側選單區塊  結束-->
            


	<!-- 右側內容區塊 開始-->
	<div class="col-xs-12 col-sm-8 areaInner">
	    <div class="container">
	        <div class="col-xs-5 col-sm-8">
	            <div class="tab-content">
	            
	            
	            
	            <!-- 更換的程式碼放下面 -->
	            
		            <!-- 輸入錯誤訊息 start -->
					<c:if test="${not empty dataErrors}">
						<font style="color:red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${dataErrors}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<!-- 輸入錯誤訊息 end -->
					
				<!-- 會員資料 區塊-->
	            <div class="panel panel-primary">
					<div class="panel-heading">
						<h2 class="panel-title text-center">基本資料</h2>
					</div>

					<form class="form-horizontal" action="slr.do" method="post">
						<input type="hidden" name="action" value="modifyData">
						<div class="form-group basedata">
							<label class="col-sm-2 control-label">公司名稱</label>
							<div class="col-sm-9">
							<p class="form-control-static"><%= slrVO.getSlr_name()%></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">公司統編</label>
							<div class="col-sm-9">
							<p class="form-control-static"><%= slrVO.getSlr_taxid()%></p>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">e-mail</label>
							<div class="col-sm-9">
							<p class="form-control-static"><%= slrVO.getSlr_id()%></p>
							</div>
						</div>
						<div class="form-group">
							<label for="contact" class="col-sm-2 control-label">聯絡人</label>
							<div class="col-sm-9">
							 <input type="text" class="form-control" id="contact" name="slr_contact" 
							   value="<%= (slrVOtmp==null)? slrVO.getSlr_contact() : ((slrVOtmp.getSlr_contact().length()>0)? slrVOtmp.getSlr_contact() : slrVO.getSlr_contact())%>">
							</div>
						</div>
						<div class="form-group">
							<label for="seller-phone" class="col-sm-2 control-label">聯絡電話</label>
							<div class="col-sm-9">
							<input type="text" class="form-control" id="seller-phone" name="slr_phone" 
							value="<%= (slrVOtmp==null)? slrVO.getSlr_phone() : ((slrVOtmp.getSlr_phone().length()>0)? slrVOtmp.getSlr_phone() : slrVO.getSlr_phone())%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">簡介</label>
							<div class="col-sm-9">
							<textarea class="form-control text-left" rows="5" name="slr_intro"><%= (slrVOtmp==null)? slrVO.getSlr_intro() : ((slrVOtmp.getSlr_intro().length()>0)? slrVOtmp.getSlr_intro() : slrVO.getSlr_intro())%></textarea></div>
						</div>
						<div class="checkbox basedata">
							<button type="submit" class="btn btn-warning btn-lg" id="dataSaveBtn">儲存</button>
						</div>
						<div class="checkbox"> </div>
					</form>
  
				</div>
				<!-- 更換的程式碼放上面 -->
                              
                              
                
                
                
             </div>
          </div>
      </div>
	</div>
	<!-- 右側區塊 結束-->


    </div>
</div>


<jsp:include page="/front/seller/footer.jsp" />


</body>
</html>