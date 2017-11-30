<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.realestate.model.*"%>

<jsp:useBean id="realestateSvc" scope="page" class="com.realestate.model.RealEstateService" />
<%
	RealtorVO realtorVO = (RealtorVO) session.getAttribute("realtorVO");
	RealtorVO realtorVO2 = (RealtorVO) request.getAttribute("realtorVO");
	if (realtorVO == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
		session.setAttribute("location", request.getRequestURI()); //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
		response.sendRedirect("realtor_register.jsp"); //*工作2 : 請該user去房仲註冊網頁
		return;
	}
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<html>

<head>
    <title>For House</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/Houselogo1.png" />
</head>

<body>
    <div class="container-fluid backgroundpng">
        <img class="row" src="<%=request.getContextPath()%>/images/fixed_bg.png">
    </div>
    <!-- navbar開始 -->
    <div class="main">
        <nav class="navbar navbar-fixed-top">
            <jsp:include page="/front/navbar.jsp" />
        </nav>
    </div>
    <!-- navbar結束 -->
    <!-- Custom Theme files -->
    <link href="<%=request.getContextPath()%>/front/realtor/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <!-- cooperkai.css -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front/realtor/css/realtor_cooper.css">
    <!-- cooperkai.css -->
    <!-- Custom Theme files -->
    <!-- realtor_cooperkai.js -->
    <script src="<%=request.getContextPath()%>/front/realtor/js/realtor_cooper.js"></script>
    <!-- realtor_cooperkai.js -->
    <!-- 房仲左邊功能列 =====================================================================================-->
    <div class="container">
        <div class="row">
            <!-- 充版面用 -->
            <div class="col-sm-1"></div>
            <!-- 充版面用 -->
            <!-- 左邊bar =================================================================================-->
            <div class="col-sm-12 col-sm-2 side-bar cooper_side_bar">
                <div class="logo text-center">
                    <a href="<%=request.getContextPath()%>/front/realtor/realtor_center.jsp"><img
						src="<%=request.getContextPath()%>/tool/showimage.do?action=rtr_photo&rtr_no=${realtorVO.rtr_no}"
						class="w3ls-mobile "
						alt="<%=realtorVO.getRtr_name()%>個人照片" data-pin-nopin="true"
						style="max-width: 50%;max-height: 40%;" title="房仲中心"> </a>
                </div>
                <div class="navigation">
                    <h3>個人資料管理</h3>
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/front/realtor/realtor_center.jsp"><i
								class="glyphicon glyphicon-user">&nbsp;基本資料</i></a></li>
                    </ul>
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/front/realtor/realtor_psw.jsp"><i
								class="glyphicon glyphicon-pencil">&nbsp;修改密碼</i></a></li>
                    </ul>
                </div>
                <div class="navigation">
                    <h3>管理粉絲頁文章</h3>
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/front/realtor/realtorArticle.jsp"><i class="glyphicon glyphicon-thumbs-up">&nbsp;新增文章</i></a></li>
                    </ul>
                </div>
                <div class="navigation">
                    <h3>誰在找房</h3>
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/front/realtor/realtor.do?action=memOpen&SEARCH_STATE=ON"><i
								class="glyphicon glyphicon-question-sign">&nbsp;搜尋當前會員</i></a></li>
                    </ul>
                </div>
                <div class="navigation">
                    <h3>管理行事曆</h3>
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/front/realtor/realtor_resrec.jsp"><i class="glyphicon glyphicon-calendar">&nbsp;行事曆</i></a></li>
                    </ul>
                </div>
            </div>
            <!-- 左邊bar結束 ========================================================-->
            <!-- 房仲左邊功能列結束 ================================================================-->
</body>

</html>