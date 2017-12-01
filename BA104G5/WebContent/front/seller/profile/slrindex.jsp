<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.slr.model.*"%>
<%
  SlrVO slrVO = (SlrVO) session.getAttribute("slrVO");
//   if (slrVO == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
//     session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
//     response.sendRedirect("login.jsp");   //*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
//     return;
//   }
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/seller/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<%--     <script src="<%=request.getContextPath()%>/front/seller/js/main.js"></script> --%>
    
    <style type="text/css">
    body {
		font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
	}
    .panel-title {
        font-size: 24px;
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

    #dashboard {
    	font-size: 1.5em;
    	margin-top: 150px;
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

	            
	            
	            
	            
	        <!-- 更換的程式碼放下面 -->
	            
		    <div class="col-xs-12 col-sm-10 maincontext" id="dashboard">
		    
                <div class="col-xs-12 col-sm-3">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h2 class="panel-title paneltext">
                        <span class="glyphicon glyphicon-tasks"></span><p class="text-right panelboxtitle">2</p></h2>
                            <p>Lorem ipsum dolor sit amet.</p>
                        </div>
                        <div class="panel-body">
                            <p class="text-right">詳情
                                <span class="glyphicon glyphicon-circle-arrow-down"></span></p>
                        </div>
                    </div>
                </div>
                
                <div class="col-xs-12 col-sm-3">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h2 class="panel-title paneltext">
                        <span class="glyphicon glyphicon-send"></span><p class="text-right panelboxtitle">10</p></h2>
                            <p>Lorem ipsum dolor sit amet.</p>
                        </div>
                        <div class="panel-body">
                            <p class="text-right">詳情
                                <span class="glyphicon glyphicon-circle-arrow-down"></span></p>
                        </div>
                    </div>
                </div>
                
                <div class="col-xs-12 col-sm-3">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h2 class="panel-title paneltext">
                        <span class="glyphicon glyphicon-question-sign"></span><p class="text-right panelboxtitle">15</p></h2>
                            <p>Lorem ipsum dolor sit amet.</p>
                        </div>
                        <div class="panel-body">
                            <p class="text-right">詳情
                                <span class="glyphicon glyphicon-circle-arrow-down"></span></p>
                        </div>
                    </div>
                </div>
                
                <div class="col-xs-12 col-sm-3">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h2 class="panel-title paneltext">
                        <span class="glyphicon glyphicon-gift"></span><p class="text-right panelboxtitle">22</p></h2>
                            <p>Lorem ipsum dolor sit amet.</p>
                        </div>
                        <div class="panel-body">
                            <p class="text-right">詳情
                                <span class="glyphicon glyphicon-circle-arrow-down"></span></p>
                        </div>
                    </div>
                </div>
                
                <div class="col-xs-12 col-sm-3">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h2 class="panel-title paneltext">
                        <span class="glyphicon glyphicon-tasks"></span><p class="text-right panelboxtitle">2</p></h2>
                            <p>Lorem ipsum dolor sit amet.</p>
                        </div>
                        <div class="panel-body">
                            <p class="text-right">詳情
                                <span class="glyphicon glyphicon-circle-arrow-down"></span></p>
                        </div>
                    </div>
                </div>
                
                <div class="col-xs-12 col-sm-3">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h2 class="panel-title paneltext">
                        <span class="glyphicon glyphicon-send"></span><p class="text-right panelboxtitle">10</p></h2>
                            <p>Lorem ipsum dolor sit amet.</p>
                        </div>
                        <div class="panel-body">
                            <p class="text-right">詳情
                                <span class="glyphicon glyphicon-circle-arrow-down"></span></p>
                        </div>
                    </div>
                </div>
                
                <div class="col-xs-12 col-sm-3">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h2 class="panel-title paneltext">
                        <span class="glyphicon glyphicon-question-sign"></span><p class="text-right panelboxtitle">15</p></h2>
                            <p>Lorem ipsum dolor sit amet.</p>
                        </div>
                        <div class="panel-body">
                            <p class="text-right">詳情
                                <span class="glyphicon glyphicon-circle-arrow-down"></span></p>
                        </div>
                    </div>
                </div>
                
                <div class="col-xs-12 col-sm-3">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h2 class="panel-title paneltext">
                        <span class="glyphicon glyphicon-gift"></span><p class="text-right panelboxtitle">22</p></h2>
                            <p>Lorem ipsum dolor sit amet.</p>
                        </div>
                        <div class="panel-body">
                            <p class="text-right">詳情
                                <span class="glyphicon glyphicon-circle-arrow-down"></span></p>
                        </div>
                    </div>
                </div>
                
            </div>
				<!-- 更換的程式碼放上面 -->
                              
                              
                
                
                
         
	<!-- 右側區塊 結束-->


    </div>
</div>


<jsp:include page="/front/seller/footer.jsp" />



</body>
</html>