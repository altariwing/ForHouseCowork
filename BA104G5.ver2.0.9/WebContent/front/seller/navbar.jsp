<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.slr.model.*"%>
<%
  SlrVO slrVO = (SlrVO) session.getAttribute("slrVO");
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/front/seller/images/Houselogo1.png" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/seller/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <style type="text/css">
    .headpic {
        margin-top: 4em;
        border-radius: 99em;
        width: 7em;
        height: 7em;
        overflow: hidden;
        (讓多出來的不顯示。)
    }

    .headpic img {
        height: 7em;
        width: 7em;
    }

    .headbrand {
        margin-top: 5em;
        padding-bottom: 3em;
    }

    .sidebar {
        margin-top: 80px;
        height: 50em;
        background-color: #337ab7;
    }

    .maincontext {
        margin-top: 100px;
    }

    .paneltitle {
        border-top-left-radius: 0px;
        border-top-right-radius: 0px;
        padding: 15px 15px;
    }

    .panel-title {
        font-size: 18px;
    }

    .panelbody {
        color: #fff;
        background-color: #337ab7;
    }

    .panelbody:hover {
        opacity: 0.8;
    }
    .navbrand {
        color: #F48500;
    }
    
    </style>
</head>

<body>

    <nav class="navbar navbar-fixed-top main">
        <div class="container-fluid">
            <div class="navbar-header col-xs-12 col-sm-4">
                <a class="navbar-brand " href="<%=request.getContextPath()%>/front/seller/profile/slrindex.jsp"><img class="navshadow" src="<%=request.getContextPath()%>/images/For House logo.png" width="120px"></a>
            </div>
            <div class="col-xs-12 col-sm-6 navbrand">
                <h1>　　　廠商會員中心</h1>
            </div>
            <div class="col-xs-12 col-sm-2">
				<ul class="nav navbar-nav logina">
	                <c:if test="${slrVO==null}">
	                    <li><a href='<%=request.getContextPath()%>/front/seller/register.jsp'><span class='glyphicon glyphicon-edit'></span> 註冊</a></li>
	                    <li><a href='<%=request.getContextPath()%>/front/seller/login.jsp'><span class='glyphicon glyphicon-log-in'></span> 登入</a></li>
	                </c:if>
	                <c:if test="${slrVO!=null}">
	                	<li><a href='<%= request.getContextPath()%>/front/seller/profile/slrdata.jsp'><span class='glyphicon glyphicon-user'>.<%= slrVO.getSlr_contact()%></span></a></li>
	                    <li><a href='<%=request.getContextPath()%>/front/seller/logout.do'><span class='glyphicon glyphicon-log-out'></span> 登出</a></li>
	                </c:if>
	            </ul>
            </div>
        </div>
    </nav>

</body>

</html>